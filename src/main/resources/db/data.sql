drop table if exists discount_card;
drop table if exists product;

create table discount_card (
    id bigserial primary key,
	number integer not null unique,
    amount smallint check (amount >= 0 and amount <= 100) not null default 2
);

create table product (
    id bigserial primary key,
	description varchar(50) not null,
	price decimal not null,
	quantity_in_stock integer not null default 0,
	wholesale_product boolean not null default false
);

insert into discount_card(number, amount) values
(1111, 2),
(2222, 2),
(3333, 4),
(4444, 5);

insert into product(description, price, quantity_in_stock, wholesale_product) values
('Milk',1.07,10,true),
('Cream 400g',2.71,20,true),
('Yogurt 400g',2.10,7,true),
('Packed potatoes 1kg',1.47,30,false),
('Packed cabbage 1kg',1.19,15,false);
