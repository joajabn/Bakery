Create table address (address_id serial primary key, latitude decimal, longitude decimal);
Create table person (person_id serial primary key, first_name varchar(50), last_name varchar(50));
Create table clients (client_id serial primary key,
person_id int, foreign key (person_id) references person(person_id) ON DELETE CASCADE,
address_id int, foreign key (address_id) references address(address_id) ON DELETE CASCADE);
Create table orders (order_id serial primary key, order_date date,
client_id int, foreign key (client_id) references clients(client_id) ON DELETE CASCADE);
Create table product_type (product_type_id serial primary key, product_type_col varchar(50));
Create table products (product_id serial primary key, name varchar(50), price decimal,
product_type_id int,
foreign key (product_type_id) references product_type(product_type_id) ON DELETE CASCADE
);
create table order_details(order_id int, product_id int,
foreign key (order_id) references orders(order_id) ON DELETE CASCADE,
foreign key (product_id) references products(product_id) ON DELETE CASCADE,
primary key (order_id, product_id));
Create table employees (employee_id serial primary key, job_startdate date,
person_id int, foreign key (person_id) references person(person_id) ON DELETE CASCADE);
Insert into address (latitude, longitude) values
(51.20, 15.80),
(50.02, 18.30),
(49.87, 17.36),
(50.98, 16.48);
Insert into person (first_name, last_name) values
('ADAM', 'KOWALSKI'),
('KAROL', 'NOWAK'),
('KAROLINA', 'RAK'),
('WERONIKA', 'KOT'),
('EWA', 'WITYNSKA'),
('JACEK', 'ROBAK'),
('JACEK', 'ROBAK'),
('WITOLD', 'LISOWSKI'),
('MARZENA', 'TRACZ');
Insert into clients (person_id, address_id) values
(1, 1),
(2, 2),
(3, 3),
(4, 4);
Insert into employees (person_id, job_startdate) values
(5, '2024-05-11'),
(6, '2023-05-11'),
(7, '2022-03-11'),
(8, '2023-08-01');
Insert into product_type (product_type_col) values
('BUN'),
('BREAD');
Insert into products (name, price, product_type_id) values
('Asparagus bun', 13.6, 1),
('Tomato bun', 8.7, 1),
('Spinach bun', 10.3, 1),
('Rhubarb bun', 12.4, 1),
('Strawberry bun', 10.8, 1),
('Raspberry bun', 13.3, 1),
('Wheat bread', 12, 2),
('Rye bread', 15, 2),
('Spelt bread', 15, 2),
('Wheat wholegrain bread', 17, 2),
('Rye wholegrain bread', 18, 2),
('Spelt wholegrain bread', 19, 2),
('Black seed bread', 15.5,	2),
('Sesame bread', 16.7, 2),
('Linseed bread', 16.2, 2),
('Black seed bun', 4.4, 1),
('Sesame bun', 3.7, 1),
('Linseed bun', 3.9, 1);
Insert into orders (client_id, order_date) values
(1, '2023-08-01'),
(2, '2024-06-01'),
(3, '2023-10-01'),
(4, '2024-05-05');
Insert into order_details (order_id, product_id) values
(1, 1),
(1, 2),
(1, 5),
(1, 6),
(1, 7),
(1, 18),
(2, 3),
(2, 9),
(2, 10),
(3, 11),
(3, 13),
(3, 14),
(4, 16);
CREATE SEQUENCE IF NOT EXISTS person_seq;
CREATE SEQUENCE IF NOT EXISTS address_seq;
CREATE SEQUENCE IF NOT EXISTS client_seq;
CREATE SEQUENCE IF NOT EXISTS employees_seq;
CREATE SEQUENCE IF NOT EXISTS orders_seq;
CREATE SEQUENCE IF NOT EXISTS products_seq;
CREATE SEQUENCE IF NOT EXISTS product_type_seq;

SELECT setval('person_seq', (SELECT MAX(p.person_id) FROM person p));
SELECT setval('address_seq', (SELECT MAX(a.address_id) FROM address a));
SELECT setval('client_seq', (SELECT MAX(c.client_id) FROM clients c));
SELECT setval('employees_seq', (SELECT MAX(e.employee_id) FROM employees e));
SELECT setval('orders_seq', (SELECT MAX(o.order_id) FROM orders o));
SELECT setval('products_seq', (SELECT MAX(p.product_id) FROM products p));
SELECT setval('product_type_seq', (SELECT MAX(p.product_type_id) FROM product_type p));