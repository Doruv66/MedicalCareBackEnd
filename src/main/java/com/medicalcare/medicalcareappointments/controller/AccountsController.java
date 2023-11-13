package com.medicalcare.medicalcareappointments.controller;

import com.medicalcare.medicalcareappointments.business.*;
import com.medicalcare.medicalcareappointments.domain.account.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
public class AccountsController {
    private final GetAccountUseCase getAccountUseCase;
    private final GetAccountsUsecase getAccountsUsecase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final CreateAccountUseCase createAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final GetDoctorsUseCase getDoctorsUseCase;

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


    @PostMapping("create-doctor")
    public ResponseEntity<CreateAccountResponse> createDoctor(@RequestBody @Valid CreateDoctorRequest request){  
        CreateAccountResponse response = createAccountUseCase.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("create-user")
    public ResponseEntity<CreateAccountResponse> createUser(@RequestBody @Valid CreatePatientRequest request){
        CreateAccountResponse response = createAccountUseCase.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("create-admin")
    public ResponseEntity<CreateAccountResponse> createAdmin(@RequestBody @Valid CreateAdminRequest request){
        CreateAccountResponse response = createAccountUseCase.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("update-user/{id}")
    public ResponseEntity<UpdateAccountResponse> updateUser(@PathVariable(value = "id") long id,
                                                            @RequestBody @Valid UpdatePatientRequest request){
        UpdateAccountResponse response = updateAccountUseCase.updateAccount(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

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
