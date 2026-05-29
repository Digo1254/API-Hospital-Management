package com.diego.hospital.hospital_api.model.prescricao;

import java.time.LocalDate;

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
@Table(name = "prescricoes")
@Data
public class Prescricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_id",referencedColumnName = "id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id",referencedColumnName = "id")
    private Paciente paciente;

    private String enderecoHospital;

    private String nomeHospital;

    private String nomeDoFarmaco;

    private String dosagem;

    private String formaFarmaceutica;

    private Long quantidadeTotal;

    private String modoDeUso;

    private LocalDate dataDaEmissao;



}
