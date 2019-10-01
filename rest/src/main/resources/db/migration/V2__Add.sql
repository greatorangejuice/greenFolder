insert into usr (id, email, faculty, name, password, role, web_money_account, course, university)
            values (1, 'mail1', 0, 'name1', 1234, 3, 9999, '2', 0);

insert into usr (id, email, faculty, name, password, role, web_money_account, course, university)
            values (2, 'mail12', 0, 'name2', 2222, 2, 9999, '4', 0);

insert into message (id, message_body, message_head, sender_user_id, recipient_user_id)
            values (1, 'i can do it', 'for task', 1, 2);

insert into task (id, faculty, keywords, name, specification, subject, task_status, customer_id, executor_id)
            values (1, 0, 'keyword', 'sessionTask', 'write some task', 'java', 0, 2, 1);

insert into offer (id, bid, offer_status, customer_id, executor_id, task_id)
            values (1, '5000', 2, 2, 1, 1);


