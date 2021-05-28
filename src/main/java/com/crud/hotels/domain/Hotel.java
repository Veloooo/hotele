package com.crud.hotels.domain;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "hotels")
@RequiredArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NonNull
    private String name;

    @Column
    @NonNull
    private String country;

    @Column
    @NonNull
    private String city;
}
