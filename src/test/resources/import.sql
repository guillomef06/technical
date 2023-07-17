INSERT INTO customers (id, user_name, date_of_birth, country) VALUES (NEXTVAL('customer_seq'), 'hello', '2000-07-07', 'France');
INSERT INTO customers (id, user_name, date_of_birth, country) VALUES (NEXTVAL('customer_seq'), 'test', '2000-07-07', 'France');
INSERT INTO airports (id, iata_code, city, country, global_taxes) VALUES (NEXTVAL('airport_seq'), 'CTA', 'Catania', 'Italia', 5.00);
INSERT INTO airports (id, iata_code, city, country, global_taxes) VALUES (NEXTVAL('airport_seq'), 'NCE', 'Nice', 'France', 10.00);
INSERT INTO flights (id, start_airport_id, destination_airport_id, airline_designator, number, base_price, departure_time) VALUES (NEXTVAL('flight_seq'), 1, 2, 'AF', 1001, 100, '2023-07-25 15:50:00.0000000');
INSERT INTO flights (id, start_airport_id, destination_airport_id, airline_designator, number, base_price, departure_time) VALUES (NEXTVAL('flight_seq'), 1, 2, 'AF', 1001, 100, '2023-08-25 15:50:00.0000000');
INSERT INTO flights (id, start_airport_id, destination_airport_id, airline_designator, number, base_price, departure_time) VALUES (NEXTVAL('flight_seq'), 1, 2, 'AF', 1001, 100, '2023-09-25 15:50:00.0000000');