package com.iyhunko.hotel.validators;

import com.iyhunko.hotel.models.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;

        if (user.getEmail().equals("") || user.getEmail() == null) {
            errors.rejectValue("email", "", "The email field cannot be empty.");
        }

        if (user.getFirstname().equals("") || user.getFirstname() == null) {
            errors.rejectValue("firstname", "", "The firstname field cannot be empty.");
        }

        if (user.getLastname().equals("") || user.getLastname() == null) {
            errors.rejectValue("lastname", "", "The lastname field cannot be empty.");
        }

        if (user.getPhone() != null && user.getPhone().length() < 5) {
            errors.rejectValue("phone", "", "The phone field must have at least 5 symbols.");
        }
    }
}
