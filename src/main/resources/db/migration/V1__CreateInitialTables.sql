CREATE TABLE account (
     account_id BIGINT PRIMARY KEY,
     username VARCHAR(255) NOT NULL,
     password VARCHAR(255) NOT NULL,
     email VARCHAR(255) NOT NULL,
     account_type VARCHAR(255) NOT NULL
);

CREATE TABLE admin (
       account_id BIGINT PRIMARY KEY REFERENCES account(account_id),
       position VARCHAR(255) NOT NULL
);

CREATE TABLE user (
      account_id BIGINT PRIMARY KEY REFERENCES account(account_id),
      first_name VARCHAR(255),
      last_name VARCHAR(255),
      date_of_birth TIMESTAMP
);

CREATE TABLE doctor (
        account_id BIGINT PRIMARY KEY REFERENCES account(account_id),
        specialization VARCHAR(255),
        photo TEXT,
        name VARCHAR(255),
        fname VARCHAR(255),
        description TEXT
);

CREATE TABLE appointment (
         appointment_id BIGINT PRIMARY KEY,
         date_time TIMESTAMP NOT NULL,
         user_id BIGINT REFERENCES account(account_id),
         doctor_id BIGINT REFERENCES account(account_id),
         appointment_status VARCHAR(255) NOT NULL
);

CREATE TABLE review (
        review_id BIGINT PRIMARY KEY,
        date TIMESTAMP NOT NULL,
        rating INT NOT NULL,
        comment TEXT,
        user_id BIGINT REFERENCES account(account_id),
        doctor_id BIGINT REFERENCES account(account_id)
);

CREATE TABLE time_slot (
       start_time TIMESTAMP NOT NULL,
       end_time TIMESTAMP NOT NULL,
       PRIMARY KEY (start_time, end_time)
);

CREATE TABLE doctor_time_slot (
      doctor_id BIGINT REFERENCES doctor(account_id),
      time_slot_start_time TIMESTAMP,
      time_slot_end_time TIMESTAMP,
      PRIMARY KEY (doctor_id, time_slot_start_time, time_slot_end_time),
      FOREIGN KEY (time_slot_start_time, time_slot_end_time) REFERENCES time_slot (start_time, end_time)
);