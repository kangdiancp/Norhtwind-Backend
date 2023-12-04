package com.northwind.backend.service;


import com.northwind.backend.dto.CustomerDto;
import com.northwind.backend.entities.Customer;

import java.util.Set;
import java.util.List;

public interface CustomerService {
    public List<Customer> findCustomerByCountry(String country);

    public Set<CustomerDto> findAllCustomer();
}
