Create table address (address_id serial primary key, latitude decimal, longitude decimal);
Create table person (person_id serial primary key, first_name varchar(50), last_name varchar(50));
Create table clients (client_id serial primary key,
person_id int, foreign key (person_id) references person(person_id),
address_id int, foreign key (address_id) references address(address_id));
Create table orders (order_id serial primary key, order_date date,
client_id int, foreign key (client_id) references clients(client_id));
Create type product_type as enum ('BUN', 'BREAD');
Create table products (product_id serial primary key, name varchar(50), price decimal,
product_type product_type);
create table order_details(order_id int, product_id int,
foreign key (order_id) references orders(order_id),
foreign key (product_id) references products(product_id),
primary key (order_id, product_id));
Create table employees (employee_id serial primary key, job_startdate date,
person_id int, foreign key (person_id) references person(person_id));
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
Insert into products (name, price, product_type) values
('Asparagus bun', 13.6, 'BUN'),
('Tomato bun', 8.7, 'BUN'),
('Spinach bun', 10.3, 'BUN'),
('Rhubarb bun', 12.4, 'BUN'),
('Strawberry bun', 10.8, 'BUN'),
('Raspberry bun', 13.3, 'BUN'),
('Wheat bread', 12, 'BREAD'),
('Rye bread', 15, 'BREAD'),
('Spelt bread', 15, 'BREAD'),
('Wheat wholegrain bread', 17, 'BREAD'),
('Rye wholegrain bread', 18, 'BREAD'),
('Spelt wholegrain bread', 19, 'BREAD'),
('Black seed bread', 15.5,	'BREAD'),
('Sesame bread', 16.7, 'BREAD'),
('Linseed bread', 16.2, 'BREAD'),
('Black seed bun', 4.4, 'BUN'),
('Sesame bun', 3.7, 'BUN'),
('Linseed bun', 3.9, 'BUN');
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