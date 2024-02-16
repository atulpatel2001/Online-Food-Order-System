package com.online.food.modal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_Id")
    private String orderId;

    @Column(name = "order_Status")
    private String orderStatus;

    @Column(name = "product_Quantity")
    private String productQuantity;
    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "shipped_Date")
    private LocalDate shippedDate;
    @Column(name = "outOfDelivery_Date")
    private LocalDate outOfDeliveryDate;

    @Column(name = "delivered_Date")
    private LocalDate deliveredDate;

    @ManyToOne
    @JoinColumn(name = "customer_Id")
    @JsonBackReference
    private Customer customer;


    @OneToOne
    @JoinColumn(name = "transaction_Id")
    @JsonBackReference
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "product_Id")
    @JsonBackReference
    private Product product;

}
