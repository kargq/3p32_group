#!/usr/bin/env bash
scp -r ././../../3p32_group/sql c3p32g02@sandcastle.cosc.brocku.ca:
ssh c3p32g02@sandcastle.cosc.brocku.ca 'PGPASSWORD=d5e6j4v9 psql --file sql/drop_all.sql;PGPASSWORD=d5e6j4v9 psql --file sql/schema.sql;PGPASSWORD=d5e6j4v9 psql --file sql/triggers.sql;PGPASSWORD=d5e6j4v9 psql --file sql/dummy_data.sql'