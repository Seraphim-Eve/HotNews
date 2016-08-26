use test;
create table if not exists Users
(
  username varchar(20) PRIMARY KEY,
  password varchar(50) NOT NULL,
  last_update_time Date NOT NULL
)