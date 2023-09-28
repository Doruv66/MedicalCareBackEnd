package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.domain.appointment.TimeSlot;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReverseTimeSlotConverterTest {
    @Test
    void reverseConvertTimeSlotEntity_shouldReverseConvertToTimeSlot() {
        //Arrange
        TimeSlot timeSlot = TimeSlot.builder()
                .startTime(new Date(2011, 11, 11))
                .endTime(new Date(2011, 11, 11))
                .build();

        //Act
        TimeSlotEntity timeSlotEntity = ReverseTimeSlotConverter.convert(timeSlot);

        //Assert
        assertEquals(timeSlot.getStartTime(), timeSlotEntity.getStartTime());
        assertEquals(timeSlot.getEndTime(), timeSlotEntity.getEndTime());
    }
}