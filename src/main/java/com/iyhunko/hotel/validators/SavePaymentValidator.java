package com.iyhunko.hotel.validators;

import com.iyhunko.hotel.models.Payment;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SavePaymentValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Payment.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Payment payment = (Payment) object;

        if (payment.getBooking() == null) {
            errors.rejectValue("room", "", "The room field can't be empty.");
        }

        if (payment.getStatus() == null) {
            errors.rejectValue("status", "", "The status field can't be empty.");
        }

        if (payment.getSum() == null) {
            errors.rejectValue("sum", "", "The sum field can't be empty.");
        }

        if (payment.getCurrency() == null) {
            errors.rejectValue("currency", "", "The currency field can't be empty.");
        }

        if (payment.getExpireAt() == null) {
            errors.rejectValue("expireAt", "", "The expire at field can't be empty.");
        }
    }

}
