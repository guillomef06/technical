create sequence customer_seq start with 1 increment by 1;
create table customer (date_of_birth date not null, gender tinyint check (gender between 0 and 1), id bigint not null, country varchar(255) not null, phone_number varchar(255), user_name varchar(255) not null, primary key (id));
