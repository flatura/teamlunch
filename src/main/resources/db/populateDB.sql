DELETE
FROM votes;
DELETE
FROM user_roles;
DELETE
FROM dishes;
DELETE
FROM restaurants;
DELETE
FROM users;

ALTER SEQUENCE global_seq RESTART WITH 10000;

INSERT INTO restaurants (id, name, enabled)
VALUES (0, 'Тануки', TRUE),
       (1, 'Чайхона 1', TRUE),
       (2, 'Дурдинъ', TRUE),
       (3, 'Кружка', FALSE);

INSERT INTO dishes (id, date, name, price, restaurant_id)
VALUES (0, '2019-05-16', 'Ролл Филадельфия', 250, 0),
       (1, '2019-05-16', 'Рис с овощами', 300, 0),
       (2, '2019-03-20', 'Чай имбирный', 150, 0),
       (3, '2019-05-16', 'Шурпа', 230, 1),
       (4, '2019-05-16', 'Чахокбили', 450, 1),
       (5, '2019-03-20', 'Бокал вина', 220, 1),
       (6, '2019-05-16', 'Борщ', 180, 2),
       (7, '2019-03-20', 'Свиная рулька', 800, 2),
       (8, '2019-03-20', 'Светлое пиво', 200, 2);

/*
 *  Encrypted passwords(bcrypt algorithm):
 *  admin --> $2a$10$nkIkAuiTPDwI1apyvvkALuGjLzlH4z6drw.P4.kRTzWnV7c5eai9K
 *  12345678 --> $2a$10$d1nYOalJluNlt5K/BPUnlOKT5A3LZ7irNzR2..vuorxd3SXkOwsSm
 */
INSERT INTO users (id, name, email, password)
VALUES (0, 'Admin', 'admin@gmail.com', '{noop}admin'),
       (1, 'User1', 'user1@gmail.com', '{noop}12345678'),
       (2, 'User2', 'user2@gmail.com', '{noop}12345678');

INSERT INTO user_roles (user_id, role)
VALUES (0, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');

INSERT INTO votes (date, user_id, restaurant_id)
VALUES ('2019-05-16', 0, 0),
       ('2019-03-20', 0, 1),
       ('2019-05-16', 1, 0),
       ('2019-03-20', 1, 1),
       ('2019-05-16', 2, 2),
       ('2019-03-20', 2, 2);