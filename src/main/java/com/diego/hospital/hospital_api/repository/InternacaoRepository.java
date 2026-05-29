package com.diego.hospital.hospital_api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.hospital.hospital_api.model.internacao.Internacao;

public interface InternacaoRepository extends JpaRepository<Internacao,Long>{

    Page<Internacao> findByPacienteId(Long pacienteId,Pageable pageable);

    Page<Internacao> findByMedicoResponsavelId(Long medicoId,Pageable pageable);

    Page<Internacao> findByEnfermeiroResponsavelId(Long enfermeiroId,Pageable pageable);

    Page<Internacao> findByDataDaInternacaoBeforeAndDataDeAltaAfter(
    LocalDateTime agora1, LocalDateTime agora2, Pageable pageable
    );
}
