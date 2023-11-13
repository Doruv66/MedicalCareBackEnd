CREATE TABLE account (
         account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
         username VARCHAR(50) NOT NULL,
         password VARCHAR(255) NOT NULL,
         email VARCHAR(100) NOT NULL,
         account_type VARCHAR(255) NOT NULL
);

CREATE TABLE admin (
           account_id BIGINT PRIMARY KEY,
           position VARCHAR(100) NOT NULL,
           FOREIGN KEY (account_id) REFERENCES account(account_id)
);

CREATE TABLE doctor (
            account_id BIGINT PRIMARY KEY,
            specialization VARCHAR(255) NOT NULL,
            photo VARCHAR(255) NOT NULL,
            name VARCHAR(255) NOT NULL,
            fname VARCHAR(255) NOT NULL,
            description LONGTEXT,
            FOREIGN KEY (account_id) REFERENCES account(account_id)
);

CREATE TABLE appointment (
             appointment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
             date_time TIMESTAMP NOT NULL,
             user_id BIGINT,
             doctor_id BIGINT,
             appointment_status VARCHAR(255) NOT NULL,
             FOREIGN KEY (user_id) REFERENCES account(account_id),
             FOREIGN KEY (doctor_id) REFERENCES account(account_id)
);

CREATE TABLE review (
            review_id BIGINT AUTO_INCREMENT PRIMARY KEY,
            date TIMESTAMP NOT NULL,
            rating INT NOT NULL,
            comment TEXT,
            user_id BIGINT,
            doctor_id BIGINT,
            FOREIGN KEY (user_id) REFERENCES account(account_id),
            FOREIGN KEY (doctor_id) REFERENCES account(account_id)
);

CREATE TABLE time_slot (
           time_slot_id BIGINT AUTO_INCREMENT PRIMARY KEY,
           start_time TIMESTAMP NOT NULL,
           end_time TIMESTAMP NOT NULL,
           doctor_id BIGINT,
           FOREIGN KEY (doctor_id) REFERENCES doctor(account_id)
);

CREATE TABLE user (
          account_id BIGINT PRIMARY KEY,
          first_name VARCHAR(50) NOT NULL,
          last_name VARCHAR(50) NOT NULL,
          date_of_birth TIMESTAMP
);