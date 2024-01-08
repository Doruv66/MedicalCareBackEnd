package com.medicalcare.medicalcareappointments.controller;

import com.medicalcare.medicalcareappointments.business.*;
import com.medicalcare.medicalcareappointments.domain.account.*;
import com.medicalcare.medicalcareappointments.exception.EmailAlreadyExistsException;
import com.medicalcare.medicalcareappointments.exception.UsernameAlreadyExistsException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AccountsController {
    private final GetAccountUseCase getAccountUseCase;
    private final GetAccountsUsecase getAccountsUsecase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final CreateAccountUseCase createAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final GetDoctorsUseCase getDoctorsUseCase;
    private final GetDoctorsByKeywordUseCase getDoctorsByKeywordUseCase;
    private final GetTop3DoctorsByRatingUseCase getTop3DoctorsByRatingUseCase;

    @GetMapping("{id}")
    public ResponseEntity<GetAccountResponse> getAccount(@PathVariable(value = "id") final long id) {
        final GetAccountResponse response = getAccountUseCase.getAccount(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("doctors")
    public ResponseEntity<GetAccountsResponse> getDoctors() {
        GetAccountsResponse response = getDoctorsUseCase.getDoctors();
        return ResponseEntity.ok(response);
    }

    @GetMapping("doctors-top")
    public ResponseEntity<GetAccountsResponse> getTop3Doctors() {
        GetAccountsResponse response = getTop3DoctorsByRatingUseCase.getTop3DoctorsByRating();
        return ResponseEntity.ok(response);
    }

    @GetMapping("doctors/search")
    public ResponseEntity<GetAccountsResponse>
    getDoctorsByKeyword(@RequestParam("keyword") final String keyword) {
        GetAccountsResponse response = getDoctorsByKeywordUseCase.getDoctorsByKeyword(keyword);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<GetAccountsResponse> getAccounts() {
        GetAccountsResponse response = getAccountsUsecase.getAll();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable(value = "id") final long id){
        deleteAccountUseCase.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }


    @RolesAllowed({"ADMIN"})
    @PostMapping("create-doctor")
    public ResponseEntity<?> createDoctor(@RequestBody @Valid CreateDoctorRequest request){
        try {
            CreateAccountResponse response = createAccountUseCase.createAccount(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());
        }
    }

    @PostMapping("create-patient")
    public ResponseEntity<?> createPatient(@RequestBody @Valid CreatePatientRequest request){
        try {
            CreateAccountResponse response = createAccountUseCase.createAccount(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());
        }
    }

    @PostMapping("create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody @Valid CreateAdminRequest request){
        try {
            CreateAccountResponse response = createAccountUseCase.createAccount(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());
        }
    }

    @RolesAllowed({"PATIENT"})
    @PutMapping("update-patient/{id}")
    @PreAuthorize("@customSecurityService.isAccountIdMatching(#id, authentication)")
    public ResponseEntity<UpdateAccountResponse> updatePatient(@PathVariable(value = "id") long id,
                                                            @RequestBody @Valid UpdatePatientRequest request){
        UpdateAccountResponse response = updateAccountUseCase.updateAccount(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @RolesAllowed({"ADMIN"})
    @PutMapping("update-doctor/{id}")
    public ResponseEntity<UpdateAccountResponse> updateDoctor(@PathVariable(value = "id") long id,
                                              @RequestBody @Valid UpdateDoctorRequest request){
        UpdateAccountResponse response = updateAccountUseCase.updateAccount(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("update-admin/{id}")
    public ResponseEntity<UpdateAccountResponse> updateAccount(@PathVariable(value = "id") long id,
                                              @RequestBody @Valid UpdateAdminRequest request){
        UpdateAccountResponse response = updateAccountUseCase.updateAccount(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
