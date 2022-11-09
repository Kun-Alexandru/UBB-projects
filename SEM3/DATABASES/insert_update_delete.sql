INSERT INTO categories(title,category_description) VALUES('GPU','Graphic card')
INSERT INTO categories(title,category_description) VALUES('CPU','Central processing unit')
INSERT INTO categories(title,category_description) VALUES('Motherboard','Motherboard')
INSERT INTO categories(title,category_description) VALUES('SSD','Solid State Drive')
INSERT INTO categories(title,category_description) VALUES('HDD','Hard Disk Drive')
INSERT INTO categories(title,category_description) VALUES('PSU','Power Supply Unit')

Select * 
from categories


INSERT INTO providers(title,address_info) VALUES('Provider1','Germany')
INSERT INTO providers(title,address_info) VALUES('Provider2','USA')
INSERT INTO providers(title,address_info) VALUES('Provider3','USA')
INSERT INTO providers(title,address_info) VALUES('Provider4','Italy')
INSERT INTO providers(title,address_info) VALUES('Provider5','Botswana')
INSERT INTO providers(title,address_info) VALUES('Provider6','Czech Republic')

Select * 
from providers

INSERT INTO clients(username,password,first_name,last_name,phone_number) VALUES('user1','1234567','David','Pop','0711111111')
INSERT INTO clients(username,password,first_name,last_name,phone_number) VALUES('user2','abcdefg','Florian','Vlad','0722222222')
INSERT INTO clients(username,password,first_name,last_name,phone_number) VALUES('user3','3333333','Cantemir','Alex','0733333333')
INSERT INTO clients(username,password,first_name,last_name,phone_number) VALUES('user4','123123123','Dumitrescu','Tudor','0744444444')
INSERT INTO clients(username,password,first_name,last_name,phone_number) VALUES('user5','parola','Baciu','Vlad','0755555555')
INSERT INTO clients(username,password,first_name,last_name,phone_number) VALUES('user6','password','Popescu','Pop','0766666666')
INSERT INTO clients(username,password,first_name,last_name,phone_number) VALUES('user7','passwrd','Matei','Alexia','0777777777')
INSERT INTO clients(username,password,first_name,last_name,phone_number) VALUES('user8','parola','Tudoran','Vladut','0788888888')
INSERT INTO clients(username,password,first_name,last_name,phone_number) VALUES('user9','pass','Rusu','Vlad','0799999999')

Select *
from clients


INSERT INTO products(title,product_description,price,discount,quantity) VALUES('RTX4090','Description1',3500,0.00,5)
INSERT INTO products(title,product_description,price,discount,quantity) VALUES('RTX4060','Description2',2499,10.00,25)
INSERT INTO products(title,product_description,price,discount,quantity) VALUES('RTX3060','Description3',1799,0.00,3)
INSERT INTO products(title,product_description,price,discount,quantity) VALUES('Kingstone A400','Description4',299,0.00,10)
INSERT INTO products(title,product_description,price,discount,quantity) VALUES('Samsung 980','Description5',459,0.00,2)
INSERT INTO products(title,product_description,price,discount,quantity) VALUES('i7 9700K','Description6',999,0.00,1)
INSERT INTO products(title,product_description,price,discount,quantity) VALUES('i5 12560-H','Description7',1399,20.00,7)
INSERT INTO products(title,product_description,price,discount,quantity) VALUES('Ryzen5 4600','Description8',1499,0.00,9)
INSERT INTO products(title,product_description,price,discount,quantity) VALUES('AEROCOOL VXPLUS750','Description9',249,0.00,2)
INSERT INTO products(title,product_description,price,discount,quantity) VALUES('SEGOTEP GP900G','Description10',449,0.00,4)
INSERT INTO products(title,product_description,price,discount,quantity) VALUES('ASUS PRIME H410M-A/CSM','Description11',333,0.00,2)
INSERT INTO products(title,product_description,price,discount,quantity) VALUES('Gigabyte B550M DS3H','Description12',799,0.00,6)
INSERT INTO products(title,product_description,price,discount,quantity) VALUES('Gigabyte H410M H V3','Description12',399,0.00,4)

Select *
from products


INSERT INTO product_reviews(product_id,client_id,number_of_stars,title,review_description) VALUES (1,2,4,'Best GPU EVER','Description1')
INSERT INTO product_reviews(product_id,client_id,number_of_stars,title,review_description) VALUES (3,2,5,'I like it','Description2')
INSERT INTO product_reviews(product_id,client_id,number_of_stars,title,review_description) VALUES (5,4,1,'Its bad!','Description3')
INSERT INTO product_reviews(product_id,client_id,number_of_stars,title,review_description) VALUES (10,5,1,'Half time doesnt work!','Description4')
INSERT INTO product_reviews(product_id,client_id,number_of_stars,title,review_description) VALUES (3,2,1,'Not that good :(','Description5')
INSERT INTO product_reviews(product_id,client_id,number_of_stars,title,review_description) VALUES (5,6,3,'Works','Description7')

---Violates onstraint of starrs >=1 and <=5
INSERT INTO product_reviews(product_id,client_id,number_of_stars,title,review_description) VALUES (4,4,6,'The best!','Description5')
---Violate referential integrity constrains (client with id 20 is not in the database)
INSERT INTO product_reviews(product_id,client_id,number_of_stars,title,review_description) VALUES (2,20,4,'The best!','Description6')

Select *
from product_reviews

INSERT INTO products_categories(product_id,category_id) VALUES (1,1)
INSERT INTO products_categories(product_id,category_id) VALUES (3,1)
INSERT INTO products_categories(product_id,category_id) VALUES (4,1)
INSERT INTO products_categories(product_id,category_id) VALUES (5,4)
INSERT INTO products_categories(product_id,category_id) VALUES (6,4)
INSERT INTO products_categories(product_id,category_id) VALUES (7,2)
INSERT INTO products_categories(product_id,category_id) VALUES (8,2)
INSERT INTO products_categories(product_id,category_id) VALUES (9,2)
INSERT INTO products_categories(product_id,category_id) VALUES (10,1)
INSERT INTO products_categories(product_id,category_id) VALUES (11,6)
INSERT INTO products_categories(product_id,category_id) VALUES (12,3)
INSERT INTO products_categories(product_id,category_id) VALUES (13,3)
INSERT INTO products_categories(product_id,category_id) VALUES (14,3)

Select *
from products_categories


INSERT INTO products_providers(product_id,id_provider,quantity) VALUES (1,3,50)
INSERT INTO products_providers(product_id,id_provider,quantity) VALUES (1,2,25)
INSERT INTO products_providers(product_id,id_provider,quantity) VALUES (3,2,14)
INSERT INTO products_providers(product_id,id_provider,quantity) VALUES (3,1,16)
INSERT INTO products_providers(product_id,id_provider,quantity) VALUES (5,6,20)
INSERT INTO products_providers(product_id,id_provider,quantity) VALUES (4,5,10)
INSERT INTO products_providers(product_id,id_provider,quantity) VALUES (8,6,15)

Select *
from products_providers

INSERT INTO client_payment(type_of_payment,client_id) VALUES ('Visa',1)
INSERT INTO client_payment(type_of_payment,client_id) VALUES ('Visa',2)
INSERT INTO client_payment(type_of_payment,client_id) VALUES ('Visa',3)
INSERT INTO client_payment(type_of_payment,client_id) VALUES ('Visa',4)
INSERT INTO client_payment(type_of_payment,client_id) VALUES ('Visa',5)
INSERT INTO client_payment(type_of_payment,client_id) VALUES ('Visa',6)
INSERT INTO client_payment(type_of_payment,client_id) VALUES ('Visa',7)
INSERT INTO client_payment(type_of_payment,client_id) VALUES ('Visa',8)
INSERT INTO client_payment(type_of_payment,client_id) VALUES ('Mastercard',3)
INSERT INTO client_payment(type_of_payment,client_id) VALUES ('Mastercard',5)
INSERT INTO client_payment(type_of_payment,client_id) VALUES ('Mastercard',8)

Select *
from client_payment

INSERT INTO shipping_addresses(client_id,city,country,address_info) VALUES (1,'Cluj','Romania','Description1')
INSERT INTO shipping_addresses(client_id,city,country,address_info) VALUES (2,'Iasi','Romania','Description2')
INSERT INTO shipping_addresses(client_id,city,country,address_info) VALUES (3,'Bucuresti','Romania','Description3')
INSERT INTO shipping_addresses(client_id,city,country,address_info) VALUES (4,'Slobozia','Romania','Description4')
INSERT INTO shipping_addresses(client_id,city,country,address_info) VALUES (5,'Vaslui','Romania','Description5')
INSERT INTO shipping_addresses(client_id,city,country,address_info) VALUES (6,'Ploiesti','Romania','Description6')
INSERT INTO shipping_addresses(client_id,city,country,address_info) VALUES (7,'Timisoara','Romania','Description7')
INSERT INTO shipping_addresses(client_id,city,country,address_info) VALUES (8,'Arad','Romania','Description8')
INSERT INTO shipping_addresses(client_id,city,country,address_info) VALUES (2,'Berlin','Germania','Description9')
INSERT INTO shipping_addresses(client_id,city,country,address_info) VALUES (2,'Palermo','Italy','Description10')
INSERT INTO shipping_addresses(client_id,city,country,address_info) VALUES (5,'Milano','Italy','Description11')
INSERT INTO shipping_addresses(client_id,city,country,address_info) VALUES (5,'Bologna','Italy','Description12')

Select *
from shipping_addresses


INSERT INTO wishlists (client_id,description_wishlist,date_of_creation) VALUES (2,'Description1','20221121 10:35:00 AM')
INSERT INTO wishlists (client_id,description_wishlist,date_of_creation) VALUES (2,'Description2','20221120 08:00:00 PM')
INSERT INTO wishlists (client_id,description_wishlist,date_of_creation) VALUES (2,'Description3','20210101 7:34:23 AM')
INSERT INTO wishlists (client_id,description_wishlist,date_of_creation) VALUES (2,'Description4','20221017 11:11:11 PM')

Select *
from wishlists

INSERT INTO wishlists_products(wishlist_id,product_id,quantity) VALUES (1,3,1)
INSERT INTO wishlists_products(wishlist_id,product_id,quantity) VALUES (1,6,2)
INSERT INTO wishlists_products(wishlist_id,product_id,quantity) VALUES (1,7,1)
INSERT INTO wishlists_products(wishlist_id,product_id,quantity) VALUES (1,10,1)
INSERT INTO wishlists_products(wishlist_id,product_id,quantity) VALUES (1,12,1)
INSERT INTO wishlists_products(wishlist_id,product_id,quantity) VALUES (2,1,1)
INSERT INTO wishlists_products(wishlist_id,product_id,quantity) VALUES (3,5,10)
INSERT INTO wishlists_products(wishlist_id,product_id,quantity) VALUES (4,1,1)
INSERT INTO wishlists_products(wishlist_id,product_id,quantity) VALUES (4,6,4)
INSERT INTO wishlists_products(wishlist_id,product_id,quantity) VALUES (4,8,1)
INSERT INTO wishlists_products(wishlist_id,product_id,quantity) VALUES (4,12,1)

Select *
from wishlists_products

INSERT INTO orders(shipping_id,date_of_placement,payment_id) VALUES (1,'20221121 10:35:00 AM',1)
INSERT INTO orders(shipping_id,date_of_placement,payment_id) VALUES (9,'20210101 7:34:23 AM',2)
INSERT INTO orders(shipping_id,date_of_placement,payment_id) VALUES (10,'20210101 7:34:23 AM',2)
INSERT INTO orders(shipping_id,date_of_placement,payment_id) VALUES (3,'20220202 9:34:23 AM',3)

Select *
from orders

INSERT INTO orders_products(product_id,order_id,quantity) VALUES (1,1,1)
INSERT INTO orders_products(product_id,order_id,quantity) VALUES (5,1,3)
INSERT INTO orders_products(product_id,order_id,quantity) VALUES (8,1,1)
INSERT INTO orders_products(product_id,order_id,quantity) VALUES (10,1,1)
INSERT INTO orders_products(product_id,order_id,quantity) VALUES (12,1,1)
INSERT INTO orders_products(product_id,order_id,quantity) VALUES (6,2,10)
INSERT INTO orders_products(product_id,order_id,quantity) VALUES (5,2,10)
INSERT INTO orders_products(product_id,order_id,quantity) VALUES (3,3,1)
INSERT INTO orders_products(product_id,order_id,quantity) VALUES (13,3,1)

Select *
from orders_products


---UPDATE---

Select *
from products

---Increase the quantity of products which have in title 980 by 2
UPDATE products
Set quantity = quantity + 2
WHERE title LIKE '%980%'

---Sets discount to 20% to products which cost over 3k
UPDATE products
SET discount = 20.0
WHERE price >= 3000

Select * 
from clients

---Sets new password to users which have the username 'user1' and last name 'Pop'
UPDATE clients
SET password = 'newpass'
WHERE username LIKE 'user1' AND last_name LIKE 'Pop'

---Sets the name of clients which have the phone numbers 0711... and 0733.. to 'TEST'
UPDATE clients
SET first_name = 'TEST'
WHERE phone_number IN ('0711111111','0733333333')

Select *
from product_reviews

---Updates the rating of reviews with 1 or 2 stars to 5 stars  
UPDATE product_reviews
SET number_of_stars = 5
WHERE number_of_stars BETWEEN 1 AND 2

---DELETE


Select *
from shipping_addresses

INSERT INTO shipping_addresses(client_id,city,country) VALUES (6,'TEST','TEST')
INSERT INTO shipping_addresses(client_id,city,country) VALUES (7,'TEST','TEST')

---Deletes all the addresses which have as address_info NULL
DELETE from shipping_addresses
WHERE address_info IS NULL

Select *
from categories

INSERT INTO categories(title,category_description) VALUES('TEST TITLE','TEST')

---Deletes all the categories which have as description 'TEST' '-' or 'idk'
DELETE from categories
WHERE category_description LIKE 'TEST' OR category_description LIKE '-' OR category_description LIKE 'idk'
