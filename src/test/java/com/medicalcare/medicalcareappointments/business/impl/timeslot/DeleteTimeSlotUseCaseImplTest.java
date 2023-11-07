package com.medicalcare.medicalcareappointments.business.impl.timeslot;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.exception.NotFoundTimeSlotException;
import com.medicalcare.medicalcareappointments.persistence.TimeSlotRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteTimeSlotUseCaseImplTest {
    @Mock
    private TimeSlotRepository timeSlotRepositoryMock;

    @InjectMocks
    private DeleteTimeSlotUseCaseImpl deleteTimeSlotUseCase;

    @Test
    void deleteTimeSlot_shouldDeleteTimeSlot() {
        // Arrange
        long timeSlotId = 1L;

        when(timeSlotRepositoryMock.findById(timeSlotId)).thenReturn(Optional.ofNullable(TimeSlotEntity.builder()
                .startTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .endTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .doctor(AccountUtilClass.createDoctorEntity())
                .build()
        ));

        // Act
        deleteTimeSlotUseCase.deleteTimeSlot(timeSlotId);

        // Assert
        verify(timeSlotRepositoryMock, times(1)).findById(timeSlotId);
        verify(timeSlotRepositoryMock, times(1)).deleteById(timeSlotId);
    }

    @Test
    void deleteTimeSlot_shouldThrowNotFoundException() {
        // Arrange
        long timeSlotId = 2L;

        when(timeSlotRepositoryMock.findById(timeSlotId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundTimeSlotException.class, () -> deleteTimeSlotUseCase.deleteTimeSlot(timeSlotId));

        verify(timeSlotRepositoryMock, times(1)).findById(timeSlotId);
        verify(timeSlotRepositoryMock, never()).deleteById(timeSlotId);
    }
}
