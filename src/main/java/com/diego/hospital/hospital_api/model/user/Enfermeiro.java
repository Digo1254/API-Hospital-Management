package com.diego.hospital.hospital_api.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "enfermeiros")
@Data
public class Enfermeiro extends User{

    private String genero;

    private String coren;

}
