package com.online.food.controller;

import com.online.food.helper.FileUploadHelper;
import com.online.food.modal.*;
import com.online.food.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @Autowired
    private OfferService offerService;

    @Autowired
    private ComplainService complainService;

    private Logger logger= LoggerFactory.getLogger(RestaurantController.class);


    //restaurant dash board
    @GetMapping("/index")
    public String indexPage(Model model){
        model.addAttribute("title","Restaurant | Dash-Board");
        return "restaurant/index";
    }


    //manage Product
    @GetMapping("/manage-product/{page}")
    public String manageProductPage(@PathVariable("page")int page ,Model model,Principal principal){
        try {

            Pageable pageable= PageRequest.of(page,5);

            String name = principal.getName();
            Customer customer = this.customerService.findByEmailId(name.trim());
            Restaurant restaurant = customer.getRestaurant();
            Page<Product> products = this.productService.findProductForRestaurant(restaurant.getRestaurantId(),pageable);
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

    //get Sub Category
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
                              @RequestParam("productImage") String productImage, Principal principal) {

        try {
            String name = principal.getName();
            Customer customer = this.customerService.findByEmailId(name);
            Restaurant restaurant = customer.getRestaurant();
            SubCategory subCategory = this.subCategoryService.findById(Long.valueOf(subCategoryId));
            Product product = Product.builder()
                    .productPrice(productPrice)
                    .productDiscription(productDescription)
                    .restaurant(restaurant)
                    .imageName(productImage)
                    .productName(productName)
                    .subCategory(subCategory)
                    .build();


                this.productService.save(product);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "success";
    }

    //delete Product
    @PostMapping("/delete-product")
    @ResponseBody
    public String deleteProduct(@RequestParam("productId") Long productId){

        try{
            Product product = this.productService.findById(productId);
            this.productService.delete(product);
            this.logger.info("Product deleted");
        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "succes";
    }

    //get Product
    @GetMapping("/get-product")
    @ResponseBody
    public Map<String,String> getProductData(@RequestParam("product_id") String productId){
        Map<String,String > data=new HashMap<>();
        Product product = this.productService.findById(Long.valueOf(productId));

        data.put("productId", String.valueOf(product.getProductId()));
        data.put("productName",product.getProductName());
        data.put("productPrice", String.valueOf(product.getProductPrice()));
        data.put("productDiscription",product.getProductDiscription());
        data.put("image",product.getImageName());

        return data;
    }


    //update product
    @PostMapping("/updateProduct")
    @ResponseBody
    public String updateProduct(@RequestParam("productName") String productName,
                              @RequestParam("productId") String productId,
                              @RequestParam("productPrice") Long productPrice,
                              @RequestParam("productDiscription") String productDescription,
                              @RequestParam("productImage") String productImage) {

        try {
            Product product = this.productService.findById(Long.valueOf(productId));
            product.setImageName(productImage);
            product.setProductName(productName);
            product.setProductPrice(productPrice);
            product.setProductDiscription(productDescription);

            this.productService.save(product);

        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }

        return "success";
    }


    //for offer
    @GetMapping("/manage-offer/{page}")
    public String manageOffer(@PathVariable("page")int page,Model model,Principal principal){

        try
        {
            Pageable pageable=PageRequest.of(page,5);
            Customer customer = this.customerService.findByEmailId(principal.getName());
            Restaurant restaurant = customer.getRestaurant();
            Page<Offer> offers = this.offerService.findByRestaurantID(restaurant.getRestaurantId(), pageable);
            model.addAttribute("offers", offers.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", offers.getTotalPages());

            List<Category> categories = this.categoryService.findAll();
            model.addAttribute("categories",categories);
            model.addAttribute("title","Restaurant | Manage Offer");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "restaurant/manage-offer";
    }


//    add offer

    @PostMapping("/add-offer")
    @ResponseBody
    public String addOffer(@RequestParam("subCategoryId") Long subCategoryId,
                           @RequestParam("offerName")  String offerName,
                           @RequestParam("offerDiscount") String offerDiscount,
                           @RequestParam("startDate")LocalDate startDate,
                           @RequestParam("lastDate") LocalDate lastDate,
                           @RequestParam("offerDescription") String offerDescription,
                           Principal principal
                           ){

        try
        {
            Customer customer = this.customerService.findByEmailId(principal.getName());
            Restaurant restaurant = customer.getRestaurant();
            SubCategory subCategory = this.subCategoryService.findById(subCategoryId);
            Offer offer = Offer.builder()
                    .offerName(offerName)
                    .offerDescription(offerDescription)
                    .offerDiscount(Double.valueOf(offerDiscount))
                    .endDate(lastDate)
                    .startDate(startDate)
                    .subCategory(subCategory)
                    .restaurant(restaurant).build();
            this.offerService.save(offer);

        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }

        return "success";
    }


//delete offer

    @PostMapping("/delete-offer")
    @ResponseBody
    public String deleteOffer(@RequestParam("offer_id") Long offer_id){
        try
        {
            System.out.println(offer_id+"-----------------------");
            Offer offer = this.offerService.findBYId(offer_id);
            this.offerService.delete(offer);
        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @GetMapping("/get-offer")
    @ResponseBody
    public Map<String,String> getOffer(@RequestParam("offer_id") Long offer_id) throws Exception{

        Offer offer = this.offerService.findBYId(offer_id);
        Map<String,String > data=new HashMap<>();
        data.put("offerId", String.valueOf(offer.getOfferId()));
        data.put("offerName",offer.getOfferName());
        data.put("offerDiscount", String.valueOf(offer.getOfferDiscount()));
        data.put("startDate", String.valueOf(offer.getStartDate()));
        data.put("lastDate", String.valueOf(offer.getEndDate()));
        data.put("offerDescription",offer.getOfferDescription());

                return data;

    }

    @PostMapping("/update-offer")
    @ResponseBody
    public String updateOffer(@RequestParam("offerId")Long offerId,
                              @RequestParam("offerName") String offerName,
                              @RequestParam("offerDiscount") String offerDiscount,
                              @RequestParam("startDate") LocalDate startDate,
                              @RequestParam("lastDate") LocalDate lastDate,
                              @RequestParam("offerDescription") String offerDescription
    ){
      try{
          Offer offer = this.offerService.findBYId(offerId);
          offer.setOfferName(offerName);
          offer.setOfferDiscount(Double.valueOf(offerDiscount));
          offer.setStartDate(startDate);
          offer.setEndDate(lastDate);
          offer.setOfferDescription(offerDescription);
          this.offerService.save(offer);
      }
      catch (Exception e){
          e.printStackTrace();
          return "error";
      }

        return "success";
    }



    @GetMapping("/manage-complain/{page}")
    public String manageComplain(@PathVariable("page") int page,Principal principal,Model model){
        try
        {
            Customer customer = this.customerService.findByEmailId(principal.getName().trim());
            Restaurant restaurant = customer.getRestaurant();

            Pageable pageable=PageRequest.of(page,5);
            Page<Complain> complains = this.complainService.findByPainationWithRestaurantId(restaurant.getRestaurantId(),pageable);
            model.addAttribute("complains", complains.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", complains.getTotalPages());
            model.addAttribute("title","Restaurant | Manage Complain");

        }
        catch (Exception e){
           e.printStackTrace();
        }

        return "restaurant/manage-complain";
    }

    @PostMapping("/update-complain-status")
    @ResponseBody
    public String updateComplainStatus(@RequestParam("complainId")Long complainId,@RequestParam("status") int status){
        try{
            Complain complain = this.complainService.findById(complainId);

            if(status ==1){
                complain.setComplainStatus(String.valueOf(Status.INPOGRESS));
            }
            else {
                complain.setComplainStatus(String.valueOf((Status.RESOLVED)));
            }
            this.complainService.save(complain);
        }
        catch (Exception e){
            e.printStackTrace();


        }
        return "success";
    }

    @PostMapping("/reply-data")
    @ResponseBody
    public String replyDataHandle(@RequestParam("replydes") String replyDes,@RequestParam("complainId") Long complainId)
    {
        try{
            Complain complain = this.complainService.findById(complainId);
            complain.setReply(replyDes);
            complain.setReplyDate(LocalDate.now());
            this.complainService.save(complain);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }
}

