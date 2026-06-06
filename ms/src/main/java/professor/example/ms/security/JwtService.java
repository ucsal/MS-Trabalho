package professor.example.ms.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
 
import java.security.Key;
import java.util.Base64;
import java.util.Date;
 
@Service
public class JwtService {
 
    @Value("${jwt.secret}")
    private String secret;
 
    private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
 
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }
 
    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }
 
    public boolean isTokenValid(String token) {
        try {
            return !getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
 
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
