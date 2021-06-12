package com.crud.hotels.domain;

import com.crud.hotels.strategy.BookingStrategy;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "rooms")
@RequiredArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NonNull
    private Integer floor;

    @Column
    @NonNull
    private Integer peopleSize;

    @Column
    @NonNull
    private Boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
