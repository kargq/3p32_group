# 3p32 Group Project

### Schema

Table creation code is in `schema.sql`

Code to drop every table is in `drop_all.sql`

### Server

`ssh c3p32g02@sandcastle.cosc.brocku.ca` to login. Enter password, check your emails for the password.

Use script `update_sql.sh` to deploy changes from this repo to the psql database. 

Note: The script drops every table and recreates them. (just runs drop_table.sql and then schema.sql as of right now)

Use `login` to open psql terminal (Or just use the `grp_psql.sh` script, login is just an alias, this is just so you don't need to enter the password)


### Todo

Would be helpful to have a script to add dummy data. Add that to update_sql.

### ER Diagram
https://www.draw.io/#G17ShW0CAUOEnBLl88cmvr-g9rNtZNtuZH


