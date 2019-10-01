create table message (
  id bigint not null auto_increment,
  message_body varchar(255),
  message_head varchar(255),
  sender_user_id bigint,
  recipient_user_id bigint,
  primary key (id)
) engine=InnoDB;

create table offer (
  id bigint not null auto_increment,
  bid varchar(255),
  offer_status integer,
  customer_id bigint,
  executor_id bigint,
  task_id bigint,
  primary key (id)
) engine=InnoDB;

create table task (
  id bigint not null auto_increment,
  faculty integer,
  keywords varchar(255),
  name varchar(255),
  specification varchar(255),
  subject varchar(255),
  task_status integer,
  customer_id bigint,
  executor_id bigint,
  primary key (id)
) engine=InnoDB;

create table usr (
  id bigint not null auto_increment,
  email varchar(255),
  faculty integer,
  name varchar(255),
  password varchar(255),
  role integer,
  web_money_account varchar(255),
  course varchar(255),
  university integer,
  primary key (id)
) engine=InnoDB;


alter table message
add constraint message_sender_usr_fk
foreign key (sender_user_id) references usr (id);

alter table message
add constraint message_recipient_usr_fk
foreign key (recipient_user_id) references usr (id);

alter table offer
add constraint offer_customer_fk
foreign key (customer_id) references usr (id);

alter table offer
add constraint offer_executor_fk
foreign key (executor_id) references usr (id);

alter table offer
add constraint offer_task_fk
foreign key (task_id) references task (id);

alter table task
add constraint task_customer_fk
foreign key (customer_id) references usr (id);

alter table task
add constraint task_executor_fk
foreign key (executor_id) references usr (id);