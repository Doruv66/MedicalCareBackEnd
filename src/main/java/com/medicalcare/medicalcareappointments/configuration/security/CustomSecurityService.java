package com.medicalcare.medicalcareappointments.configuration.security;

import com.medicalcare.medicalcareappointments.configuration.security.token.AccessToken;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomSecurityService {

    @Autowired
    private AccessToken accessToken;

    public boolean isAccountIdMatching(Long accId, Authentication authentication) {

        //check for null
        if(authentication == null || this.accessToken == null) {
            return false;
        }

        //return if account Id matches
        Long accountIdFromToken = this.accessToken.getAccountId();
        return accId != null && accountIdFromToken != null && accId.equals(accountIdFromToken);
    }
}
