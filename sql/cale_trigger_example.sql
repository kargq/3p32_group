-- following command only needs to be run once per database
create language plpgsql;


-- Throws and exception if drivers age is < 16 (determined by DoB and current date)
create or replace function driver_age_check() returns trigger as $$
declare
  driver_age interval;
begin
  driver_age := age(NEW.DoB); -- get the value of DoB which is being inserted
  
  if driver_age < interval '16 year' then
    raise exception 'Driver is too young to get a license';
  end if;
  
  return NEW; -- returns the inserted row if the age is >= 16
end;
$$ LANGUAGE plpgsql;

-- Creates the trigger which calls the function defined above
create trigger bef_ins_driver
before insert on driver
for each row
execute procedure driver_age_check();