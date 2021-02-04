package trius.springframework.controllers;


import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import trius.springframework.security.ApplicationUser;
import trius.springframework.security.ApplicationUserDetailsService;

@Controller
public class HomeController {

    ApplicationUserDetailsService userDetailsService = new ApplicationUserDetailsService();

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "login";
    }

    @GetMapping("/sign")
    public String sign(){
        //userDetailsService.saveUser(user);
        return "sign";
    }

}
