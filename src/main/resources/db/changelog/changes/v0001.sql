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

create table scrabble.games
(
    id          bigserial    not null,
    title       varchar(255) not null,
    language_id bigserial    not null,
    primary key (id)
);

create table scrabble.game_fields
(
    id         bigserial   not null,
    x_position integer     not null,
    y_position integer     not null,
    game_id    bigserial   not null,
    letter_id  bigserial   not null,
    field_type varchar(64) not null,
    primary key (id)
);

create table scrabble.letters
(
    id    bigserial not null,
    value varchar(3),
    score integer   not null,
    joker boolean   not null,
    primary key (id)
);


ALTER TABLE scrabble.translations
    ADD CONSTRAINT fk_translations_translation_keys
        FOREIGN KEY (translation_key_id)
            REFERENCES scrabble.translation_keys (id);

ALTER TABLE scrabble.translations
    ADD CONSTRAINT fk_translations_languages
        FOREIGN KEY (language_id)
            REFERENCES scrabble.languages (id);

ALTER TABLE scrabble.games
    ADD CONSTRAINT fk_games_languages
        FOREIGN KEY (language_id)
            REFERENCES scrabble.languages (id);

ALTER TABLE scrabble.game_fields
    ADD CONSTRAINT fk_game_fields_games
        FOREIGN KEY (game_id)
            REFERENCES scrabble.games (id);

ALTER TABLE scrabble.game_fields
    ADD CONSTRAINT fk_game_fields_letters
        FOREIGN KEY (letter_id)
            REFERENCES scrabble.letters (id);
