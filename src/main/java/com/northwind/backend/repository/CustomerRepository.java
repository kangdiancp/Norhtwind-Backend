package com.northwind.backend.repository;

import com.northwind.backend.entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    //JPQL
    List<Customer> findByCountry(String country);
    
    //native query
    @Query(value="select * from dbo.customers where lower(country)=:country",nativeQuery=true)
    List<Customer> findCustomerByCountry(String country);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update dbo.customers  set contacttitle=:contact where customerid=:customerId", 
    nativeQuery = true)
    int updateCustomerContact(String contact,String customerId);


}
