package com.northwind.backend.repository;


import com.northwind.backend.entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
	Optional<Users> findByUserName(String username);

	Boolean existsByUserName(String username);

	Boolean existsByUserEmail(String email);
}
