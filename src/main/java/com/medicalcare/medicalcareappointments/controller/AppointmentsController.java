package com.medicalcare.medicalcareappointments.controller;

import com.medicalcare.medicalcareappointments.business.*;
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
    private final GetUpcomingAppointmentsUseCase getUpcomingAppointmentsUseCase;
    private final GetPreviousAppointmentsUseCase getPreviousAppointmentsUseCase;
    private final GetDoctorAppointmentsUseCase getDoctorAppointmentsUseCase;

    @GetMapping("{id}")
    public ResponseEntity<GetAppointmentResponse> getAppointment(@PathVariable(value = "id") final long id) {
        final GetAppointmentResponse response = getAppointmentUseCase.getAppointment(id);
        return ResponseEntity.ok().body(response);
    }

    @RolesAllowed({"PATIENT"})
    @GetMapping("/previous/{accId}")
    public ResponseEntity<GetAppointmentsResponse> getPreviousAppointment(@PathVariable(value = "accId") final long accId) {
        final GetAppointmentsResponse response = getPreviousAppointmentsUseCase.getPreviousAppointments(accId);
        return ResponseEntity.ok().body(response);
    }

    @RolesAllowed({"PATIENT"})
    @GetMapping("/upcoming/{accId}")
    public ResponseEntity<GetAppointmentsResponse> getUpcomingAppointment(@PathVariable(value = "accId") final long accId) {
        final GetAppointmentsResponse response = getUpcomingAppointmentsUseCase.getUpcomingAppointments(accId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<GetAppointmentsResponse> getAppointments() {
        GetAppointmentsResponse response = getAppointmentsUseCase.getAppointments();
        return ResponseEntity.ok(response);
    }

    @RolesAllowed({"DOCTOR"})
    @GetMapping("doctor/{doctorId}")
    public ResponseEntity<GetAppointmentsResponse>
    getDoctorAppointments (@PathVariable(value = "doctorId") final long doctorId) {
        GetAppointmentsResponse response = getDoctorAppointmentsUseCase.getDoctorAppointments(doctorId);
        return ResponseEntity.ok(response);
    }

    @RolesAllowed({"PATIENT"})
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


    @RolesAllowed({"DOCTOR"})
    @PutMapping("{id}")
    public ResponseEntity<UpdateAppointmentResponse> updateAppointment(@PathVariable(value = "id") final long id,
                                                  @RequestBody @Valid UpdateAppointmentRequest request){
        UpdateAppointmentResponse response = updateAppointmentUseCase.updateAppointment(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
