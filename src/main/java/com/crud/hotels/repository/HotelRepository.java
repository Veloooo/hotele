package com.crud.hotels.repository;

import com.crud.hotels.domain.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
    List<Hotel> findAll();

    Optional<Hotel> findTaskById(Long id);

    Hotel save(Hotel task);

    void deleteById(Long taskId);

    void deleteAll();


}
