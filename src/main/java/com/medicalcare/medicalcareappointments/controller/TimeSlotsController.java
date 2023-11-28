package com.medicalcare.medicalcareappointments.controller;

import com.medicalcare.medicalcareappointments.business.CreateTimeSlotUseCase;
import com.medicalcare.medicalcareappointments.business.DeleteTimeSlotUseCase;
import com.medicalcare.medicalcareappointments.business.GetTimeSlotsByDateUseCase;
import com.medicalcare.medicalcareappointments.domain.timeslot.CreateTimeSlotRequest;
import com.medicalcare.medicalcareappointments.domain.timeslot.CreateTimeSlotResponse;
import com.medicalcare.medicalcareappointments.domain.timeslot.GetTimeSlotsResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/timeslots")
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
public class TimeSlotsController {
    private final CreateTimeSlotUseCase createTimeSlotUseCase;
    private final DeleteTimeSlotUseCase deleteTimeSlotUseCase;
    private final GetTimeSlotsByDateUseCase getTimeSlotsByDateUseCase;

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTimeSlot(@PathVariable(value = "id") final long id) {
        deleteTimeSlotUseCase.deleteTimeSlot(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<CreateTimeSlotResponse> createTimeSlot(@RequestBody @Valid CreateTimeSlotRequest request) {
        CreateTimeSlotResponse response = createTimeSlotUseCase.createTimeSlot(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RolesAllowed({"PATIENT", "DOCTOR"})
    @GetMapping("/{doctorId}/{timestamp}")
    public ResponseEntity<GetTimeSlotsResponse> getTimeSlotsByDate(
            @PathVariable("doctorId") long doctorId,
            @PathVariable("timestamp") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date timestamp
    ) {
            Timestamp sqlTimestamp = new Timestamp(timestamp.getTime());
            sqlTimestamp.setTime(sqlTimestamp.getTime() + (2 * 60 * 60 * 1000));
            GetTimeSlotsResponse response = getTimeSlotsByDateUseCase.getTimeSlotsByDate(sqlTimestamp, doctorId);
            return ResponseEntity.ok().body(response);
    }

}
