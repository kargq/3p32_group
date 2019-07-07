-- following command only needs to be run once per database
create language plpgsql;

/* 
change level after each gain of 1000 experience
change stats according to level change

initialization values to 10 for all somehow too?

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



-- min level for skill (equal or lower than character level)
-- Trigger #1, for skill assignment
create or replace function skill_min_level_check() returns trigger as $$
declare
    n_skill_id n_char_name;
begin
-- row will have skill_id and char_name, need to make checks according to that
n_skill_id = NEW.skill_id;
n_char_name = NEW.char_name;
-- need to check if the skill belongs to the charachters class
if not exists(select * 
from Earned_Skill ES,  Character CH, Skill S
-- check if skill_id exists in all skills associated with charachter classname
-- check if char level is greater than skill level
where CH.char_name = n_char_name and ES.skill_id = n_skill_id and ES.cls_name = CH.has_class and S.skill_id = ES.skill_id and S.min_level < CH.char_level)
then 
raise exception 'Invalid skill assignment, check min level and class for skill';
end if;

return NEW;
end;
$$ LANGUAGE plpgsql;

create trigger bef_ins_has_earned
before insert on has_earned 
for each row
execute procedure skill_min_level_check();

-- EO Trigger #1

-- min level for equipment



-- gem level, max gems