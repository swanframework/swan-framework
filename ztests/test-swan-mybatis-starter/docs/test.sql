create table if not exists t_auto
(
    id   bigint auto_increment
        primary key,
    name longtext null,
    age  int      null
);

create table if not exists t_auto_del
(
    id   bigint auto_increment
        primary key,
    name mediumtext null,
    age  int        null,
    del  int        null
);

create table if not exists t_auto_del_version
(
    id      bigint auto_increment
        primary key,
    name    tinytext null,
    age     int      null,
    remark  tinytext null,
    version int      null,
    del     int      null
);

create table if not exists t_auto_version
(
    id      bigint auto_increment
        primary key,
    name    text null,
    age     int  null,
    version int  null
);

