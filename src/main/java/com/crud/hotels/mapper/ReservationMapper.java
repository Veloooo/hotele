package com.crud.hotels.mapper;

import com.crud.hotels.domain.Reservation;
import com.crud.hotels.domain.ReservationDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationMapper {
    public Reservation mapToReservation(final ReservationDto ReservationDto) {
        return new Reservation(
                ReservationDto.getId()
        );
    }

    public ReservationDto mapToReservationDto(final Reservation Reservation) {
        return new ReservationDto(
                Reservation.getId()
        );
    }

    public List<ReservationDto> mapToReservationDtoList(final List<Reservation> ReservationList) {
        return ReservationList.stream()
                .map(this::mapToReservationDto)
                .collect(Collectors.toList());
    }

}
