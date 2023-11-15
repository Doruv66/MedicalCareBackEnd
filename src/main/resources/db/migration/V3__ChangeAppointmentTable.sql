ALTER TABLE appointment DROP COLUMN date_time;

ALTER TABLE appointment ADD COLUMN timeslot_id BIGINT;

ALTER TABLE appointment ADD CONSTRAINT fk_appointment_timeslot
    FOREIGN KEY (timeslot_id) REFERENCES time_slot(time_slot_id);