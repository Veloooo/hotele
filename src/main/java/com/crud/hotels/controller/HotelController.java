package com.crud.hotels.controller;

import com.crud.hotels.domain.Hotel;
import com.crud.hotels.dto.HotelDto;
import com.crud.hotels.exception.HotelNotFoundException;
import com.crud.hotels.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/hotels")
public class HotelController {
    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> getHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping(value = "/{hotelId}")
    public Hotel getHotel(@PathVariable Long hotelId) {
        return hotelService.getHotelById(hotelId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createHotel(@Valid @RequestBody HotelDto hotelDto) {
        hotelService.createHotel(hotelDto);
    }

    @PutMapping(path = "/{hotelId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Hotel editHotel(@Valid @RequestBody HotelDto hotelDto, @PathVariable Long hotelId) {
        return hotelService.editHotel(hotelId, hotelDto);
    }

    @DeleteMapping(path = "/{hotelId}")
    public void editHotel(@PathVariable Long hotelId) {
         hotelService.deleteHotel(hotelId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{hotelId}/available")
    public boolean getAvailable(@PathVariable Long hotelId) throws HotelNotFoundException {
        /**
         * Zwrócenie czy w danym hotelu są wolne pokoje
         *
         * Tu trzeba się jeszcze zastanowić o dodanie zapytania o ilu osobowe i w jakim terminie
         */
        return true;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/available")
    public List<HotelDto> getHotelsWithAvailableRooms() {
        /**
         * Zwrócenie listy hoteli w których są dostępne pokoje według zadanych kryteriów
         */
        return new ArrayList<>();
    }

}
