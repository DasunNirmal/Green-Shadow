package lk.ijse.greenshadow.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.ijse.greenshadow.service.JWTService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {
    @Value("${spring.jwtKey}")
    private String jwtKey;

    @Override
    public String getUserEmail(String extractedJwtToken) {
        return extractClaim(extractedJwtToken, Claims::getSubject);
    }

    private <T> T extractClaim(String extractedJwtToken, Function<Claims,T> claimResolve) {
        final Claims claims = extractAllClaims(extractedJwtToken);
        return claimResolve.apply(claims);
    }

    private Claims extractAllClaims(String extractedJwtToken) {
        return Jwts.parser().setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(extractedJwtToken)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] bytes = Decoders.BASE64.decode(jwtKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String generateJwtToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateJwtToken(UserDetails userDetails) {
        return generateJwtToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String extractedJwtToken, UserDetails userDetails) {
        final String username = getUserEmail(extractedJwtToken);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(extractedJwtToken));
    }

    private boolean isTokenExpired(String extractedJwtToken) {
        return extractExpiration(extractedJwtToken).before(new Date());
    }

    private Date extractExpiration(String extractedJwtToken) {
        return extractClaim(extractedJwtToken, Claims::getExpiration);
    }
}
