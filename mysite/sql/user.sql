-- UserRepository
select * from user;
desc user;

-- join
insert
   into user
values(null, '관리자', 'admin@mysite.com', '1234', 'male', now());

-- login
select no, name
   from user
  where email='src@gmail.com'
	and password='1234';