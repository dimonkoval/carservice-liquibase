CREATE TABLE IF NOT EXISTS orders (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        car_id BIGINT,
                        problemDescription VARCHAR(255),
                        date_acceptance DATETIME,
                        date_completion DATETIME,
                        statusOrder VARCHAR(50),
                        costTotal DECIMAL(10, 2),
                        FOREIGN KEY (car_id) REFERENCES cars (id)
);