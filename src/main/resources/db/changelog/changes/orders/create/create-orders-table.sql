CREATE TABLE IF NOT EXISTS orders (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        car_id BIGINT,
                        problem_description VARCHAR(255),
                        date_acceptance DATETIME,
                        date_completion DATETIME,
                        status_order VARCHAR(50),
                        cost_total DECIMAL(10, 2),
                        FOREIGN KEY (car_id) REFERENCES cars (id)
);