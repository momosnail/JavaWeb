1. 需求：用户信息的增删改查操作
   1. 设计：
       1. 技术选型：Servlet+JSP+MySQL+JDBCTempleat+Duird+BeanUtilS+tomcat
       2. 数据库设计：
          ```sql
          create database td; -- 创建数据库
          use td; 			   -- 使用数据库
          create table user(   -- 创建表
          id int primary key auto_increment,
          name varchar(20) not null,
          gender varchar(5),
          age int,
          address varchar(32),
          qq	varchar(20),
          email varchar(50)
          );
          ```
         

2. 开发：
    1. 环境搭建
        1. 创建数据库环境
        2. 创建项目，导入需要的jar包

    2. 编码
3. 测试
4. 部署运维