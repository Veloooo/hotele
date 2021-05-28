package com.crud.hotels.service;

import com.crud.hotels.domain.Hotel;
import com.crud.hotels.dto.HotelDto;
import com.crud.hotels.exception.HotelNotFoundException;
import com.crud.hotels.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//TODO add automatic mapper from dto to dao
//TODO add automatic mapper from dao to dto
// at this moment controller return dao but it doesn't matter for now

@Service
@RequiredArgsConstructor
public class HotelService {
    private HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Transactional(readOnly = true)
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Hotel getHotelById(Long hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new HotelNotFoundException());
    }

    @Transactional
    public void createHotel(HotelDto hotelDto) {
        Hotel hotel = new Hotel(hotelDto.getName(), hotelDto.getCountry(), hotelDto.getCity());
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
            throw new HotelNotFoundException();
        }
    }

//    public List<Hotel> getAllHotels() {
//        return repository.findAll();
//    }
//
//    public Optional<Hotel> getHotel(final Long id){
//        return repository.findTaskById(id);
//    }
//
//    public Hotel saveHotel(final Hotel task) {
//        return repository.save(task);
//    }
//
//    public void deleteHotel(final Long taskId){
//        repository.deleteById(taskId);
//    }
//
//    public void deleteTasks(){
//        repository.deleteAll();
//    }
//
//    public List<Reservation> getAllReservations() {
//        return reservationRepository.findAll();
//    }
//
//    public Optional<Reservation> getReservation(final Long id){
//        return reservationRepository.findTaskById(id);
//    }
//
//    public Reservation saveReservation(final Reservation reservation) {
//        return reservationRepository.save(reservation);
//    }
//
//    public void deleteReservation(final Long taskId){
//        reservationRepository.deleteById(taskId);
//    }
//
//    public void deleteReservations(){
//        reservationRepository.deleteAll();
//    }
}
//    public Hotel mapToHotel(final HotelDto HotelDto) {
//        return new Hotel(
//                HotelDto.getId()
//        );
//    }
//
//    public HotelDto mapToHotelDto(final Hotel Hotel) {
//        return new HotelDto(
//                Hotel.getId()
//        );
//    }
//
//    public List<HotelDto> mapToHotelDtoList(final List<Hotel> HotelList) {
//        return HotelList.stream()
//                .map(this::mapToHotelDto)
//                .collect(Collectors.toList());
//    }
