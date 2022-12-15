Select *
from providers

create or alter view getWishlistsFromClients as
    select wishlist_id,description_wishlist,date_of_creation
        from wishlists WL inner join clients CL on CL.client_id = WL.client_id

Select *
from TestViews

insert into Views(Name) values ('getWishlistsFromClients')
insert into Tables (Name) values ('clients')
insert into Tables (Name) values ('wishlists')
insert into Tests (Name) values ('test2')
insert into TestTables (TestID, TableID, NoOfRows, Position) values (3,3,1500,1) --clients table
insert into TestTables (TestID, TableID, NoOfRows, Position) values (3,4,5000,2) --wishlists table
insert into TestViews (TestID, ViewID) values (3,3) -- 'getWishlistsFromClients' view


create or alter procedure populateTableClients (@rows int) as
    while @rows > 0 begin
		INSERT INTO clients(username,password,first_name,last_name,phone_number) VALUES
		('USER_TEST','password_test','Name_test','Surname_test','0711111111')
        set @rows = @rows - 1
    end

create or alter  procedure populateTableWishlists (@rows int) as
    while @rows > 0 begin
		INSERT INTO wishlists (client_id,description_wishlist,date_of_creation) VALUES 
		((select top 1 client_id from	clients order by newid())
		,'Description_test',
		'20221121 10:35:00 AM')
        set @rows = @rows - 1
    end

Delete from product_reviews

execute run 'test2'

Select *
from Tables

Select *
from TestRunTables

Select *
from TestRuns

Select *
from TestRunViews

Select *
from Views

Select *
from TestViews


Select *
from Tests


Select *
from TestTables


Select *
from categories

INSERT INTO categories(category_id,title,category_description) VALUES(10,'brr','brr')
