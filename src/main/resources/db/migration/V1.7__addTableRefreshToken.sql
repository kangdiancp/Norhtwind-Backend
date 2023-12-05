CREATE TABLE refresh_token(
    reto_id integer identity,
    reto_user_id integer,
    reto_token varchar(125),
    reto_expire_date date,
    constraint pk_reto_id primary key(reto_id),
    constraint uq_reto_token unique(reto_token),
    constraint fk_reto_user_id foreign key(reto_user_id) references users(user_id)
)
