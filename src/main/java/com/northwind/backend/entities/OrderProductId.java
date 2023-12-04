package com.northwind.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import jakarta.persistence.Embeddable;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductId implements Serializable {

    private Long orderId;
    private Long productId;
}
