package pl.buarzej.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/admin")
    public String getAdmin() {
        return "Hello admin!";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "Hello login!";
    }
}
