package com.medicalcare.medicalcareappointments.business.impl.timeslot;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.business.impl.account.AccountConverter;
import com.medicalcare.medicalcareappointments.business.impl.timeslot.TimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlot;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlotType;
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
                .timeSlotId(1L)
                .startTime(new Timestamp(new Date(2011, 11, 11).getTime()))
                .endTime(new Timestamp(new Date(2011, 11, 11).getTime()))
                .timeSlotType(TimeSlotType.AVAILABLE)
                .doctor(AccountUtilClass.createDoctorEntity())
                .build();

        //Act
        TimeSlot timeSlot = TimeSlotConverter.convert(timeSlotEntity);

        //Assert
        assertEquals(timeSlotEntity.getTimeSlotId(), timeSlot.getTimeSlotId());
        assertEquals(timeSlotEntity.getTimeSlotType(), timeSlot.getTimeSlotType());
        assertEquals(AccountConverter.convert(timeSlotEntity.getDoctor()), timeSlot.getDoctor());
        assertEquals(timeSlotEntity.getStartTime(), timeSlot.getStartTime());
        assertEquals(timeSlotEntity.getEndTime(), timeSlot.getEndTime());
    }


}