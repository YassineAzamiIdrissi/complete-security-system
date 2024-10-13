package com.security.robust.api.security.system.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.lang.System.currentTimeMillis;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = userDetails.getUsername();
        return !isTokenExpired(token) && username.equals(extractSubject(token));
    }

    public boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    private Date extractExpirationDate(String token) {
        return extractSpecificClaim(token, Claims::getExpiration);
    }

    public String extractSubject(String token) {
        return extractSpecificClaim(token, Claims::getSubject);
    }

    private <T> T extractSpecificClaim(String token, Function<Claims, T> resolver) {
        Claims allClaim = extractAllClaims(token);
        return resolver.apply(allClaim);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().
                setSigningKey(generateSignInKey()).
                build().
                parseClaimsJws(token).
                getBody();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails, new HashMap<>());
    }

    public String generateToken(UserDetails userDetails,
                                Map<String, Object> extraClaims) {
        return buildToken(userDetails, extraClaims, jwtExpiration);
    }

    private String buildToken(
            UserDetails userDetails,
            Map<String, Object> extraClaims,
            long jwtExp
    ) {
        var auths = userDetails.
                getAuthorities().stream().
                map(GrantedAuthority::getAuthority).
                toList();
        System.out.println("DISSIZ THE EMAAIIIIIL :::: ");
        System.out.println(userDetails.getUsername());
        System.out.println("DISSIZ THE EMAAIIIIIL :::: ");
        return Jwts.builder().
                setClaims(extraClaims).
                setSubject(userDetails.getUsername()).
                setIssuedAt(new Date(currentTimeMillis())).
                setExpiration(new Date(currentTimeMillis() + jwtExp)).
                signWith(generateSignInKey()).
                claim("authorities", auths).
                compact();
    }

    private Key generateSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
