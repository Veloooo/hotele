package com.crud.hotels.mapper;

import com.crud.hotels.domain.Hotel;
import com.crud.hotels.domain.HotelDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelMapper {
    public Hotel mapToHotel(final HotelDto HotelDto) {
        return new Hotel(
                HotelDto.getId()
        );
    }

    public HotelDto mapToHotelDto(final Hotel Hotel) {
        return new HotelDto(
                Hotel.getId()
        );
    }

    public List<HotelDto> mapToHotelDtoList(final List<Hotel> HotelList) {
        return HotelList.stream()
                .map(this::mapToHotelDto)
                .collect(Collectors.toList());
    }

}
