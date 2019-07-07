-- following command only needs to be run once per database
CREATE
LANGUAGE plpgsql;

-- Throws and exception if drivers age is < 16 (determined by DoB and current date)

CREATE OR REPLACE FUNCTION driver_age_check ()
    RETURNS TRIGGER
    AS $$
DECLARE
    driver_age interval;
BEGIN
    driver_age := age(NEW.DoB);
    -- get the value of DoB which is being inserted
    IF driver_age < interval '16 year' THEN
        raise
        exception 'Driver is too young to get a license';
    END IF;
    RETURN NEW;
    -- returns the inserted row if the age is >= 16
END;
$$
LANGUAGE plpgsql;

-- Creates the trigger which calls the function defined above

CREATE TRIGGER bef_ins_driver
    BEFORE INSERT ON driver FOR EACH ROW
    EXECUTE PROCEDURE driver_age_check ();

