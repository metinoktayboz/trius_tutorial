package trius.springframework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import trius.springframework.commands.UserForm;
import trius.springframework.converters.UserFormtoUserAccount;
import trius.springframework.repositories.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserFormtoUserAccount userFormtoUserAccount;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserFormtoUserAccount userFormtoUserAccount){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userFormtoUserAccount = userFormtoUserAccount;
    }

    public void saveUserForm(UserForm userForm) {
        saveUser(userFormtoUserAccount.convert(userForm));

    }


    public void saveUser(UserAccount user){
        final String encryptedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);
        userRepository.save(new UserAccount(user.getEmail(), user.getPassword()));
    }
}
