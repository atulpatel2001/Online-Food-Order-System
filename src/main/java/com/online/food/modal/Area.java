package com.online.food.modal;

import jakarta.persistence.*;
import lombok.*;

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
    private City city;
}
