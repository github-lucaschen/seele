drop database if exists `seele`;
create database `seele` default character set utf8mb4 collate utf8mb4_general_ci;
use seele;
create user 'seele'@'%' identified by 'seele-password';
# grant select, insert, update, delete on seele.* to seele;
grant all on seele.* to seele;
flush privileges;

drop table if exists operation_log;
create table operation_log
(
    id             bigint auto_increment primary key,
    user_code      varchar(70)                                not null,
    client_code    varchar(20)                                not null,
    description    varchar(100)                               not null,
    log_type       tinyint unsigned                           null,
    operation_type tinyint unsigned                           null,
    class_name     varchar(200)                               not null,
    method         varchar(100)                               not null,
    ip             varchar(100)                               not null,
    url            varchar(200)                               not null,
    http_method    varchar(10)                                not null,
    user_agent     varchar(200)                               not null,
    parameter      varchar(500)                               not null,
    request_time   tinyint unsigned                           null,
    result_type    tinyint unsigned                           null,
    response       varchar(1000)                              null,
    error_message  varchar(1000)                              null,
    is_deleted     tinyint unsigned default '0'               null,
    create_time    datetime         default CURRENT_TIMESTAMP null,
    update_time    datetime         default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);


drop table if exists oum_department;
create table oum_department
(
    id              bigint auto_increment primary key,
    parent_id       bigint           default 0 comment '父部门id',
    ancestors       varchar(50)      default '' comment '祖级列表',
    department_name varchar(30)      default '' comment '部门名称',
    order_number    int(4)           default 0 comment '显示顺序',
    leader          varchar(20)      default null comment '负责人',
    phone           varchar(11)      default null comment '联系电话',
    email           varchar(50)      default null comment '邮箱',
    is_enabled      tinyint unsigned default '1' comment '部门状态（1正常 0停用）',
    is_deleted      tinyint unsigned default '0'               null,
    create_time     datetime         default CURRENT_TIMESTAMP null,
    update_time     datetime         default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
) engine = innodb
  auto_increment = 1000 comment = '部门表';

drop table if exists oum_user;
create table oum_user
(
    id            bigint auto_increment primary key,
    department_id bigint           default null comment '部门ID',
    user_code     varchar(30)                                not null comment '用户账号',
    nick_name     varchar(30)                                not null comment '用户昵称',
    type          varchar(2)       default '00' comment '用户类型（00系统用户）',
    email         varchar(50)      default '' comment '用户邮箱',
    phone         varchar(11)      default '' comment '手机号码',
    gender        char(1)          default 'M' comment '用户性别（M 男 F 女 U 未知）',
    avatar        varchar(100)     default '' comment '头像地址',
    password      varchar(100)     default '' comment '密码',
    is_enabled    tinyint unsigned default '1' comment '部门状态（1正常 0停用）',
    login_ip      varchar(128)     default '' comment '最后登录IP',
    login_date    datetime                                   null comment '最后登录时间',
    remark        varchar(500)     default null comment '备注',
    is_deleted    tinyint unsigned default '0'               null,
    create_time   datetime         default CURRENT_TIMESTAMP null,
    update_time   datetime         default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP


) engine = innodb
  auto_increment = 1000 comment = '用户信息表';

drop table if exists oum_post;
create table oum_post
(
    id           bigint auto_increment primary key,
    post_code    varchar(64)                                not null comment '岗位编码',
    post_name    varchar(50)                                not null comment '岗位名称',
    order_number int(4) unsigned                            not null comment '显示顺序',
    is_enabled   tinyint unsigned default '1' comment '状态（1正常 0停用）',
    is_deleted   tinyint unsigned default '0'               null,
    create_time  datetime         default CURRENT_TIMESTAMP null,
    update_time  datetime         default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
) comment = '岗位信息表';

insert into oum_post(post_code, post_name, order_number)
values ('ceo', '董事长', 1);
insert into oum_post(post_code, post_name, order_number)
values ('se', '项目经理', 2);
insert into oum_post(post_code, post_name, order_number)
values ('hr', '人力资源', 3);
insert into oum_post(post_code, post_name, order_number)
values ('user', '普通员工', 4);

drop table if exists oum_role;
create table oum_role
(
    id                     bigint auto_increment primary key,
    role_name              varchar(30)                                not null comment '角色名称',
    role_key               varchar(100)                               not null comment '角色权限字符串',
    order_number           int(4) unsigned                            not null comment '显示顺序',
    data_scope             tinyint unsigned default '1' comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    remark                 varchar(500)     default null comment '备注',
    is_menu_strictly       tinyint unsigned default '1' comment '菜单树选择项是否关联显示',
    is_department_strictly tinyint unsigned default '1' comment '部门树选择项是否关联显示',
    is_enabled             tinyint unsigned default '1' comment '角色状态（1正常 0停用）',
    is_deleted             tinyint unsigned default '0'               null,
    create_time            datetime         default CURRENT_TIMESTAMP null,
    update_time            datetime         default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
) engine = innodb
  auto_increment = 100 comment = '角色信息表';

insert into oum_role(role_name, role_key, order_number, data_scope, remark)
values ('超级管理员', 'admin', 1, 1, '超级管理员');
insert into oum_role(role_name, role_key, order_number, data_scope, remark)
values ('普通角色', 'common', 2, 2, '普通角色');

drop table if exists sys_menu;
create table sys_menu
(
    id           bigint auto_increment primary key,
    menu_name    varchar(50)                                not null comment '菜单名称',
    parent_id    bigint           default '0' comment '父菜单ID',
    order_number int(4) unsigned  default '0' comment '显示顺序',
    path         varchar(200)     default '' comment '路由地址',
    component    varchar(255)     default null comment '组件路径',
    query        varchar(255)     default null comment '路由参数',
    is_frame     tinyint unsigned default '1' comment '是否为外链（0是 1否）',
    is_cache     tinyint unsigned default '1' comment '是否缓存（1缓存 0不缓存）',
    menu_type    char(1)          default '' comment '菜单类型（D目录 M菜单 B按钮）',
    is_visible   tinyint unsigned default '1' comment '菜单状态（1显示 0隐藏）',
    perms        varchar(100)     default null comment '权限标识',
    icon         varchar(100)     default '#' comment '菜单图标',
    remark       varchar(500)     default '' comment '备注',
    is_enabled   tinyint unsigned default '1' comment '（1正常 0停用）',
    is_deleted   tinyint unsigned default '0'               null,
    create_time  datetime         default CURRENT_TIMESTAMP null,
    update_time  datetime         default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
) engine = innodb
  auto_increment = 2000 comment = '菜单权限表';

drop table if exists oum_user_role;
create table oum_user_role
(
    id      bigint auto_increment primary key,
    user_id bigint not null comment '用户ID',
    role_id bigint not null comment '角色ID',
    unique index uk_user_id_role_id (user_id, role_id)
) comment = '用户和角色关联表';

drop table if exists oum_role_menu;
create table oum_role_menu
(
    id      bigint auto_increment primary key,
    role_id bigint(20) not null comment '角色ID',
    menu_id bigint(20) not null comment '菜单ID',
    unique index uk_role_id_menu_id (role_id, menu_id)
) comment = '角色和菜单关联表';

drop table if exists oum_role_dept;
create table oum_role_dept
(
    id            bigint auto_increment primary key,
    role_id       bigint(20) not null comment '角色ID',
    department_id bigint(20) not null comment '部门ID',
    unique index uk_role_id_menu_id (role_id, department_id)
) comment = '角色和部门关联表';

drop table if exists oum_user_post;
create table oum_user_post
(
    id      bigint auto_increment primary key,
    user_id bigint(20) not null comment '用户ID',
    post_id bigint(20) not null comment '岗位ID',
    unique index uk_user_id_post_id (user_id, post_id)
) comment = '用户与岗位关联表';

drop table if exists sys_dict_type;
create table sys_dict_type
(
    id          bigint auto_increment primary key,
    dict_name   varchar(100)     default '' comment '字典名称',
    dict_type   varchar(100)     default '' comment '字典类型',
    remark      varchar(500)     default null comment '备注',
    is_deleted  tinyint unsigned default '0'               null,
    create_time datetime         default CURRENT_TIMESTAMP null,
    update_time datetime         default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    unique index uk_dict_type (dict_type)
) engine = innodb
  auto_increment = 100 comment = '字典类型表';



drop table if exists sys_dict_data;
create table sys_dict_data
(
    id          bigint auto_increment primary key,
    dict_sort   int(4)           default 0 comment '字典排序',
    dict_label  varchar(100)     default '' comment '字典标签',
    dict_value  varchar(100)     default '' comment '字典键值',
    dict_type   varchar(100)     default '' comment '字典类型',
    css_class   varchar(100)     default null comment '样式属性（其他样式扩展）',
    list_class  varchar(100)     default null comment '表格回显样式',
    is_default  tinyint unsigned default '1' comment '是否默认（1是 0否）',
    remark      varchar(500)     default null comment '备注',
    is_deleted  tinyint unsigned default '0'               null,
    create_time datetime         default CURRENT_TIMESTAMP null,
    update_time datetime         default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
) engine = innodb
  auto_increment = 100 comment = '字典数据表';

drop table if exists sys_config;
create table sys_config
(
    id           bigint auto_increment primary key,
    config_name  varchar(100)     default '' comment '参数名称',
    config_key   varchar(100)     default '' comment '参数键名',
    config_value varchar(500)     default '' comment '参数键值',
    config_type  char(1)          default 'N' comment '系统内置（Y是 N否）',
    remark       varchar(500)     default null comment '备注',
    is_deleted   tinyint unsigned default '0'               null,
    create_time  datetime         default CURRENT_TIMESTAMP null,
    update_time  datetime         default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
) engine = innodb
  auto_increment = 100 comment = '参数配置表';