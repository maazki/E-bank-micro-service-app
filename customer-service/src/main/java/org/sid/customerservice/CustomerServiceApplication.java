package org.sid.customerservice;

import org.sid.customerservice.entity.Customer;
import org.sid.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args ->{
            List.of("mohamed","ali","ride").forEach(c->
            {
                customerRepository.save(Customer.builder().name(c).email(c+"@gmail.com").build());
            });
        };
    };

}
