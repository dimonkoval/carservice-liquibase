CREATE TABLE IF NOT EXISTS service_masters (
                              service_id BIGINT,
                              master_id BIGINT,
                              FOREIGN KEY (master_id) REFERENCES masters (id),
                              FOREIGN KEY (service_id) REFERENCES services (id)
);