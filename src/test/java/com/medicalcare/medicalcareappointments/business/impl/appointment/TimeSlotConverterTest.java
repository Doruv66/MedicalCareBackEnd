package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.domain.appointment.TimeSlot;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TimeSlotConverterTest {
    @Test
    void convertTimeSlot_shouldConvertTimeSlot() {
        //Arrange
        TimeSlotEntity timeSlotEntity = TimeSlotEntity.builder()
                .startTime(new Date(2011, 11, 11))
                .endTime(new Date(2011, 11, 11))
                .build();

        //Act
        TimeSlot timeSlot = TimeSlotConverter.convert(timeSlotEntity);

        //Assert
        assertEquals(timeSlotEntity.getStartTime(), timeSlot.getStartTime());
        assertEquals(timeSlotEntity.getEndTime(), timeSlot.getEndTime());
    }


}