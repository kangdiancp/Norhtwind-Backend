package com.northwind.backend.entities;


import com.northwind.backend.entities.enums.CartStatus;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cartid")
    private Long cartId;

    @ManyToOne
    private Customer customer;

    @Nonnull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CartStatus status;
}
