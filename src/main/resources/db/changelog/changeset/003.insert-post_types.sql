--liquibase formatted sql
--changeset magarusik:insert-post-types
insert into post_types (name)
values ('news'),
       ('note');