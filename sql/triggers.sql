-- following command only needs to be run once per database
CREATE
    LANGUAGE plpgsql;

-- Trigger group #1, for skill assignment
-- min level for skill (equal or lower than character level)

CREATE OR REPLACE FUNCTION skill_min_level_check()
    RETURNS TRIGGER
AS
$$
BEGIN
    -- row will have skill_id and char_name, need to make checks according to that
    -- check if skill is already assigned through auto skills, and if so, do not assign it.
    -- need to check if the skill belongs to the Characters class and is in the earned skills table.
    IF NOT EXISTS(
            SELECT *
            FROM Earned_Skill ES,
                 Character CH,
                 Skill S
            WHERE
              -- find if there is such a skill in the earned skills table, if not, throw an exception.
                CH.char_name = NEW.char_name
              AND ES.skill_id = NEW.skill_id
              AND ES.cls_name = CH.has_class
              AND S.skill_id = ES.skill_id) THEN
        raise
            exception 'This character class is not allowed to access this skill.';
    END IF;
    -- need to check min level.
    IF NOT EXISTS(
            SELECT *
            FROM Earned_Skill ES,
                 Character CH,
                 Skill S
                 -- check if skill_id exists in all skills associated with Character classname
                 -- check if char level is greater than skill level
                 -- finds the earned skill, checks if min level is less than char's level, in that case this query will return not empty.
                 -- i.e. not exists evaluates to false. Otherwise, exception is thrown.
            WHERE CH.char_name = NEW.char_name
              AND ES.skill_id = NEW.skill_id
              AND ES.cls_name = CH.has_class
              AND S.skill_id = ES.skill_id
              AND S.min_level <= CH.char_level) THEN
        raise
            exception 'Invalid skill assignment, check min level for skill';
    END IF;
    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER bef_ins_has_earned
    BEFORE INSERT
    ON has_earned
    FOR EACH ROW
EXECUTE PROCEDURE skill_min_level_check();

-- EO Trigger #1
-- Trigger group #2
-- min level for equipment

CREATE OR REPLACE FUNCTION equipment_instance_assign_check()
    RETURNS TRIGGER
AS
$$
BEGIN
    -- all we need from the row is the Character name and equipment instance id
    -- only need to check if min level of equipment is less than char level
    -- so we're just enforcing the character level against the equipment level here.
    IF EXISTS(
            SELECT *
            FROM Character C,
                 Equipment E
            WHERE C.char_name = NEW.char_name
              AND (E.eqp_id = NEW.armour_equipped
                OR E.eqp_id = NEW.main_equipped
                OR E.eqp_id = NEW.secondary_equipped)
              AND C.char_level < E.elevel) THEN
        raise
            exception 'Character level is too low for given equipment';
    END IF;

    -- check class of main weapon

    IF new.main_equipped notnull and EXISTS(
            SELECT *
            FROM equipment E,
                 main_weapon_instance mwi,
                 class_equipment ce
            WHERE mwi.main_weapon_instance_id = NEW.main_equipped
              and mwi.eqp_id = E.eqp_id
              and ce.eqp_id = e.eqp_id
              and ce.cls_name = NEW.has_class
        )
    THEN
        raise exception 'Equipment instance cannot be equipped for this class';
    end if;

    -- check class of secondary weapon

    IF new.secondary_equipped notnull and EXISTS(
            SELECT *
            FROM equipment E,
                 secondary_equipment_instance mwi,
                 class_equipment ce
            WHERE mwi.secondary_weapon_instance_id = NEW.secondary_equipped
              and mwi.eqp_id = E.eqp_id
              and ce.eqp_id = e.eqp_id
              and ce.cls_name = NEW.has_class
        )
    THEN
        raise exception 'Equipment instance cannot be equipped for this class';
    end if;

    -- check class of armour


    IF new.armour_equipped notnull and not EXISTS(
            SELECT *
            FROM equipment E,
                 armour_instance mwi,
                 class_equipment ce
            WHERE mwi.armour_instance_id = NEW.armour_equipped
              and mwi.eqp_id = E.eqp_id
              and ce.eqp_id = e.eqp_id
              and ce.cls_name = NEW.has_class
        )
    THEN
        raise exception 'Equipment instance cannot be equipped for this class';
    end if;

    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER bef_char_equipment_update
    BEFORE UPDATE
    ON Character
    FOR EACH ROW
EXECUTE PROCEDURE equipment_instance_assign_check();

CREATE TRIGGER bef_char_equipment_insert
    BEFORE INSERT
    ON Character
    FOR EACH ROW
EXECUTE PROCEDURE equipment_instance_assign_check();

-- EO Trigger #2
-- Trigger group #3
-- gem level
-- start armour

CREATE OR REPLACE FUNCTION gem_min_level_check_armour()
    RETURNS TRIGGER
AS
$$
BEGIN
    -- really just need values of gem Id
    -- check will be when a row is added in any of the embed tables.
    IF EXISTS(
            SELECT *
            FROM Gem G,
                 Armour_instance AI,
                 Equipment E
            WHERE NEW.gem_id = G.gem_id
              AND NEW.armour_instance_id = AI.armour_instance_id
              AND E.eqp_id = AI.eqp_id
              AND G.glevel > E.elevel) THEN
        raise
            exception 'Equipment level is too low for equipping the gem';
    END IF;
    IF (SELECT e.gem_limit
        FROM equipment e,
             armour_instance ai
        where e.eqp_id = ai.eqp_id
          and ai.armour_instance_id = NEW.armour_instance_id
        limit 1) <= (SELECT count(*) from armour_embed ae where ae.armour_instance_id = NEW.armour_instance_id)
    then
        raise exception 'Gem limit for armour instance reached';
    end if;
    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER bef_ins_armour_embed
    BEFORE INSERT
    ON armour_embed
    FOR EACH ROW
EXECUTE PROCEDURE gem_min_level_check_armour();

-- end armour

-- start main

CREATE OR REPLACE FUNCTION gem_min_level_check_main()
    RETURNS TRIGGER
AS
$$
BEGIN
    IF EXISTS(
            SELECT *
            FROM Gem G,
                 main_weapon_instance I,
                 Equipment E
            WHERE NEW.gem_id = G.gem_id
              AND NEW.main_weapon_instance_id = I.main_weapon_instance_id
              AND E.eqp_id = I.eqp_id
              AND G.glevel > E.elevel) THEN
        raise
            exception 'Equipment level is too low for equipping the gem';
    END IF;
    IF (SELECT e.gem_limit
        FROM equipment e,
             main_weapon_instance ai
        where e.eqp_id = ai.eqp_id
          and ai.main_weapon_instance_id = NEW.main_weapon_instance_id
        limit 1) <=
       (SELECT count(*) from main_weapon_instance ae where ae.main_weapon_instance_id = NEW.main_weapon_instance_id)
    then
        raise exception 'Gem limit for main weapon instance reached';
    end if;
    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER bef_ins_main_embed
    BEFORE INSERT
    ON main_embed
    FOR EACH ROW
EXECUTE PROCEDURE gem_min_level_check_main();

-- end main
-- start secondary

CREATE OR REPLACE FUNCTION gem_min_level_check_secondary()
    RETURNS TRIGGER
AS
$$
BEGIN
    IF EXISTS(
            SELECT *
            FROM Gem G,
                 secondary_equipment_instance I,
                 Equipment E
            WHERE NEW.gem_id = G.gem_id
              AND NEW.secondary_weapon_instance_id = I.secondary_weapon_instance_id
              AND E.eqp_id = I.eqp_id
              AND G.glevel > E.elevel) THEN
        raise
            exception 'Equipment level is too low for equipping the gem';
    END IF;
    IF (SELECT e.gem_limit
        FROM equipment e,
             secondary_equipment_instance ai
        where e.eqp_id = ai.eqp_id
          and ai.secondary_weapon_instance_id = NEW.secondary_weapon_instance_id
        limit 1) <= (SELECT count(*)
                     from secondary_equipment_instance ae
                     where ae.secondary_weapon_instance_id = NEW.secondary_weapon_instance_id)
    then
        raise exception 'Gem limit for secondary equipment instance reached';
    end if;
    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER bef_ins_secondary_embed
    BEFORE INSERT
    ON secondary_embed
    FOR EACH ROW
EXECUTE PROCEDURE gem_min_level_check_secondary();

-- end secondary
-- EO Trigger #3
-- Trigger Group #4

/* 
change level after each gain of 1000 experience
change stats according to level change

initialization values to 10 for all somehow too? (hadled as default values in Character table)

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

if (NEW.has_class = "warrior")
then

UPDATE Character C
SET 
char_life = (C.char_experience/1000) * (50) + 10
WHERE C.char_name = NEW.char_name


end if;
return NEW;
 */
CREATE OR REPLACE FUNCTION update_levels()
    RETURNS TRIGGER
AS
$$
declare
    _cls_name CHAR(20); _base_life INT; _base_power INT; _base_strength INT; _base_will INT; _base_speed INT;
BEGIN
    NEW.char_level := NEW.char_experience / 1000;

    select * INTO _cls_name, _base_life, _base_power, _base_strength, _base_will, _base_speed
    from class
    where class.cls_name = NEW.has_class
    limit 1;

    IF (lower(NEW.has_class) = 'warrior') THEN
        NEW.char_life := (NEW.char_experience / 1000) * _base_life + 10;
        NEW.char_power := (NEW.char_experience / 1000) * _base_power + 10;
        NEW.char_strength := (NEW.char_experience / 1000) * _base_strength + 10;
        NEW.char_defence := (NEW.char_experience / 1000) * _base_will + 10;
        NEW.char_will := (NEW.char_experience / 1000) * _base_life + 10;
        NEW.char_speed := (NEW.char_experience / 1000) * _base_speed + 10;
    END IF;

    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER on_insert_character
    BEFORE INSERT
    ON character
    FOR EACH ROW
EXECUTE PROCEDURE update_levels();

CREATE TRIGGER on_update_character
    BEFORE UPDATE
    ON character
    FOR EACH ROW
EXECUTE PROCEDURE update_levels();

-- EO Trigger #4

-- Trigger group #5: Earned and auto skills
-- start earned insertion

CREATE OR REPLACE FUNCTION check_earned_skill_in_auto()
    RETURNS TRIGGER
AS
$$
BEGIN
    -- check if earned skill is in auto (with given classname) and if it is, don't add it
    IF EXISTS(
            SELECT *
            FROM Auto_Skill A
            WHERE A.skill_id = NEW.skill_id
              AND A.cls_name = NEW.cls_name) THEN
        raise
            exception 'Skill is already an auto skill and cannot be listed as an earned skill';
    END IF;
    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER on_add_earned_skill
    BEFORE INSERT
    ON Earned_Skill
    FOR EACH ROW
EXECUTE PROCEDURE check_earned_skill_in_auto();

-- end earned insertion
-- start auto insertion

CREATE OR REPLACE FUNCTION check_auto_skill_in_earned()
    RETURNS TRIGGER
AS
$$
BEGIN
    -- check if earned skill is in auto (with given classname) and if it is, don't add it
    IF EXISTS(
            SELECT *
            FROM Earned_Skill A
            WHERE A.skill_id = NEW.skill_id
              AND A.cls_name = NEW.cls_name) THEN
        raise
            exception 'Skill is already an earned skill and cannot be listed as an auto skill';
    END IF;
    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER on_add_auto_skill
    BEFORE INSERT
    ON Auto_Skill
    FOR EACH ROW
EXECUTE PROCEDURE check_auto_skill_in_earned();

-- end auto insertion
-- EO Trigger #5

-- Trigger group #6: Clan

CREATE OR REPLACE FUNCTION on_clan_chief_change()
    RETURNS TRIGGER
AS
$$
BEGIN
    -- insert chief into clan_member as well
    -- need check before inserting otherwise it just throws an error and exits
    IF NOT EXISTS(
            SELECT *
            FROM clan_member C
            WHERE C.char_name = NEW.chief
              AND C.cln_name = new.clanname
        )
    THEN
        INSERT INTO clan_member
        VALUES (NEW.clanname, NEW.chief);
    END IF;
    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER on_change_clan
    AFTER INSERT OR UPDATE
    ON clan
    FOR EACH ROW
EXECUTE PROCEDURE on_clan_chief_change();

CREATE OR REPLACE FUNCTION on_delete_clan_member()
    RETURNS TRIGGER
AS
$$
BEGIN
    -- check if the memeber being deleted is a clan chief
    IF EXISTS(
            SELECT *
            FROM clan C
            WHERE C.chief = OLD.char_name
              AND C.clanname = OLD.cln_name
        )
    THEN
        raise exception 'Clan member is a chief, appoint new chief before removing.';
    END IF;
    RETURN OLD;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER on_delete_clan_member
    BEFORE DELETE
    ON clan_member
    FOR EACH ROW
EXECUTE PROCEDURE on_delete_clan_member();