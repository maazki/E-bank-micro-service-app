package org.sid.customerservice.services;

import org.sid.customerservice.dtos.CustomerDTO;
import org.sid.customerservice.dtos.CustomerRequestDTO;
import org.sid.customerservice.entity.Customer;

import java.util.List;

public interface CustomerService {
    public List<CustomerDTO> getAllCustomers();
    public CustomerDTO getCustomerById(Long id);
    public CustomerDTO save(CustomerRequestDTO customerRequestDTO);
}
