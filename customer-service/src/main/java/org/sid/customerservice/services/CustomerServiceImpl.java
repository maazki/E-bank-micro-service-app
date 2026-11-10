package org.sid.customerservice.services;

import org.sid.customerservice.dtos.CustomerDTO;
import org.sid.customerservice.dtos.CustomerRequestDTO;
import org.sid.customerservice.entity.Customer;
import org.sid.customerservice.mappers.CustomerMapper;
import org.sid.customerservice.repository.CustomerRepository;
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
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers =customerRepository.findAll();
        return customers.stream().map(customer -> dtoMapper.toCustomerDTO(customer)).collect(Collectors.toList());

    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer  customer=customerRepository.findById(id).orElseThrow(()->new RuntimeException("Customer Not Found"));
       // CustomerDTO customerDTO= dtoMapper.toCustomerDTO(customer);
        return dtoMapper.toCustomerDTO(customer);
    }

    @Override
    public CustomerDTO save(CustomerRequestDTO CustomerRequestDTO) {
        Customer customer=dtoMapper.toCustomer(CustomerRequestDTO);
        Customer saveCustomer = customerRepository.save(customer);
        return dtoMapper.toCustomerDTO(saveCustomer);

    }
}
