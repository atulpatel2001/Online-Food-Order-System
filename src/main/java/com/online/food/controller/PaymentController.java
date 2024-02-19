package com.online.food.controller;


import com.online.food.modal.*;
import com.online.food.services.*;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private TransactionService transcationService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @PostMapping("/create_order_single")
    @ResponseBody
    public String createOrder(@RequestBody Map<String, Object> data) throws RazorpayException {
        int amount = Integer.parseInt(data.get("amount").toString());
        int quantity=Integer.parseInt(data.get("quantity").toString());
        int total=amount*quantity;
        var client = new RazorpayClient("rzp_test_ADEnLyqI9oALQY", "dtZFd3HrvdyoXYGLpfTrEDUv");
        JSONObject orderRequest = new JSONObject();

        orderRequest.put("amount", total * 100);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "txn_235425");
        Order order = client.orders.create(orderRequest);

        Transaction transaction = Transaction.builder()
                .orderId(order.get("id"))
                .amount(String.valueOf(total))
                .paymentId(null)
                .receipt(order.get("receipt"))
                .transcationdate(LocalDateTime.now())
                .orderStatus("created")

        .build();

        Transaction add = this.transcationService.add(transaction);

       return  order.toString();
    }

    @PostMapping("/update_singleProductPayment")
    @ResponseBody
    public ResponseEntity<?> updateOrder(@RequestBody Map<String, String> data,Principal principal) {

        try{
            Transaction transaction = this.transcationService.getByOrderId(data.get("order_id").toString());
            transaction.setPaymentId(data.get("payment_id").toString());
            transaction.setTranscationStatus(String.valueOf(Status.COMPLETED));

            transaction.setTranscationdate(LocalDateTime.now());
            Transaction transaction1 = this.transcationService.add(transaction);

            Product product = this.productService.findById(Long.valueOf(data.get("product_Id")));

            Customer customer = this.customerService.findByEmailId(principal.getName());
            int quantity= Integer.parseInt(data.get("quantity"));

            com.online.food.modal.Order order = com.online.food.modal.Order.builder().product(product).orderStatus(String.valueOf(Status.PENDING)).customer(customer).transaction(transaction).productQuantity(data.get("quantity").toString()).orderDate(LocalDate.now()).build();

            com.online.food.modal.Order order1 = this.orderService.add(order);

            Cart cart = this.cartService.findCartForProductAndCustomer(customer, product);
            this.cartService.delete(cart);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(Map.of("msg", "updated"));
    }


}
