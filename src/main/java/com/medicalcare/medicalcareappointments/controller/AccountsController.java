package com.medicalcare.medicalcareappointments.controller;

import com.medicalcare.medicalcareappointments.business.*;
import com.medicalcare.medicalcareappointments.domain.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountsController {
    private final GetAccountUseCase getAccountUseCase;
    private final GetAccountsUsecase getAccountsUsecase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final CreateAccountUseCase createAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;

    @GetMapping("{id}")
    public ResponseEntity<Account> getAccount(@PathVariable(value = "id") final long id){
        final Optional<Account> accountOptional = getAccountUseCase.getAccount(id);
        if(accountOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(accountOptional.get());
    }

    @GetMapping
    public ResponseEntity<GetAccountsResponse> getAccounts(){
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
    public ResponseEntity<CreateAccountResponse> createUser(@RequestBody @Valid CreateUserRequest request){
        CreateAccountResponse response = createAccountUseCase.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("create-admin")
    public ResponseEntity<CreateAccountResponse> createAdmin(@RequestBody @Valid CreateAdminRequest request){
        CreateAccountResponse response = createAccountUseCase.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("update-user/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable(value = "id") long id,
                                              @RequestBody @Valid UpdateUserRequest request){
        request.setAccountId(id);
        updateAccountUseCase.updateAccount(request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update-doctor/{id}")
    public ResponseEntity<Void> updateDoctor(@PathVariable(value = "id") long id,
                                              @RequestBody @Valid UpdateDoctorRequest request){
        request.setAccountId(id);
        updateAccountUseCase.updateAccount(request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update-admin/{id}")
    public ResponseEntity<Void> updateAccount(@PathVariable(value = "id") long id,
                                              @RequestBody @Valid UpdateAdminRequest request){
        request.setAccountId(id);
        updateAccountUseCase.updateAccount(request);
        return ResponseEntity.noContent().build();
    }
}
