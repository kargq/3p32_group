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

CREATE TABLE Head_Armour_Instance ( 
    head_armour_id INTEGER,
    head_armour_instance_id INTEGER PRIMARY KEY,  
    FOREIGN KEY (head_armour_id) REFERENCES Head_Armour 
);

CREATE TABLE Chest_Armour_Instance ( 
    chest_armour_id INTEGER,
    chest_armour_instance_id INTEGER PRIMARY KEY,
    FOREIGN KEY (chest_armour_id) REFERENCES Chest_Armour
);

CREATE TABLE Leg_Armour_Instance ( 
    leg_armour_id INTEGER,
    leg_armour_instance_id INTEGER PRIMARY KEY,  
    FOREIGN KEY (leg_armour_id) REFERENCES Leg_Armour
);

CREATE TABLE Feet_Armour_Instance ( 
    feet_armour_id INTEGER,
    feet_armour_instance_id INTEGER PRIMARY KEY,
    FOREIGN KEY (feet_armour_id) REFERENCES Feet_Armour
);

CREATE TABLE Main_Weapon_Instance ( 
    main_weapon_id INTEGER,
    main_weapon_instance_id INTEGER PRIMARY KEY,
    FOREIGN KEY (main_weapon_id) REFERENCES Main_Weapon
);

CREATE TABLE Secondary_Weapon_Instance ( 
    seconday_weapon_id INTEGER PRIMARY KEY,
    seconday_weapon_instance_id INTEGER PRIMARY KEY,
    FOREIGN KEY (secondary_weapon_id) REFERENCES Seconday_Weapon
);

/* Relationships */
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
    chr_name CHAR(30),
    PRIMARY KEY (skill_id, chr_name),
    FOREIGN KEY (skill_id) REFERENCES Skill,
    FOREIGN KEY (chr_name) REFERENCES Charachter
);

CREATE TABLE Class_Equipment (
    cls_name char(20),
    eqp_id INTEGER,
    PRIMARY KEY (cls_name, eqp_id),
    FOREIGN KEY (cls_name) REFERENCES Class,
    FOREIGN KEY (eqp_id) REFERENCES Equipment
);

CREATE TABLE Embedded_Gems (
    gem_id INTEGER,
    eqp_id INTEGER,
    PRIMARY KEY (gem_id, eqp_id),
    FOREIGN KEY (gem_id) REFERENCES Gem,
    FOREIGN KEY (eqp_id) REFERENCES Equipment
);

CREATE TABLE Has_Class (
    chr_name CHAR(30) NOT NULL,
    cls_name CHAR(20),
    PRIMARY KEY (chr_name, cls_name),
    FOREIGN KEY (chr_name) REFERENCES Charachter,
    FOREIGN KEY (cls_name) REFERENCES Class
);

CREATE TABLE Clan_Member (
    cln_name CHAR(30) NOT NULL,
    chr_name CHAR(30) NOT NULL,
    PRIMARY KEY (cln_name, chr_name),
    FOREIGN KEY (cln_name) REFERENCES Clan,
    FOREIGN KEY (chr_name) REFERENCES Charachter
);

CREATE TABLE Clan_Chief (
    cln_name CHAR(30) NOT NULL,
    chr_name CHAR(30),
    PRIMARY KEY (cln_name, chr_name),
    FOREIGN KEY (cln_name) REFERENCES Clan,
    FOREIGN KEY (chr_name) REFERENCES Charachter
);

CREATE TABLE Head_Equipped (
    eqp_id INTEGER,
    chr_name CHAR(30),
    PRIMARY KEY (eqp_id, chr_name),
    FOREIGN KEY (eqp_id) REFERENCES Head_Armour_Instance,
    FOREIGN KEY (chr_name) REFERENCES Charachter
);

CREATE TABLE Chest_Equipped (
    eqp_id INTEGER,
    chr_name CHAR(30),
    PRIMARY KEY (eqp_id, chr_name),
    FOREIGN KEY (eqp_id) REFERENCES Chest_Armour_Instance,
    FOREIGN KEY (chr_name) REFERENCES Charachter
);

CREATE TABLE Legs_Equipped (
    eqp_id INTEGER,
    chr_name CHAR(30),
    PRIMARY KEY (eqp_id, chr_name),
    FOREIGN KEY (eqp_id) REFERENCES Leg_Armour_Instance,
    FOREIGN KEY (chr_name) REFERENCES Charachter
);

CREATE TABLE Feet_Equipped (
    eqp_id INTEGER,
    chr_name CHAR(30),
    PRIMARY KEY (eqp_id, chr_name),
    FOREIGN KEY (eqp_id) REFERENCES Feet_Armour_Instance,
    FOREIGN KEY (chr_name) REFERENCES Charachter
);

CREATE TABLE Main_Equipped (
    eqp_id INTEGER,
    chr_name CHAR(30),
    PRIMARY KEY (eqp_id, chr_name),
    FOREIGN KEY (eqp_id) REFERENCES Main_Weapon_Instance,
    FOREIGN KEY (chr_name) REFERENCES Charachter
);

CREATE TABLE Secondary_Equipped (
    eqp_id INTEGER,
    chr_name CHAR(30),
    PRIMARY KEY (eqp_id, chr_name),
    FOREIGN KEY (eqp_id) REFERENCES Secondary_Weapon_Instance,
    FOREIGN KEY (chr_name) REFERENCES Charachter
);

/* gem embed relations */
CREATE TABLE head_embed (
  head_armour_instance_id INTEGER,
  gem_id INTEGER,
  PRIMARY KEY (head_armour_instance_id, gem_id),
  FOREIGN KEY (head_armour_instance_id) REFERENCES Head_Armour_Instance,
  FOREIGN KEY (gem_id) REFERENCES Gem
);

CREATE TABLE chest_embed (
  chest_armour_instance_id INTEGER,
  gem_id INTEGER,
  PRIMARY KEY (chest_armour_instance_id, gem_id),
  FOREIGN KEY (chest_armour_instance_id) REFERENCES Chest_Armour_Instance,
  FOREIGN KEY (gem_id) REFERENCES Gem
);

CREATE TABLE leg_embed (
  leg_armour_instance_id INTEGER,
  gem_id INTEGER,
  PRIMARY KEY (leg_armour_instance_id, gem_id),
  FOREIGN KEY (leg_armour_instance_id) REFERENCES Leg_Armour_Instance,
  FOREIGN KEY (gem_id) REFERENCES Gem
);

CREATE TABLE feet_embed (
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
  FOREIGN KEY (secondary_weapon_instance_id) REFERENCES Secondary_Weapon_Instance,
  FOREIGN KEY (gem_id) REFERENCES Gem
);
