package com.nesa.springboot_rms.common.supporter;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Id implements Serializable{
    private final Long value;

    public Id(Object value) {
        if (value instanceof Number) {
            this.value = ((Number) value).longValue();
        } else if (value instanceof String) {
            this.value = Long.parseLong((String) value);
        } else {
            this.value = null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id that = (Id) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    @JsonValue
    public String toString() {
        return value != null ? value.toString() : "";
    }
}
