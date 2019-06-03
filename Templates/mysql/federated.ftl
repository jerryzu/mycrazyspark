select engine,support from information_schema.engines where engine='federated';

show engines;

install plugin federated soname 'ha_federated.so'; 

drop server svr;
create server svr foreign data wrapper mysql options (user 'root', password '', host '127.0.0.1', database 'txbs');

drop table rul_sqlscript_link;
create table  rul_sqlscript_link (
    remark varchar(28) not null,
    rulid bigint default 0 not null,
    seq decimal(20,0),
    cde varchar(50),
    name varchar(200),
    enabled bigint default 0 not null,
    sqltxt text
) engine=federated  default charset=utf8mb4
connection='svr/rul_sqlscript';
