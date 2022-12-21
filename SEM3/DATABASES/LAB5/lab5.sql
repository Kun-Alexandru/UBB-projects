create table Client ( --Ta
    client_id int primary key, -- aid
    cnp int unique, -- a2
    year_of_birth int -- ...
)

create table Backpack ( --Tb
	backpack_id int primary key, --bid
	capacity int, --b2
	number_of_pockets int --..
)

create table Client_Backpack(
	cb_id int primary key,
	client_id int references Client(client_id),
	backpack_id int references Backpack(backpack_id)
)


create or alter procedure insertIntoClient(@rows int) as
	while @rows > 0 begin
		insert into Client values (@rows, @rows+10, floor(rand()*70)+1950)
		set @rows = @rows - 1
	end
go

create or alter procedure insertIntoBackPack(@rows int) as
	while @rows > 0 begin
		insert into Backpack (backpack_id,capacity,number_of_pockets) values (@rows, floor(rand()*45)+5,floor(rand()*7)+3)
		set @rows = @rows - 1
	end
go

create or alter procedure insertIntoClientBackPack(@rows int) as
	declare @client_id int
	declare @backpack_id int
	while @rows > 0 begin
		insert into Client_Backpack(cb_id,client_id,backpack_id) values (@rows, (select top 1 client_id from Client order by NEWID()), (select top 1 backpack_id from Backpack order by NEWID()))
		set @rows = @rows - 1
	end
go

exec insertIntoClient 10000
exec insertIntoBackPack 3000
exec insertIntoClientBackPack 5000

Delete from Client_Backpack
Select *
from Client_Backpack

create nonclustered index indexClient on Client(year_of_birth desc)
drop index indexClient on Client

--SELECT * FROM table_name WHERE column_name = value; clustered index scan
select * from Client  order by client_id -- clustered index scan  cost: 0.03
select * from Client where client_id = 366-- clustered index seek cost: 0.003
select year_of_birth from Client order by year_of_birth -- non-custered index scan cost: 0.02
select cnp from Client where cnp = 2750 -- non-custered index seek cost: 0.03
select * from Client where cnp = 3000 -- key lookup cost: 0.006


SELECT * FROM table_name WITH (SEEK) WHERE column_name = value;

select * from Backpack where capacity = 30 -- Clustered Index Scan cost: 0.011 without index

create nonclustered index indexBackpack on Backpack(capacity) include (backpack_id, number_of_pockets)
drop index indexBackpack on Backpack

select * from Backpack where capacity = 30 -- Clustered Index Scan cost: 0.003 with index


create or alter view udView as
    select top 500 C.year_of_birth, B.capacity
    from Client_Backpack CB join Client C on CB.client_id = C.client_id join Backpack B on CB.backpack_id = B.backpack_id
    where B.capacity > 10 and C.year_of_birth < 2005

create nonclustered index indexClient on Client(year_of_birth desc)
drop index indexClient on Client
create nonclustered index indexBackpack on Backpack(capacity) include (backpack_id, number_of_pockets)
drop index indexBackpack on Backpack


select * from udView


-- 0.21 cost without indexes
-- 0.20 cost with indexes
