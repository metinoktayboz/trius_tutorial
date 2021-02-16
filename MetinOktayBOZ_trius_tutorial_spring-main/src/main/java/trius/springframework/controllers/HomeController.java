package trius.springframework.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import trius.springframework.commands.ProductForm;
import trius.springframework.commands.UserForm;
import trius.springframework.domain.Product;
import trius.springframework.security.ApplicationUser;
import trius.springframework.security.ApplicationUserDetailsService;
import trius.springframework.security.UserAccount;
import trius.springframework.security.UserService;
import trius.springframework.services.ProductService;

import javax.validation.Valid;

@Controller
public class HomeController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "login";
    }

    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    public String sign(Model model){
        model.addAttribute("userForm", new UserForm());
        //userDetailsService.saveUser(user);
        return "sign";
    }

    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult result, Model model){
        model.addAttribute("userForm", userForm);

        if(result.hasErrors()){
            return "sign";
        }

        userService.saveUserForm(userForm);

        return "redirect:/login";
    }

}
