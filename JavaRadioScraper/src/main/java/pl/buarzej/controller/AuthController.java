package pl.buarzej.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> me(Authentication auth) {
        if (auth == null) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(Map.of(
                "username", auth.getName(),
                "role", auth.getAuthorities().iterator().next().getAuthority()
        ));
    }
}
