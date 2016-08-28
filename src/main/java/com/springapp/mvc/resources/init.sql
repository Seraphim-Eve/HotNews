use test;
create table if not exists Users
(
  nickname varchar(100) NOT NULL,
  username varchar(20) PRIMARY KEY,
  password varchar(50) NOT NULL,
  last_update_time varchar(30) NOT NULL
)