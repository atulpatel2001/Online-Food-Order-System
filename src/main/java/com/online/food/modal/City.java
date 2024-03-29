package com.online.food.modal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_city")
@Builder
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long cityId;
    @Column(name = "city_name")
    private String cityName;

    @Column(name = "city_discription",length = 6000)
    private String cityDiscription;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "city")
    @JsonBackReference
    private List<Area> areas;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "city")
    @JsonBackReference
    private List<Restaurant> restaurants;


}
