create table scrabble.languages
(
    id           bigserial not null,
    language     varchar(64),
    lang_code    varchar(16),
    ui_lang_code varchar(16),
    primary key (id)
);
