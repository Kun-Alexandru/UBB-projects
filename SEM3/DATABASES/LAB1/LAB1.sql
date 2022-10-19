CREATE TABLE clients(
    client_id INT PRIMARY KEY IDENTITY,
    username VARCHAR(50),
    password VARCHAR(50),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    phone_number VARCHAR(50)
);

CREATE TABLE shipping_addresses(
    shipping_id INT PRIMARY KEY IDENTITY,
    client_id INT FOREIGN KEY REFERENCES Clients(client_id),
    city VARCHAR(50),
    country VARCHAR(50),
    address_info VARCHAR(50)
);

CREATE TABLE orders(
    order_id INT PRIMARY KEY IDENTITY,
    shipping_id INT FOREIGN KEY REFERENCES Shipping_addresses(shipping_id),
    date_of_placement DATETIME
);

CREATE TABLE products(
    product_id INT PRIMARY KEY IDENTITY,
    title VARCHAR(50),
    product_description VARCHAR(50),
    price INT,
    discount FLOAT,
    quantity INT
);

CREATE TABLE providers(
    id_provider INT PRIMARY KEY IDENTITY,
    title VARCHAR(50),
    address_info VARCHAR(50)
);

CREATE TABLE products_providers(
    id INT PRIMARY KEY IDENTITY,
    id_provider INT FOREIGN KEY REFERENCES providers(id_provider),
    product_id INT FOREIGN KEY REFERENCES products(product_id),
    quantity INT
    UNIQUE(id_provider, product_id)
);

CREATE TABLE categories(
    category_id INT PRIMARY KEY IDENTITY,
    title VARCHAR(50),
    category_description VARCHAR(50)
);

CREATE TABLE products_categories(
    id INT PRIMARY KEY IDENTITY,
    product_id INT FOREIGN KEY REFERENCES products(product_id),
    category_id INT FOREIGN KEY REFERENCES categories(category_id)
);

CREATE TABLE product_reviews(
    review_id INT PRIMARY KEY IDENTITY,
    product_id INT FOREIGN KEY REFERENCES products(product_id),
    client_id INT FOREIGN KEY REFERENCES clients(client_id),
	number_of_stars INT,
    title VARCHAR(50),
    review_description VARCHAR(50)
	CHECK(number_of_stars) <=5 AND CHECK (number_of_stars) >= 1
);

CREATE TABLE orders_products(
    id INT PRIMARY KEY IDENTITY,
    product_id INT FOREIGN KEY REFERENCES products(product_id),
    order_id INT FOREIGN KEY REFERENCES orders(order_id),
    quantity INT
);

CREATE TABLE client_payment(
    payment_id INT PRIMARY KEY IDENTITY,
    type_of_payment VARCHAR(50),
    client_id INT FOREIGN KEY REFERENCES clients(client_id),
);

CREATE TABLE wishlists(
    wishlist_id INT PRIMARY KEY IDENTITY,
    client_id INT FOREIGN KEY REFERENCES clients(client_id),
    description_wishlist VARCHAR(50),
    date_of_creation DATETIME
);

CREATE TABLE wishlists_products(
    id INT PRIMARY KEY IDENTITY,
    wishlist_id INT FOREIGN KEY REFERENCES wishlists(wishlist_id),
    product_id INT FOREIGN KEY REFERENCES products(product_id),
    quantity INT
);
