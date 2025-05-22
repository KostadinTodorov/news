package bg.tuvarna.sit.newsblog.security;

import bg.tuvarna.sit.newsblog.exception.ApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String JWTSecret;

    @Value("${jwt.expiration-milliseconds}")
    private int JWTExpirationDate;

    public String generateToken(Authentication authentication) {
        String userName = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime()+JWTExpirationDate);

        String token = Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
        return  token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWTSecret));
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        }
        catch (MalformedJwtException ex) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        }
        catch (ExpiredJwtException ex) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        }
        catch (UnsupportedJwtException ex) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        }
        catch (IllegalArgumentException ex) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "JWT string claims is empty");
        }
    }
}