package com.rest_api.amazoff.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest_api.amazoff.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail2);

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}
