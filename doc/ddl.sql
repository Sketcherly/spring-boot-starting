-- portal.sys_menu definition

CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父级菜单id',
  `order_num` int(11) NOT NULL DEFAULT 0 COMMENT '显示顺序',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '菜单链接',
  `target` varchar(20) NOT NULL DEFAULT '' COMMENT '页面打开方式  menuItem 新标签页  menuBlank 新窗口',
  `menu_type` varchar(20) NOT NULL COMMENT '类型  DIR 目录  MENU 菜单  BTN 按钮',
  `visible` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否显示',
  `icon` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单图标',
  `active` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否有效',
  `deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- portal.sys_role definition

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL DEFAULT '' COMMENT '角色名',
  `role_key` char(64) NOT NULL DEFAULT '' COMMENT '角色权限',
  `role_sort` int(8) NOT NULL DEFAULT 0 COMMENT '角色排序',
  `menu_ids` varchar(256) NOT NULL DEFAULT '' COMMENT '菜单权限列表',
  `active` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否有效',
  `deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_idx_roleKey` (`role_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- portal.sys_user definition

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户表',
  `username` varchar(100) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` char(64) NOT NULL DEFAULT '' COMMENT '密码',
  `roles` varchar(100) NOT NULL DEFAULT '' COMMENT '角色列表',
  `active` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否有效',
  `deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;