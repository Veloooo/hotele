package com.crud.hotels.controller;

import com.crud.hotels.dto.ReservationDto;
import com.crud.hotels.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/hotels/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ReservationDto getReservation(@PathVariable Long reservationId) {
        /**
         * Zwrócenie rezerwacji
         */
       return null;
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
       return;
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReservationDto editReservation(@RequestBody ReservationDto reservationDto) {
        return null;
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/")
    public void deleteReservation(@PathVariable Long reservationId) {
        return;
    }

    /**
     * ?????????????????????????????????????????
     * GET
     * /hotels/reservations/{id}/share - wysłanie rezerwacji na ustalony host i port
     * To bedzie oddzielny kontroler
     */

}
