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
INSERT INTO class
VALUES ('Warrior', 50, 2, 5, 5, 1, 3),
       ('Ranger', 30, 2, 4, 3, 3, 5),
       ('White Mage', 15, 5, 1, 2, 5, 2),
       ('Black Mage', 20, 5, 1, 2, 5, 2);


/* Test data */
INSERT INTO Gem
values (23412, 1, 1, 1, 1, 1, 1, 1, 1, 1);

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