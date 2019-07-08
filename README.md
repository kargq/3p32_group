# 3p32 Group Project

### Getting Started
Be sure to run through the hibernate process, and have a `hibernate.cfg.xml` file that works for your machine. The `connection.url` property will be different.

Also be sure to add the following before any of the `mapping` properties.
```
<property name="connection.username">c3p32g02</property>
<property name="connection.password">d5e6j4v9</property>
```

### Schema

All the sql is located in `sql/`

`schema.sql`: Table creation code

`drop_all.sql`: Code to drop every table

`dummy_data.sql`: sql to add dummy data

`triggers.sql`: Sql to add triggers

`drop_triggers.sql`: Sql to drop all triggers

### Server

`ssh c3p32g02@sandcastle.cosc.brocku.ca` to login. Enter password `d5e6j4v9` (Super secure to put it up here, I know) check your emails for the password.

type login and press enter to open the postgres terminal for our database.

##### Useful Scripts: -

./`update_sql.sh`: deploy changes from this repo to the psql database. 

Note: The script drops every table and recreates them. (just runs drop_table.sql and then schema.sql as of right now)

./`reset-data.sh`: Delete all data from tables. This recreates the tables after dropping them.

`login`: to open psql terminal (Or just use the `grp_psql.sh` script, login is just an alias, this is just so you don't need to enter the password)


### Todo

Would be helpful to have a script to add dummy data. Add that to update_sql.

### ER Diagram
https://www.draw.io/#G17ShW0CAUOEnBLl88cmvr-g9rNtZNtuZH

![ER diagram](media/ER.png)
