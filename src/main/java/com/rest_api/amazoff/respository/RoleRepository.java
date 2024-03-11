package com.rest_api.amazoff.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest_api.amazoff.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {

	Optional<Roles> findByName(String roleName);

}
