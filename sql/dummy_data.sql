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
       ('Black', 2, 4, 2, 5, 2, 2, 6, 2);

insert into equipment (eqp_name, estrength, edefence, gem_limit, elevel)
values ('Leather Jacket', 1, 2, 3, 5),
       ('Iron Chainmail', 2, 3, 4, 6),
       ('White t-shirt', 3, 5, 5, 7),
       ('Black t-shirt', 3, 5, 5, 4),
       ('Bullet-proof vest', 3, 5, 5, 9),
       ('Long Sword', 3, 5, 5, 4),
       ('Staff', 7, 10, 6, 3),
       ('Lightsaber', 7, 5, 6, 6),
       ('Whip', 7, 10, 6, 5),
       ('Brass Knuckles', 7, 5, 6, 4),
       ('White Gloves', 5, 10, 6, 2),
       ('Dagger', 2, 2, 2, 2);

insert into armour (eqp_id)
values (1),
       (2),
       (3),
       (4),
       (5);

insert into main_weapon (eqp_id)
values (6),
       (7),
       (8),
       (9);

insert into secondary_equipment (eqp_id)
values (10),
       (11),
       (12);

insert into armour_instance (eqp_id)
values (1),
       (2),
       (3),
       (4),
       (5),
       (1),
       (2),
       (3),
       (4),
       (5);

insert into main_weapon_instance (eqp_id)
values (6),
       (7),
       (8),
       (9),
       (6),
       (7),
       (8),
       (9);

insert into secondary_equipment_instance (eqp_id)
values (10),
       (11),
       (12),
       (10),
       (11),
       (12);

insert into armour_embed (armour_instance_id, gem_id)
values (1, 1),
       (2, 1),
       (3, 1),
       (4, 4),
       (5, 1),
       (6, 4),
       (7, 1),
       (8, 1),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 3),
       (6, 3),
       (7, 3),
       (8, 3);
/*---------- --------------------------- */

insert into character (char_name, has_class)
values ('Ian', 'Warrior');

insert into character (char_name, has_class)
values ('Kindeep', 'Ranger');

insert into character (char_name, has_class)
values ('Cameron', 'White Mage');

insert into character (char_name, has_class)
values ('Marlon', 'Black Mage');

insert into character (char_name, has_class)
values ('Cale', 'Warrior');

insert into character (char_name, has_class)
values ('Sammy', 'Ranger');

insert into character (char_name, has_class)
values ('Jhonny', 'White Mage');

insert into character (char_name, has_class)
values ('Katie', 'Black Mage');

insert into character (char_name, has_class)
values ('Nate', 'Warrior');

insert into character (char_name, has_class)
values ('Paul', 'Ranger');

insert into character (char_name, has_class)
values ('Sidd', 'White Mage');

insert into character (char_name, has_class)
values ('God', 'Black Mage');

insert into character (char_name, has_class)
values ('Jesus', 'Warrior');

insert into character (char_name, has_class)
values ('Dillon', 'Ranger');

insert into character (char_name, has_class)
values ('Kyle', 'Black Mage');

insert into character (char_name, has_class)
values ('Sara', 'White Mage');

insert into character (char_name, has_class)
values ('Patrick', 'Warrior');

update character c
set char_experience = 1000
where c.char_name = 'Ian';

update character c
set char_experience = 2000
where c.char_name = 'Cameron';

update character c
set char_experience = 3000
where c.char_name = 'Marlon';

update character c
set char_experience = 4000
where c.char_name = 'Kindeep';

update character c
set char_experience = 5000
where c.char_name = 'Cameron';

update character c
set char_experience = 6000
where c.char_name = 'Marlon';

update character c
set char_experience = 7000
where c.char_name = 'Cale';

update character c
set char_experience = 8000
where c.char_name = 'Sammy';

update character c
set char_experience = 9000
where c.char_name = 'Jhonny';

update character c
set char_experience = 10000
where c.char_name = 'Nate';

update character c
set char_experience = 1000
where c.char_name = 'Paul';

update character c
set char_experience = 2000
where c.char_name = 'Sidd';

update character c
set char_experience = 3000
where c.char_name = 'Katie';

update character c
set char_experience = 4000
where c.char_name = 'God';

update character c
set char_experience = 5000
where c.char_name = 'Jesus';

update character c
set char_experience = 6000
where c.char_name = 'Dillon';

update character c
set char_experience = 7000
where c.char_name = 'Kyle';

update character c
set char_experience = 8000
where c.char_name = 'Sara';

update character c
set char_experience = 9000
where c.char_name = 'Patrick';


insert into clan
values ('RocketRaiders', 'Kindeep');

insert into clan_member
values ('RocketRaiders', 'Marlon');

insert into clan
values ('Soaked', 'Ian');

insert into clan_member
values ('Soaked', 'Marlon');

insert into clan_member
values ('RocketRaiders', 'Ian');
insert into clan
values ('Goolies', 'Katie');

insert into clan_member
values ('Goolies', 'God');

insert into clan
values ('Slayers', 'Sidd');

insert into clan_member
values ('Slayers', 'Patrick');

insert into clan_member
values ('Slayers', 'Sammy');

insert into clan_member
values ('Slayers', 'Cameron');

insert into clan
values ('Beasties', 'Nate');

insert into clan_member
values ('Beasties', 'Jesus');

insert into clan
values ('Gwalla', 'Dillon');

insert into clan_member
values ('Gwalla', 'Jhonny');

insert into clan_member
values ('Gwalla', 'Paul');

insert into skill
values (1, 0, 100, 'Ki blast fired with two hands', 'Kamehameha');

insert into skill
values (2, -2, 8, 'Bending of earths valuable element', 'Air bending ');

insert into skill
values (3, 4, 5, 'double energy sphere of Ki', 'Twin dragon shot');

insert into skill
values (4, 10, 10, 'kick in the air', 'Aerial kick');

insert into earned_skill
values (1, 'White Mage');

insert into earned_skill
values (4, 'Warrior');

insert into auto_skill
values (3, 'Black Mage');

insert into auto_skill
values (2, 'Ranger');

insert into skill
values (5, 1, 1, 'As a Saiyan, Goku possesses immens', 'Superhuman Strength'),
       (6, 2, 2, 'Goku can move/react at least as fast a', 'Superhuman Speed'),
       (7, 3, 3, 'Gokus body is far more durable th', 'Superhuman Durabili'),
       (8, 4, 4, 'Goku is able to think and react at ex', 'Superhuman Senses'),
       (9, 5, 5, 'Thanks to his physiology and willpo', 'Superhuman Enduranc'),
       (10, 6, 6, 'Thanks to his physiology, Goku possesses', 'Powerful Lungs'),
       (11, 7, 7, 'Thanks to his Saiyan biology, Goku poss', 'Heat Resistance');

insert into Gem
values (9034, 'Emerald', 2, 3, 4, 5, 3, 3, 4, 5),
(90, 'Diamond', 2, 3, 4, 5, 3, 3, 4, 55),
(910, 'Jade', 2, 3, 4, 5, 3, 3, 4, 5),
(92, 'Ruby', 2, 3, 3, 5, 3, 3, 4, 5),
(93, 'More Emerald', 54, 3, 4, 5, 3, 3, 4, 5),
(94, 'White Emerald', 23, 3, 4, 5, 3, 3, 4, 5),
(95, 'Not emerald', 2, 3, 4, 5, 3, 3, 4, 5),
(96, 'Is this emar', 2, 3, 43, 5, 3, 3, 4, 5),
(97, 'But Jade', 2, 3, 4, 53, 3, 3, 4, 5),
(98, 'OH m y wor', 2, 3, 4, 5, 3, 3, 4, 5),
(908, 'Black Diamond', 2, 3, 4, 5, 3, 3, 4, 5),
(909, 'Pink Dmnd', 21, 3, 4, 5, 3, 3, 4, 5),
(900, 'Red Read', 2, 3, 24, 5, 3, 3, 4, 5),
(9012, 'Read dore', 2, 3, 4, 5, 3, 3, 4, 5),
(9034, 'Wrase', 2, 3, 4, 5, 3, 3, 43, 5),
(9011, 'Waaan', 2, 32, 4, 5, 32, 3, 24, 5)
;

insert into auto_skill
values
(90, 'Warrior'),
(9034, 'Warrior'),
(910, 'White Mage'),
(92, 'Warrior'),
(94, 'Black Mage'),
(95, 'Warrior'),
(96, 'Ranger'),
(96, 'White Mage'),
(97, 'Warrior'),
(97, 'Black Mage')
;

insert into earned_skill
values
(98, 'Warrior'),
(98, 'White Mage'),
(98, 'Ranger'),
(908, 'White Mage'),
(909, 'Black Mage'),
(9012, 'Warrior'),
(9011, 'Warrior')
;




