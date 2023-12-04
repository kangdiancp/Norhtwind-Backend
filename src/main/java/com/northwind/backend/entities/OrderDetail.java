package com.northwind.backend.entities;

import com.northwind.backend.dto.OrderDetailDto;
import lombok.*;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orderdetails")
@IdClass(OrderProductId.class)
public class OrderDetail {


/*    @EmbeddedId
    private OrderProductId orderProductId;*/

    @Id
    private Long orderId;

    @Id
    private Long productId;


    @Column(name="unit_price")
    private Double unitPrice;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @Column(name="discount")
    private float discount;

}
