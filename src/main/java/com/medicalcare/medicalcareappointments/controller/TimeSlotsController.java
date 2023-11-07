package com.medicalcare.medicalcareappointments.controller;

import com.medicalcare.medicalcareappointments.business.CreateTimeSlotUseCase;
import com.medicalcare.medicalcareappointments.business.DeleteTimeSlotUseCase;
import com.medicalcare.medicalcareappointments.domain.timeslot.CreateTimeSlotRequest;
import com.medicalcare.medicalcareappointments.domain.timeslot.CreateTimeSlotResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timeslots")
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
public class TimeSlotsController {
    private final CreateTimeSlotUseCase createTimeSlotUseCase;
    private final DeleteTimeSlotUseCase deleteTimeSlotUseCase;


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

}
