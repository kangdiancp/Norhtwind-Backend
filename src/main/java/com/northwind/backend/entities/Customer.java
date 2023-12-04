package com.northwind.backend.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="customers",schema="dbo")
public class Customer {
    @Id
    @Column(name="customerid",updatable = false, nullable = false)
    private String customerId;

    @Column(name="customername")
    private String customerName;

    @Column(name="contactname")
    private String contactName;



    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;  
}