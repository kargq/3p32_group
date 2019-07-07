-- following command only needs to be run once per database
CREATE
LANGUAGE plpgsql;

/* 
change level after each gain of 1000 experience
change stats according to level change

initialization values to 10 for all somehow too?

+------------+------+-------+----------+---------+------+-------+
|            | Life | Power | Strength | Defence | Will | Speed |
+------------+------+-------+----------+---------+------+-------+
| Warrior    | 50   | 2     | 5        | 5       | 1    | 3     |
+------------+------+-------+----------+---------+------+-------+
| Ranger     | 30   | 2     | 4        | 3       | 3    | 5     |
+------------+------+-------+----------+---------+------+-------+
| White mage | 15   | 5     | 1        | 2       | 5    | 2     |
+------------+------+-------+----------+---------+------+-------+
| Black mage | 20   | 5     | 1        | 2       | 5    | 2     |
+------------+------+-------+----------+---------+------+-------+
 */
 
-- Trigger group #1, for skill assignment
-- min level for skill (equal or lower than character level)

CREATE OR REPLACE FUNCTION skill_min_level_check ()
    RETURNS TRIGGER
    AS $$
BEGIN
    -- row will have skill_id and char_name, need to make checks according to that
    -- need to check if the skill belongs to the charachters class
    IF NOT EXISTS (
            SELECT
                *
            FROM
                Earned_Skill ES,
                Character CH,
                Skill S
                -- check if skill_id exists in all skills associated with charachter classname
                -- check if char level is greater than skill level
            WHERE
                CH.char_name = NEW.char_name AND ES.skill_id = NEW.skill_id AND ES.cls_name = CH.has_class AND S.skill_id = ES.skill_id AND S.min_level < CH.char_level) THEN
            raise
        exception 'Invalid skill assignment, check min level and class for skill';
    END IF;
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER bef_ins_has_earned
    BEFORE INSERT ON has_earned
    FOR EACH ROW
    EXECUTE PROCEDURE skill_min_level_check ();

-- EO Trigger #1
-- Trigger group #2
-- min level for equipment

CREATE OR REPLACE FUNCTION equipment_instance_assign_check ()
    RETURNS TRIGGER
    AS $$
BEGIN
    -- all we need from the row is the charachter name and equipment instance id
    -- only need to check if min level of equipment is less than char level
    IF EXISTS (
        SELECT
            *
        FROM
            Character C,
            Equipment E
        WHERE
            C.char_name = NEW.char_name
            AND (E.eqp_id = NEW.armour_equipped
                OR E.eqp_id = NEW.main_equipped
                OR E.eqp_id = NEW.secondary_equipped)
            AND C.char_level < E.elevel) THEN
        raise
exception 'Charachter level is too low for given equipment';
END IF;
            RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER bef_char_equipment_update
    BEFORE UPDATE ON Character
    FOR EACH ROW
    EXECUTE PROCEDURE equipment_instance_assign_check ();

CREATE TRIGGER bef_char_equipment_insert
    BEFORE UPDATE ON Character
    FOR EACH ROW
    EXECUTE PROCEDURE equipment_instance_assign_check ();

-- EO Trigger #2
-- Trigger group #3
-- gem level, max gems
-- start armour

CREATE OR REPLACE FUNCTION gem_min_level_check_armour ()
    RETURNS TRIGGER
    AS $$
BEGIN
    -- really just need values of gem Id
    -- check will be when a row is added in any of the embed tables.
    IF EXISTS (
        SELECT
            *
        FROM
            Gem G,
            Armour_instance AI,
            Equipment E
        WHERE
            NEW.gem_id = G.gem_id
            AND NEW.armour_instance_id = AI.armour_instance_id
            AND E.eqp_id = AI.eqp_id
            AND G.glevel > E.elevel) THEN
        raise
exception 'Equipment level is too low for equipping the gem';
END IF;
            RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER bef_ins_armour_embed
    BEFORE INSERT ON armour_embed
    FOR EACH ROW
    EXECUTE PROCEDURE gem_min_level_check_armour ();

-- end armour
-- start main

CREATE OR REPLACE FUNCTION gem_min_level_check_main ()
    RETURNS TRIGGER
    AS $$
BEGIN
    IF EXISTS (
        SELECT
            *
        FROM
            Gem G,
            Main_instance I,
            Equipment E
        WHERE
            NEW.gem_id = G.gem_id
            AND NEW.main_instance_id = I.main_instance_id
            AND E.eqp_id = I.eqp_id
            AND G.glevel > E.elevel) THEN
        raise
exception 'Equipment level is too low for equipping the gem';
END IF;
            RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER bef_ins_main_embed
    BEFORE INSERT ON main_embed
    FOR EACH ROW
    EXECUTE PROCEDURE gem_min_level_check_main ();

-- end main
-- start secondary

CREATE OR REPLACE FUNCTION gem_min_level_check_secondary ()
    RETURNS TRIGGER
    AS $$
BEGIN
    IF EXISTS (
        SELECT
            *
        FROM
            Gem G,
            Secondary_instance I,
            Equipment E
        WHERE
            NEW.gem_id = G.gem_id
            AND NEW.secondary_instance_id = I.secondary_instance_id
            AND E.eqp_id = I.eqp_id
            AND G.glevel > E.elevel) THEN
        raise
exception 'Equipment level is too low for equipping the gem';
END IF;
            RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER bef_ins_secondary_embed
    BEFORE INSERT ON secondary_embed
    FOR EACH ROW
    EXECUTE PROCEDURE gem_min_level_check_secondary ();

-- end secondary
-- EO Trigger #3
