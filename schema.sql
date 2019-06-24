/**
 * SQL code to create the database.  
 */

/* Entities that need to be changed */
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

/* Relationships */
CREATE TABLE Earned_Skill (
    skill_id INTEGER,
    cls_name CHAR(20),
    PRIMARY KEY (skill_id, cls_name),
    FOREIGN KEY skill_id REFERENCES Skill,
    FOREIGN KEY cls_name REFERENCES Class
);

CREATE TABLE Auto_Skill (
    skill_id INTEGER,
    cls_name CHAR(20),
    PRIMARY KEY (skill_id, cls_name),
    FOREIGN KEY skill_id REFERENCES Skill,
    FOREIGN KEY cls_name REFERENCES Class
);

CREATE TABLE Has_Earned (
    skill_id INTEGER,
    chr_name CHAR(30),
    PRIMARY KEY (skill_id, chr_name),
    FOREIGN KEY skill_id REFERENCES Skill,
    FOREIGN KEY chr_name REFERENCES Charachter
);

CREATE TABLE Class_Equipment (
    cls_name char(20),
    eqp_id INTEGER,
    PRIMARY KEY (cls_name, eqp_id),
    FOREIGN KEY cls_name REFERENCES Class,
    FOREIGN KEY eqp_id REFERENCES Equipment
);

CREATE TABLE Embedded_Gems (
    gem_id INTEGER,
    eqp_id INTEGER,
    PRIMARY KEY (gem_id, eqp_id),
    FOREIGN KEY gem_id REFERENCES Gem,
    FOREIGN KEY eqp_id REFERENCES Equipment
);

CREATE TABLE Has_Class (
    chr_name CHAR(30) NOT NULL,
    cls_name CHAR(20),
    PRIMARY KEY (chr_name, cls_name),
    FOREIGN KEY chr_name REFERENCES Charachter,
    FOREIGN KEY cls_name REFERENCES Class
);

CREATE TABLE Clan_Member (
    cln_name CHAR(30) NOT NULL,
    chr_name CHAR(30) NOT NULL,
    PRIMARY KEY (cln_name, chr_name),
    FOREIGN KEY cln_name REFERENCES Clan,
    FOREIGN KEY chr_name REFERENCES Charachter
);

CREATE TABLE Clan_Chief (
    cln_name CHAR(30) NOT NULL,
    chr_name CHAR(30),
    PRIMARY KEY (cln_name, chr_name),
    FOREIGN KEY cln_name REFERENCES Clan,
    FOREIGN KEY chr_name REFERENCES Charachter
);

CREATE TABLE Head_Equipped (
    eqp_id INTEGER,
    chr_name CHAR(30),
    PRIMARY KEY (eqp_id, chr_name),
    FOREIGN KEY eqp_id REFERENCES Equipment,
    FOREIGN KEY chr_name REFERENCES Charachter
);

CREATE TABLE Chest_Equipped (
    eqp_id INTEGER,
    chr_name CHAR(30),
    PRIMARY KEY (eqp_id, chr_name),
    FOREIGN KEY eqp_id REFERENCES Equipment,
    FOREIGN KEY chr_name REFERENCES Charachter
);

CREATE TABLE Legs_Equipped (
    eqp_id INTEGER,
    chr_name CHAR(30),
    PRIMARY KEY (eqp_id, chr_name),
    FOREIGN KEY eqp_id REFERENCES Equipment,
    FOREIGN KEY chr_name REFERENCES Charachter
);

CREATE TABLE Feet_Equipped (
    eqp_id INTEGER,
    chr_name CHAR(30),
    PRIMARY KEY (eqp_id, chr_name),
    FOREIGN KEY eqp_id REFERENCES Equipment,
    FOREIGN KEY chr_name REFERENCES Charachter
);

CREATE TABLE Main_Equipped (
    eqp_id INTEGER,
    chr_name CHAR(30),
    PRIMARY KEY (eqp_id, chr_name),
    FOREIGN KEY eqp_id REFERENCES Equipment,
    FOREIGN KEY chr_name REFERENCES Charachter
);

CREATE TABLE Secondary_Equipped (
    eqp_id INTEGER,
    chr_name CHAR(30),
    PRIMARY KEY (eqp_id, chr_name),
    FOREIGN KEY eqp_id REFERENCES Equipment,
    FOREIGN KEY chr_name REFERENCES Charachter
);

