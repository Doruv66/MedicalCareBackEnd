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
@RequestMapping("/doctors")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class DoctorController {

    private final GetAccountUseCase getAccountUseCase;
    private final GetDoctorsUseCase getDoctorsUseCase;
    private final GetTop3DoctorsByRatingUseCase getTop3DoctorsByRatingUseCase;
    private final GetDoctorsByKeywordUseCase getDoctorsByKeywordUseCase;
    private final CreateAccountUseCase createAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;

    @GetMapping("{id}")
    public ResponseEntity<GetAccountResponse> getAccount(@PathVariable(value = "id") final long id) {
        final GetAccountResponse response = getAccountUseCase.getAccount(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/doctors")
    public ResponseEntity<GetDoctorsResponse> getDoctors(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "6") int pageSize
    ) {
        GetDoctorsResponse response = getDoctorsUseCase.getDoctors(pageNumber, pageSize);
        return ResponseEntity.ok(response);
    }

    @GetMapping("top")
    public ResponseEntity<GetAccountsResponse> getTop3Doctors() {
        GetAccountsResponse response = getTop3DoctorsByRatingUseCase.getTop3DoctorsByRating();
        return ResponseEntity.ok(response);
    }

    @GetMapping("search")
    public ResponseEntity<GetAccountsResponse>
    getDoctorsByKeyword(@RequestParam("keyword") final String keyword) {
        GetAccountsResponse response = getDoctorsByKeywordUseCase.getDoctorsByKeyword(keyword);
        return ResponseEntity.ok(response);
    }

    @RolesAllowed({"ADMIN"})
    @PostMapping
    public ResponseEntity<?> createDoctor(@RequestBody @Valid CreateDoctorRequest request){
        try {
            CreateAccountResponse response = createAccountUseCase.createAccount(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());
        }
    }

    @RolesAllowed({"ADMIN"})
    @PutMapping("{id}")
    public ResponseEntity<UpdateAccountResponse> updateDoctor(@PathVariable(value = "id") long id,
                                                              @RequestBody @Valid UpdateDoctorRequest request){
        UpdateAccountResponse response = updateAccountUseCase.updateAccount(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
