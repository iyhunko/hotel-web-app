package com.iyhunko.hotel.enums;

import java.util.Arrays;

public enum BookingStatus {
    CREATED("created"),
    REQUESTED("requested"),
    PAID("paid"),
    APPROVED("approved");

    private final String name;

    BookingStatus(String name) {
        this.name = name;
    }

    public static boolean isInEnum(String value) {
        return Arrays.stream(BookingStatus.class.getEnumConstants()).anyMatch(e -> e.name().equals(value));
    }

    public String getName() {
        return name;
    }
}
