package org.sid.customerservice.services;

import org.sid.customerservice.dtos.CustomerDTO;
import org.sid.customerservice.dtos.CustomerRequestDTO;
import org.sid.customerservice.entity.Customer;
import org.sid.customerservice.mappers.CustomerMapper;
import org.sid.customerservice.repository.CustomerRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;
    private CustomerMapper dtoMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper dtoMapper) {
        this.customerRepository = customerRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    @McpTool(description = "Get all customers")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers =customerRepository.findAll();
        return customers.stream().map(customer -> dtoMapper.toCustomerDTO(customer)).collect(Collectors.toList());

    }

    @Override
    @McpTool(description = "Find  customer by ID")
    public CustomerDTO getCustomerById(@McpToolParam(description = "The customer id") Long id) {
        Customer  customer=customerRepository.findById(id).orElseThrow(()->new RuntimeException("Customer Not Found"));
       // CustomerDTO customerDTO= dtoMapper.toCustomerDTO(customer);
        return dtoMapper.toCustomerDTO(customer);
    }

    @Override
    @McpTool(description = "Save a new customer ")
    public CustomerDTO save(@McpToolParam(description = "The customer to save (name,email)")CustomerRequestDTO CustomerRequestDTO) {
        Customer customer=dtoMapper.toCustomer(CustomerRequestDTO);
        Customer saveCustomer = customerRepository.save(customer);
        return dtoMapper.toCustomerDTO(saveCustomer);

    }
}
