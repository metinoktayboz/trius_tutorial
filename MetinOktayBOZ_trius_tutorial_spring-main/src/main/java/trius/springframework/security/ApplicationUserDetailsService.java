package trius.springframework.security;


import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import trius.springframework.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import trius.springframework.security.UserAccount;

import java.util.Optional;

@Primary
@Service
public class ApplicationUserDetailsService extends UserAccount implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserAccount> optionalApplicationUser = userRepository.findById(s);
        if(optionalApplicationUser.isPresent()) {
            return new ApplicationUser(optionalApplicationUser.get().getEmail(),optionalApplicationUser.get().getPassword());
        } else
            return null;
    }

    //Bu fonksiyonda nullpointer alıyporum
    public void saveUser(SecurityProperties.User user){
        final String encryptedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);
        userRepository.save(new UserAccount(user.getName(), user.getPassword()));
    }
}
