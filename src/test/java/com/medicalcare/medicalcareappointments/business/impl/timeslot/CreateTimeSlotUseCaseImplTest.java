package com.medicalcare.medicalcareappointments.business.impl.timeslot;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.business.impl.account.AccountConverter;
import com.medicalcare.medicalcareappointments.business.impl.account.ReverseAccountConverter;
import com.medicalcare.medicalcareappointments.domain.timeslot.CreateTimeSlotRequest;
import com.medicalcare.medicalcareappointments.domain.timeslot.CreateTimeSlotResponse;
import com.medicalcare.medicalcareappointments.persistence.TimeSlotRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
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
    void createTimeSlot_shouldCreateNewTimeSlot() {
        // Arrange
        long timeSlotId = 1L;
        CreateTimeSlotRequest request = CreateTimeSlotRequest.builder()
                .startTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .endTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .doctor(AccountUtilClass.createDoctor())
                .build();
        TimeSlotEntity timeSlot = TimeSlotEntity.builder()
                .timeSlotId(timeSlotId)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .doctor((DoctorEntity) ReverseAccountConverter.convert(request.getDoctor()))
                .build();
        when(timeSlotRepositoryMock.save(any(TimeSlotEntity.class))).thenReturn(timeSlot);

        // Act
        CreateTimeSlotResponse actualResult = createTimeSlotUseCase.createTimeSlot(request);

        // Assert
        CreateTimeSlotResponse expectedResult = CreateTimeSlotResponse.builder()
                .id(timeSlotId)
                .build();

        assertEquals(actualResult, expectedResult);
        verify(timeSlotRepositoryMock, times(1)).save(any(TimeSlotEntity.class));
    }
}
