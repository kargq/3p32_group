/**
 * Schema 
 */
/* Table creation queries */
/* Equipment */
CREATE TABLE Gem
(
    gem_id    SERIAL PRIMARY KEY,
    gname     CHAR(20),
    glife     INTEGER CHECK (glife >= 0),
    gpower    INTEGER CHECK (gpower >= 0),
    gspeed    INTEGER CHECK (gspeed >= 0),
    gdefence  INTEGER CHECK (gdefence >= 0),
    gwill     INTEGER CHECK (gwill >= 0),
    gstrength INTEGER CHECK (gstrength >= 0),
    glevel    INTEGER CHECK (glevel >= 0),
    gblock    INTEGER CHECK (gblock >= 0)
);

CREATE TABLE Equipment
(
    eqp_id    SERIAL PRIMARY KEY,
    eqp_name  CHAR(20),
    ewill     INTEGER CHECK (ewill >= 0),
    espeed    INTEGER CHECK (espeed >= 0),
    edefence  INTEGER CHECK (edefence >= 0),
    estrength INTEGER CHECK (estrength >= 0),
    eblcok    INTEGER CHECK (eblcok >= 0),
    epower    INTEGER CHECK (epower >= 0),
    elife     INTEGER CHECK (elife >= 0),
    elevel    INTEGER CHECK (elevel >= 0),
    gem_limit INTEGER CHECK (gem_limit >= 0)
);

CREATE TABLE Armour
(
    eqp_id INTEGER PRIMARY KEY,
    FOREIGN KEY (eqp_id) REFERENCES Equipment ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Main_Weapon
(
    eqp_id INTEGER PRIMARY KEY,
    FOREIGN KEY (eqp_id) REFERENCES Equipment ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Secondary_Equipment
(
    eqp_id INTEGER PRIMARY KEY,
    FOREIGN KEY (eqp_id) REFERENCES Equipment ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Armour_Instance
(
    armour_instance_id SERIAL PRIMARY KEY,
    eqp_id             INTEGER,
    FOREIGN KEY (eqp_id) REFERENCES Armour ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Main_Weapon_Instance
(
    main_weapon_instance_id SERIAL PRIMARY KEY,
    eqp_id                  INTEGER,
    FOREIGN KEY (eqp_id) REFERENCES Main_Weapon ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Secondary_Equipment_Instance
(
    secondary_weapon_instance_id SERIAL PRIMARY KEY,
    eqp_id                       INTEGER,
    FOREIGN KEY (eqp_id) REFERENCES Secondary_Equipment ON DELETE CASCADE ON UPDATE CASCADE
);

/* Entities that need to be changed */
CREATE TABLE Skill
(
    skill_id     SERIAL PRIMARY KEY,
    min_level    INTEGER CHECK (min_level >= 0),
    power_cost   INTEGER CHECK (power_cost >= 0),
    sdescription CHAR(60),
    sname        CHAR(20)
);

CREATE TABLE Class
(
    cls_name      CHAR(20) PRIMARY KEY,
    base_Life     INT CHECK (base_Life >= 0),
    base_Power    INT CHECK (base_Power >= 0),
    base_Strength INT CHECK (base_Strength >= 0),
    base_Defence  INT CHECK (base_Defence >= 0),
    base_Will     INT CHECK (base_Will >= 0),
    base_Speed    INT CHECK (base_Speed >= 0)
);

CREATE TABLE Character
(
    char_name          CHAR(30) PRIMARY KEY,
    char_experience    INTEGER DEFAULT 0,
    char_speed         INTEGER DEFAULT 10,
    char_block         INTEGER DEFAULT 10,
    char_power         INTEGER DEFAULT 10,
    char_level         INTEGER DEFAULT 0,
    char_will          INTEGER DEFAULT 10,
    char_life          INTEGER DEFAULT 10,
    char_defence       INTEGER DEFAULT 10,
    char_strength      INTEGER DEFAULT 10,
    -- class

    has_class          CHAR(20) NOT NULL,
    FOREIGN KEY (has_class) REFERENCES Class ON DELETE CASCADE ON UPDATE CASCADE,
    -- equipment slots

    armour_equipped    INTEGER,
    UNIQUE (armour_equipped),
    FOREIGN KEY (armour_equipped) REFERENCES Armour_Instance,
    main_equipped      INTEGER,
    UNIQUE (main_equipped),
    FOREIGN KEY (main_equipped) REFERENCES Main_Weapon_Instance,
    secondary_equipped INTEGER,
    UNIQUE (secondary_equipped),
    FOREIGN KEY (secondary_equipped) REFERENCES Secondary_Equipment_Instance
);

/* Skill Relationships */
CREATE TABLE Earned_Skill
(
    skill_id INTEGER,
    cls_name CHAR(20),
    PRIMARY KEY (skill_id, cls_name),
    FOREIGN KEY (skill_id) REFERENCES Skill ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (cls_name) REFERENCES Class ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Auto_Skill
(
    skill_id INTEGER,
    cls_name CHAR(20),
    PRIMARY KEY (skill_id, cls_name),
    FOREIGN KEY (skill_id) REFERENCES Skill ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (cls_name) REFERENCES Class ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Has_Earned
(
    skill_id  INTEGER,
    char_name CHAR(30),
    PRIMARY KEY (skill_id, char_name),
    FOREIGN KEY (skill_id) REFERENCES Skill ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (char_name) REFERENCES Character ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Class_Equipment
(
    cls_name char(20),
    eqp_id   INTEGER,
    PRIMARY KEY (cls_name, eqp_id),
    FOREIGN KEY (cls_name) REFERENCES Class ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (eqp_id) REFERENCES Equipment ON DELETE CASCADE ON UPDATE CASCADE
);

/* Clan */
CREATE TABLE Clan
(
    clanname CHAR(30) PRIMARY KEY,
    chief    CHAR(30) NOT NULL UNIQUE,
    FOREIGN KEY (chief) REFERENCES Character
);

CREATE TABLE Clan_Member
(
    cln_name  CHAR(30),
    char_name CHAR(30) PRIMARY KEY,
    FOREIGN KEY (cln_name) REFERENCES Clan ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (char_name) REFERENCES Character ON DELETE CASCADE ON UPDATE CASCADE
);

/* gem embed relations */
CREATE TABLE armour_embed
(
    armour_instance_id INTEGER,
    gem_id             INTEGER,
    PRIMARY KEY (armour_instance_id, gem_id),
    FOREIGN KEY (armour_instance_id) REFERENCES Armour_Instance ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (gem_id) REFERENCES Gem ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE main_embed
(
    main_weapon_instance_id INTEGER,
    gem_id                  INTEGER,
    PRIMARY KEY (main_weapon_instance_id, gem_id),
    FOREIGN KEY (main_weapon_instance_id) REFERENCES Main_Weapon_Instance ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (gem_id) REFERENCES Gem ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE secondary_embed
(
    secondary_weapon_instance_id INTEGER,
    gem_id                       INTEGER,
    PRIMARY KEY (secondary_weapon_instance_id, gem_id),
    FOREIGN KEY (secondary_weapon_instance_id) REFERENCES Secondary_Equipment_Instance ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (gem_id) REFERENCES Gem ON DELETE CASCADE ON UPDATE CASCADE
);

