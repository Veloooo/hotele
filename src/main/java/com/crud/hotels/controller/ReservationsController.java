package com.crud.hotels.controller;

import com.crud.hotels.domain.Hotel;
import com.crud.hotels.domain.HotelDto;
import com.crud.hotels.domain.Reservation;
import com.crud.hotels.domain.ReservationDto;
import com.crud.hotels.mapper.HotelMapper;
import com.crud.hotels.mapper.ReservationMapper;
import com.crud.hotels.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/hotels/reservations")
public class ReservationsController {

    private final DbService service;
    private final HotelMapper hotelMapper;
    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationsController(DbService service, HotelMapper hotelMapper, ReservationMapper reservationMapper) {
        this.service = service;
        this.hotelMapper = hotelMapper;
        this.reservationMapper = reservationMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ReservationDto getReservation(@PathVariable Long reservationId) {
        /**
         * Zwrócenie rezerwacji
         */
        return new ReservationDto(1L);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<ReservationDto> getReservations() {
        /**
         * Zwrócenie wszystkich rezerwacji ??????????????????????????????????????
         */
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createReservation(@RequestBody ReservationDto reservationDto) {
        service.saveReservation(reservationMapper.mapToReservation(reservationDto));
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReservationDto editReservation(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationMapper.mapToReservation(reservationDto);
        Reservation savedReservation = service.saveReservation(reservation);
        return reservationMapper.mapToReservationDto(savedReservation);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/")
    public void deleteReservation(@PathVariable Long id) {
        service.deleteReservation(id);
    }

    /**
     * ?????????????????????????????????????????
     * GET
     * /hotels/reservations/{id}/share - wysłanie rezerwacji na ustalony host i port
     * ?????????????????????????????????????????
     */

}
