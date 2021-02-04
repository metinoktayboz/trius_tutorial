package trius.springframework.security;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.userdetails.User;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


public class ApplicationUser extends  User {
    public ApplicationUser(String email, String password) {
       super(email,password, new ArrayList<>());
    }

}
