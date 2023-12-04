package com.northwind.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class CustomerDto {
    private String customerId;
    private String companyName;
    private String contactName;

    private String address;
    private String city;
    private String country;
    private Set<OrderDto> ordersDto;
}
