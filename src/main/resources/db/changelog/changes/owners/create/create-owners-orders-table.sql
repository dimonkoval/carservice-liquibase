CREATE TABLE IF NOT EXISTS owner_orders (
                              owner_id BIGINT,
                              order_id BIGINT,
                              FOREIGN KEY (owner_id) REFERENCES owners (id),
                              FOREIGN KEY (order_id) REFERENCES orders (id)
);
