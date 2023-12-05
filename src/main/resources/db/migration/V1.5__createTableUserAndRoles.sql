
create table users(
  user_id integer identity,
  user_name varchar(20),
  user_email varchar(50),
  user_password varchar(120),
  constraint pk_user_id primary key(user_id)
);

create table roles(
  role_id integer identity primary key,
  role_name varchar(20)
);

create table user_roles(
	user_id integer,
	role_id integer,
	constraint pk_user_role_id primary key(user_id,role_id),
	constraint fk_user_id foreign key (user_id) references users(user_id),
	constraint fk_role_id foreign key (role_id) references roles(role_id),
);