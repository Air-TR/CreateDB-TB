drop table if exists `s_employe`;

CREATE TABLE `s_sys_role` (
  `id` char(32) NOT NULL ,
  `name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  
  `status` varchar(30) DEFAULT NULL COMMENT '状态',
  `created_by` char(36) DEFAULT NULL COMMENT '创建人',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` char(36) DEFAULT null comment '修改人',
  `updated_at` datetime DEFAULT NULL COMMENT '修改时间',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '角色';

CREATE TABLE `s_employe` (
  `id` char(32) NOT NULL comment 'id',
  `login_name` varchar(32) NOT NULL COMMENT '登录名称',
  `login_passwd` varchar(32) NOT NULL COMMENT '密码',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `role_id` char(30) DEFAULT NULL COMMENT '角色id',
  `mobile_phone` varchar(16) DEFAULT NULL COMMENT '联系电话',
  
  `status` varchar(30) DEFAULT NULL COMMENT '状态',
  `created_by` char(32) DEFAULT NULL COMMENT '创建人',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` char(32) DEFAULT null comment '修改人',
  `updated_at` datetime DEFAULT NULL COMMENT '修改时间',
  
  foreign key(`role_id`) references `s_sys_role`(`id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 comment '系统的用户';

CREATE TABLE `s_sys_role_menu` (
  `id` char(32) NOT NULL comment 'id',
  `role_id` char(20) DEFAULT NULL COMMENT '角色id',
  `menu_id` char(20) DEFAULT NULL COMMENT '菜单id',
  
  `status` varchar(20) DEFAULT 'enable' COMMENT 'enable,disable',
  `created_by` char(36) DEFAULT NULL COMMENT '创建人',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` char(36) DEFAULT null comment '修改人',
  `updated_at` datetime DEFAULT NULL COMMENT '修改时间',
  
  foreign key(`role_id`) references `s_sys_role`(`id`),
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT '角色和菜单的关系';
