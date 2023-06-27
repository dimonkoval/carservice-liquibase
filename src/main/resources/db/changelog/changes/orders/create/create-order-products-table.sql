CREATE TABLE IF NOT EXISTS order_products (
                                order_id BIGINT,
                                product_id BIGINT,
                                FOREIGN KEY (order_id) REFERENCES orders (id),
                                FOREIGN KEY (product_id) REFERENCES products (id)
);
