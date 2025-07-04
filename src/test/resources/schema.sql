Create table IF NOT EXISTS address (address_id serial primary key, latitude decimal, longitude decimal);
Create table IF NOT EXISTS person (person_id serial primary key, first_name varchar(50), last_name varchar(50));
Create table IF NOT EXISTS clients (client_id serial primary key,
person_id int, foreign key (person_id) references person(person_id) ON DELETE CASCADE,
address_id int, foreign key (address_id) references address(address_id) ON DELETE CASCADE);
Create table IF NOT EXISTS orders (order_id serial primary key, order_date date,
client_id int, foreign key (client_id) references clients(client_id) ON DELETE CASCADE);
Create type IF NOT EXISTS product_type as enum ('BUN', 'BREAD');
Create table IF NOT EXISTS products (product_id integer auto_increment primary key, name varchar(50), price decimal,
product_type product_type);
create table IF NOT EXISTS order_details(order_id int, product_id int,
foreign key (order_id) references orders(order_id) ON DELETE CASCADE,
foreign key (product_id) references products(product_id) ON DELETE CASCADE,
primary key (order_id, product_id));
Create table IF NOT EXISTS employees (employee_id serial primary key, job_startdate date,
person_id int, foreign key (person_id) references person(person_id) ON DELETE CASCADE);