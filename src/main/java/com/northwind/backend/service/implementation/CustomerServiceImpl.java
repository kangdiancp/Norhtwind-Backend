package com.northwind.backend.service.implementation;

import com.northwind.backend.dto.CustomerDto;
import com.northwind.backend.entities.Customer;
import com.northwind.backend.repository.CustomerRepository;
import com.northwind.backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    
    private final CustomerRepository repository;

    public static CustomerDto mapToDto(Customer customer){
        return new CustomerDto(
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getContactName(),
                customer.getAddress(),
                customer.getCity(),
                customer.getCountry(),
                Collections.EMPTY_SET
        );
    }

    @Override
    public List<Customer> findCustomerByCountry(String country) {
        return repository.findCustomerByCountry(country);
    }

    @Override
    public Set<CustomerDto> findAllCustomer() {
        return repository.findAll().stream().map(CustomerServiceImpl::mapToDto).collect(Collectors.toSet());
    }

}
