DELETE
FROM USER_ROLES;
DELETE
FROM VOTES;
DELETE
FROM USERS;
DELETE
FROM dishes;
DELETE
FROM menus;
DELETE
FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurants(name)
VALUES ('Mafia'),
       ('Pesto Cafe'),
       ('Murakami');

INSERT INTO menus(localDate, description, restaurant_id)
VALUES ('2020-06-27', 'Mafia menu', 100000),
       ('2020-06-20', 'Mafia menu', 100000),
       ('2020-06-27', 'Pesto Cafe menu', 100001),
       ('2020-06-20', 'Pesto Cafe menu', 100001),
       ('2020-06-27', 'Murakami menu', 100002),
       ('2020-06-20', 'Murakami menu', 100002);

INSERT INTO dishes(name, price, menu_id)
VALUES ('Double Fila', 199, 100003),
       ('Super Fila XL', 299, 100003),
       ('Super Fila XXL', 599, 100004),
       ('Carbonara', 175, 100004),
       ('Chicken Milaneze', 148, 100005),
       ('Pork ribs', 270, 100005),
       ('Potatos free', 120, 100006),
       ('Fish file', 290, 100006),
       ('Avocado roll', 59, 100007),
       ('Philadelphia roll', 399, 100007),
       ('California roll', 175, 100008),
       ('New Style roll', 269, 100008);

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100021),
       ('ADMIN', 100022);

INSERT INTO votes (date, user_id, restaurant_id)
VALUES ('2020-06-21', 100021, 100000),
       ('2020-06-20', 100021, 100001);
