package com.northwind.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "user_name"),
           @UniqueConstraint(columnNames = "user_email")
       })
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="user_id")
  private Long userId;

  @NotBlank
  @Size(max = 20)
  @Column(name="user_name")
  private String userName;

  @NotBlank
  @Size(max = 50)
  @Email
  @Column(name="user_email")
  private String userEmail;

  @NotBlank
  @Size(max = 120)
  @Column(name="user_password")
  private String userPassword;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", 
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();


  public Users(String username, String userEmail, String userPassword) {
    this.userName = username;
    this.userEmail = userEmail;
    this.userPassword = userPassword;
  }

}
