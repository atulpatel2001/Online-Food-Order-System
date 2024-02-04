package com.online.food.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_sub_category")
@Builder
public class SubCategory {

    private Long subCategoryId;

    private String subCategoryName;

    private String subCategoryDiscription;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
