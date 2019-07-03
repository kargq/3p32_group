/**
 * Schema 
 */

/* Table creation queries */

/* Equipment */
CREATE TABLE Gem (
    gem_id INTEGER PRIMARY KEY,
    glife INTEGER,
    gpower INTEGER,
    gname INTEGER,
    gspeed INTEGER,
    gdefence INTEGER,
    gwill INTEGER,
    gstrength INTEGER,
    glevel INTEGER,
    gblock INTEGER
);

CREATE TABLE Equipment (
    eqp_id INTEGER PRIMARY KEY,
    eqp_name CHAR(20),
    ewill INTEGER,
    espeed INTEGER,
    edefence INTEGER,
    estrength INTEGER,
    eblcok INTEGER,
    epower INTEGER,
    elife INTEGER,
    elevel INTEGER,
    gem_limit INTEGER
);

CREATE TABLE Armour (
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

CREATE TABLE Armour_Instance ( 
    eqp_id INTEGER,
    feet_armour_instance_id INTEGER PRIMARY KEY,
    FOREIGN KEY (eqp_id) REFERENCES Feet_Armour
);

CREATE TABLE Main_Weapon_Instance ( 
    eqp_id INTEGER,
    main_weapon_instance_id INTEGER PRIMARY KEY,
    FOREIGN KEY (eqp_id) REFERENCES Main_Weapon
);

CREATE TABLE Secondary_Equipment_Instance (
    eqp_id INTEGER,
    secondary_weapon_instance_id INTEGER PRIMARY KEY,
    FOREIGN KEY (eqp_id) REFERENCES Secondary_Equipment
);

/* Entities that need to be changed */

CREATE TABLE Skill (
    skill_id INTEGER PRIMARY KEY,
    min_level INTEGER,
    power_cost INTEGER,
    sdescription CHAR(60),
    sname CHAR(20)
);

CREATE TABLE Class (
    cls_name CHAR(20) PRIMARY KEY
);

CREATE TABLE Character (
    char_name CHAR(30) PRIMARY KEY,
    char_experience INTEGER,
    char_speed INTEGER,
    char_block INTEGER,
    char_power INTEGER,
    char_level INTEGER,
    char_will INTEGER,
    char_life INTEGER,
    char_defence INTEGER,
    char_strength INTEGER,
    -- class
    has_class CHAR(20) NOT NULL,
    FOREIGN KEY (has_class) REFERENCES Class,
    -- equipment slots
    head_equipped INTEGER,
    UNIQUE (head_equipped),
    FOREIGN KEY (head_equipped) REFERENCES Head_Armour_Instance,
    chest_equipped INTEGER,
    UNIQUE(chest_equipped),
    FOREIGN KEY (chest_equipped) REFERENCES Chest_Armour_Instance,
    legs_equipped INTEGER,
    UNIQUE(legs_equipped),
    FOREIGN KEY (legs_equipped) REFERENCES Legs_Armour_Instance,
    feet_equipped INTEGER,
    UNIQUE(feet_equipped),
    FOREIGN KEY (feet_equipped) REFERENCES Feet_Armour_Instance,
    main_equipped INTEGER,
    UNIQUE(main_equipped),
    FOREIGN KEY (main_equipped) REFERENCES Main_Weapon_Instance,
    secondary_equipped INTEGER,
    UNIQUE (secondary_equipped),
    FOREIGN KEY (secondary_equipped) REFERENCES Secondary_Equipment_Instance
);

/* Skill Relationships */

CREATE TABLE Earned_Skill (
    skill_id INTEGER,
    cls_name CHAR(20),
    PRIMARY KEY (skill_id, cls_name),
    FOREIGN KEY (skill_id) REFERENCES Skill,
    FOREIGN KEY (cls_name) REFERENCES Class
);

CREATE TABLE Auto_Skill (
    skill_id INTEGER,
    cls_name CHAR(20),
    PRIMARY KEY (skill_id, cls_name),
    FOREIGN KEY (skill_id) REFERENCES Skill,
    FOREIGN KEY (cls_name) REFERENCES Class
);

CREATE TABLE Has_Earned (
    skill_id INTEGER,
    char_name CHAR(30),
    PRIMARY KEY (skill_id, char_name),
    FOREIGN KEY (skill_id) REFERENCES Skill,
    FOREIGN KEY (char_name) REFERENCES Character
);

CREATE TABLE Class_Equipment (
    cls_name char(20),
    eqp_id INTEGER,
    PRIMARY KEY (cls_name, eqp_id),
    FOREIGN KEY (cls_name) REFERENCES Class,
    FOREIGN KEY (eqp_id) REFERENCES Equipment
);

/* Clan */

CREATE TABLE Clan (
    clanname CHAR(30) PRIMARY KEY,
    chief CHAR(30) NOT NULL,
    FOREIGN KEY (chief) REFERENCES Character
);

CREATE TABLE Clan_Member (
    cln_name CHAR(30),
    char_name CHAR(30) PRIMARY KEY,
    FOREIGN KEY (cln_name) REFERENCES Clan,
    FOREIGN KEY (char_name) REFERENCES Character
);

/* gem embed relations */

CREATE TABLE armour_embed (
  feet_armour_instance_id INTEGER,
  gem_id INTEGER,
  PRIMARY KEY (feet_armour_instance_id, gem_id),
  FOREIGN KEY (feet_armour_instance_id) REFERENCES Feet_Armour_Instance,
  FOREIGN KEY (gem_id) REFERENCES Gem
);

CREATE TABLE main_embed (
  main_weapon_instance_id INTEGER,
  gem_id INTEGER,
  PRIMARY KEY (main_weapon_instance_id, gem_id),
  FOREIGN KEY (main_weapon_instance_id) REFERENCES Main_Weapon_Instance,
  FOREIGN KEY (gem_id) REFERENCES Gem
);

CREATE TABLE secondary_embed (
  secondary_weapon_instance_id INTEGER,
  gem_id INTEGER,
  PRIMARY KEY (secondary_weapon_instance_id, gem_id),
  FOREIGN KEY (secondary_weapon_instance_id) REFERENCES Secondary_Equipment_Instance,
  FOREIGN KEY (gem_id) REFERENCES Gem
);
