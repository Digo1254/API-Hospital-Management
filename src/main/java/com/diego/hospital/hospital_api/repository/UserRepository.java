package com.diego.hospital.hospital_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.hospital.hospital_api.model.user.User;

public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User> findByEmail(String email);

}
