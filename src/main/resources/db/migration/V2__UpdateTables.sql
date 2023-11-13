
ALTER TABLE user
    RENAME TO patient;

ALTER TABLE patient
    DROP COLUMN first_name,
    DROP COLUMN last_name;

ALTER TABLE doctor
    DROP COLUMN name,
    DROP COLUMN fname;

ALTER TABLE patient
    ADD CONSTRAINT FK_account_id
        FOREIGN KEY (account_id) REFERENCES account(account_id);

ALTER TABLE review
    CHANGE COLUMN user_id patient_id BIGINT;

ALTER TABLE appointment
    CHANGE COLUMN user_id patient_id BIGINT;
