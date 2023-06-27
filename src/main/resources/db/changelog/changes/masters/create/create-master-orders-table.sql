CREATE TABLE IF NOT EXISTS master_orders (
                              master_id BIGINT,
                              order_id BIGINT,
                              FOREIGN KEY (master_id) REFERENCES masters (id),
                              FOREIGN KEY (order_id) REFERENCES orders (id)
);