package com.crud.hotels.backend.service;

import com.crud.hotels.backend.domain.Hotel;
import com.crud.hotels.backend.dto.HotelDto;
import com.crud.hotels.backend.exception.EntityNotFoundException;
import com.crud.hotels.backend.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    private HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    private static boolean accept(Hotel hotel) {
        return hotel.getFreeRooms() > 0;
    }

    @Transactional(readOnly = true)
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Hotel getHotelById(Long hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Hotel> getAllHotelsWithFreeRooms() {
        return hotelRepository.getAllHotelsWithFreeRooms();
    }

    @Transactional(readOnly = true)
    public boolean checkIfHotelAvailable(Long id) {
        return hotelRepository.getHotelById(id)
                .orElseThrow(EntityNotFoundException::new)
                .getFreeRooms() > 0;
    }

    @Transactional
    public void createHotel(HotelDto hotelDto) {
        Hotel hotel = new Hotel(hotelDto.getName(), hotelDto.getCountry(), hotelDto.getCity(), hotelDto.getTotalRooms(), hotelDto.getTotalRooms());
        hotelRepository.save(hotel);
    }

    @Transactional
    public Hotel editHotel(Long id, HotelDto hotelDto) {
        Hotel hotel = getHotelById(id);
        hotel.setName(hotelDto.getName());
        hotel.setCity(hotelDto.getCity());
        hotel.setCountry(hotelDto.getCountry());
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long hotelId) {
        try {
            hotelRepository.deleteById(hotelId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException();
        }
    }
}
