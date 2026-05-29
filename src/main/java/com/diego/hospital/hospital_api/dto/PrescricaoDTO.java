package com.diego.hospital.hospital_api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Data
public class PrescricaoDTO {
    @NotBlank(message = "É obrigatorio colocar o endereço do hospital")
    private String enderecoHospital;

    @NotBlank(message = "É obrigatorio colocar o nome do hospital")
    private String nomeHospital;

    @NotBlank(message = "É obrigatório colocar o nome do farmaco")
    private String nomeDoFarmaco;

    @NotBlank(message = "É obrigatório colocar a dosagem")
    private String dosagem;

    @NotBlank(message = "É obrigatório colocar a forma farmacêutica")
    private String formaFarmaceutica;

    @NotNull(message = "É obrigatório colocar a quantidade")
    private Long quantidadeTotal;

    @NotBlank(message = "É obrigatório colocar o modo de uso")
    private String modoDeUso;

    @NotNull(message = "É obrigatório colocar a data da emissão")
    private LocalDate dataDaEmissao;

    @NotNull(message = "É obrigatório colocar o médico que escreveu essa prescrição")
    private Long medicoId;

    @NotNull(message = "É obrigatório colocar um paciente para ter posse dessa prescrição")
    private Long pacienteId;

}
