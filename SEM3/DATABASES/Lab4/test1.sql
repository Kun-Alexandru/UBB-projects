create or alter view getProviders as
    select *
    from providers


insert into Views(Name) values ('getProviders')
insert into Tables (Name) values ('providers')
insert into Tests (Name) values ('test1')
insert into TestTables (TestID, TableID, NoOfRows, Position) values (2,2,1000,1) --providers table
insert into TestViews (TestID, ViewID) values (2,2) -- 'getProviders' view

create or alter procedure populateTableProviders (@rows int) as
    while @rows > 0 begin
        INSERT INTO providers(title,address_info) VALUES('TEST_TITLE','TEST_COUNTRY')
        set @rows = @rows-1
    end
GO


execute run 'test1'


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
