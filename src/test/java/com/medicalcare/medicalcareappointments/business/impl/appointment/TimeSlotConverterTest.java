package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.impl.account.TimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.account.TimeSlot;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TimeSlotConverterTest {
    @Test
    void convertTimeSlot_shouldConvertTimeSlot() {
        //Arrange
        TimeSlotEntity timeSlotEntity = TimeSlotEntity.builder()
                .startTime(new Timestamp(new Date(2011, 11, 11).getTime()))
                .endTime(new Timestamp(new Date(2011, 11, 11).getTime()))
                .build();

        //Act
        TimeSlot timeSlot = TimeSlotConverter.convert(timeSlotEntity);

        //Assert
        assertEquals(timeSlotEntity.getStartTime(), timeSlot.getStartTime());
        assertEquals(timeSlotEntity.getEndTime(), timeSlot.getEndTime());
    }


}