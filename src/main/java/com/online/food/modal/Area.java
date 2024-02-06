package com.online.food.modal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_area")
@Builder
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long areaId;

    @Column(name = "area-name")
    private String areaName;

    @Column(name = "area_discription",length = 6000)
    private String areaDiscription;
    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonBackReference
    private City city;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "area")
    @JsonBackReference
    private List<Restaurant> restaurants ;
}
