DROP TABLE IF EXISTS order_line_item;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customer_role;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS contacts;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS line_items;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS cart_cartItem;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS cart_items;

CREATE TABLE IF NOT EXISTS addresses(
    id BIGINT NOT NULL AUTO_INCREMENT,
    city VARCHAR(50),
    street VARCHAR(50),
    house_number VARCHAR(5),
    zip_code VARCHAR(10),
    country VARCHAR(15),
    state VARCHAR(15),
    firstname VARCHAR(254),
    lastname VARCHAR(254),

    PRIMARY KEY(id)
);

CREATE TABLE  IF NOT EXISTS contacts(
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(254),
    cellular VARCHAR(15),
    landline_phone VARCHAR(15),

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS role (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS customers(
    id BIGINT NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(15),
    lastname VARCHAR(25),
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    header_addres_id BIGINT,
    delivery_addres_id BIGINT,
    contact_id BIGINT,

    PRIMARY KEY(id),

    CONSTRAINT customers_fk_01 FOREIGN KEY (header_addres_id) REFERENCES addresses(id),
    CONSTRAINT customers_fk_02 FOREIGN KEY (delivery_addres_id) REFERENCES addresses(id),
    CONSTRAINT customers_fk_03 FOREIGN KEY (contact_id) REFERENCES contacts(id)
);

CREATE TABLE IF NOT EXISTS customer_role(
    customer_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    CONSTRAINT customer_role_fk_01 FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT customer_role_fk_02 FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE IF NOT EXISTS categories(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS products(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    product_code VARCHAR(13) UNIQUE,
    price DECIMAL(10,2),
    category_id BIGINT NOT NULL,
    description VARCHAR(255),

    PRIMARY KEY(id),

    CONSTRAINT products_fk_01 FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS line_items(
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_id BIGINT,
    quantity INTEGER,

    PRIMARY KEY(id),

    CONSTRAINT line_items_fk_01 FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS orders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    order_date TIMESTAMP,
    total_amount DECIMAL(10,2),
    customer_id BIGINT NOT NULL,

    PRIMARY KEY (id),

   CONSTRAINT fk_customer_id_01 FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE IF NOT EXISTS order_line_item(
    order_id BIGINT,
    line_item_id BIGINT,

    CONSTRAINT order_line_item_uq_01 UNIQUE (line_item_id),
    CONSTRAINT order_line_item_fq_01 FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT order_line_item_fq_02 FOREIGN KEY (line_item_id) REFERENCES line_items(id)
);

CREATE TABLE IF NOT EXISTS cart(
    id BIGINT NOT NULL AUTO_INCREMENT,
    total_amount DECIMAL(10,2),
    customer_id BIGINT,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS cartItems(
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_id BIGINT,
    quantity INTEGER,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS cart_cartItem(
    cart_id BIGINT,
    cartItem_id BIGINT,

    CONSTRAINT cart_cartItem_uq_01 UNIQUE (cartItem_id),
    CONSTRAINT cart_cartItem_fq_01 FOREIGN KEY (cart_id) REFERENCES cart(id),
    CONSTRAINT cart_cartItem_fq_02 FOREIGN KEY (cartItem_id) REFERENCES cartItems(id)
);
