use PC_componenst_store
GO

--Lab 3. Altering the Database

--assigned: week 6; due: week 8
--Sometimes, after you design a database, you need to change its structure. Unfortunately, 
--changes aren’t correct every time, so they must be reverted. 
--Your task is to create a versioning mechanism that allows you to easily switch between database versions.

--Write SQL scripts that:
--a. modify the type of a column; 
--b. add / remove a column; 
--c. add / remove a DEFAULT constraint; 
--d. add / remove a primary key; 
--e. add / remove a candidate key; 
--f. add / remove a foreign key;
--g. create / drop a table. 

--For each of the scripts above, write another one that reverts the operation. Place each script in a stored procedure. Use a simple, intuitive naming convention.

--Create a new table that holds the current version of the database schema. Simplifying assumption: the version is an integer number.

--Write a stored procedure that receives as a parameter a version number and brings the database to that version.


--a. modify the type of a column;

CREATE PROCEDURE SetQuantityProductProvidersFloat
AS
	ALTER TABLE products_providers 
	ALTER COLUMN quantity float
GO

CREATE PROCEDURE SetQuantityProductProvidersInt 
AS
	ALTER TABLE products_providers
	ALTER COLUMN quantity int
GO

SELECT * FROM INFORMATION_SCHEMA.COLUMNS

EXEC setQuantityProductProvidersFloat
Select *
from products_providers
EXEC SetQuantityProductProvidersInt


--b. add / remove a column;

CREATE PROCEDURE AddDescriptionToClientPayment
AS
	ALTER TABLE client_payment
	ADD descrip VARCHAR(50)
GO

CREATE PROCEDURE RemoveDescriptionFromClientPayment
AS
	ALTER TABLE client_payment
	DROP COLUMN descrip
GO

EXEC AddDescriptionToClientPayment
Select *
from client_payment
EXEC RemoveDescriptionFromClientPayment


--c. add / remove a DEFAULT constraint;

CREATE PROCEDURE addDefaultQuantityInOrderProducts
AS
	ALTER TABLE orders_products
	ADD CONSTRAINT DefaultQuantityConstraint DEFAULT (1) FOR quantity
GO

CREATE PROCEDURE dropDefaultQuantityInOrderProdcuts
AS
	ALTER TABLE orders_products
	Drop Constraint DefaultQuantityConstraint
GO

EXEC addDefaultQuantityInOrderProducts
INSERT INTO orders_products(product_id,order_id) VALUES (7,3)
Select *
from orders_products
DELETE FROM orders_products WHERE id = 12
EXEC dropDefaultQuantityInOrderProdcuts



--g. create / drop a table.

CREATE PROCEDURE addTypeOfCard
AS
	CREATE TABLE TypeOfCard(
		namee varchar(100) not null,
		descrip varchar (100) not null,
		father_company varchar (100) not null,
		constraint TypeOfCard_PRIMARY_KEY primary key (namee)
							)
GO

CREATE PROCEDURE dropTypeOfCard
AS
	drop table TypeOfCard
GO

EXEC addTypeOfCard
Select *
from TypeOfCard
EXEC dropTypeOfCard

--d. add / remove a primary key;

CREATE PROCEDURE addPrimaryKeyTypeOfCard
AS	
	ALTER TABLE TypeOfCard
	DROP CONSTRAINT TypeOfCard_PRIMARY_KEY
	ALTER TABLE TypeOfCard
	ADD CONSTRAINT TypeOfCard_PRIMARY_KEY PRIMARY KEY (namee, father_company)
GO

CREATE PROCEDURE removePrimaryKeyTypeOfCard
AS
	ALTER TABLE TypeOfCard
	DROP CONSTRAINT TypeOfCard_PRIMARY_KEY
	ALTER TABLE TypeOfCard
	ADD CONSTRAINT TypeOfCard_PRIMARY_KEY PRIMARY KEY (namee)
GO

EXEC addPrimaryKeyTypeOfCard
EXEC removePrimaryKeyTypeOfCard


--e. add / remove a candidate key;

CREATE PROCEDURE addCandidateKeyCard 
AS
	ALTER TABLE TypeOfCard
	ADD CONSTRAINT TypeOfCard_CANDIDATE_KEY UNIQUE (namee, father_company)
GO

CREATE PROCEDURE removeCnaddidateKeyCard
AS
	ALTER TABLE TypeOfCard
	DROP CONSTRAINT TypeOfCard_CANDIDATE_KEY
GO


EXEC addCandidateKeyCard
EXEC removeCnaddidateKeyCard

--f. add / remove a foreign key;


CREATE PROCEDURE removeForeignKeyOrders
AS
	ALTER TABLE orders
	DROP CONSTRAINT FK_orders_client_payment
GO

CREATE PROCEDURE addForeignKeyOrders
AS 
	ALTER TABLE orders
	ADD CONSTRAINT FK_orders_client_payment FOREIGN KEY (payment_id) REFERENCES client_payment(payment_id)
GO

EXEC removeForeignKeyOrders
EXEC addForeignKeyOrders


CREATE TABLE versionTable (
    version int
)

INSERT INTO versionTable VALUES (1)

Select *
from versionTable

CREATE TABLE proceduresTable (
		oldVersion int,
		newVersion int,
		PRIMARY KEY (oldVersion, newVersion),
		procedureName varchar(100)
							 )

insert into proceduresTable values (1, 2, 'SetQuantityProductProvidersFloat')
insert into proceduresTable values (2, 1, 'SetQuantityProductProvidersInt')
insert into proceduresTable values (2, 3, 'AddDescriptionToClientPayment')
insert into proceduresTable values (3, 2, 'RemoveDescriptionFromClientPayment')
insert into proceduresTable values (3, 4, 'addDefaultQuantityInOrderProducts')
insert into proceduresTable values (4, 3, 'dropDefaultQuantityInOrderProdcuts')
insert into proceduresTable values (4, 5, 'addTypeOfCard')
insert into proceduresTable values (5, 4, 'dropTypeOfCard')
insert into proceduresTable values (5, 6, 'addPrimaryKeyTypeOfCard')
insert into proceduresTable values (6, 5, 'removePrimaryKeyTypeOfCard')
insert into proceduresTable values (6, 7, 'addCandidateKeyCard')
insert into proceduresTable values (7, 6, 'removeCnaddidateKeyCard')
insert into proceduresTable values (7, 8, 'addForeignKeyOrders')
insert into proceduresTable values (8, 7, 'removeForeignKeyOrders')

create procedure goToVersion(@newVersion int) as
    declare @currentVersion int
    declare @proc varchar(max)
    select @currentVersion=version from versionTable

    IF @newVersion > 8 OR @newVersion <= 0
	BEGIN
        RAISERROR ('Invalid version', 10, 1)
	END
	ELSE 
	BEGIN
		WHILE @currentVersion > @newVersion 
		BEGIN
			SELECT @proc=procedureName FROM proceduresTable WHERE oldVersion=@currentVersion and newVersion=@currentVersion-1
			PRINT 'The version was ' + convert(varchar(10), @currentVersion) + ' , new version is ' + convert(varchar(10), @currentVersion-1) + ' applied ' + @proc
			EXEC (@proc)
			SET @currentVersion=@currentVersion-1
		END

		WHILE @currentVersion < @newVersion 
		BEGIN
			SELECT @proc=procedureName FROM proceduresTable WHERE oldVersion=@currentVersion and newVersion=@currentVersion+1
			PRINT 'The version was ' + convert(varchar(10), @currentVersion) + ' , new version is ' + convert(varchar(10), @currentVersion+1) + ' applied ' + @proc
			EXEC (@proc)
			SET @currentVersion=@currentVersion+1
		END
		UPDATE versionTable SET version = @newVersion
	END

DROP PROCEDURE goToVersion

Select *
from versionTable

UPDATE versionTable SET version = 1

Select *
from TypeOfCard
execute goToVersion 1

select *
from TypeOfCard

select descrip
from client_payment

EXEC SetQuantityProductProvidersInt
EXEC RemoveDescriptionFromClientPayment
EXEC dropDefaultQuantityInOrderProdcuts
EXEC dropTypeOfCard
EXEC removePrimaryKeyTypeOfCard
EXEC removeCnaddidateKeyCard
EXEC removeForeignKeyOrders

Select *
from client_payment
