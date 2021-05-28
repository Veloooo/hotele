package com.crud.hotels.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {
    @NotNull
    private String name;

    @NotNull
    private String country;

    @NotNull
    private String city;
}
