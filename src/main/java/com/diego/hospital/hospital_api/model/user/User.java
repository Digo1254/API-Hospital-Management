package com.diego.hospital.hospital_api.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    public enum Role{
        ADMIN,MEDICO,PACIENTE,ENFERMEIRO,RECEPCIONISTA
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String senha;

    private String cpf;

    private String telefone;
    @Enumerated(EnumType.STRING)
    private Role role;
}
