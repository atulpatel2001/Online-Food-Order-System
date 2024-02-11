package com.online.food.modal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_restaurant")
@Builder
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long restaurantId;
    @Column(name = "restaurant_name")
    private String restaurantName;
    @Column(name = "phone_number")
    private Long restaurantPhoneNumber;

    @Column(name = "restaurant_address",length = 2000)
    private String restaurantAddress;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "restaurant")
    @JsonBackReference
    private List<Product> products;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "restaurant")
    @JsonBackReference
    private List<Offer> offers;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "restaurant")
    @JsonBackReference
    private List<Complain> complain;



}
