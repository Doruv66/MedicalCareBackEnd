package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.impl.account.ReverseTimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.account.TimeSlot;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReverseTimeSlotConverterTest {
    @Test
    void reverseConvertTimeSlotEntity_shouldReverseConvertToTimeSlot() {
        //Arrange
        TimeSlot timeSlot = TimeSlot.builder()
                .startTime(new Timestamp(new Date(2011, 11, 11).getTime()))
                .endTime(new Timestamp(new Date(2011, 11, 11).getTime()))
                .build();

        //Act
        TimeSlotEntity timeSlotEntity = ReverseTimeSlotConverter.convert(timeSlot);

        //Assert
        assertEquals(timeSlot.getStartTime(), timeSlotEntity.getStartTime());
        assertEquals(timeSlot.getEndTime(), timeSlotEntity.getEndTime());
    }
}