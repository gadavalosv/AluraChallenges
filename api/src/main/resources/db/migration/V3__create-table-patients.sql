create table patients(
    id bigserial not null,
    name varchar(100) not null,
    email varchar(100) not null unique,
    identity_document varchar(14) not null unique,
    phone varchar(20) not null,
    street varchar(100) not null,
    district varchar(100) not null,
    city varchar(100) not null,
    number varchar(20),
    complement varchar(100),

    primary key(id)
);