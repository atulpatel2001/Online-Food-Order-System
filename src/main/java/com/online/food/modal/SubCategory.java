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
@Table(name = "tbl_sub_category")
@Builder
public class SubCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subCategory_id")
    private Long subCategoryId;

    @Column(name = "subCategory_name")
    private String subCategoryName;

    @Column(name = "subCategory_discription",length = 6000)
    private String subCategoryDiscription;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "subCategory")
    @JsonBackReference
    private List<Offer> offers;


}
