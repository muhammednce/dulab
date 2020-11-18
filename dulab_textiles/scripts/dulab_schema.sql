create database dulab;

use dulab;

CREATE TABLE `user_group_t` (
  `group_code` varchar(10) NOT NULL,
  `group_name` varchar(120) NOT NULL,
  `is_deleted` char(1) NOT NULL DEFAULT 'N',
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`group_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `screen_t` (
  `module_no` int NOT NULL,
  `screen_no` int NOT NULL,
  `module_name` varchar(50) DEFAULT NULL,
  `screen_name` varchar(50) DEFAULT NULL,
  `module_icon` varchar(30) NOT NULL,
  `screen_icon` varchar(30) DEFAULT NULL,
  `router_link` varchar(50) DEFAULT NULL,
  `sub_menu` tinyint(1) NOT NULL DEFAULT '0',
  `sub_menu_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`module_no`,`screen_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `user_group_privilege_t` (
  `group_code` varchar(10) NOT NULL,
  `module_no` int NOT NULL,
  `screen_no` int NOT NULL,
  `is_create` tinyint(1) DEFAULT NULL,
  `is_edit` tinyint(1) DEFAULT NULL,
  `is_view` tinyint(1) DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`group_code`,`module_no`,`screen_no`),
  KEY `FK_user_group_privilege_t_screen_t_idx` (`module_no`,`screen_no`),
  CONSTRAINT `FK_user_group_privilege_t_screen_t` FOREIGN KEY (`module_no`, `screen_no`) REFERENCES `screen_t` (`module_no`, `screen_no`),
  CONSTRAINT `FK_user_group_privilege_t_user_group_t` FOREIGN KEY (`group_code`) REFERENCES `user_group_t` (`group_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `user_t` (
  `user_code` varchar(50) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `login_pwd` varchar(80) NOT NULL,
  `mail_id` varchar(50) DEFAULT NULL,
  `is_locked` char(1) NOT NULL DEFAULT 'N',
  `group_code` varchar(10) DEFAULT NULL,
  `is_company_user` char(1) DEFAULT NULL,
  PRIMARY KEY (`user_code`),
  KEY `fk_user_group_code` (`group_code`),
  CONSTRAINT `fk_user_group_code` FOREIGN KEY (`group_code`) REFERENCES `user_group_t` (`group_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `user_logs_t` (
  `user_code` varchar(255) NOT NULL,
  `last_login_time` datetime NOT NULL,
  `last_login_ip` varchar(50) NOT NULL,
  `is_logged_in` tinyint(1) NOT NULL,
  `logout_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `oauth_access_token` (
  `authentication_id` varchar(255) NOT NULL,
  `token_id` varchar(255) NOT NULL,
  `token` blob NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `client_id` varchar(255) NOT NULL,
  `authentication` blob NOT NULL,
  `refresh_token` varchar(255) NOT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) NOT NULL,
  `token` blob NOT NULL,
  `authentication` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


Insert into screen_t(module_no, screen_no, module_name, screen_name, module_icon, screen_icon, router_link, sub_menu, sub_menu_name)
values (10,'0','Home','Home','fa fa-fw fa-home','fa fa-fw fa-home','./home',0,null);

Insert into screen_t(module_no, screen_no, module_name, screen_name, module_icon, screen_icon, router_link, sub_menu, sub_menu_name)
values (20,'1','Inventory','Opening Stock','fa fa-fw fa-home','fa fa-fw fa-home','./home',1,null);

Insert into screen_t(module_no, screen_no, module_name, screen_name, module_icon, screen_icon, router_link, sub_menu, sub_menu_name)
values (20,'2','Inventory','Stock Adjustment','fa fa-fw fa-home','fa fa-fw fa-home','./home',1,null);

insert into user_group_t(group_code, group_name, created_by, created_date, modified_by, modified_date)
values ('Test','Sample Test','Admin',now(),'Admin',now());

insert into user_group_privilege_t (group_code, module_no, screen_no,is_create,is_edit,is_view,is_delete, modified_by, modified_date)
values ('Test','10','0',1,1,1,1,'Admin',now());
insert into user_group_privilege_t (group_code, module_no, screen_no,is_create,is_edit,is_view,is_delete, modified_by, modified_date)
values ('Test','20','1',1,1,1,1,'Admin',now());
insert into user_group_privilege_t (group_code, module_no, screen_no,is_create,is_edit,is_view,is_delete, modified_by, modified_date)
values ('Test','20','2',1,1,1,1,'Admin',now());

insert into user_t (user_code, user_name, login_pwd, mail_id, group_code, is_company_user)
values ('Admin','Elan',md5('Admin@123'),'elan@gmail.com','Test','Y');
