drop database if exists `seele`;
create database `seele` default character set utf8mb4 collate utf8mb4_general_ci;
use seele;
create user 'seele'@'%' identified by 'seele-password';
# grant select, insert, update, delete on seele.* to seele;
grant all on seele.* to seele;
flush privileges;
