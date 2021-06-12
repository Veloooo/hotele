package com.crud.hotels.controller;

import com.crud.hotels.domain.Hotel;
import com.crud.hotels.dto.HotelDto;
import com.crud.hotels.dto.UserDto;
import com.crud.hotels.exception.HotelNotFoundException;
import com.crud.hotels.service.HotelService;
import com.crud.hotels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path="/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void login(@Valid @RequestBody UserDto userDto) {
        userService.login(userDto);
    }

    @PostMapping(path="/logout", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void logout(@Valid @RequestBody UserDto userDto) {
        userService.logout(userDto);
    }


    @GetMapping(path = "/{id}")
    public UserDto getUser() {
        return userService.getAllHotels();
    }

    @GetMapping(value = "/{hotelId}")
    public Hotel getHotel(@PathVariable Long hotelId) {
        return hotelService.getHotelById(hotelId);
    }


    @PutMapping(path = "/{hotelId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Hotel editHotel(@Valid @RequestBody HotelDto hotelDto, @PathVariable Long hotelId) {
        return hotelService.editHotel(hotelId, hotelDto);
    }

    @DeleteMapping(path = "/{hotelId}")
    public void editHotel(@PathVariable Long hotelId) {
         hotelService.deleteHotel(hotelId);
    }

    @GetMapping(path = "/{hotelId}/available")
    public boolean getAvailable(@PathVariable Long hotelId) throws HotelNotFoundException {
        /**
         * Zwrócenie czy w danym hotelu są wolne pokoje
         *
         * Tu trzeba się jeszcze zastanowić o dodanie zapytania o ilu osobowe i w jakim terminie
         */
        return true;
    }

    @GetMapping(path = "/available")
    public List<HotelDto> getHotelsWithAvailableRooms() {
        /**
         * Zwrócenie listy hoteli w których są dostępne pokoje według zadanych kryteriów
         */
        return new ArrayList<>();
    }

}
