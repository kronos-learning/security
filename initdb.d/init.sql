use sample

create table users (
  id int primary key auto_increment,
  login_id varchar(50) not null unique,
  password varchar(50) not null,
  admin int(1) not null default 0
);

create table books (
  id int primary key auto_increment,
  name varchar(50) not null unique,
  price int(6)
);

insert into users values(1, 'user1', 'pass1', 1);
insert into users values(2, 'user2', 'pass2', 0);
insert into users values(3, 'user3', 'pass3', 0);

insert into books values(1, 'Java Book', 3000);
insert into books values(2, 'PHP Book', 2000);
insert into books values(3, 'Ruby Book', 4000);
