create table hibernate_sequence (next_val bigint) engine=InnoDB;

insert into hibernate_sequence values ( 1 );

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

create table role (
  id bigint not null,
  name varchar(255),
  primary key (id)
) engine=InnoDB;

create table task (
  id bigint not null auto_increment,
  secret_id varchar(255),
  deadline varchar(255),
  type varchar(255),
  course varchar(255),
  university integer,
  faculty integer,
  keywords varchar(255),
  name varchar(255),
  specification varchar(255),
  description varchar(255),
  subject varchar(255),
  task_status integer,
  customer_id bigint,
  executor_id bigint,
  primary key (id)
) engine=InnoDB;

create table user_roles (
  user_id bigint not null,
  role_id bigint not null
) engine=InnoDB;

create table usr (
  id bigint not null auto_increment,
  user_status integer,
  city varchar(255),
  course varchar(255),
  email varchar(255),
  faculty integer,
  name varchar(255),
  username varchar(255),
  password varchar(255),
  surname varchar(255),
  university integer,
  web_money_account varchar(255),
  activation_code varchar(255),
  updated date,
  password_restore_link varchar(255),
  primary key (id)
) engine=InnoDB;


alter table message
add constraint  message_sender_user_fk
foreign key (sender_user_id) references usr (id);

alter table message
add constraint message_recipient_user_fk
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

alter table user_roles
add constraint user_roles_role_fk
foreign key (role_id) references role (id);

alter table user_roles
add constraint user_roles_user_fk
foreign key (user_id) references usr (id);