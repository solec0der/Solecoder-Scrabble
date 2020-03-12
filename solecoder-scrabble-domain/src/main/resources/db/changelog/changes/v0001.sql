create table scrabble.languages
(
    id            bigserial not null,
    display_value varchar(64),
    lang_code     varchar(16),
    ui_lang_code  varchar(16),
    primary key (id)
);

create table scrabble.translation_keys
(
    id  bigserial not null,
    key varchar(255),
    primary key (id)
);

create table scrabble.translations
(
    id                 bigserial not null,
    translation_key_id bigserial not null,
    language_id        bigserial not null,
    value              varchar(255),
    primary key (id)
);
