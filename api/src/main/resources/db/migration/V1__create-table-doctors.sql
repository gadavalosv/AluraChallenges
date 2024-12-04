--PostgrSQL
create table doctors (
    id bigserial not null,
    name varchar(100) not null,
    email varchar(100) not null unique,
    document varchar(6) not null unique,
    specialty varchar(100) not null,
    street varchar(100) not null,
    district varchar(100) not null,
    city varchar(100) not null,
    number varchar(20),
    complement varchar(100),

    primary key (id)
);

--MySQL:
--create table doctors(
--    id bigint not null auto_increment,
--    name varchar(100) not null,
--    email varchar(100) not null unique,
--    document varchar(6) not null unique,
--    specialty varchar(100) not null,
--    street varchar(100) not null,
--    district varchar(100) not null,
--    city varchar(100) not null,
--    number varchar(20),
--    complement varchar(100),
--
--    primary key(id)
--);