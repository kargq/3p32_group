/**
 * Schema 
 */

/* Table creation queries */

/* Equipment */
DROP TABLE Gem CASCADE;

DROP TABLE Equipment CASCADE;

DROP TABLE Armour CASCADE;

DROP TABLE Main_Weapon CASCADE;

DROP TABLE Secondary_Equipment CASCADE;

DROP TABLE Armour_Instance CASCADE;

DROP TABLE Main_Weapon_Instance CASCADE;

DROP TABLE Secondary_Equipment_Instance CASCADE;

DROP TABLE Skill CASCADE;

DROP TABLE Class CASCADE;

DROP TABLE Character CASCADE;

/* Skill Relationships */

DROP TABLE Earned_Skill CASCADE;

DROP TABLE Auto_Skill CASCADE;

DROP TABLE Has_Earned CASCADE;

DROP TABLE Class_Equipment CASCADE;

/* Clan */

DROP TABLE Clan CASCADE;

DROP TABLE Clan_Member CASCADE;

/* gem embed relations */

DROP TABLE armour_embed CASCADE;

DROP TABLE main_embed CASCADE;

DROP TABLE secondary_embed CASCADE;