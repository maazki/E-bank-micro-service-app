package org.sid.customerservice.controllers;

import org.sid.customerservice.dtos.CustomerDTO;
import org.sid.customerservice.dtos.CustomerRequestDTO;
import org.sid.customerservice.entity.Customer;
import org.sid.customerservice.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerRestController {

    private CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    @GetMapping("customers/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customers")
    public CustomerDTO save(@RequestBody CustomerRequestDTO customer) {
        return customerService.save(customer);
    }


}
