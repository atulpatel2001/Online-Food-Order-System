package com.online.food.controller;

import com.online.food.helper.FileUploadHelper;
import com.online.food.modal.*;
import com.online.food.services.*;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/order-food")
public class CustomerController {


    @Autowired
    private AreaService areaService;
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CityService cityService;
    @Autowired
    private ComplainService complainService;

    private Logger logger = LoggerFactory.getLogger((CustomerController.class));

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;


    @ModelAttribute
    public void addCommanData(Model model, Principal principal) {
        String userName = principal.getName();
        this.logger.info("Login By " + userName);

        // get User Detail By UserName(EmailId)
        Customer customer = this.customerService.findByEmailId(userName);

        int count = this.cartService.countCart(customer);
        if (count == 0) {
            model.addAttribute("countCart", count);
        } else {
            model.addAttribute("countCart", count);
        }
        model.addAttribute("customer", customer);
    }

    @GetMapping("/index")
    public String indexpage(Model model) {
        model.addAttribute("title", "Order-Food | Order Food Online in India");
        List<Product> products = this.productService.findVegitarianProduct();

        model.addAttribute("products", products);

        return "customer/index";
    }

    @GetMapping("/complain")

    public String complainPage(Model model) {

        model.addAttribute("title", "Order-Food | Complain");
        List<City> cities = this.cityService.findAll();
        model.addAttribute("cities", cities);
        return "customer/complain";
    }

    @GetMapping("/get-areas")
    @ResponseBody
    public List<Area> getAllArea(@RequestParam Long cityId, Model model) {
        List<Area> areas = null;
        try {
            areas = this.areaService.getAreaByCityId(cityId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return areas;
    }


    @GetMapping("/get-restaurant")
    @ResponseBody
    public List<Restaurant> getRestaurant(@RequestParam Long areaId, Model model) {
        List<Restaurant> restaurants = null;
        try {
            restaurants = this.restaurantService.findByareaId(areaId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurants;
    }


    @PostMapping("/add-complain")
    @ResponseBody
    public String addComplainData(@RequestParam("complainSubject") String complainSubject,
                                  @RequestParam("restaurantId") Long restaurantId
            , @RequestParam("attachment") MultipartFile attachment
            , @RequestParam("complainDescription") String complainDescription, Principal principal) {
        try {
            boolean b = FileUploadHelper.uploadFile(attachment, "static/image/complain-img");
            if (b) {
                Restaurant restaurant = this.restaurantService.findById(restaurantId);
                Customer customer = this.customerService.findByEmailId(principal.getName().trim());


                Complain build = Complain.builder()
                        .complainSubject(complainSubject)
                        .complainDescription(complainDescription)
                        .attachment(attachment.getOriginalFilename())
                        .complainDate(LocalDate.now())
                        .complainStatus(String.valueOf(Status.PENDING))
                        .restaurant(restaurant)
                        .customer(customer).build();
                this.complainService.save(build);
            } else {
                return "file is not upload";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }


    @PostMapping("/add-to-cart")
    @ResponseBody
    public String addToCart(@RequestParam("product_id") String product_id, Principal principal) {
        try {
            Product product = this.productService.findById(Long.valueOf(product_id));
            Customer customer = this.customerService.findByEmailId(principal.getName());

            Cart cart = this.cartService.findCartForProductAndCustomer(customer, product);

            if (cart == null) {
                Cart cart2 = Cart.builder().customer(customer).product(product).itemQuantity(1L).build();
                this.cartService.add(cart2);
            } else {
                cart.setItemQuantity(cart.getItemQuantity() + 1);
                this.cartService.add(cart);

            }

        } catch (Exception e) {
            e.getMessage();
        }
        return "added to cart";
    }


    //cart page
    @GetMapping("/checkout")
    public String cartPage(Model model, Principal principal) {
        List<Cart> carts = this.cartService.findCartForCustomer(this.customerService.findByEmailId(principal.getName()));
        model.addAttribute("carts", carts);
        int totalAmount = 0;
        for (Cart c : carts) {
            Long itemQuantity = c.getItemQuantity();
            String productPrice = String.valueOf(c.getProduct().getProductPrice());
            totalAmount += Integer.parseInt(productPrice) * itemQuantity;
        }
        model.addAttribute("totalPrice", totalAmount);
        model.addAttribute("title", "Food Cart");
        return "customer/cart-page";
    }


    //change product quantity
    @PostMapping("/change-quantity")
    @ResponseBody
    public String changeQuantity(@RequestParam("quantity") String quantity, @RequestParam("cartId") String cartId) {
        try {

            if (Integer.parseInt(quantity) == 0) {
                Cart cart = this.cartService.get(Long.valueOf(cartId));
                this.cartService.delete(cart);
                this.logger.info("cart has been deleted because quantity value is zero");

            } else {
                Cart cart = this.cartService.get(Long.valueOf(cartId));
                cart.setItemQuantity(Long.valueOf(quantity));
                this.cartService.add(cart);

                this.logger.info("update quantity");
            }

        } catch (Exception e) {
            e.getMessage();
        }

        return "change";
    }


    //delete product from cart
    @PostMapping("/delete-cart")
    @ResponseBody
    public String deleteCart(@RequestParam("cart_id") String cartId) {
        try {
            Cart cart = this.cartService.get(Long.valueOf(cartId));
            this.cartService.delete(cart);
        } catch (Exception e) {
            e.getMessage();
        }
        return "deleted";
    }


    //add address
    @PostMapping("/add-address")
    @ResponseBody
    public String addAddress(Principal principal,
                             @RequestParam("fullName") String fullName,
                             @RequestParam("phoneNumber") String phoneNumber,
                             @RequestParam("state") String state,
                             @RequestParam("district") String district,
                             @RequestParam("city") String city,
                             @RequestParam("pinCode") String pinCode,
                             @RequestParam("houseNo") String houseNo,
                             @RequestParam("buildingName") String buildingName,
                             @RequestParam("area") String area,
                             @RequestParam("colony") String colony) {

        try {
            System.out.println("Received data:");
            System.out.println("Full Name: " + fullName);
            System.out.println("Phone Number: " + phoneNumber);
            System.out.println("State: " + state);
            System.out.println("District: " + district);
            System.out.println("City: " + city);
            System.out.println("Pin Code: " + pinCode);
            System.out.println("House No: " + houseNo);
            System.out.println("Building Name: " + buildingName);
            System.out.println("Area: " + area);
            System.out.println("Colony: " + colony);

        } catch (Exception e) {
            e.getMessage();


        }
        return "success";
    }


    //update address
    @PostMapping("/update-address")
    @ResponseBody
    public String updateAddress(
            @RequestParam("addressId") Long addressId,

                                @RequestParam("fullName") String fullName,
                                 @RequestParam("phoneNumber") String phoneNumber,
                                 @RequestParam("state") String state,
                                 @RequestParam("district") String district,
                                 @RequestParam("city") String city,
                                 @RequestParam("pinCode") String pinCode,
                                 @RequestParam("houseNo") String houseNo,
                                 @RequestParam("buildingName") String buildingName,
                                 @RequestParam("area") String area,
                                 @RequestParam("colony") String colony) {


        try {
            System.out.println(addressId);
            System.out.println("Received data:");
            System.out.println("Full Name: " + fullName);
            System.out.println("Phone Number: " + phoneNumber);
            System.out.println("State: " + state);
            System.out.println("District: " + district);
            System.out.println("City: " + city);
            System.out.println("Pin Code: " + pinCode);
            System.out.println("House No: " + houseNo);
            System.out.println("Building Name: " + buildingName);
            System.out.println("Area: " + area);
            System.out.println("Colony: " + colony);

        } catch (Exception e) {
            e.getMessage();


        }
        return "success";
    }

}
