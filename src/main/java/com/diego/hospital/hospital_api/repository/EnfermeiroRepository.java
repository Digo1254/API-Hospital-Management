package com.diego.hospital.hospital_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.hospital.hospital_api.model.user.Enfermeiro;

public interface EnfermeiroRepository extends JpaRepository<Enfermeiro,Long>{

}
