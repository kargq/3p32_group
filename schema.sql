/**
 * SQL code to create the database.  
 */
CREATE TABLE Skill (
    skill_id INTEGER PRIMARY KEY,
    min_level INTEGER,
    power_cost INTEGER,
    description CHAR(60)
);

CREATE TABLE Class (
    cls_name CHAR(20) PRIMARY KEY
);

CREATE TABLE Charachter (
    chr_name CHAR(30) PRIMARY KEY,
    experience INTEGER,
    speed INTEGER,
    block INTEGER,
    power INTEGER,
    level INTEGER,
    will INTEGER,
    life INTEGER,
    defence INTEGER,
    strength INTEGER,
);

CREATE TABLE Clan (
    cln_name CHAR(30) PRIMARY KEY,
);

CREATE TABLE Gem (
    gem_id INTEGER PRIMARY KEY,
    life INTEGER,
    power INTEGER,
    name INTEGER,
    speed INTEGER,
    defence INTEGER,
    will INTEGER,
    strength INTEGER,
    level INTEGER,
    block INTEGER
);

CREATE TABLE Equipment (
    eqp_id INTEGER PRIMARY KEY,
    eqp_name CHAR(20),
    will INTEGER,
    speed INTEGER,
    defence INTEGER,
    strength INTEGER,
    blcok INTEGER,
    power INTEGER,
    life INTEGER,
    level INTEGER,
    gem_limit INTEGER,
);

CREATE TABLE Head_Armour (
    eqp_id INTEGER PRIMARY KEY,
    FOREIGN KEY (eqp_id) REFERENCES Equipment ON DELETE CASCADE
);

CREATE TABLE Chest_Armour (
    eqp_id INTEGER PRIMARY KEY,
    FOREIGN KEY (eqp_id) REFERENCES Equipment ON DELETE CASCADE
);

CREATE TABLE Legs_Armour (
    eqp_id INTEGER PRIMARY KEY,
    FOREIGN KEY (eqp_id) REFERENCES Equipment ON DELETE CASCADE
);

CREATE TABLE Feet_Armour (
    eqp_id INTEGER PRIMARY KEY,
    FOREIGN KEY (eqp_id) REFERENCES Equipment ON DELETE CASCADE
);

CREATE TABLE Main_Weapon (
    eqp_id INTEGER PRIMARY KEY,
    FOREIGN KEY (eqp_id) REFERENCES Equipment ON DELETE CASCADE
);

CREATE TABLE Secondary_Equipment (
    eqp_id INTEGER PRIMARY KEY,
    FOREIGN KEY (eqp_id) REFERENCES Equipment ON DELETE CASCADE
);

