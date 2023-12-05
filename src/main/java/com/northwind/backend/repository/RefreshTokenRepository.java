package com.northwind.backend.repository;


import com.northwind.backend.entities.RefreshToken;
import com.northwind.backend.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByRetoToken(String token);

  @Modifying
  int deleteByUser(Users user);
}
