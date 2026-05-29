package com.diego.hospital.hospital_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "O email é obrigatório")
    @Email
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6,message = "A senha é no mínimo de 6 caractéres para mais")
    private String senha;

}
