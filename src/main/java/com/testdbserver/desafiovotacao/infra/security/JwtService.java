package com.testdbserver.desafiovotacao.infra.security;

import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.utils.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET = "214125442A472D4B6150645367556B58703273357638792F423F4528482B4D62";

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(Associate associate) {
        return generateToken(new HashMap<>(), associate);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            Associate associate
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(associate.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + DateUtils.hour * 24))
                .compact();
    }

    public boolean isTokenValid(String token, Associate associate) {
        final String username = extractUsername(token);
        return (username.equals(associate.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Key getSignInKey() {
         byte[] key = Decoders.BASE64.decode(SECRET);
         return Keys.hmacShaKeyFor(key);
    }
}
