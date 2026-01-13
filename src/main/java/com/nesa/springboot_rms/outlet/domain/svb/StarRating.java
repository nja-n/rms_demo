package com.nesa.springboot_rms.outlet.domain.svb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StarRating {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

    private final int value;

    StarRating(int value) {
        this.value = value;
    }

    @JsonValue // Tells Jackson to send the number 5 to Flutter, not "FIVE"
    public int getValue() {
        return value;
    }

    @JsonCreator // Tells Jackson to convert the number 5 from Flutter into StarRating.FIVE
    public static StarRating fromInt(int value) {
        for (StarRating rating : StarRating.values()) {
            if (rating.value == value) {
                return rating;
            }
        }
        throw new IllegalArgumentException("Invalid Star Rating: " + value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}