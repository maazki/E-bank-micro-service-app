package org.sid.customerservice.mappers;

import org.sid.customerservice.dtos.CustomerDTO;
import org.sid.customerservice.dtos.CustomerRequestDTO;
import org.sid.customerservice.entity.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDTO toCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public Customer toCustomer(CustomerRequestDTO customerRequestDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRequestDTO, customer);
        return customer;
    }
}
