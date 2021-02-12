package com.iyhunko.hotel.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.servlet.http.HttpServletRequest;

public class UpdateProfileValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return HttpServletRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        HttpServletRequest request = (HttpServletRequest) object;

        if (request.getParameter("email") == null || request.getParameter("email").equals("")) {
            errors.rejectValue("email", "", "The email field can't be empty.");
        }

        if (request.getParameter("firstname") == null || request.getParameter("firstname").equals("")) {
            errors.rejectValue("firstname", "", "The firstname field can't be empty.");
        }

        if (request.getParameter("lastname") == null || request.getParameter("lastname").equals("")) {
            errors.rejectValue("lastname", "", "The lastname field can't be empty.");
        }

        if (request.getParameter("phone") == null || request.getParameter("phone").equals("")) {
            errors.rejectValue("phone", "", "The phone field can't be empty.");
        }

        if (request.getParameter("password") != null && !request.getParameter("password").equals("")) {
            if (!request.getParameter("password").equals(request.getParameter("repeat_password"))) {
                errors.rejectValue("password", "", "The password and repeat password must be the same.");
            }
            if (request.getParameter("password").length() < 8) {
                errors.rejectValue("password", "", "The password field value must have at least 8 chars.");
            }
        } else if (request.getParameter("repeat_password") != null && !request.getParameter("repeat_password").equals("")) {
            if (!request.getParameter("repeat_password").equals(request.getParameter("password"))) {
                errors.rejectValue("repeat_password", "", "The password and repeat password must be the same.");
            }
            if (request.getParameter("repeat_password").length() < 8) {
                errors.rejectValue("repeat_password", "", "The repeat password field value must have at least 8 chars.");
            }
        }
    }
}
