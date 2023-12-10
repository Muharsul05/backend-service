--liquibase formatted sql
--changeset magarusik:create-postType-table
create table post_type
(
    id   bigserial primary key not null,
    name varchar
);

--liquibase formatted sql
--changeset magarusik:insert-postTypes
insert into post_type (name)
values ('news'),
       ('note');

--changeset magarusik:addForeignKeyConstraint-post-type-id
ALTER TABLE post_entity
    ADD CONSTRAINT fk_post_type_id FOREIGN KEY (type_id) REFERENCES post_type (id) ON DELETE CASCADE;