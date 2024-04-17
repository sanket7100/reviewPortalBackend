package com.sanket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sanket.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	

	@Query("from UserEntity e where e.email=:email")
	public Optional<UserEntity> canUserLogin(@Param("email") String email);
	
	
	@Query("from UserEntity e where e.email=:email")
	public UserEntity getUserIdByMail(@Param("email") String email);
}
