package com.medicalcare.medicalcareappointments.controller;

import com.medicalcare.medicalcareappointments.business.GetAppointmentUseCase;
import com.medicalcare.medicalcareappointments.business.CreateAppointmentUseCase;
import com.medicalcare.medicalcareappointments.business.DeleteAppointmentUseCase;
import com.medicalcare.medicalcareappointments.business.GetAppointmentsUseCase;
import com.medicalcare.medicalcareappointments.business.UpdateAppointmentUseCase;
import com.medicalcare.medicalcareappointments.domain.appointment.*;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
public class AppointmentsController {
    private final GetAppointmentsUseCase getAppointmentsUseCase;
    private final GetAppointmentUseCase getAppointmentUseCase;
    private final CreateAppointmentUseCase createAppointmentUseCase;
    private final DeleteAppointmentUseCase deleteAppointmentUseCase;
    private final UpdateAppointmentUseCase updateAppointmentUseCase;

    @GetMapping("{id}")
    public ResponseEntity<GetAppointmentResponse> getAppointment(@PathVariable(value = "id") final long id) {
        final GetAppointmentResponse response = getAppointmentUseCase.getAppointment(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<GetAppointmentsResponse> getAppointments() {
        GetAppointmentsResponse response = getAppointmentsUseCase.getAppointments();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable(value = "id") final long id){
        deleteAppointmentUseCase.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @RolesAllowed({"PATIENT"})
    @PostMapping
    public ResponseEntity<CreateAppointmentResponse> createAppointment(@RequestBody @Valid CreateAppointmentRequest request) {
        CreateAppointmentResponse response = createAppointmentUseCase.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateAppointmentResponse> updateAppointment(@PathVariable(value = "id") final long id,
                                                  @RequestBody @Valid UpdateAppointmentRequest request){
        UpdateAppointmentResponse response = updateAppointmentUseCase.updateAppointment(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
