package com.nesa.springboot_rms.common.utils;

public interface Mapper<D, E> {
    E toEntity(D source);
    D toDomain(E source);
}
