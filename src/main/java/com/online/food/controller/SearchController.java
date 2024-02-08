package com.online.food.controller;

import com.online.food.modal.Area;
import com.online.food.modal.SubCategory;
import com.online.food.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private CityService cityService;

    @Autowired
    private AreaService areaService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private RestaurantService restaurantService;

    //Search City
    @GetMapping("/search-city/{cityName}")
    public ResponseEntity<?> searchCity(@PathVariable String cityName){
        return ResponseEntity.ok(this.cityService.searchByTitle(cityName));
    }

    @GetMapping("/search-area/{query}")
    public ResponseEntity<?> searchArea(@PathVariable String query){
        List<Area> areas = this.areaService.searchArea(query);


        return ResponseEntity.ok(areas);
    }

    @GetMapping("/search-category/{query}")
    public ResponseEntity<?> searchCategory(@PathVariable String query){
        return ResponseEntity.ok(this.categoryService.searchByCategoryName(query));
    }

    @GetMapping("/search-sub-category/{query}")
    public ResponseEntity<?> searchSubCategory(@PathVariable String query){
        return ResponseEntity.ok(this.subCategoryService.searchSubCategory(query));
    }
    @GetMapping("/search-restaurant/{query}")

    public ResponseEntity<?> searchRestaurant(@PathVariable String query){
     return ResponseEntity.ok(this.restaurantService.searchRestaurant(query));
    }
}
