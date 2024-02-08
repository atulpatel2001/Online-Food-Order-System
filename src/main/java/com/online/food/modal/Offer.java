package com.online.food.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_offer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private Long offerId;
    @Column(name = "offer_name")
    private String offerName;
    @Column(name = "offer_discription",length = 6000)
    private String offerDescription;
    @Column(name = "offer_discount")
    private Double offerDiscount;
    @Column(name = "offer_start_date")
    private LocalDate startDate;
    @Column(name = "offer_end_date")
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "sub-category_id")
    private SubCategory subCategory;
}
