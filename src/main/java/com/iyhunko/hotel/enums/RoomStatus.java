package com.iyhunko.hotel.enums;

import java.util.Arrays;

public enum RoomStatus {
    FREE("free"),
    BOOKED("booked"),
    LOCKED("locked"),
    OCCUPIED("occupied");

    private final String name;

    RoomStatus(String name) {
        this.name = name;
    }

    public static boolean isInEnum(String value) {
        return Arrays.stream(RoomStatus.class.getEnumConstants()).anyMatch(e -> e.name().equals(value));
    }

    public String getName() {
        return name;
    }
}
