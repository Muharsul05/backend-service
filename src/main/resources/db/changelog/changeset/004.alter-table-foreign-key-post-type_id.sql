--liquibase formatted sql
--changeset magarusik:add-foreign-key-posts-type_id
ALTER TABLE posts
    ADD CONSTRAINT fk_post_type_id
        FOREIGN KEY (type_id)
        REFERENCES post_types (id) ON DELETE CASCADE;