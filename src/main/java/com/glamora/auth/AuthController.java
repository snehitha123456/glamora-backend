package com.glamora.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/api/auth")
public class AuthController {
    private final AuthService auth;
    public AuthController(AuthService auth){this.auth=auth;}
    public record RegisterRequest(@NotBlank @Size(min=2,max=100) String name,@Email @NotBlank String email,@NotBlank @Size(min=8,max=72) String password){}
    public record LoginRequest(@Email @NotBlank String email,@NotBlank String password){}
    @PostMapping("/register") public AuthService.AuthResult register(@Valid @RequestBody RegisterRequest r){return auth.register(r.name(),r.email(),r.password());}
    @PostMapping("/login") public AuthService.AuthResult login(@Valid @RequestBody LoginRequest r){return auth.login(r.email(),r.password());}
    @GetMapping("/me") public Map<String,String> me(@RequestHeader(value="Authorization",required=false) String header){String email=auth.emailFromToken(token(header));return Map.of("email",email);}
    public static String token(String header){if(header==null||!header.startsWith("Bearer ")) return "";return header.substring(7).trim();}
}
