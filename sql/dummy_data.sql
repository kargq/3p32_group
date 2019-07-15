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
INSERT INTO Gem
values (0, 'Red', 2, 1, 2, 4, 1, 6, 1, 1),
        (1, 'Green', 9, 4, 2, 5, 1, 3, 6, 1),
        (2, 'Blue', 2, 4, 4, 1, 1, 3, 1, 1),
        (3, 'Yellow', 1, 4, 1, 5, 1, 7, 3, 1);

insert into equipment (eqp_name, eqp_id,
                        estrength,
                        edefence,
                        gem_limit, elevel)
values ('Leather Jacket', 0, 1, 2, 3, 5),
        ('Iron Chainmail', 1, 2, 3, 4, 6),
        ('Gold Chestplate', 2, 3, 5, 5, 4),
        ('Diamond Chestplate', 3, 7, 10, 6, 5);

insert into armour 
values (0),
        (1),
        (2),
        (3);

insert into armour_instance (armour_instance_id, eqp_id)
values (0, 0),
        (1, 1),
        (2, 2),
        (3, 3),
        (4, 0),
        (5, 1),
        (6, 2),
        (7, 3);

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