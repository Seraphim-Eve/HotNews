use test;
/* 用户注册/登陆表 */
create table if not exists Users(
  email varchar(50) NOT NULL COMMENT '邮箱跟用户名相同',
  username varchar(50) PRIMARY KEY COMMENT '用户名=邮箱',
  nickname varchar(100) NOT NULL COMMENT '昵称',
  password varchar(50) NOT NULL COMMENT '密码MD5值',
  reg_time varchar(50) NOT NULL COMMENT '注册时间',
  reset_code varchar(50) COMMENT '重置密码验证Code.(code为空则失效,在发送更改密码邮件后默认30分钟设置为空)',
  last_login_time varchar(50) COMMENT '最后一次登录时间'
);

/* 博客表 */
create table if not exists blog(
	id varchar(50) primary key comment '博客id(e.g: blog_)',
	title varchar(50) not null comment '博客标题',
	discuss_id varchar(50) comment '博客评论id(关联评论表)',
	good_id VARCHAR(50) comment '点赞id(关联点赞表)',
	create_time varchar(50) not null comment '博客创建时间',
  update_time varchar(50) comment '博客更新时间',
	author varchar(50) not null comment '博客作者',
	authority varchar(20) not null comment '博客权限(私有/公开) default:私有(private)',
	content text comment '博客内容'
);

/* 评论表 */
create table if not exists discuss(
  id varchar(50) not null comment '评论id',
  time varchar(50) not null comment '评论时间',
  d_user varchar(50) not null comment '评论用户',
  content text comment '评论内容'
);

/* 点赞表 */
create table if not exists good(
  id varchar(50) not NULL COMMENT '赞id',
  good_username varchar(50) not null comment '点赞的用户'
)

