package lk.ijse.greenshadow.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {
    String getUserEmail(String extractedJwtToken);
    boolean isTokenValid(String extractedJwtToken, UserDetails userDetails);
    String generateJwtToken(Map<String,Object> extractClaims, UserDetails userDetails);
    String generateJwtToken(UserDetails userEntity);
}
