package com.backend.Manager_restaurant.models.enums;

public enum DaysOfWeek {
    Monday(1),
    Tuesday(2),
    Wednesday(3),
    Thursday(4),
    Friday(5),
    Saturday(6),
    Sunday(7);

    private int code;

    private DaysOfWeek(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static DaysOfWeek valueOf(int code) {
        for (DaysOfWeek value : DaysOfWeek.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Day of week code");
    }
}
