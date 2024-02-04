package com.online.food.modal;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_category")
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_discription",length = 6000)
    private String categoryDiscription;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<SubCategory> subCategories;
}
