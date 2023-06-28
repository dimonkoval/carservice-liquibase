CREATE TABLE IF NOT EXISTS owner_cars (
                            owner_id BIGINT,
                            car_id BIGINT,
                            FOREIGN KEY (owner_id) REFERENCES owners (id),
                            FOREIGN KEY (car_id) REFERENCES cars (id),
                            CONSTRAINT uk_owner_car UNIQUE (owner_id, car_id)
);
