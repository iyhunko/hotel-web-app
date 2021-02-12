package com.iyhunko.hotel.validators;

import com.iyhunko.hotel.models.Booking;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SaveBookingValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Booking.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Booking booking = (Booking) object;

        if (booking.getRoom() == null) {
            errors.rejectValue("room", "", "The room field can't be empty.");
        }

        if (booking.getStatus() == null) {
            errors.rejectValue("status", "", "The status field can't be empty.");
        }
    }
}
