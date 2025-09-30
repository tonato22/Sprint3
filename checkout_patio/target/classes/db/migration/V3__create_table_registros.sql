CREATE TABLE registro (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          moto_placa VARCHAR(20) NOT NULL,
                          check_in TIMESTAMP NOT NULL,
                          check_out TIMESTAMP,
                          FOREIGN KEY (moto_placa) REFERENCES moto(placa) ON DELETE CASCADE
);
