package lk.riyapola.riyapola.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.entity.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTTokenGenerator {
    @Value("${riyapola.app.jwtSecret}")
    private String jwtSecret;
    @Value("${riyapola.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Admin adminDTO) {
        return Jwts.builder()
                .setId(String.valueOf(adminDTO.getAdmin_id()))
                .setSubject((adminDTO.getUserName()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateJwtToken(Customer customerDTO) {
        return Jwts.builder()
                .setId(String.valueOf(customerDTO.getCustomer_id()))
                .setSubject((customerDTO.getUserName()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    public boolean validateJwtToken(String authToken) {
        String jwtToken = authToken.substring("Bearer ".length());
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(jwtToken);
            return true;
        } catch (Exception e) {
            System.out.println("Error when generating token...");
        }

        return false;
    }
}
