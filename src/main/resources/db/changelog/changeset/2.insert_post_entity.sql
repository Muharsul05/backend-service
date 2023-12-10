--liquibase formatted sql
--changeset magarusik:insert-posts
insert into post_entity (title, full_text, date, type_id)
values ('Миграции схемы базы данных с Liquibase',
        'Большая часть приложений, которые мне встречались, хранят данные в SQL базе данных. Приложение публикуется на несколько стендов: стенд разработки, пре-прод и прод. А над приложением трудится команда разработчиков.',
        current_date,
        1),
       ('Глубокое погружение в Stream API Java: Понимание и Применение',
        'Версия Java 8 принесла множество новшеств, которые значительно упростили обработку и манипулирование данными. Одним из таких нововведений стал Stream API - эффективный инструмент для обработки коллекций в функциональном стиле.',
        current_date,
        1),
       ('Валидация данных в Spring Boot',
        'Пользователи часто передают в приложение некорректные данные. Такое происходит либо из злого умысла, либо по ошибке. Сто́ит проверять данные на соответствие бизнес-требованиям.',
        current_date,
        2),
       ('Характеристики Spliterator',
        'Spliterator обладает специальными характеристиками, которые сообщают об особенностях источника данных, из которого он был создан.',
        current_date,
        2);

