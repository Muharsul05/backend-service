--liquibase formatted sql
--changeset magarusik:create-post-table
create table post_entity
(
    id        bigserial primary key not null,
    title     varchar               not null unique,
    full_text varchar               not null,
    date      date                  not null,
    type_id   bigint                not null
);