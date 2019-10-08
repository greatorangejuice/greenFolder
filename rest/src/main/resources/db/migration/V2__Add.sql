insert into usr (id, email, faculty, name, username, password, web_money_account, course, university, user_status)
            values (1, 'mail1', 0, 'name1', 'first', 1234, 9999, '2', 0, 0);

insert into usr (id, email, faculty, name, username,password, web_money_account, course, university, user_status)
            values (2, 'mail12', 0, 'name2', 'second','$2a$04$8jgZcjTI/gS/mzEmKNg3AOn48ginyrt/D1FRli.Ae.JKkqYoJvtw.', 9999, '4', 0, 0);

insert into usr (id, email, faculty, name, username,password, web_money_account, course, university, user_status)
            values (3, 'mail12', 0, 'test', 'third','$2a$04$QJYjqfdimlnDbtJeHL7iPuc0zh/VwLXPSxgBiRm4JnpOvSFYDJrYC', 9999, '4', 0, 0);

insert into usr (id, email, faculty, name, username, password, web_money_account, course, university, user_status)
            values (4, 'greenfolderteam@gmail.com', 0, 'greenFolder', 'greenFolder', 1234, 9999, '2', 0, 0);

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

insert into message (id, message_body, message_head, sender_user_id, recipient_user_id)
            values (1, 'i can do it', 'for task', 1, 2);

insert into task (id, faculty, keywords, name, specification, subject, task_status, customer_id, executor_id)
            values (1, 0, 'keyword', 'sessionTask', 'write some task', 'java', 0, 2, 1);

insert into offer (id, bid, offer_status, customer_id, executor_id, task_id)
            values (1, '5000', 2, 2, 1, 1);


