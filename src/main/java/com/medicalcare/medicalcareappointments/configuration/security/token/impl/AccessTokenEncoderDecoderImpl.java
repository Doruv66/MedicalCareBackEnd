package com.medicalcare.medicalcareappointments.configuration.security.token.impl;

import com.medicalcare.medicalcareappointments.configuration.security.token.AccessToken;
import com.medicalcare.medicalcareappointments.configuration.security.token.AccessTokenDecoder;
import com.medicalcare.medicalcareappointments.configuration.security.token.AccessTokenEncoder;
import com.medicalcare.medicalcareappointments.configuration.security.token.exception.InvalidAccessTokenException;
import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccessTokenEncoderDecoderImpl implements AccessTokenDecoder, AccessTokenEncoder {
    private final Key key;

    public AccessTokenEncoderDecoderImpl (@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
    @Override
    public AccessToken decode(String accessTokenEncoded){
        try {
            Jwt<?, Claims> jwt = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(accessTokenEncoded);
            Claims claims = jwt.getBody();

            Long accountId = claims.get("accountId", Long.class);
            String role = claims.get("role", String.class);

            return new AccessTokenImpl(claims.getSubject(), accountId, AccountType.valueOf(role));
        } catch (JwtException e) {
            throw new InvalidAccessTokenException(e.getMessage());
        }
    }

    @Override
    public String encode(AccessToken accessToken) {
        Map<String, Object> claimsMap = new HashMap<>();
        if ((accessToken.getRole()) != null) {
            claimsMap.put("role", accessToken.getRole());
        }
        if (accessToken.getAccountId() != null) {
            claimsMap.put("accountId", accessToken.getAccountId());
        }

        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(accessToken.getSubject())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .addClaims(claimsMap)
                .signWith(key)
                .compact();
    }
}
