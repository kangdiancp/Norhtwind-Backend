package com.northwind.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @Column(name="supplierid")
    private Long supplierId;

    @Column(name="companyname")
    private String companyName;

    @Column(name="contactname")
    private String contactName;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;
}
