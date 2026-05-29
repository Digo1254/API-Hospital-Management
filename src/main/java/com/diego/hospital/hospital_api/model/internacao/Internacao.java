package com.diego.hospital.hospital_api.model.internacao;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.diego.hospital.hospital_api.model.user.Enfermeiro;
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
@Table(name = "internacoes")
@Data
public class Internacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataDaInternacao;

    private LocalDateTime dataDeAlta;

    private String motivoDaInternacao;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medicoResponsavel;

    @ManyToOne
    @JoinColumn(name = "enfermeiro_id")
    private Enfermeiro enfermeiroResponsavel;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private String rgAcompanhante;

    private String telefoneAcompanhante;

    private String vinculoComPacienteAcompanhante;

    private LocalDate dataDeNascimentoAcompanhante;

    private String generoAcompanhante;

    private String enderecoAcompanhante;

    private Boolean apresentaSintomasDeSaudeAcompanhante;

    private Boolean urgencia;

}
