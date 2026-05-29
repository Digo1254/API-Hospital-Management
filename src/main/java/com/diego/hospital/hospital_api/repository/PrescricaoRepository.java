package com.diego.hospital.hospital_api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.hospital.hospital_api.model.prescricao.Prescricao;

public interface PrescricaoRepository extends JpaRepository<Prescricao,Long>{

    Page<Prescricao> findByPacienteId(Long pacienteId,Pageable pageable);

    Page<Prescricao> findByMedicoId(Long medicoId,Pageable pageable);

}
