package com.northwind.backend.entities;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private Long orderId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customerid")
    private Customer customer;

    @Column(name = "orderdate")
    private LocalDateTime orderDate;

    @Column(name = "requireddate")
    private LocalDateTime requiredDate;

    @Column(name = "shippeddate")
    private LocalDateTime shippedDate;

    @Column(name = "subtotal")
    private Double subTotal;

    @Nonnull
    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "total_qty")
    private int totalQty;

    //@EqualsAndHashCode.Exclude
    //@OneToMany(mappedBy = "orderProductPk",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
/*    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "orderId")*/
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "orderId")
    private Set<OrderDetail> orderDetails;

/*    @OneToOne
    private Cart cart;*/
}
