package lk.riyapola.riyapola.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import lk.riyapola.riyapola.entity.Admin;
import lk.riyapola.riyapola.entity.Customer;
import lk.riyapola.riyapola.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTTokenGenerator {
    final CustomerRepo customerRepo;
    @Value("${riyapola.app.jwtSecret}")
    private String jwtSecret;
    @Value("${riyapola.app.jwtExpirationMs}")
    private int jwtExpirationMs;
@Autowired
    public JWTTokenGenerator(CustomerRepo customerRepo) {

        this.customerRepo = customerRepo;
    }

    public String generateJwtToken(Admin adminDTO) {
        return Jwts.builder()
                .setId(String.valueOf(adminDTO.getAdmin_id()))
                .setSubject((adminDTO.getUserName()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateJwtToken(Customer customer) {
        System.out.println("jwt id ============= " + customer);
        return Jwts.builder()
                .setId(String.valueOf(customer.getCustomer_id()))
                .setSubject((customer.getUserName()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    public boolean validateJwtToken(String authToken) {
        String jwtToken = authToken.substring("Bearer".length());
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(jwtToken);
            return true;
        } catch (Exception e) {
            System.out.println("Error when generating token...");
        }

        return false;
    }

    public Customer getCustomerFromJwtToken(String token) {
        String jwtToken = token.substring("Bearer".length());
        String id = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(jwtToken).getBody().getId();
        Integer customerId = Integer.parseInt(id);
        return customerRepo.getCustomerByCustomerId(customerId);
    }
}
