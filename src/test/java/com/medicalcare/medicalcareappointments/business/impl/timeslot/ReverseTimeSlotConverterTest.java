package com.medicalcare.medicalcareappointments.business.impl.timeslot;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.business.impl.account.ReverseAccountConverter;
import com.medicalcare.medicalcareappointments.business.impl.timeslot.ReverseTimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlot;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlotType;
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
                .timeSlotId(1L)
                .startTime(new Timestamp(new Date(2011, 11, 11).getTime()))
                .endTime(new Timestamp(new Date(2011, 11, 11).getTime()))
                .timeSlotType(TimeSlotType.AVAILABLE)
                .doctor(AccountUtilClass.createDoctor())
                .build();

        //Act
        TimeSlotEntity timeSlotEntity = ReverseTimeSlotConverter.convert(timeSlot);

        //Assert
        assertEquals(timeSlot.getTimeSlotId(), timeSlotEntity.getTimeSlotId());
        assertEquals(timeSlot.getTimeSlotType(), timeSlotEntity.getTimeSlotType());
        assertEquals(ReverseAccountConverter.convert(timeSlot.getDoctor()), timeSlotEntity.getDoctor());
        assertEquals(timeSlot.getStartTime(), timeSlotEntity.getStartTime());
        assertEquals(timeSlot.getEndTime(), timeSlotEntity.getEndTime());
    }
}