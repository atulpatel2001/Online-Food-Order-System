package com.online.food.controller;

import com.online.food.modal.Area;
import com.online.food.modal.Category;
import com.online.food.modal.City;
import com.online.food.modal.SubCategory;
import com.online.food.services.AreaService;
import com.online.food.services.CategoryService;
import com.online.food.services.CityService;
import com.online.food.services.SubCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private CityService cityService;
    @Autowired
    private AreaService areaService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/index")
    public String indexPage(Model model) {
        try {
            model.addAttribute("title", "Admin | DashBoard");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "admin/index";
    }

    //for city
    @GetMapping("manage-city/{page}")
    public String manageCityPage(@PathVariable("page") int page, Model model) {

        try {
            Pageable pageable = PageRequest.of(page, 5);
            Page<City> cities = this.cityService.findAllCityByPagination(pageable);

            model.addAttribute("cities", cities.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", cities.getTotalPages());
            model.addAttribute("title", "Manage-City");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/manage-city";
    }

    //add city data
    @PostMapping("/add-cityData")
    @ResponseBody
    public String addCityData(@RequestBody Map<String, String> data) {
        try {

            String cityName=data.get("cityName");
            String cityDiscription=data.get("cityDiscription");
            City byCityName = this.cityService.findByCityName(cityName);
            if (byCityName == null) {
                City build = City.builder().cityDiscription(cityDiscription).cityName(cityName).build();
                this.cityService.save(build);
            } else {
                this.logger.info("already present this city");
                return "already present this city";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";

    }

    @GetMapping("/get-city")
    @ResponseBody
    public City getCity(@RequestParam("cityId") String cityId){


        City byId = this.cityService.findById(Long.valueOf(cityId));

        return byId;
    }
    //update city data

    @PostMapping("/update-cityData")
    @ResponseBody
    public String updateCityData(@RequestBody Map<String, String> data) {


        try {
            String cityId=data.get("cityId");
            String cityName=data.get("cityName");
            String cityDiscription=data.get("cityDiscription");
            City city = this.cityService.findById(Long.valueOf(cityId));
            city.setCityName(cityName);
            city.setCityDiscription(cityDiscription);

            this.cityService.save(city);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }


    //delete city
    @PostMapping("/delete-city")
    @ResponseBody
    public String deleteCity(@RequestParam String city_id) {
        try {

            City city = this.cityService.findById(Long.valueOf(city_id));
            this.cityService.delete(city);
            this.logger.info("City Has Been Deleted");

        } catch (Exception e) {
            e.getMessage();

            this.logger.info("Something Went Wrong during delete a City !!!");
        }
        return "City deleted successfully";
    }


    //for area
    @GetMapping("/manage-area/{page}")
    public String manageAreaPage(@PathVariable("page") int page, Model model) {

        try {
            Pageable pageable = PageRequest.of(page, 5);
            Page<Area> areas = this.areaService.findAreaByPagination(pageable);

            List<City> cities = this.cityService.findAll();
            model.addAttribute("cities", cities);


            model.addAttribute("areas", areas.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", areas.getTotalPages());
            model.addAttribute("title", "Manage-Area");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/manage-area";
    }

    //add area
    @PostMapping("/add-AreaData")
    @ResponseBody
    public String addAreaData(@RequestBody Map<String, String> data) {
        try {


            String cityId = data.get("cityId");
            String areaName = data.get("areaName");
            String areaDiscription = data.get("areaDiscription");
            Area areaByName = this.areaService.getAreaByName(areaName);
            if (areaByName == null) {

                City city = this.cityService.findById(Long.valueOf(cityId));

                Area area = Area.builder().areaDiscription(areaDiscription).areaName(areaName).city(city).build();
                this.areaService.save(area);

                this.logger.info("added Area");
            } else {
                return "already present this area";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }


    //delete area
    @PostMapping("/delete-area")
    @ResponseBody
    public String deleteArea(@RequestParam String area_id) {
        try {

            Area byId = this.areaService.findById(Long.valueOf(area_id));
            this.areaService.delete(byId);
            this.logger.info("Area Has Been Deleted");

        } catch (Exception e) {
            e.getMessage();

            this.logger.info("Something Went Wrong during delete a Area !!!");
        }
        return "Area deleted successfully";
    }


    //manage area
    @GetMapping("/manage-category/{page}")
    public String manageCategoryPage(@PathVariable("page") int page, Model model) {

        try {
            Pageable pageable = PageRequest.of(page, 5);
            Page<Category> categories = this.categoryService.findByPagination(pageable);


            model.addAttribute("categories", categories.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", categories.getTotalPages());
            model.addAttribute("title", "Manage-Category");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/manage-category";
    }


    //add area
    @PostMapping("/add-categoryData")
    @ResponseBody
    public String addCategoryData(@RequestBody Map<String, String> data) {
        try {
            String categoryName = data.get("categoryName");
            String categoryDiscription = data.get("categoryDiscription");

            Category byCategoryName = this.categoryService.getByCategoryName(categoryName);
            if (byCategoryName == null) {
                Category build = Category.builder().categoryName(categoryName).categoryDiscription(categoryDiscription).build();
                this.categoryService.save(build);
                this.logger.info("added Category");
            } else {
                return "already present this category";
            }


        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "success";
    }


    //delete category
    @PostMapping("/delete-category")
    @ResponseBody
    public String deleteCategory(@RequestParam String category_id) {
        try {

            Category category = this.categoryService.findById(Long.valueOf(category_id));
            this.categoryService.delete(category);
            this.logger.info("Category Has Been Deleted");

        } catch (Exception e) {
            e.getMessage();

            this.logger.info("Something Went Wrong during delete a Category !!!");
        }
        return "Category deleted successfully";
    }


    //manage sub category

    @GetMapping("/manage-sub-category/{page}")
    public String manageSubCategoryPage(@PathVariable("page") int page, Model model) {

        try {
            Pageable pageable = PageRequest.of(page, 5);

            List<Category> categories = this.categoryService.findAll();
            Page<SubCategory> subCategories = this.subCategoryService.findByPagination(pageable);
            model.addAttribute("categories", categories);
            model.addAttribute("subCategories", subCategories.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", subCategories.getTotalPages());
            model.addAttribute("title", "Manage-Category");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/manage-sub-category";
    }


    //add sub category
    @PostMapping("/add-subcategoryData")
    @ResponseBody
    public String addSubCategoryData(@RequestBody Map<String, String> data) {
        try {
            String categoryId = data.get("categoryId");
            String subCategoryName = data.get("subCategoryName");
            String subCategoryDiscription=data.get("subCategoryDiscription");



            SubCategory subCategory = this.subCategoryService.findBySubCategoryName(subCategoryName);
            if (subCategory == null) {
                Category category = this.categoryService.findById(Long.valueOf(categoryId));
                SubCategory subCategory1 = SubCategory.builder().subCategoryDiscription(subCategoryDiscription).subCategoryName(subCategoryName).category(category).build();
                    this.subCategoryService.save(subCategory1);
                this.logger.info("added SubCategory");
            } else {
                return "already present this subcategory";
            }


        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "success";
    }


    //delete sub category

    @PostMapping("/delete-sub-category")
    @ResponseBody
    public String deleteSubCategory(@RequestParam String sub_category_id) {
        try {

            SubCategory subCategory = this.subCategoryService.findById(Long.valueOf(sub_category_id));
            this.subCategoryService.delete(subCategory);
            this.logger.info("SubCategory Has Been Deleted");

        } catch (Exception e) {
            e.getMessage();

            this.logger.info("Something Went Wrong during delete a SubCategory !!!");
        }
        return "SubCategory deleted successfully";
    }


}
