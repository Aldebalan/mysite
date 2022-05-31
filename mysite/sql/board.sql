-- board
desc board;
delete from board;
select * from board;

insert into board
values(null, 'a', 'a', 0, now(), 1, 0, 0, 1);

insert into board
values(null, 'b', 'b', 0, now(), 1, 1, 1, 2);

-- 왜 안됨?? depth가 5가 되면 안들어감 왠지는 모르겠음.... 
-- 참조 무결성 위배...?
insert into board
values(null, 'e', 'e', 0, now(), 2, 2,  2, 5);

