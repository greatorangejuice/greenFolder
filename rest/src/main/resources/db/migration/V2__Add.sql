insert into usr (id, email, city, faculty, name, surname, username, password, web_money_account, course, university, user_status)
            values (1, 'test@gmail.com', 'test', 0, 'test', 'test', 'test', '$2a$04$ci8uFyDcq0O2BZJ8058jnOCGwVH/PwU4IxbuQndKioeirjgqEoRO2', 132333481, '2', 0, 0);

insert into usr (id, email, city, faculty, name, surname, username,password, web_money_account, course, university, user_status)
            values (2, 'tommy@gmail.com', 'milan', 0, 'tommy', 'hendrix', 'tommy','$2a$04$zq.5IdJEJKphDb0RJTG6POGjYd1w/uKPNys4HKmXOsZCwa/HZVuLe', 33348456261, '4', 0, 0);

insert into usr (id, email, city, faculty, name, surname, username,password, web_money_account, course, university, user_status)
            values (3, 'valera@gmail.com', 'orsha', 0, 'valera', 'pirozhkov', 'valera','$2a$04$jGSeUMMvJlB9suJGzB/ag.DAU6SbhjhiuwbtMYhW0Hs9Ej76QnySK', 18916511111315, '4', 0, 0);

insert into usr (id, email, city, faculty, name, username, password, web_money_account, course, university, user_status)
            values (4, 'greenfolderteam@gmail.com', 'minsk', 0, 'greenFolder', 'greenFolder', '$2a$04$xzPLUlX80/z8kTT9VA51i.4zpSQqgSkeciHydOIyBnFpDv5X1jKYm', 99999999999, '2', 0, 0);

insert into role (id, name) values (1, 'USER');

insert into role (id, name) values (2, 'ADMIN');

insert into role (id, name) values (3, 'EXECUTOR');

insert into role (id, name) values (4, 'CUSTOMER');

insert  into user_roles (user_id, role_id) values (1, 1);

insert  into user_roles (user_id, role_id) values (1, 3);

insert  into user_roles (user_id, role_id) values (1, 4);

insert  into user_roles (user_id, role_id) values (2, 1);

insert  into user_roles (user_id, role_id) values (2, 4);

insert  into user_roles (user_id, role_id) values (3, 1);

insert  into user_roles (user_id, role_id) values (3, 3);

insert  into user_roles (user_id, role_id) values (4, 2);

insert into message (id, message_body, message_head, sender_user_id, recipient_user_id)
            values (1, 'i can do it', 'for task', 1, 2);

insert into message (id, message_body, message_head, sender_user_id, recipient_user_id)
            values (2, 'ok', 'for task', 2, 1);

insert into task (id, secret_id, course, deadline, type, faculty, keywords, name, specification, subject, task_status, customer_id, executor_id)
            values (1, 'secretId1', '3', '31.12.2019', 'course project', 0, 'java, lab', 'sessionTask', 'need some rest service', 'java', 0, 2, 1);

insert into task (id, secret_id, course, deadline, type, faculty, keywords, name, specification, subject, task_status, customer_id, executor_id)
            values (2, 'secretId2', '2', '22.11.2019', 'lab', 0, 'python', 'python course project', 'neural network', 'python', 0, 1, 2);

insert into offer (id, bid, offer_status, customer_id, executor_id, task_id)
            values (1, '300', 2, 2, 1, 1);

insert into offer (id, bid, offer_status, customer_id, executor_id, task_id)
            values (2, '450', 2, 1, 2, 2);


