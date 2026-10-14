package com.glamora.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

@Service
public class AuthService {
    private final CustomerRepository customers;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Value("${app.auth.secret:change-this-secret-before-deployment-to-a-long-random-value}") private String secret;
    public AuthService(CustomerRepository customers){this.customers=customers;}
    public record AuthResult(String token, Long id, String name, String email) {}
    public AuthResult register(String name,String email,String password){
        String cleanEmail=email==null?"":email.trim().toLowerCase();
        if(name==null||name.trim().length()<2) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Please enter your name.");
        if(!cleanEmail.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Enter a valid email address.");
        if(password==null||password.length()<8) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password must be at least 8 characters.");
        if(customers.existsByEmailIgnoreCase(cleanEmail)) throw new ResponseStatusException(HttpStatus.CONFLICT,"An account with this email already exists.");
        Customer c=customers.save(new Customer(name.trim(),cleanEmail,encoder.encode(password)));
        return result(c);
    }
    public AuthResult login(String email,String password){
        Customer c=customers.findByEmailIgnoreCase(email==null?"":email.trim()).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Email or password is incorrect."));
        if(password==null||!encoder.matches(password,c.getPasswordHash())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Email or password is incorrect.");
        return result(c);
    }
    public String emailFromToken(String token){
        try {
            String[] parts=token.split("\\."); if(parts.length!=2||!constantTimeEquals(parts[1],sign(parts[0]))) throw new Exception();
            String payload=new String(Base64.getUrlDecoder().decode(parts[0]),StandardCharsets.UTF_8);
            String[] fields=payload.split("\\|",3); if(fields.length!=3||Long.parseLong(fields[1])<Instant.now().getEpochSecond()) throw new Exception();
            return fields[2];
        } catch(Exception e){throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Please log in to continue.");}
    }
    private AuthResult result(Customer c){String payload=c.getId()+"|"+(Instant.now().getEpochSecond()+86400)+"|"+c.getEmail();String encoded=Base64.getUrlEncoder().withoutPadding().encodeToString(payload.getBytes(StandardCharsets.UTF_8));return new AuthResult(encoded+"."+sign(encoded),c.getId(),c.getName(),c.getEmail());}
    private String sign(String value){try{Mac mac=Mac.getInstance("HmacSHA256");mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),"HmacSHA256"));return Base64.getUrlEncoder().withoutPadding().encodeToString(mac.doFinal(value.getBytes(StandardCharsets.UTF_8)));}catch(Exception e){throw new IllegalStateException(e);}}
    private boolean constantTimeEquals(String a,String b){return java.security.MessageDigest.isEqual(a.getBytes(StandardCharsets.UTF_8),b.getBytes(StandardCharsets.UTF_8));}
}
