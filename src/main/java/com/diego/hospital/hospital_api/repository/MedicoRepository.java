package com.diego.hospital.hospital_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.hospital.hospital_api.model.user.Medico;

public interface MedicoRepository extends JpaRepository<Medico,Long>{

}
