package com.diego.hospital.hospital_api.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name ="medicos")
@Data
public class Medico extends User{

    private String especialidade;

    private String crm;

    private String genero;

}
