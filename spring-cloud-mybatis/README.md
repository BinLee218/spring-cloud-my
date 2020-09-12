# 测试mybatis并且分析源码所以创建的项目
## mybatis多数据源
配置多个数据源，来测试sql正确性；  
后面会加上源码分析和分库分表的插件；   
### 附上DDL
```
CREATE TABLE `book_info` (
     `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
     `book_name` varchar(128) NOT NULL COMMENT '图书名称',
     `book_desc` varchar(256) NOT NULL COMMENT '图书描述',
     `book_author` varchar(45) NOT NULL COMMENT '图书作者',
     `book_price` decimal(20,4) NOT NULL COMMENT '图书价格',
     `book_num` int(11) NOT NULL COMMENT '图书数量',
     `on_line` int(11) NOT NULL COMMENT '是否上架0否1是',
     `book_type` int(11) NOT NULL,
     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     PRIMARY KEY (`id`)
   ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```
```
CREATE TABLE `book_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) NOT NULL COMMENT '用户名',
  `user_login_name` varchar(64) NOT NULL COMMENT '登录名',
  `user_password` varchar(45) NOT NULL COMMENT '登录密码',
  `user_email` varchar(45) NOT NULL COMMENT '邮箱',
  `salt` varchar(45) NOT NULL COMMENT '盐',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `system_user` int(11) NOT NULL DEFAULT '0' COMMENT '是否是系统用户 0否 1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```
