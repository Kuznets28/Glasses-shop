create table glasses (id serial primary key,
					  sku varchar(50) unique,
					  name varchar(50) unique,
					  price int,
					  pathToPhoto varchar(50));

