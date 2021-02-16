package trius.springframework.converters;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import trius.springframework.commands.ProductForm;
import trius.springframework.commands.UserForm;
import trius.springframework.domain.Product;
import trius.springframework.security.UserAccount;

@Component
public class UserFormtoUserAccount implements Converter<UserForm, UserAccount> {
    @Override
    public UserAccount convert(UserForm userForm) {
        UserAccount userAccount = new UserAccount();

        userAccount.setEmail(userForm.getEmail());
        userAccount.setPassword(userForm.getPassword());

        return userAccount;
    }
}
