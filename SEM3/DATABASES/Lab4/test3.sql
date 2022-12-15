create or alter view getAllClientTypeOfPayments as
    select CP.type_of_payment
    from client_payment CP
        inner join TypeOfCard TC on TC.namee = TC.namee
    GROUP BY CP.type_of_payment

insert into Views(Name) values ('getAllClientTypeOfPayments')
insert into Tables (Name) values ('TypeOfCard')
insert into Tables (Name) values ('client_payment')
insert into Views (Name) values ('getAllClientTypeOfPayments')
insert into Tests (Name) values ('test3')
insert into TestTables (TestID, TableID, NoOfRows, Position) values (4,3,1000,1) --clients table
insert into TestTables (TestID, TableID, NoOfRows, Position) values (4,5,3000,2) --typeofcard table
insert into TestTables (TestID, TableID, NoOfRows, Position) values (4,6,1000,3) --client_payment table
insert into TestViews (TestID, ViewID) values (4,5) -- 'getAllClientTypeOfPayments' view


Select *
from TestTables

create or alter procedure populateTableClients (@rows int) as
    while @rows > 0 begin
		INSERT INTO clients(username,password,first_name,last_name,phone_number) VALUES
		('USER_TEST','password_test','Name_test','Surname_test','0711111111')
        set @rows = @rows - 1
    end

create or alter procedure populateTableTypeOfCard (@rows int) as
    while @rows > 0 begin
		INSERT INTO TypeOfCard(namee,descrip,father_company) VALUES
		(CONCAT('TEST_NAME', @rows),'DESCRIPTION_TEST',(select top 1 namee from CardCompanies order by newid()))
        set @rows = @rows - 1
    end

create or alter procedure populateTableClient_Payment (@rows int) as
	while @rows > 0 begin
		declare @name varchar(100)
		declare @father_comp varchar(100)

		Select TOP 1 @name = namee, @father_comp = father_company
		from TypeOfCard
		order by newid()

		INSERT INTO client_payment(type_of_payment,client_id,namee,father_company)
		VALUES
		(CONCAT('CARD_TEST',floor(rand()*500)+1),(select top 1 client_id from clients order by newid()),@name,@father_comp)
		Set @rows = @rows - 1
	end

execute run 'test3'

Delete from wishlists
Delete from client_payment
Delete from TypeOfCard

Select *
from client_payment

Select *
from TestRunTables

select *
from TestRunViews

Select *
from client_payment


