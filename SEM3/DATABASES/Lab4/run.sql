create or alter procedure run (@testName varchar(50)) as
    declare @procedure varchar(100)
    declare @testStartTime datetime2
    declare @startTime datetime2
    declare @endTime datetime2
    declare @tableName varchar(50)
    declare @numRowsToInsert int
    declare @orderOfExecution int
    declare @view varchar(50)
    declare @testId int
    select @testId=TestID from Tests where Name=@testName
    declare @testRunId int
    set @testRunId = (select top 1(TestRunID)+1 from TestRuns order by TestRunID desc)
    if @testRunId is null
        set @testRunId = 0

    declare tableCursorInsert cursor local scroll for
        select T1.Name, T2.NoOfRows, T2.Position
        from Tables T1 join TestTables T2 on T1.TableID = T2.TableID
        where T2.TestID = @testId
        order by T2.Position asc

	declare tableCursorDelete cursor local scroll for
        select T1.Name, T2.NoOfRows, T2.Position
        from Tables T1 join TestTables T2 on T1.TableID = T2.TableID
        where T2.TestID = @testId
        order by T2.Position desc

    declare viewCursor cursor for
        select V.Name
        from Views V join TestViews TV on V.ViewID = TV.ViewID
        where TV.TestID = @testId

	IF (@testName='test2')
	BEGIN
		exec ('Delete from client_payment')
		exec ('Delete from TypeOfCard')
	END

	IF (@testName='test3')
	BEGIN
		exec ('Delete from wishlists')
	END

    set @testStartTime = sysdatetime()
    open tableCursorDelete
    fetch from tableCursorDelete into @tableName, @numRowsToInsert, @orderOfExecution
    while @@FETCH_STATUS = 0 begin
        exec ('DELETE FROM '+ @tableName)
        fetch next from tableCursorDelete into @tableName, @numRowsToInsert, @orderOfExecution
    end
    close tableCursorDelete
	deallocate tableCursorDelete

    open tableCursorInsert
    SET IDENTITY_INSERT TestRuns ON
    insert into TestRuns (TestRunID, Description, StartAt)values (@testRunId, 'Test : ' + @testName, @testStartTime)
    SET IDENTITY_INSERT TestRuns OFF
    fetch from tableCursorInsert into @tableName, @numRowsToInsert, @orderOfExecution
    while @@FETCH_STATUS = 0 begin
        set @procedure = 'populateTable' + @tableName
        set @startTime = sysdatetime()
        exec @procedure @numRowsToInsert
        set @endTime = sysdatetime()
        insert into TestRunTables (TestRunID, TableId, StartAt, EndAt) values (@testRunId, (select TableID from Tables where Name=@tableName), @startTime, @endTime)
        fetch next from tableCursorInsert into @tableName, @numRowsToInsert, @orderOfExecution
    end
    close tableCursorInsert
    deallocate tableCursorInsert

    open viewCursor
    fetch viewCursor into @view
    while @@FETCH_STATUS = 0 begin
        set @procedure = 'SELECT * FROM ' + @view
        set @startTime = sysdatetime()
        exec (@procedure)
        set @endTime = sysdatetime()
        insert into TestRunViews (TestRunID, ViewID, StartAt, EndAt) values (@testRunId, (select ViewID from Views where Name=@view), @startTime, @endTime)
        fetch next from viewCursor into @view
    end
    close viewCursor
    deallocate viewCursor

    update TestRuns
    set EndAt=sysdatetime()
    where TestRunID = @testRunId
GO
