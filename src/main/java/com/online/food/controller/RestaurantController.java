package com.online.food.controller;

import com.online.food.helper.FileUploadHelper;
import com.online.food.modal.*;
import com.online.food.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CityService cityService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubCategoryService subCategoryService;
    @GetMapping("/index")
    public String indexPage(Model model){
        model.addAttribute("title","Restaurant | Dash-Board");
        return "restaurant/index";
    }

    @GetMapping("/manage-product/{page}")
    public String manageProductPage(@PathVariable("page")int page ,Model model){
        try {

            Pageable pageable= PageRequest.of(page,5);
            Page<Product> products = this.productService.findByPagination(pageable);
            model.addAttribute("products", products.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", products.getTotalPages());

            List<Category> categories = this.categoryService.findAll();
            model.addAttribute("categories",categories);
            model.addAttribute("title","Restaurant | Manage Product");

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "restaurant/manage-product";
    }
    @GetMapping("/get-sub-category")
    @ResponseBody
    public List<SubCategory> getSubCategory(@RequestParam("categoryId") Long categoryId){
        List<SubCategory> subCategories=null;

        try{
            subCategories=this.subCategoryService.findByCategoryId(categoryId);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return subCategories;

    }


    //add product

    @PostMapping("/saveProduct")
    @ResponseBody
    public String saveProduct(@RequestParam("productName") String productName,
                              @RequestParam("subCategoryId") String subCategoryId,
                              @RequestParam("productPrice") Long productPrice,
                              @RequestParam("productDiscription") String productDescription,
                              @RequestParam("productImage") MultipartFile productImage, Principal principal) {

        try {
            String name = principal.getName();
            Customer customer = this.customerService.findByEmailId(name);
            Restaurant restaurant = customer.getRestaurant();
            SubCategory subCategory = this.subCategoryService.findById(Long.valueOf(subCategoryId));
            Product product = Product.builder()
                    .productPrice(productPrice)
                    .productDiscription(productDescription)
                    .restaurant(restaurant)
                    .imageName(productImage.getOriginalFilename())
                    .productName(productName)
                    .subCategory(subCategory)
                    .build();

            boolean b = FileUploadHelper.uploadFile(productImage, "static/image/product-img");
            if(b){
                this.productService.save(product);
            }
            else {
                return "error";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }



        return "success";
    }
}
