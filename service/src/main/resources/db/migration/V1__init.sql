CREATE SEQUENCE IF NOT EXISTS products_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE products
(
   id         BIGINT                                    NOT NULL,
   name      VARCHAR(200)                               NOT NULL,
   quantity  integer                                    NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
   updated_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_products PRIMARY KEY (id)
);