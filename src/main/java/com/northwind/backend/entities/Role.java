package com.northwind.backend.entities;

import com.northwind.backend.entities.enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="role_id")
  private Integer roleId;

  @Enumerated(EnumType.STRING)
  @Column(name="role_name",length = 20)
  private ERole roleName;

}