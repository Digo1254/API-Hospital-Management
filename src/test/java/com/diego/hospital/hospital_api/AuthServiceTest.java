package com.diego.hospital.hospital_api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.diego.hospital.hospital_api.dto.LoginDTO;
import com.diego.hospital.hospital_api.dto.RegisterDTO;
import com.diego.hospital.hospital_api.model.user.Medico;
import com.diego.hospital.hospital_api.model.user.User;
import com.diego.hospital.hospital_api.model.user.User.Role;
import com.diego.hospital.hospital_api.repository.UserRepository;
import com.diego.hospital.hospital_api.service.AuthService;
import com.diego.hospital.hospital_api.service.JwtService;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @Mock
    private JwtService jwtService; 

    @Test
    public void erroQuandoNaoEncontrarUsuario(){
        LoginDTO dto = new LoginDTO();
        dto.setEmail("naoexiste@gmail.com");
        dto.setSenha("12345566");

        when(userRepository.findByEmail("naoexiste@gmail.com"))
        .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, ()->authService.login(dto));
    }

    @Test
    public void erroQuandoSenhaInvalida(){
        LoginDTO dto = new LoginDTO();
        dto.setEmail("diego@gmail.com");
        dto.setSenha("senhaerrada");

        User user = new Medico();

        user.setEmail("diego@gmail.com");
        user.setSenha("senhacorreta");

        when(userRepository.findByEmail("diego@gmail.com"))
        .thenReturn(Optional.of(user));

        assertThrows(RuntimeException.class, ()->authService.login(dto));
    }

    @Test
    public void erroQuandoEmailJaExiste(){
        RegisterDTO dto = new RegisterDTO();
        dto.setEmail("jaexiste@gmail.com");
        dto.setSenha("12345");
        dto.setNome("Diego");
        dto.setRole(Role.MEDICO);

        User user = new Medico();
        user.setEmail("jaexiste@gmail.com");

        when(userRepository.findByEmail("jaexiste@gmail.com"))
        .thenReturn(Optional.of(user));

        assertThrows(RuntimeException.class, ()->authService.registrar(dto));
    }

    @Test
    public void loginRetornaToken(){
        LoginDTO dto = new LoginDTO();
        dto.setEmail("diego@gmail.com");
        dto.setSenha("senha");

        User user = new Medico();
        user.setEmail("diego@gmail.com");
        user.setSenha("senha");
        user.setRole(Role.MEDICO);

        when(userRepository.findByEmail("diego@gmail.com"))
        .thenReturn(Optional.of(user));

        when(jwtService.gerarToken(anyString(), anyString()))
        .thenReturn("token-fake");

        String token = authService.login(dto);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

}
