package com.diego.hospital.hospital_api.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class ConsultaDTO {

    @NotNull(message = "É obrigatório colocar um paciente")
    private Long idPaciente;

    @NotNull(message = "É obrigatório colocar um médico que irá realizar a consulta")
    private Long idMedico;

    @NotBlank(message = "É obrigatório colocar o motivo da consulta")
    private String motivoDaConsulta;

    @NotNull(message = "É obrigatório colocar a data da consulta")
    private LocalDateTime dataDaConsulta;


    //Opcional
    private String rgAcompanhante;

    private String telefoneAcompanhante;

    private String vinculoComPacienteAcompanhante;

}
