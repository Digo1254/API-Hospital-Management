package com.diego.hospital.hospital_api.model.consulta;

import java.time.LocalDateTime;

import com.diego.hospital.hospital_api.model.user.Medico;
import com.diego.hospital.hospital_api.model.user.Paciente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "consultas")
@Data
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String motivoDaConsulta;

    private LocalDateTime dataDaConsulta;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medicoResponsavel;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private String rgAcompanhante;

    private String telefoneAcompanhante;

    private String vinculoComPacienteAcompanhante;



}
