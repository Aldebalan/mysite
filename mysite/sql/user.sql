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
    
-- findByNo
select no, name, email, gender
   from user
  where no=1;
  
select * from guestbook;

select no, name, date_format(reg_date, '%Y/%m/%d %H:%i:%s') as reg_date, message
				  from guestbook
				 order by reg_date desc