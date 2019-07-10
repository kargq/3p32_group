INSERT INTO Gem
values (23412, 1, 1, 1, 1, 1, 1, 1, 1, 1);

INSERT INTO class
VALUES ('Warrior');
INSERT INTO class
VALUES ('Ranger');
INSERT INTO class
VALUES ('White Mage');
INSERT INTO class
VALUES ('Black Mage');

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