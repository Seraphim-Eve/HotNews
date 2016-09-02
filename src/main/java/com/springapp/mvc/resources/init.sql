use test;
create table if not exists Users
(
  email varchar(50) NOT NULL COMMENT '邮箱跟用户名相同',
  username varchar(50) PRIMARY KEY COMMENT '用户名=邮箱',
  nickname varchar(100) NOT NULL COMMENT '昵称',
  password varchar(50) NOT NULL COMMENT '密码MD5值',
  reg_time varchar(50) NOT NULL COMMENT '注册时间',
  reset_code varchar(50) COMMENT '重置密码验证Code.(code为空则失效,在发送更改密码邮件后默认30分钟设置为空)',
  last_login_time varchar(50) COMMENT '最后一次登录时间'
);