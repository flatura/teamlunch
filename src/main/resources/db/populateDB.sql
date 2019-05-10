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

INSERT INTO restaurants (id, name)
VALUES (0, 'Tanuki'),
       (1, 'Чайхона 1'),
       (2, 'Дурдинъ');

INSERT INTO dishes (id, name, price, restaurant_id)
VALUES (0, 'Ролл Филадельфия', 250, 0),
       (1, 'Рис с овощами', 300, 0),
       (2, 'Чай имбирный', 150, 0),
       (3, 'Шурпа', 230, 1),
       (4, 'Чахокбили', 450, 1),
       (5, 'Бокал вина', 220, 1),
       (6, 'Борщ', 180, 2),
       (7, 'Свиная рулька', 800, 2),
       (8, 'Светлое пиво', 200, 2);

/*
 *  Encrypted passwords(bcrypt algorithm):
 *  admin --> $2a$10$nkIkAuiTPDwI1apyvvkALuGjLzlH4z6drw.P4.kRTzWnV7c5eai9K
 *  12345678 --> $2a$10$d1nYOalJluNlt5K/BPUnlOKT5A3LZ7irNzR2..vuorxd3SXkOwsSm
 */
INSERT INTO users (id, name, email, password)
VALUES (0, 'Admin', 'admin@gmail.com', '$2a$10$nkIkAuiTPDwI1apyvvkALuGjLzlH4z6drw.P4.kRTzWnV7c5eai9K'),
       (1, 'User1', 'user1@gmail.com', '$2a$10$d1nYOalJluNlt5K/BPUnlOKT5A3LZ7irNzR2..vuorxd3SXkOwsSm'),
       (2, 'User2', 'user2@gmail.com', '$2a$10$d1nYOalJluNlt5K/BPUnlOKT5A3LZ7irNzR2..vuorxd3SXkOwsSm');

INSERT INTO user_roles (user_id, role)
VALUES (0, 'ROLE_USER'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

INSERT INTO votes (date, user_id, restaurant_id)
VALUES ('2019-03-20', 0, 0);
VALUES ('2019-03-20', 1, 1);
VALUES ('2019-03-20', 2, 0);
VALUES ('2019-03-21', 0, 1);
VALUES ('2019-03-21', 1, 2);
VALUES ('2019-03-21', 2, 2);