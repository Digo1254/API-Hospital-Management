package com.diego.hospital.hospital_api.model.user;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pacientes")
@Data
public class Paciente extends User{

    private LocalDate dataDeNascimento;

    private String genero;

    private String nomePlano;

    private String numeroDoPlano;

    private String endereco;

}
