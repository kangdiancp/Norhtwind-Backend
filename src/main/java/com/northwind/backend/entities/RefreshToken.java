package com.northwind.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "refresh_token")
public class RefreshToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="reto_id")
  private long retoId;

  @OneToOne
  @JoinColumn(name = "reto_user_id", referencedColumnName = "user_id")
  private Users user;

  @Column(name="reto_token",nullable = false, unique = true)
  private String retoToken;

  @Column(name="reto_expire_date",nullable = false)
  private Instant retoExpiryDate;


}
