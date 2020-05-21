create table scrabble.user
(
    id                varchar(256) not null,
    created_timestamp bigserial    not null,
    username          varchar(256) not null,
    enabled           boolean      not null,
    totp              boolean      not null,
    email_verified    boolean      not null,
    not_before        bigserial    not null,
    primary key (id)
);
