create sequence public.person_seq;
alter sequence person_seq restart with 4;
create sequence public.address_seq;
alter sequence address_seq restart with 5;
create sequence public.client_seq;
alter sequence client_seq restart with 4;
Insert into address (latitude, longitude) values
(51.20, 15.80),
(50.02, 18.30),
(49.87, 17.36),
(50.98, 16.48);
Insert into person (first_name, last_name) values
('Joanna', 'Testowa'),
('Jan', 'Kowalski'),
('Adam', 'Nowak');
Insert into clients (person_id, address_id) values
(1, 1),
(2, 2),
(3, 3);
