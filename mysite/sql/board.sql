-- board
desc board;
delete from board;
select * from board;
drop table board;

-- user
select * from user;
select 1 , 0, now(), ifnull(MAX(g_no) + 1, 1) , 1, 0, 1 from board;
insert into board
values(null, 'a1', 'a1', 0, now(), 1, 0, 0, 1);

insert into board
values(null, 'a2', 'a2', 0, now(), 1, 1, 1, 2);

insert into board

values(null, 'a3', 'a3', 0, now(), 1, 1, 2, 3);

insert into board
values(null, 'a4', 'a4', 0, now(), 1, 2, 2, 4);

insert into board
values(null, 'a4', 'a4', 0, now(), 2, 0, 0, 1);


-- 왜 안됨?? depth가 5가 되면 안들어감 왠지는 모르겠음.... 
-- 참조 무결성 위배...?
insert into board
values(null, 'e', 'e', 0, now(), 2, 2,  2, 5);

select * from emaillist;
desc emaillist;