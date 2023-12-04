package com.northwind.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
//@MappedSuperclass
@Table(name="business_entity",schema = "dbo")
public class BusinessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entity_id")
    private Long entityId;

    @Column(name="modified_date", nullable = false)
    private LocalDateTime modifiedDate = LocalDateTime.now();
}
