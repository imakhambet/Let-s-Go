package cz.macha.spring.validation;

import cz.macha.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class RegValidation {

    private final UserService userService;

    private List<String> errors = new ArrayList<>();

    @Autowired
    public RegValidation(UserService userService) {
        this.userService = userService;
    }


    public void validation(String username, String email, String password){
        username(username);
        email(email);
        password(password);
    }

    void username(String username) {
        if (username.length() < 6)
            errors.add("USERNAME MUST BE AT LEAST 6 CHARACTERS");
        else if (userService.getUserByLogin(username) != null)
            errors.add("USERNAME " + username + " ALREADY TAKEN");

    }

    void email(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find())
            errors.add("EMAIL IS NOT CORRECT");

    }

    void password(String password) {
        if (password.length() < 6)
            errors.add("PASSWORD MUST BE AT LEAST 6 CHARACTERS");
    }


    public List<String> getErrors() {
        return errors;
    }
}
