package com.crud.hotels.controller;

import com.crud.hotels.domain.Hotel;
import com.crud.hotels.domain.HotelDto;
import com.crud.hotels.mapper.HotelMapper;
import com.crud.hotels.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/hotels")
public class HotelsController {

    private final DbService service;
    private final HotelMapper hotelMapper;

    @Autowired
    public HotelsController(DbService service, HotelMapper hotelMapper) {
        this.service = service;
        this.hotelMapper = hotelMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<HotelDto> getHotels() {
        List<Hotel> hotels = service.getAllHotels();
        return hotelMapper.mapToHotelDtoList(hotels);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public HotelDto getHotel(@PathVariable Long hotelId) throws HotelNotFoundException {
        return hotelMapper.mapToHotelDto(service.getHotel(hotelId).orElseThrow(HotelNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createHotel(@RequestBody HotelDto HotelDto) {
        Hotel Hotel = hotelMapper.mapToHotel(HotelDto);
        service.saveHotel(Hotel);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/available")
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
