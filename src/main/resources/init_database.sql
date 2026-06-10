create table if not exists users(
	id serial primary key,
	name varchar(100) not null,
    password varchar(255) not null,
    email varchar(255) not null unique,
    role varchar(20) default 'USER',
    address text,
    phone varchar(20)

);
create table if not exists glasses(
    id serial primary key,
    article varchar(50) not null unique,
    name_model varchar(255) not null unique,
    price DECIMAL(10, 2) not null,
    description text,
    count_glasses integer,
    path_to_photo varchar(100) not null
);
create table if not exists cart(
    id serial primary key,
    user_id integer references users(id) on delete cascade
);

create table if not exists cart_item (
    id serial primary key,
    cart_id integer references cart(id) on delete cascade,
    glasses_id integer references glasses(id) on delete cascade,
    quantity integer not null default 1
);

create table if not exists orders (
    id serial primary key,
    user_id integer references users(id),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    address text not null,
    phone varchar(20),
    total_price decimal(10, 2) not null,
    status varchar(50) not null
);

create table if not exists order_item (
    id serial primary key,
    order_id integer references orders(id),
    glasses_id integer references glasses(id),
    quantity integer not null default 1,
    price_at_time DECIMAL(10, 2) NOT NULL
)