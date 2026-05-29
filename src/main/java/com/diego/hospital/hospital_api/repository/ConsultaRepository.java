package com.diego.hospital.hospital_api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.hospital.hospital_api.model.consulta.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta,Long>{

    Page<Consulta> findAll(Pageable pageable);
    Page<Consulta> findByPacienteId(Long pacienteId,Pageable pageable);

    Page<Consulta>findByMedicoResponsavelId(Long medicoId,Pageable pageable);

    Page<Consulta> findByDataDaConsultaBetween(LocalDateTime inicio,LocalDateTime fim,Pageable pageable);

    Consulta findByDataDaConsulta(LocalDateTime dataDaConsulta);

}
