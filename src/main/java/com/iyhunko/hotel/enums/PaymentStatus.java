package com.iyhunko.hotel.enums;

import java.util.Arrays;

public enum PaymentStatus {
    CREATED("created"),
    PENDING("pending"),
    FAILURE("failure"),
    SUCCESS("success");

    private final String name;

    PaymentStatus(String name) {
        this.name = name;
    }

    public static boolean isInEnum(String value) {
        return Arrays.stream(PaymentStatus.class.getEnumConstants()).anyMatch(e -> e.name().equals(value));
    }

    public String getName() {
        return name;
    }
}
