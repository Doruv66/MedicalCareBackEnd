package com.medicalcare.medicalcareappointments.controller;

import com.medicalcare.medicalcareappointments.business.CreateAccountUseCase;
import com.medicalcare.medicalcareappointments.business.GetAccountUseCase;
import com.medicalcare.medicalcareappointments.business.UpdateAccountUseCase;
import com.medicalcare.medicalcareappointments.domain.account.*;
import com.medicalcare.medicalcareappointments.exception.EmailAlreadyExistsException;
import com.medicalcare.medicalcareappointments.exception.UsernameAlreadyExistsException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AdminController {

    private final GetAccountUseCase getAccountUseCase;
    private final CreateAccountUseCase createAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;

    @GetMapping("{id}")
    @PreAuthorize("@customSecurityService.isAccountIdMatching(#id, authentication)")
    public ResponseEntity<GetAccountResponse> getAccount(@PathVariable(value = "id") final long id) {
        final GetAccountResponse response = getAccountUseCase.getAccount(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> createAdmin(@RequestBody @Valid CreateAdminRequest request){
        try {
            CreateAccountResponse response = createAccountUseCase.createAccount(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateAccountResponse> updateAccount(@PathVariable(value = "id") long id,
                                                               @RequestBody @Valid UpdateAdminRequest request){
        UpdateAccountResponse response = updateAccountUseCase.updateAccount(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
