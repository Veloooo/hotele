package com.crud.hotels.repository;

import com.crud.hotels.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query(value = "SELECT * FROM hotels WHERE free_rooms > 0", nativeQuery = true)
    List<Hotel> getAllHotelsWithFreeRooms();

    Optional<Hotel> getHotelById(Long id);
}
