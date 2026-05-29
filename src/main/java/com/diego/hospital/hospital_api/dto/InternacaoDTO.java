package com.diego.hospital.hospital_api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class InternacaoDTO {

    @NotNull(message = "É obrigatório colocar a data da internação")
    private LocalDateTime dataDaInternacao;

    private LocalDateTime dataDeAlta;

    @NotBlank(message = "É obrigatório colocar o motivo da internação")
    private String motivoDaInternacao;

    private String rgAcompanhante;

    private String telefoneAcompanhante;

    private String vinculoComPacienteAcompanhante;

    private LocalDate dataDeNascimentoAcompanhante;

    private String generoAcompanhante;

    private String enderecoAcompanhante;

    private Boolean apresentaSintomasDeSaudeAcompanhante;

    @NotNull(message = "É obrigatório colocar se apresenta urgência ou não")
    private Boolean urgencia;

    @NotNull(message = "É obrigatório colocar um paciente")
    private Long idPaciente;

    @NotNull(message = "É obrigatório colocar um médico responsável por essa internação")
    private Long idMedico;

    @NotNull(message = "É obrigatório colocar um enfermeiro responsável por essa internação")
    private Long idEnfermeiro;

}
