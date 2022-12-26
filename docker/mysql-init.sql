CREATE
    DATABASE IF NOT EXISTS primary_db;
CREATE
    DATABASE IF NOT EXISTS secondary_db;

SET
    GLOBAL sql_mode = 'STRICT_TRANS_TABLES';


use primary_db;

create table user_detail
(
    id         bigint auto_increment primary key,
    first_name varchar(100) not null,
    last_name  varchar(100) not null
);

use secondary_db;

create table order_master
(
    id           bigint auto_increment primary key,
    created_time timestamp default current_timestamp,
    updated_time timestamp default current_timestamp
);