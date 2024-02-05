package com.online.food;

import com.online.food.modal.Customer;
import com.online.food.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class OnlineFoodOrderSystemApplication  implements CommandLineRunner {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(OnlineFoodOrderSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer customer = Customer.builder().customerName("Atul Patel").customerEmail("ajpatel7096@gmail.com").customerPassword(this.bCryptPasswordEncoder.encode("atul@2001")).customerRole("ROLE_ADMIN").enable(false).customerJoinDate(LocalDateTime.now()).build();

		Customer customer1 = this.customerService.findByEmailId(customer.getCustomerEmail());
		if(customer1 == null){
			this.customerService.save(customer);
		}
		else {

		}

	}


}
