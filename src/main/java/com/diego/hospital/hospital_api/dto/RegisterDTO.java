package com.diego.hospital.hospital_api.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.diego.hospital.hospital_api.model.user.User.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class RegisterDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6,message = "A senha tem que ter no minimo 6 caracteres")
    private String senha;

    @NotBlank(message = "Cpf é obrigatório")
    @CPF(message = "Cpf inválido")
    private String cpf;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @NotNull(message = "Role é obritaório")
    private Role role;

    private String genero;

    private String coren;

    private String especialidade;

    private String crm;

    private LocalDate dataDeNascimento;

    private String nomePlano;

    private String numeroDoPlano;

    private String endereco;

    private String re;
    
}
