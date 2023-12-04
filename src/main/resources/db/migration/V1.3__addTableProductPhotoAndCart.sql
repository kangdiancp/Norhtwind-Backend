CREATE TABLE Product_Photos(
  ppho_id  INTEGER IDENTITY PRIMARY KEY,
  ppho_filename VARCHAR(125),
  ppho_filetype VARCHAR(15),
  ppho_filesize smallint,
  ppho_url VARCHAR(256),
  ppho_product_id integer,
  FOREIGN KEY (ppho_product_id) REFERENCES Products(ProductID)
);


create table carts
(
    cart_id   INTEGER  IDENTITY PRIMARY KEY,
    product_id  smallint,
    customer_id smallint,
    status      varchar(25) not null,
    createdDate timestamp    not null,
    constraint UQ_product_customer_id UNIQUE(product_id,customer_id)
);


