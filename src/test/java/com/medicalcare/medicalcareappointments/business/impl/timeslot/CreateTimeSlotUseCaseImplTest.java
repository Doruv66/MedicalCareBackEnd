package com.medicalcare.medicalcareappointments.business.impl.timeslot;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.domain.timeslot.CreateTimeSlotRequest;
import com.medicalcare.medicalcareappointments.domain.timeslot.CreateTimeSlotResponse;
import com.medicalcare.medicalcareappointments.persistence.TimeSlotRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateTimeSlotUseCaseImplTest {

    @Mock
    private TimeSlotRepository timeSlotRepositoryMock;

    @InjectMocks
    private CreateTimeSlotUseCaseImpl createTimeSlotUseCase;

    @Test
    void createTimeSlot_shouldCreateNewTimeSlots() {
        // Arrange
        CreateTimeSlotRequest request = CreateTimeSlotRequest.builder()
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .doctor(AccountUtilClass.createDoctor())
                .build();

        when(timeSlotRepositoryMock.findTimeSlotsByDateAndDoctorId(any(Timestamp.class), any(Long.class)))
                .thenReturn(new ArrayList<>());

        // Act
        CreateTimeSlotResponse actualResult = createTimeSlotUseCase.createTimeSlot(request);

        // Assert
        CreateTimeSlotResponse expectedResult = CreateTimeSlotResponse.builder().build();

        assertEquals(actualResult, expectedResult);
        verify(timeSlotRepositoryMock, times(9)).save(any(TimeSlotEntity.class));
    }
}