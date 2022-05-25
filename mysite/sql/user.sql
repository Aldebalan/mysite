-- UserRepository

desc user;

insert
   into user
values(null, '관리자', 'admin@mysite.com', '1234', 'male', now());

select * from user;

