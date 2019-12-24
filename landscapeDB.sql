DROP DATABASE IF EXISTS landscapeDB;

CREATE DATABASE landscapeDB;

USE landscapeDB;

/* Tables */
CREATE TABLE customers (
customer_id				INT				AUTO_INCREMENT				PRIMARY KEY,
first_name				VARCHAR(25)		NOT NULL,
last_name				VARCHAR(25)		NOT NULL
);
CREATE TABLE phone_numbers (
phone_number_id			INT				AUTO_INCREMENT				PRIMARY KEY,
customer_id				INT				NOT NULL					REFERENCES customers (customer_id) ON DELETE CASCADE,
phone_number			VARCHAR(25)		NOT NULL,
description				VARCHAR(25)		
);
CREATE TABLE addresses (
address_id				INT				AUTO_INCREMENT				PRIMARY KEY,
customer_id				INT				NOT NULL					REFERENCES customers (customer_id) ON DELETE CASCADE,
address_1				VARCHAR(50)		NOT NULL,
address_2				VARCHAR(50)		DEFAULT NULL,
city					VARCHAR(50)		NOT NULL,
state					VARCHAR(25)		NOT NULL,
zip_code				VARCHAR(25)		NOT NULL
);
CREATE TABLE invoices (
invoice_id				INT				AUTO_INCREMENT				PRIMARY KEY,
customer_id				INT				NOT NULL					REFERENCES customers (customer_id) ON DELETE CASCADE,
invoice_number			VARCHAR(50)		NOT NULL,
invoice_date			VARCHAR(50)		NOT NULL,
invoice_total			VARCHAR(50)		NOT NULL,
payment_total			VARCHAR(50)		NOT NULL					DEFAULT 0,
credit_total			VARCHAR(50)		NOT NULL					DEFAULT 0,
invoice_due_date		VARCHAR(50)		NOT NULL,
last_payment_date		VARCHAR(50)	
);
CREATE TABLE items (
item_id					INT				AUTO_INCREMENT				PRIMARY KEY,
item_name				VARCHAR(25)		NOT NULL					UNIQUE,
item_description		VARCHAR(200)
);
CREATE TABLE prices (
price_id				INT				AUTO_INCREMENT				PRIMARY KEY,
customer_id				INT				NOT NULL					REFERENCES customers (customer_id) ON DELETE CASCADE,
item_id					INT				NOT NULL					REFERENCES items (item_id) ON DELETE CASCADE,
price					VARCHAR(50)		NOT NULL					DEFAULT 0
);
CREATE TABLE invoice_line_items (
invoice_id				INT				NOT NULL					REFERENCES invoices (invoice_id) ON DELETE CASCADE,
invoice_sequence		INT				NOT NULL,
price_id				INT				NOT NULL					REFERENCES prices (price_id) ON DELETE CASCADE,
line_item_quantity		VARCHAR(50)		NOT NULL,
CONSTRAINT PK_invoice_id_sequence PRIMARY KEY (invoice_id, invoice_sequence)
);
DELIMITER //

-- Insert Statements as Procedures; Returns int id (if applicable) to identify every customer
CREATE PROCEDURE createCustomer(
IN fName VARCHAR(25), 
IN lName VARCHAR(25),
OUT c_id INT
)	BEGIN
    
    INSERT INTO customers (first_name, last_name)
    VALUES (fName, lName);
    
    SELECT customer_id INTO c_id
    FROM customers
    WHERE first_name = fName AND last_name = lName;
    
END // 
CREATE PROCEDURE createNumber(
IN c_id INT,
IN num VARCHAR(25),
IN descr VARCHAR(25),
OUT n_id INT
)	BEGIN
	
    INSERT INTO phone_numbers (customer_id, phone_number, description)
    VALUES (c_id, num, descr);
    
    SELECT phone_number_id INTO n_id
    FROM phone_numbers
    WHERE c_id = customer_id AND num = phone_number;
    
END //
CREATE PROCEDURE createAddress(
IN c_id	INT,
IN add_1 VARCHAR(50),
IN add_2 VARCHAR(50),
IN town VARCHAR(50),
IN st	VARCHAR(25),
IN zip VARCHAR(25),
OUT a_id INT
)	BEGIN
	
    INSERT INTO addresses (customer_id, address_1, address_2, city, state, zip_code)
    VALUES (c_id, add_1, add_2, town, st, zip);
    
    SELECT address_id INTO a_id
    FROM addresses
    WHERE customer_id = c_id AND address_1 = add_1;
    
END //
CREATE PROCEDURE createInvoice(
IN c_id INT, 
IN i_number VARCHAR(50), 
IN i_date VARCHAR(50), 
IN i_total VARCHAR(50), 
IN p_total VARCHAR(50), 
IN c_total VARCHAR(50), 
IN i_due_date VARCHAR(50), 
IN l_payment_date VARCHAR(50),
OUT in_id INT
)	BEGIN 

    INSERT INTO invoices (customer_id, invoice_number, invoice_date, invoice_total, payment_total, credit_total, invoice_due_date, last_payment_date)
    VALUES (c_id, i_number, i_date, i_total, p_total, c_total, i_due_date, l_payment_date);
    
    SELECT invoice_id INTO in_id
    FROM invoices
    WHERE customer_id = c_id AND invoice_number = i_number;
    
END //
CREATE PROCEDURE createItem(
IN nme VARCHAR(25),
IN descr VARCHAR(200),
OUT it_id INT
)	BEGIN

	INSERT INTO items (item_name, item_description)
    VALUES (nme, descr);
    
    SELECT item_id INTO it_id
    FROM items
    WHERE nme = item_name AND descr = item_description;

END //
CREATE PROCEDURE createPrice(
IN c_id INT,
IN it_id INT,
IN cost VARCHAR(50),
OUT p_id INT
)	BEGIN
	
    INSERT INTO prices (customer_id, item_id, price)
    VALUES (c_id, it_id, cost);
    
    SELECT price_id INTO p_id
    FROM prices
    WHERE c_id = customer_id AND it_id = item_id;
    
END //
CREATE PROCEDURE createInvoiceLineItem(	
IN in_id INT,
IN p_id INT,
IN line_quantity INT,
OUT in_sequence INT
)	BEGIN
	
    INSERT INTO invoice_line_items (invoice_id, price_id, line_item_quantity)
    VALUES (in_id, p_id, line_quantity);
    
    SELECT invoice_sequence INTO in_sequence
    FROM invoice_line_items
    WHERE invoice_id = in_id AND price_id = p_id;

END //

-- Update Statements as Procedures
CREATE PROCEDURE updateCustomer(
IN c_id INT,
IN fName VARCHAR(25),
IN lName VARCHAR(25)
)	BEGIN

	UPDATE customers
    SET first_name = fName, last_name = lName
    WHERE customer_id = c_id;

END //
CREATE PROCEDURE updateNumber(
IN n_id INT,
IN num VARCHAR(25),
IN descr VARCHAR(25)
)	BEGIN

	UPDATE phone_numbers
    SET phone_number = num, description = descr
    WHERE phone_number_id = n_id;

END //
CREATE PROCEDURE updateAddress( 
IN a_id INT,
IN add_1 VARCHAR(50),
IN add_2 VARCHAR(50),
IN town VARCHAR(50),
IN st	VARCHAR(25),
IN zip VARCHAR(25)
)	BEGIN
	
    UPDATE addresses
    SET address_1 = add_1, address_2 = add_2, city = town, state = st, zip_code = zip
	WHERE address_id = a_id;

END //
CREATE PROCEDURE updateInvoice(
IN in_id INT,
IN i_number VARCHAR(50), 
IN i_date VARCHAR(50), 
IN i_total VARCHAR(50), 
IN p_total VARCHAR(50), 
IN c_total VARCHAR(50), 
IN i_due_date VARCHAR(50), 
IN l_payment_date VARCHAR(50)
)	BEGIN

	UPDATE invoices
    SET invoice_number = i_number, invoice_date = i_date, invoice_total = i_total, payment_total = p_total, credit_total = c_total, invoice_due_date = i_due_date, last_payment_date = l_payment_date
    WHERE invoice_id = in_id;

END //
CREATE PROCEDURE updateItem(
IN it_id INT,
IN nme VARCHAR(25),
IN descr VARCHAR(200)
)	BEGIN

	UPDATE items
    SET item_name = nme, item_description = descr
    WHERE item_id = it_id;

END //
CREATE PROCEDURE updatePrice(
IN c_id INT,
IN it_id INT,
IN cost VARCHAR(50)
)	BEGIN

	UPDATE prices
    SET price = cost
    WHERE customer_id = c_id AND item_id = it_id;

END //
CREATE PROCEDURE updateInvoiceLineItem(

IN in_id INT,
IN in_sequence INT,
IN line_quantity INT
)	BEGIN
	
    UPDATE invoice_line_items
    SET line_item_quantity = line_quantity
    WHERE invoice_id = in_id AND invoice_sequence = in_id;
    
END //
DELIMITER ;

