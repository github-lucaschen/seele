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

insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1000, 0, '0', '空之彼端', 0, 'ceo', '18888888888', 'ceo@skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1001, 1000, '0,1000', '总经理办公室', 1, 'dp', '18888888888', 'dp@skybeyondtech
.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1002, 1000, '0,1000', '综合部', 2, 'gad', '18888888888', 'gad@skybeyondtech
.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1003, 1000, '0,1000', '财务部', 3, 'ga', '18888888888', 'ga@skybeyondtech
.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1004, 1000, '0,1000', '人力资源部', 4, 'hr', '18888888888', 'hr@skybeyondtech
.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1005, 1000, '0,1000', '研发部', 5, 'rd', '18888888888', 'rd@skybeyondtech
.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1006, 1000, '0,1000', '销售部', 6, 'sd', '18888888888', 'sd@skybeyondtech
.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1007, 1000, '0,1000', '采购', 7, 'pd', '18888888888', 'pd@skybeyondtech
.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1008, 1000, '0,1000', '人力资源部', 8, 'hr', '18888888888', 'hr@skybeyondtech
.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1100, 1000, '0,1000', '北京分公司', 100, 'bj', '18888888888',
        'leader@bj.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1101, 1000, '0,1000', '天津分公司', 101, 'tj', '18888888888',
        'leader@tj.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1102, 1000, '0,1000', '山西分公司', 102, 'sx', '18888888888',
        'leader@sx.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1103, 1000, '0,1000', '河北分公司', 103, 'he', '18888888888',
        'leader@he.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1104, 1000, '0,1000', '内蒙古分公司', 104, 'nm', '18888888888',
        'leader@nm.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1105, 1000, '0,1000', '辽宁分公司', 105, 'ln', '18888888888',
        'leader@ln.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1106, 1000, '0,1000', '吉林分公司', 106, 'jl', '18888888888',
        'leader@jl.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1107, 1000, '0,1000', '上海分公司', 107, 'sh', '18888888888',
        'leader@sh.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1108, 1000, '0,1000', '黑龙江分公司', 108, 'hl', '18888888888',
        'leader@hl.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1109, 1000, '0,1000', '江苏分公司', 109, 'js', '18888888888',
        'leader@js.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1110, 1000, '0,1000', '浙江分公司', 110, 'zj', '18888888888',
        'leader@zj.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1111, 1000, '0,1000', '安徽分公司', 111, 'ah', '18888888888',
        'leader@ah.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1112, 1000, '0,1000', '福建分公司', 112, 'fj', '18888888888',
        'leader@fj.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1113, 1000, '0,1000', '江西分公司', 113, 'jx', '18888888888',
        'leader@jx.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1114, 1000, '0,1000', '山东分公司', 114, 'sd', '18888888888',
        'leader@sd.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1115, 1000, '0,1000', '河南分公司', 115, 'ha', '18888888888',
        'leader@ha.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1116, 1000, '0,1000', '湖北分公司', 116, 'hb', '18888888888',
        'leader@hb.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1117, 1000, '0,1000', '湖南分公司', 117, 'hn', '18888888888',
        'leader@hn.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1118, 1000, '0,1000', '广西分公司', 118, 'gx', '18888888888',
        'leader@gx.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1119, 1000, '0,1000', '广东分公司', 119, 'gd', '18888888888',
        'leader@gd.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1120, 1000, '0,1000', '海南分公司', 120, 'hi', '18888888888',
        'leader@hi.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1121, 1000, '0,1000', '重庆分公司', 121, 'cq', '18888888888',
        'leader@cq.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1122, 1000, '0,1000', '四川分公司', 122, 'sc', '18888888888',
        'leader@sc.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1123, 1000, '0,1000', '贵州分公司', 123, 'gz', '18888888888',
        'leader@gz.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1124, 1000, '0,1000', '云南分公司', 124, 'yn', '18888888888',
        'leader@yn.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1125, 1000, '0,1000', '西藏分公司', 125, 'xz', '18888888888',
        'leader@xz.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1126, 1000, '0,1000', '陕西分公司', 126, 'sn', '18888888888',
        'leader@sn.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1127, 1000, '0,1000', '甘肃分公司', 127, 'gs', '18888888888',
        'leader@gs.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1128, 1000, '0,1000', '青海分公司', 128, 'qh', '18888888888',
        'leader@qh.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1129, 1000, '0,1000', '宁夏分公司', 129, 'nx', '18888888888',
        'leader@nx.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (1130, 1000, '0,1000', '新疆分公司', 130, 'xj', '18888888888',
        'leader@xj.skybeyondtech.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (2001, 1100, '0,1000,1100', '综合部', 1, 'bj-gad', '18888888888', 'gad@bj.skybeyondtech
.com');
insert into oum_department(id, parent_id, ancestors, department_name, order_number, leader, phone,
                           email)
values (2002, 1100, '0,1000,1100', '销售部', 2, 'bj-sd', '18888888888', 'sd@bj.skybeyondtech
.com');

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

insert into oum_user(id, department_id, user_code, nick_name, type, email, phone, gender, password)
values (1, 1002, 'appsadmin', '系统管理员', '00', 'appsadmin@skybeyondtech.com', '18888888888', 'M',
        'password');
insert into oum_user(id, department_id, user_code, nick_name, type, email, phone, gender, password)
values (2, 1005, 'seele', '希尔', '00', 'seele@skybeyondtech.com', '18888888888', 'F',
        'password');

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

insert into oum_post(id, post_code, post_name, order_number)
values (1, 'ceo', '董事长', 1);
insert into oum_post(id, post_code, post_name, order_number)
values (2, 'se', '项目经理', 2);
insert into oum_post(id, post_code, post_name, order_number)
values (3, 'hr', '人力资源', 3);
insert into oum_post(id, post_code, post_name, order_number)
values (4, 'user', '普通员工', 4);

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

insert into oum_role(id, role_name, role_key, order_number, data_scope, remark)
values (1, '超级管理员', 'admin', 1, 1, '超级管理员');
insert into oum_role(id, role_name, role_key, order_number, data_scope, remark)
values (2, '普通角色', 'common', 2, 2, '普通角色');

drop table if exists oum_menu;
create table oum_menu
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

-- 一级菜单
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (1, '人员组织管理', 0, 1, 'oum', '', '', 1, 0, 'D', 1, '', 'oum', '人员组织管理目录');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (2, '系统管理', 0, 2, 'system', '', '', 1, 0, 'D', 1, '', 'system', '系统管理目录');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (3, '系统监控', 0, 3, 'monitor', '', '', 1, 0, 'D', 1, '', 'monitor', '系统监控目录');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (4, '系统工具', 0, 4, 'tool', '', '', 1, 0, 'D', 1, '', 'tool', '系统工具目录');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (5, '希尔官网', 0, 5, 'https://seele.skybeyondtech.com', '', '', 0, 0, 'D', 1, '', 'guide',
        '希尔官网地址');
-- 二级菜单
-- 人员组织管理
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (101, '用户管理', 1, 1, 'user', 'oum/user/index', '', 1, 0, 'M', 1, 'oum:user:list',
        'user', '用户管理菜单');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (102, '角色管理', 1, 2, 'role', 'oum/role/index', '', 1, 0, 'M', 1, 'oum:role:list',
        'peoples', '角色管理菜单');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (103, '菜单管理', 1, 3, 'menu', 'oum/menu/index', '', 1, 0, 'M', 1, 'oum:menu:list',
        'tree-table', '菜单管理菜单');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (104, '部门管理', 1, 4, 'dept', 'oum/dept/index', '', 1, 0, 'M', 1, 'oum:dept:list',
        'tree', '部门管理菜单');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (105, '岗位管理', 1, 5, 'post', 'oum/post/index', '', 1, 0, 'M', 1, 'oum:post:list',
        'post', '岗位管理菜单');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
-- 系统管理
values (201, '字典管理', 2, 1, 'dict', 'system/dict/index', '', 1, 0, 'M', 1, 'system:dict:list',
        'dict', '字典管理菜单');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (202, '参数设置', 2, 2, 'config', 'system/config/index', '', 1, 0, 'M', 1,
        'system:config:list',
        'edit', '参数设置菜单');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (203, '通知公告', 2, 3, 'notice', 'system/notice/index', '', 1, 0, 'M', 1,
        'system:notice:list',
        'message', '通知公告菜单');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (204, '日志管理', 2, 4, 'log', '', '', 1, 0, 'D', 1, '',
        'log', '日志管理菜单');
-- 系统监控
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (301, '在线用户', 3, 1, 'online', 'monitor/online/index', '', 1, 0, 'M', 1,
        'monitor:online:list',
        'online', '在线用户菜单');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (302, '定时任务', 3, 2, 'job', 'monitor/job/index', '', 1, 0, 'M', 1, 'monitor:job:list',
        'job', '定时任务菜单');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (303, 'Sentinel 控制台', 3, 3, 'http://seele.service.skybeyondtech.com:8718',
        '', '', 0, 0, 'M', 1,
        'monitor:sentinel:list',
        'sentinel', '流量控制菜单');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (304, 'Nacos 控制台', 3, 4, 'http://seele.service.skybeyondtech.com:8848/nacos',
        '', '', 0, 0, 'M', 1,
        'monitor:nacos:list',
        'nacos', '服务治理菜单');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (305, 'Admin 控制台', 3, 5, 'http://seele.service.skybeyondtech.com:9100/login', '', '', 0,
        0,
        'M', 1,
        'monitor:server:list',
        'server', '服务监控菜单');
-- 系统工具
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (401, '表单构建', 4, 1, 'build', 'tool/build/index', '', 1, 0, 'M', 1, 'tool:build:list',
        'build', '表单构建菜单');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (402, '代码生成', 4, 1, 'gen', 'tool/gen/index', '', 1, 0, 'M', 1, 'tool:gen:list',
        'code', '代码生成菜单');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (403, '系统接口', 4, 1, 'http://swagger-html', '', '', 0, 0, 'M', 1,
        'system:user:list',
        'swagger', '系统接口菜单');
-- 三级菜单
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (20401, '授权日志', 204, 1, 'authenticate', 'system/authenticate/index', '', 1, 0, 'M', 1,
        'system:authenticate:index',
        'log', '日志管理菜单');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (20402, '操作日志', 204, 2, 'operation', 'system/operation/index', '', 1, 0, 'M', 1,
        'system:operation:index',
        'log', '日志管理菜单');
-- 用户管理按钮
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (101001, '用户查询', 101, 1, '', '', '', 1, 0, 'B', 1, 'oum:user:query', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (101002, '用户新增', 101, 2, '', '', '', 1, 0, 'B', 1, 'oum:user:add', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (101003, '用户修改', 101, 3, '', '', '', 1, 0, 'B', 1, 'oum:user:edit', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (101004, '用户删除', 101, 4, '', '', '', 1, 0, 'B', 1, 'oum:user:remove', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (101005, '用户导出', 101, 5, '', '', '', 1, 0, 'B', 1, 'oum:user:export', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (101006, '用户导入', 101, 6, '', '', '', 1, 0, 'B', 1, 'oum:user:import', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (101007, '重置密码', 101, 7, '', '', '', 1, 0, 'B', 1, 'oum:user:resetPwd', '#', '');
-- 角色管理按钮
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (102001, '角色查询', 102, 1, '', '', '', 1, 0, 'B', 1, 'oum:role:query', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (102002, '角色新增', 102, 2, '', '', '', 1, 0, 'B', 1, 'oum:role:add', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (102003, '角色修改', 102, 3, '', '', '', 1, 0, 'B', 1, 'oum:role:edit', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (102004, '角色删除', 102, 4, '', '', '', 1, 0, 'B', 1, 'oum:role:remove', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (102005, '角色导出', 102, 5, '', '', '', 1, 0, 'B', 1, 'oum:role:export', '#', '');
-- 菜单管理按钮
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (103001, '菜单查询', 103, 1, '', '', '', 1, 0, 'B', 1, 'oum:menu:query', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (103002, '菜单新增', 103, 2, '', '', '', 1, 0, 'B', 1, 'oum:menu:add', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (103003, '菜单修改', 103, 3, '', '', '', 1, 0, 'B', 1, 'oum:menu:edit', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (103004, '菜单删除', 103, 4, '', '', '', 1, 0, 'B', 1, 'oum:menu:remove', '#', '');
-- 部门管理按钮
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (104001, '部门查询', 104, 1, '', '', '', 1, 0, 'B', 1, 'oum:dept:query', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (104002, '部门新增', 104, 2, '', '', '', 1, 0, 'B', 1, 'oum:dept:add', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (104003, '部门修改', 104, 3, '', '', '', 1, 0, 'B', 1, 'oum:dept:edit', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (104004, '部门删除', 104, 4, '', '', '', 1, 0, 'B', 1, 'oum:dept:remove', '#', '');
-- 岗位管理按钮
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (105001, '岗位查询', 105, 1, '', '', '', 1, 0, 'B', 1, 'oum:post:query', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (105002, '岗位新增', 105, 2, '', '', '', 1, 0, 'B', 1, 'oum:post:add', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (105003, '岗位修改', 105, 3, '', '', '', 1, 0, 'B', 1, 'oum:post:edit', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (105004, '岗位删除', 105, 4, '', '', '', 1, 0, 'B', 1, 'oum:post:remove', '#', '');

-- 字典管理按钮
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (201001, '字典查询', 201, 1, '', '', '', 1, 0, 'B', 1, 'system:dict:query', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (201002, '字典新增', 201, 2, '', '', '', 1, 0, 'B', 1, 'system:dict:add', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (201003, '字典修改', 201, 3, '', '', '', 1, 0, 'B', 1, 'system:dict:edit', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (201004, '字典删除', 201, 4, '', '', '', 1, 0, 'B', 1, 'system:dict:remove', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (201005, '字典导出', 201, 5, '', '', '', 1, 0, 'B', 1, 'system:dict:export', '#', '');
-- 参数管理按钮
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (202001, '参数查询', 202, 1, '', '', '', 1, 0, 'B', 1, 'system:config:query', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (202002, '参数新增', 202, 2, '', '', '', 1, 0, 'B', 1, 'system:config:add', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (202003, '参数修改', 202, 3, '', '', '', 1, 0, 'B', 1, 'system:config:edit', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (202004, '参数删除', 202, 4, '', '', '', 1, 0, 'B', 1, 'system:config:remove', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (202005, '参数导出', 202, 5, '', '', '', 1, 0, 'B', 1, 'system:config:export', '#', '');
-- 通知管理按钮
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (203001, '参数查询', 203, 1, '', '', '', 1, 0, 'B', 1, 'system:notice:query', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (203002, '参数新增', 203, 2, '', '', '', 1, 0, 'B', 1, 'system:notice:add', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (203003, '参数修改', 203, 3, '', '', '', 1, 0, 'B', 1, 'system:notice:edit', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (203004, '参数删除', 203, 4, '', '', '', 1, 0, 'B', 1, 'system:notice:remove', '#', '');
-- 授权日志管理按钮
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (20401001, '日志查询', 20401, 1, '', '', '', 1, 0, 'B', 1, 'system:authenticate:query', '#',
        '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (20401002, '日志删除', 20401, 2, '', '', '', 1, 0, 'B', 1, 'system:authenticate:remove', '#',
        '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (20401003, '日志导出', 20401, 3, '', '', '', 1, 0, 'B', 1, 'system:authenticate:export', '#',
        '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (20401004, '账户解锁', 20401, 4, '', '', '', 1, 0, 'B', 1, 'system:authenticate:unlock', '#',
        '');
-- 操作日志管理按钮
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (20402001, '日志查询', 20402, 1, '', '', '', 1, 0, 'B', 1, 'system:operation:query', '#',
        '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (20402002, '日志删除', 20402, 2, '', '', '', 1, 0, 'B', 1, 'system:operation:remove', '#',
        '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (20402003, '日志导出', 20402, 3, '', '', '', 1, 0, 'B', 1, 'system:operation:export', '#',
        '');
-- 在线用户按钮
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (301001, '在线查询', 301, 1, '', '', '', 1, 0, 'B', 1, 'monitor:online:query', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (301002, '批量强退', 301, 2, '', '', '', 1, 0, 'B', 1, 'monitor:online:batchLogout', '#',
        '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (301003, '单挑强退', 301, 3, '', '', '', 1, 0, 'B', 1, 'monitor:online:forceLogout', '#',
        '');
-- 定时任务按钮
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (302001, '任务查询', 302, 1, '', '', '', 1, 0, 'B', 1, 'monitor:job:query', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (302002, '任务新增', 302, 2, '', '', '', 1, 0, 'B', 1, 'monitor:job:add', '#',
        '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (302003, '任务修改', 302, 3, '', '', '', 1, 0, 'B', 1, 'monitor:job:edit', '#',
        '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (302004, '任务删除', 302, 4, '', '', '', 1, 0, 'B', 1, 'monitor:job:remove', '#',
        '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (302005, '状态修改', 302, 5, '', '', '', 1, 0, 'B', 1, 'monitor:job:changeStatus', '#',
        '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (302006, '任务导出', 302, 6, '', '', '', 1, 0, 'B', 1, 'monitor:job:export', '#',
        '');
-- 代码生成按钮
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (402001, '生成查询', 402, 1, '', '', '', 1, 0, 'B', 1, 'tool:gen:query', '#', '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (402002, '生成修改', 402, 2, '', '', '', 1, 0, 'B', 1, 'tool:gen:edit', '#',
        '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (402003, '生成删除', 402, 3, '', '', '', 1, 0, 'B', 1, 'tool:gen:remove', '#',
        '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (402004, '导入代码', 402, 4, '', '', '', 1, 0, 'B', 1, 'tool:gen:import', '#',
        '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (402005, '预览代码', 402, 5, '', '', '', 1, 0, 'B', 1, 'tool:gen:preview', '#',
        '');
insert into oum_menu(id, menu_name, parent_id, order_number, path, component, query, is_frame,
                     is_cache,
                     menu_type, is_visible, perms, icon, remark)
values (402006, '生成代码', 402, 6, '', '', '', 1, 0, 'B', 1, 'tool:gen:code', '#',
        '');


drop table if exists oum_user_role;
create table oum_user_role
(
    id          bigint auto_increment primary key,
    user_id     bigint                                     not null comment '用户ID',
    role_id     bigint                                     not null comment '角色ID',
    is_deleted  tinyint unsigned default '0'               null,
    create_time datetime         default CURRENT_TIMESTAMP null,
    update_time datetime         default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    unique index uk_user_id_role_id (user_id, role_id)
) comment = '用户和角色关联表';

insert into oum_user_role(user_id, role_id)
values (1, 1);
insert into oum_user_role(user_id, role_id)
values (2, 2);

drop table if exists oum_role_menu;
create table oum_role_menu
(
    id          bigint auto_increment primary key,
    role_id     bigint(20)                                 not null comment '角色ID',
    menu_id     bigint(20)                                 not null comment '菜单ID',
    is_deleted  tinyint unsigned default '0'               null,
    create_time datetime         default CURRENT_TIMESTAMP null,
    update_time datetime         default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    unique index uk_role_id_menu_id (role_id, menu_id)
) comment = '角色和菜单关联表';

insert into oum_role_menu(role_id, menu_id)
values (1, 1);
insert into oum_role_menu(role_id, menu_id)
values (2, 1);
insert into oum_role_menu(role_id, menu_id)
values (1, 2);
insert into oum_role_menu(role_id, menu_id)
values (2, 2);
insert into oum_role_menu(role_id, menu_id)
values (1, 3);
insert into oum_role_menu(role_id, menu_id)
values (2, 3);
insert into oum_role_menu(role_id, menu_id)
values (1, 4);
insert into oum_role_menu(role_id, menu_id)
values (2, 4);
insert into oum_role_menu(role_id, menu_id)
values (1, 5);
insert into oum_role_menu(role_id, menu_id)
values (2, 5);
insert into oum_role_menu(role_id, menu_id)
values (1, 101);
insert into oum_role_menu(role_id, menu_id)
values (2, 101);
insert into oum_role_menu(role_id, menu_id)
values (1, 102);
insert into oum_role_menu(role_id, menu_id)
values (2, 102);
insert into oum_role_menu(role_id, menu_id)
values (1, 103);
insert into oum_role_menu(role_id, menu_id)
values (2, 103);
insert into oum_role_menu(role_id, menu_id)
values (1, 104);
insert into oum_role_menu(role_id, menu_id)
values (2, 104);
insert into oum_role_menu(role_id, menu_id)
values (1, 105);
insert into oum_role_menu(role_id, menu_id)
values (2, 105);
insert into oum_role_menu(role_id, menu_id)
values (1, 201);
insert into oum_role_menu(role_id, menu_id)
values (2, 201);
insert into oum_role_menu(role_id, menu_id)
values (1, 202);
insert into oum_role_menu(role_id, menu_id)
values (2, 202);
insert into oum_role_menu(role_id, menu_id)
values (1, 203);
insert into oum_role_menu(role_id, menu_id)
values (2, 203);
insert into oum_role_menu(role_id, menu_id)
values (1, 204);
insert into oum_role_menu(role_id, menu_id)
values (2, 204);
insert into oum_role_menu(role_id, menu_id)
values (1, 301);
insert into oum_role_menu(role_id, menu_id)
values (2, 301);
insert into oum_role_menu(role_id, menu_id)
values (1, 302);
insert into oum_role_menu(role_id, menu_id)
values (2, 302);
insert into oum_role_menu(role_id, menu_id)
values (1, 303);
insert into oum_role_menu(role_id, menu_id)
values (2, 303);
insert into oum_role_menu(role_id, menu_id)
values (1, 304);
insert into oum_role_menu(role_id, menu_id)
values (2, 304);
insert into oum_role_menu(role_id, menu_id)
values (1, 305);
insert into oum_role_menu(role_id, menu_id)
values (2, 305);
insert into oum_role_menu(role_id, menu_id)
values (1, 401);
insert into oum_role_menu(role_id, menu_id)
values (2, 401);
insert into oum_role_menu(role_id, menu_id)
values (1, 402);
insert into oum_role_menu(role_id, menu_id)
values (2, 402);
insert into oum_role_menu(role_id, menu_id)
values (1, 403);
insert into oum_role_menu(role_id, menu_id)
values (2, 403);
insert into oum_role_menu(role_id, menu_id)
values (1, 20401);
insert into oum_role_menu(role_id, menu_id)
values (2, 20401);
insert into oum_role_menu(role_id, menu_id)
values (1, 20402);
insert into oum_role_menu(role_id, menu_id)
values (2, 20402);
insert into oum_role_menu(role_id, menu_id)
values (1, 101001);
insert into oum_role_menu(role_id, menu_id)
values (2, 101001);
insert into oum_role_menu(role_id, menu_id)
values (1, 101002);
insert into oum_role_menu(role_id, menu_id)
values (2, 101002);
insert into oum_role_menu(role_id, menu_id)
values (1, 101003);
insert into oum_role_menu(role_id, menu_id)
values (2, 101003);
insert into oum_role_menu(role_id, menu_id)
values (1, 101004);
insert into oum_role_menu(role_id, menu_id)
values (2, 101004);
insert into oum_role_menu(role_id, menu_id)
values (1, 101005);
insert into oum_role_menu(role_id, menu_id)
values (2, 101005);
insert into oum_role_menu(role_id, menu_id)
values (1, 101006);
insert into oum_role_menu(role_id, menu_id)
values (2, 101006);
insert into oum_role_menu(role_id, menu_id)
values (1, 101007);
insert into oum_role_menu(role_id, menu_id)
values (2, 101007);
insert into oum_role_menu(role_id, menu_id)
values (1, 102001);
insert into oum_role_menu(role_id, menu_id)
values (2, 102001);
insert into oum_role_menu(role_id, menu_id)
values (1, 102002);
insert into oum_role_menu(role_id, menu_id)
values (2, 102002);
insert into oum_role_menu(role_id, menu_id)
values (1, 102003);
insert into oum_role_menu(role_id, menu_id)
values (2, 102003);
insert into oum_role_menu(role_id, menu_id)
values (1, 102004);
insert into oum_role_menu(role_id, menu_id)
values (2, 102004);
insert into oum_role_menu(role_id, menu_id)
values (1, 102005);
insert into oum_role_menu(role_id, menu_id)
values (2, 102005);
insert into oum_role_menu(role_id, menu_id)
values (1, 103001);
insert into oum_role_menu(role_id, menu_id)
values (2, 103001);
insert into oum_role_menu(role_id, menu_id)
values (1, 103002);
insert into oum_role_menu(role_id, menu_id)
values (2, 103002);
insert into oum_role_menu(role_id, menu_id)
values (1, 103003);
insert into oum_role_menu(role_id, menu_id)
values (2, 103003);
insert into oum_role_menu(role_id, menu_id)
values (1, 103004);
insert into oum_role_menu(role_id, menu_id)
values (2, 103004);
insert into oum_role_menu(role_id, menu_id)
values (1, 104001);
insert into oum_role_menu(role_id, menu_id)
values (2, 104001);
insert into oum_role_menu(role_id, menu_id)
values (1, 104002);
insert into oum_role_menu(role_id, menu_id)
values (2, 104002);
insert into oum_role_menu(role_id, menu_id)
values (1, 104003);
insert into oum_role_menu(role_id, menu_id)
values (2, 104003);
insert into oum_role_menu(role_id, menu_id)
values (1, 104004);
insert into oum_role_menu(role_id, menu_id)
values (2, 104004);
insert into oum_role_menu(role_id, menu_id)
values (1, 105001);
insert into oum_role_menu(role_id, menu_id)
values (2, 105001);
insert into oum_role_menu(role_id, menu_id)
values (1, 105002);
insert into oum_role_menu(role_id, menu_id)
values (2, 105002);
insert into oum_role_menu(role_id, menu_id)
values (1, 105003);
insert into oum_role_menu(role_id, menu_id)
values (2, 105003);
insert into oum_role_menu(role_id, menu_id)
values (1, 105004);
insert into oum_role_menu(role_id, menu_id)
values (2, 105004);
insert into oum_role_menu(role_id, menu_id)
values (1, 201001);
insert into oum_role_menu(role_id, menu_id)
values (2, 201001);
insert into oum_role_menu(role_id, menu_id)
values (1, 201002);
insert into oum_role_menu(role_id, menu_id)
values (2, 201002);
insert into oum_role_menu(role_id, menu_id)
values (1, 201003);
insert into oum_role_menu(role_id, menu_id)
values (2, 201003);
insert into oum_role_menu(role_id, menu_id)
values (1, 201004);
insert into oum_role_menu(role_id, menu_id)
values (2, 201004);
insert into oum_role_menu(role_id, menu_id)
values (1, 201005);
insert into oum_role_menu(role_id, menu_id)
values (2, 201005);
insert into oum_role_menu(role_id, menu_id)
values (1, 202001);
insert into oum_role_menu(role_id, menu_id)
values (2, 202001);
insert into oum_role_menu(role_id, menu_id)
values (1, 202002);
insert into oum_role_menu(role_id, menu_id)
values (2, 202002);
insert into oum_role_menu(role_id, menu_id)
values (1, 202003);
insert into oum_role_menu(role_id, menu_id)
values (2, 202003);
insert into oum_role_menu(role_id, menu_id)
values (1, 202004);
insert into oum_role_menu(role_id, menu_id)
values (2, 202004);
insert into oum_role_menu(role_id, menu_id)
values (1, 202005);
insert into oum_role_menu(role_id, menu_id)
values (2, 202005);
insert into oum_role_menu(role_id, menu_id)
values (1, 203001);
insert into oum_role_menu(role_id, menu_id)
values (2, 203001);
insert into oum_role_menu(role_id, menu_id)
values (1, 203002);
insert into oum_role_menu(role_id, menu_id)
values (2, 203002);
insert into oum_role_menu(role_id, menu_id)
values (1, 203003);
insert into oum_role_menu(role_id, menu_id)
values (2, 203003);
insert into oum_role_menu(role_id, menu_id)
values (1, 203004);
insert into oum_role_menu(role_id, menu_id)
values (2, 203004);
insert into oum_role_menu(role_id, menu_id)
values (1, 301001);
insert into oum_role_menu(role_id, menu_id)
values (2, 301001);
insert into oum_role_menu(role_id, menu_id)
values (1, 301002);
insert into oum_role_menu(role_id, menu_id)
values (2, 301002);
insert into oum_role_menu(role_id, menu_id)
values (1, 301003);
insert into oum_role_menu(role_id, menu_id)
values (2, 301003);
insert into oum_role_menu(role_id, menu_id)
values (1, 302001);
insert into oum_role_menu(role_id, menu_id)
values (2, 302001);
insert into oum_role_menu(role_id, menu_id)
values (1, 302002);
insert into oum_role_menu(role_id, menu_id)
values (2, 302002);
insert into oum_role_menu(role_id, menu_id)
values (1, 302003);
insert into oum_role_menu(role_id, menu_id)
values (2, 302003);
insert into oum_role_menu(role_id, menu_id)
values (1, 302004);
insert into oum_role_menu(role_id, menu_id)
values (2, 302004);
insert into oum_role_menu(role_id, menu_id)
values (1, 302005);
insert into oum_role_menu(role_id, menu_id)
values (2, 302005);
insert into oum_role_menu(role_id, menu_id)
values (1, 302006);
insert into oum_role_menu(role_id, menu_id)
values (2, 302006);
insert into oum_role_menu(role_id, menu_id)
values (1, 402001);
insert into oum_role_menu(role_id, menu_id)
values (2, 402001);
insert into oum_role_menu(role_id, menu_id)
values (1, 402002);
insert into oum_role_menu(role_id, menu_id)
values (2, 402002);
insert into oum_role_menu(role_id, menu_id)
values (1, 402003);
insert into oum_role_menu(role_id, menu_id)
values (2, 402003);
insert into oum_role_menu(role_id, menu_id)
values (1, 402004);
insert into oum_role_menu(role_id, menu_id)
values (2, 402004);
insert into oum_role_menu(role_id, menu_id)
values (1, 402005);
insert into oum_role_menu(role_id, menu_id)
values (2, 402005);
insert into oum_role_menu(role_id, menu_id)
values (1, 402006);
insert into oum_role_menu(role_id, menu_id)
values (2, 402006);
insert into oum_role_menu(role_id, menu_id)
values (1, 20401001);
insert into oum_role_menu(role_id, menu_id)
values (2, 20401001);
insert into oum_role_menu(role_id, menu_id)
values (1, 20401002);
insert into oum_role_menu(role_id, menu_id)
values (2, 20401002);
insert into oum_role_menu(role_id, menu_id)
values (1, 20401003);
insert into oum_role_menu(role_id, menu_id)
values (2, 20401003);
insert into oum_role_menu(role_id, menu_id)
values (1, 20401004);
insert into oum_role_menu(role_id, menu_id)
values (2, 20401004);
insert into oum_role_menu(role_id, menu_id)
values (1, 20402001);
insert into oum_role_menu(role_id, menu_id)
values (2, 20402001);
insert into oum_role_menu(role_id, menu_id)
values (1, 20402002);
insert into oum_role_menu(role_id, menu_id)
values (2, 20402002);
insert into oum_role_menu(role_id, menu_id)
values (1, 20402003);
insert into oum_role_menu(role_id, menu_id)
values (2, 20402003);

drop table if exists oum_role_dept;
create table oum_role_dept
(
    id            bigint auto_increment primary key,
    role_id       bigint(20)                                 not null comment '角色ID',
    department_id bigint(20)                                 not null comment '部门ID',
    is_deleted    tinyint unsigned default '0'               null,
    create_time   datetime         default CURRENT_TIMESTAMP null,
    update_time   datetime         default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    unique index uk_role_id_menu_id (role_id, department_id)
) comment = '角色和部门关联表';

-- 角色部门- 1-N 部门

-- 综合部
insert into oum_role_dept(role_id, department_id)
values (1, 1000);
insert into oum_role_dept(role_id, department_id)
values (1, 1002);
-- 北京分中心-综合部
insert into oum_role_dept(role_id, department_id)
values (2, 1002);
insert into oum_role_dept(role_id, department_id)
values (2, 1100);
insert into oum_role_dept(role_id, department_id)
values (2, 2001);

drop table if exists oum_user_post;
create table oum_user_post
(
    id          bigint auto_increment primary key,
    user_id     bigint(20)                                 not null comment '用户ID',
    post_id     bigint(20)                                 not null comment '岗位ID',
    is_deleted  tinyint unsigned default '0'               null,
    create_time datetime         default CURRENT_TIMESTAMP null,
    update_time datetime         default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    unique index uk_user_id_post_id (user_id, post_id)
) comment = '用户与岗位关联表';

-- 一对多 1-N 岗位
insert into oum_user_post(user_id, post_id)
values (1, 1);
insert into oum_user_post(user_id, post_id)
values (2, 2);

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

insert into sys_dict_type(dict_name, dict_type, remark)
values ('用户性别', 'sys_user_gender', '用户性别列表');
insert into sys_dict_type(dict_name, dict_type, remark)
values ('是否', 'sys_int_boolean', '是否列表列表');
insert into sys_dict_type(dict_name, dict_type, remark)
values ('任务分组', 'sys_job_group', '任务分组列表');
insert into sys_dict_type(dict_name, dict_type, remark)
values ('通知类型', 'sys_notice_type', '通知类型列表');
insert into sys_dict_type(dict_name, dict_type, remark)
values ('通知状态', 'sys_notice_status', '通知状态列表');
insert into sys_dict_type(dict_name, dict_type, remark)
values ('操作类型', 'sys_operation_type', '操作类型列表');
insert into sys_dict_type(dict_name, dict_type, remark)
values ('系统状态', 'sys_common_status', '登录状态列表');

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

insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (1, '未知', 'U', 'sys_user_gender', '', '', 1, '性别未知');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (2, '男', 'M', 'sys_user_gender', '', '', 0, '性别男');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (3, '女', 'F', 'sys_user_gender', '', '', 0, '性别女');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (1, '否', '0', 'sys_int_boolean', '', 'primary', 0, '否');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (2, '是', '1', 'sys_int_boolean', '', 'danger', 1, '是');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (1, '默认', 'DEFAULT', 'sys_job_group', '', '', 1, '任务类型：默认');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (2, '系统', 'SYSTEM', 'sys_job_group', '', '', 0, '任务类型：系统');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (1, '通知', '1', 'sys_notice_type', '', 'warning', 1, '通告');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (2, '公告', '2', 'sys_notice_type', '', 'success', 0, '公告');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (1, '正常', '1', 'sys_notice_status', '', 'primary', 1, '正常状态');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (2, '关闭', '2', 'sys_notice_status', '', 'danger', 0, '关闭状态');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (1, '未知', 'unknown', 'sys_operation_type', '', 'info', 1, '其他');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (2, '新增', 'insert', 'sys_operation_type', '', 'info', 0, '新增');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (3, '更新', 'update', 'sys_operation_type', '', 'info', 0, '更新');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (4, '删除', 'delete', 'sys_operation_type', '', 'danger', 0, '删除');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (5, '授权', 'grant', 'sys_operation_type', '', 'primary', 0, '授权');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (6, '导出', 'export', 'sys_operation_type', '', 'warning', 0, '导出');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (7, '导入', 'import', 'sys_operation_type', '', 'warning', 0, '导入');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (8, '清空', 'clean', 'sys_operation_type', '', 'danger', 0, '清空');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (9, '登录', 'login', 'sys_operation_type', '', 'primary', 0, '登录');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (10, '退出', 'logout', 'sys_operation_type', '', 'info', 0, '退出');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (11, '强制退出', 'force', 'sys_operation_type', '', 'danger', 0, '强制退出');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (1, '正常', '1', 'sys_common_status', '', 'primary', 1, '通告');
insert into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class,
                           is_default, remark)
values (2, '失败', '0', 'sys_common_status', '', 'danger', 0, '公告');

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