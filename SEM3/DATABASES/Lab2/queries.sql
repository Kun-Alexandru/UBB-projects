---a.2 queries with the union operation; use UNION [ALL] and OR;

---show all the different countries that appear in the database (shipping address country and provider country)
---distinct query
Select distinct country from shipping_addresses
UNION
Select distinct address_info from providers

---show all shipping addresses from Italy or Germany

Select city,country,address_info from shipping_addresses
WHERE (country LIKE 'Germania' or country like 'Italy')

---b. 2 queries with the intersection operation; use INTERSECT and IN;

---Display the clients which have an active review and have an address in Romania

Select client_id
from product_reviews
INTERSECT
Select client_id
from shipping_addresses
WHERE country LIKE 'Romania'


---Display the products which are in atleast an order

Select title
FROM products
WHERE product_id IN(Select product_id from orders_products)


--- c. 2 queries with the difference operation; use EXCEPT and NOT IN;

---show the id of products which don't have any reviews
---order by
Select product_id from products
EXCEPT
Select product_id from product_reviews
ORDER BY product_id DESC

---show the name of clients which don't have a review or a wishlist
---order by

Select first_name,last_name
FROM clients
WHERE client_id NOT IN( 
				Select client_id
				from product_reviews
				)
				AND 
				client_id NOT IN(
				Select client_id
				from wishlists)
ORDER BY first_name,last_name


---d.4 queries with INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN (one query per operator);
---one query will join at least 3 tables, while another one will 
---join at least two many-to-many relationships;

---Display title of products which appear on a wishlist and on an order; three tables
---distinct query
Select DISTINCT TITLE from products
INNER JOIN orders_products ON products.product_id = orders_products.product_id
INNER JOIN wishlists_products ON products.product_id = wishlists_products.product_id

---Display information about shipping addresses which appear on an order and the corresponding order, but also about the products which don't appear in any order
---i used left join because i also want to see all the rows from 1st table which don't have anything
---asociated with them in the 2nd table, in this case i want to also see all the shipping addresses
---even if they don't have a matching id in the orders
Select * from shipping_addresses 
LEFT JOIN orders ON shipping_addresses.shipping_id = orders.shipping_id

---Display information about reviews and the product that it is reviewing, but also about all the products which don't have a review
---i used right join because i also want to see all the rows from 2nd table which don't have anything
---asociated with them in the 1st table, in this case i want to also see all the products 
---even if they don't have a matching id in the reviews
Select * from product_reviews
RIGHT JOIN products ON product_reviews.product_id = products.product_id


---Display all the products alongside their category,reeviews and providers

Select * from products
FULL JOIN products_categories ON products.product_id = products_categories.product_id
FULL JOIN product_reviews ON products.product_id = product_reviews.product_id
FULL JOIN products_providers ON products.product_id = products_providers.product_id

--- e. 2 queries with the IN operator and a subquery in the WHERE clause; 
---in at least one case, the subquery should include a subquery in its own WHERE clause;


---	Select shipping addresses which are used for an order
Select *
from shipping_addresses
WHERE shipping_addresses.shipping_id IN (Select shipping_id from orders)

---Display the name of the products which are in category 'GPU'

Select title 
from products
WHERE products.product_id IN (Select product_id from products_categories 
								WHERE products_categories.category_id IN(Select category_id from categories WHERE (categories.title LIKE 'GPU')))

---f.2 queries with the EXISTS operator and a subquery in the WHERE clause;

---Select all shipping addresses which have atleast 1 order and are from Romania

Select * from orders

SELECT *
FROM shipping_addresses
WHERE EXISTS 
  (SELECT *
   FROM orders
   WHERE shipping_addresses.shipping_id = orders.shipping_id
   )
   AND country like 'Romania'

---Select all categories which have atleast 1 product in that category
---order by
Select *
from products_categories
ORDER BY category_id

SELECT *
FROM categories
WHERE EXISTS 
  (SELECT *
   FROM products_categories
   WHERE categories.category_id = products_categories.category_id);


---g. 2 queries with a subquery in the FROM clause;  


---Select top 3 most expensive products which cost less than 500
---top
Select TOP 3 Q.title
from (Select * from products P WHERE p.	price < 500) Q
ORDER BY Q.price DESC


---Total number of stars given by client with id 2 in the reviews

Select AVG(Q.number_of_stars) from
(Select number_of_stars from product_reviews WHERE client_id = 2) Q

---Display the number of different countries from shipping addresses
---distinct query
Select Count(*) from
(Select distinct country from shipping_addresses) Q

---h.4 queries with the GROUP BY clause, 3 of which also contain the HAVING clause; 2 of the latter will also have a subquery 
---in the HAVING clause; use the aggregation operators: COUNT, SUM, AVG, MIN, MAX;

---Display the product ids of products which have on average a review score more than 3
---arithmetic expression in select
Select SUM(number_of_stars)/Count(*) as 'avgRatingReceived', product_id
from product_reviews
GROUP BY product_id
HAVING SUM(number_of_stars)/Count(*) > 3 


---Display the total quantity of each product that providers have on stock
---arithmetic expression in select
Select SUM(quantity) as 'totalQuantity', product_id, SUM(quantity)*(Select price from products where products.product_id = products_providers.product_id) as 'ValueOfProdutsOnProviderStock'
from products_providers
GROUP BY product_id


---Display the number of products which are in each category with an even id
Select Count(*), category_id
from products_categories
GROUP BY category_id
HAVING category_id IN (Select category_id from categories where category_id%2 =0)

---Display all the countries from shipping addresses which have less than 50% apperance
---arithmetic expression in select
Select *
from shipping_addresses

Select COUNT(*),country
from shipping_addresses
GROUP by country
HAVING COUNT(*) < (Select Count(*)/(2) from shipping_addresses )

--- 4 queries using ANY and ALL to introduce a subquery in the WHERE clause (2 queries per operator);         
--- rewrite 2 of them with aggregation operators, and the other 2 with IN / [NOT] IN.

---Display all the products which have more than half quantity on stock than all products from "CPU" category
---arithmetic expression in select
Select title,quantity,quantity*price as 'TotalCostOfProdInStock'
from products P1
WHERE quantity > ALL(
			Select quantity
			from products P2
			where P2.product_id IN (Select product_id from products_categories WHERE category_id = 2))



Select title,quantity,quantity*price as 'TotalCostOfProdInStock'
from products P1
WHERE quantity > (
			Select MAX(quantity)
			from products P2
			where P2.product_id IN (Select product_id from products_categories WHERE category_id = 2))


---Display all the product ids from reviews which have more stars than the review with lowest amount of starts gives by user with id 6
---distinct query
Select distinct product_id 
from product_reviews
WHERE number_of_stars > ANY(Select number_of_stars from product_reviews where client_id = 6)

Select distinct product_id 
from product_reviews
WHERE number_of_stars > (Select min(number_of_stars) from product_reviews where client_id = 6)

---Select all the orders which contain the product with id 5 in them
Select *
from orders
WHERE order_id = ANY(Select order_id from orders_products where product_id = 5)

Select *
from orders
WHERE order_id IN(Select order_id from orders_products where product_id = 5)

---Select all the client payment which are not the most popular type of payment
---order by
---top
Select * 
from client_payment
WHERE type_of_payment <> ALL(Select TOP 1 type_of_payment
							from client_payment
							GROUP by type_of_payment
							ORDER BY COUNT(*) DESC)


Select * 
from client_payment
WHERE type_of_payment NOT IN(Select TOP 1 type_of_payment
							from client_payment
							GROUP by type_of_payment
							ORDER BY COUNT(*) DESC)
