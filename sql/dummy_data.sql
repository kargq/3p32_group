/* Required data */


/*
(Wouldn't trust this table with my life but should be accurate)
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

/* Class data - required */
INSERT INTO class (cls_name,
                   base_Life,
                   base_Power,
                   base_Strength,
                   base_Defence,
                   base_Will,
                   base_Speed)
VALUES ('Warrior', 50, 2, 5, 5, 1, 3),
       ('Ranger', 30, 2, 4, 3, 3, 5),
       ('White Mage', 15, 5, 1, 2, 5, 2),
       ('Black Mage', 20, 5, 1, 2, 5, 2);


/* Test data */

/*---------- gems and armour -------------- */
INSERT INTO Gem (gname, glife, gpower, gspeed, gdefence, gwill, gstrength, glevel, gblock)
values ('Red', 2, 1, 2, 4, 1, 6, 1, 1),
        ('Green', 9, 4, 2, 5, 1, 3, 6, 1),
        ('Blue', 2, 4, 4, 1, 1, 3, 1, 1),
        ('Yellow', 1, 4, 1, 5, 1, 7, 3, 1),
        ('Test', 2, 2, 2, 2, 2, 2, 2, 2);

insert into equipment (eqp_name, estrength, edefence, gem_limit, elevel)
values ('Leather Jacket', 1, 2, 3, 5),
        ('Iron Chainmail', 2, 3, 4, 6),
        ('swooords', 3, 5, 5, 4),
        ('Diamond Chestplate', 7, 10, 6, 5),
        ('TEST', 2, 2, 2, 2);

insert into armour (eqp_id)
values (0),
        (1),
        (3);

insert into main_weapon (eqp_id)
values  (2),
        (2),
        (1),
        (3);

insert into secondary_equipment (eqp_id)
values (4),
        (1),
        (3);

insert into armour_instance (eqp_id)
values (0),
        (1),
        (3),
        (0),
        (1),
        (3);

insert into main_weapon_instance (eqp_id)
values (2),
        (3),
        (0);

insert into secondary_equipment_instance (eqp_id)
values (4),
        (3),
        (0);

insert into armour_embed (armour_instance_id, gem_id)
values (0,0),
        (1,0),
        (2,0),
        (3,3),
        (4,0),
        (5,3),
        (6,0),
        (7,0),
        (1,1),
        (2,1),
        (3,1),
        (4,2),
        (5,2),
        (6,2),
        (7,2);
/*---------- --------------------------- */

insert into character (char_name, has_class)
values ('Ian', 'Warrior');

insert into character (char_name, has_class)
values ('Kindeep', 'Ranger');

insert into character (char_name, has_class)
values ('Cameron', 'White Mage');

insert into character (char_name, has_class)
values ('Marlon', 'Black Mage');

update character c
set char_experience = 3000
where c.char_name = 'Ian';

insert into clan
values ('RocketRaiders', 'Kindeep');

insert into clan
values ('RocketRaiders', 'Marlon');

insert into clan_member
values ('RocketRaiders', 'Ian');