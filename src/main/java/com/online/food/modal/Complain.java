package com.online.food.modal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_complain")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "complain_id")
    private Long complainId;

    @Column(name = "complain_subject")
    private String complainSubject;

    @Column(name = "complain_description")
    private String complainDescription;

    @Column(name = "attachment_id")
    private String attachment;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
