package trius.springframework.security;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import trius.springframework.repositories.UserRepository;

import javax.annotation.PostConstruct;

@Component
public class InitializeUsers {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        //userRepository.save(new UserAccount("mob@gmail.com", passwordEncoder.encode("12345")));
    }

}
