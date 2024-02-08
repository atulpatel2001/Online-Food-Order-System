package com.online.food.modal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_customer")
@Builder
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_email",unique = true,nullable = false)
    private String customerEmail;

    @Column(name = "customer_password")
    private String customerPassword;

    @Column(name = "customer_role")
    private String customerRole;


    @Column(name = "customer_join-date")
    private LocalDateTime customerJoinDate;

    private boolean enable;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "customer")
    @JsonBackReference
    private Restaurant restaurant;



}
