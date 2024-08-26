package com.personal.flightsearch.mappers;

public interface Mapper <T, U> {
    U map(T t);
    T reverseMap(U u);
}
