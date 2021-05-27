package com.crud.hotels.service;

import com.crud.hotels.domain.Hotel;
import com.crud.hotels.domain.Reservation;
import com.crud.hotels.repository.HotelRepository;
import com.crud.hotels.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {

    private final HotelRepository repository;
    private final ReservationRepository reservationRepository;

    public List<Hotel> getAllHotels() {
        return repository.findAll();
    }

    public Optional<Hotel> getHotel(final Long id){
        return repository.findTaskById(id);
    }

    public Hotel saveHotel(final Hotel task) {
        return repository.save(task);
    }

    public void deleteHotel(final Long taskId){
        repository.deleteById(taskId);
    }

    public void deleteTasks(){
        repository.deleteAll();
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservation(final Long id){
        return reservationRepository.findTaskById(id);
    }

    public Reservation saveReservation(final Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(final Long taskId){
        reservationRepository.deleteById(taskId);
    }

    public void deleteReservations(){
        reservationRepository.deleteAll();
    }



}
