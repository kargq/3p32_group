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
values ('Kindeep', 'Warrior');

update character c
set char_experience = 3000
where c.char_name = 'Ian';