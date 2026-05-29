package com.diego.hospital.hospital_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.hospital.hospital_api.dto.LoginDTO;
import com.diego.hospital.hospital_api.dto.RegisterDTO;
import com.diego.hospital.hospital_api.model.user.Enfermeiro;
import com.diego.hospital.hospital_api.model.user.Medico;
import com.diego.hospital.hospital_api.model.user.Paciente;
import com.diego.hospital.hospital_api.model.user.Recepcionista;
import com.diego.hospital.hospital_api.model.user.User;
import com.diego.hospital.hospital_api.repository.EnfermeiroRepository;
import com.diego.hospital.hospital_api.repository.MedicoRepository;
import com.diego.hospital.hospital_api.repository.PacienteRepository;
import com.diego.hospital.hospital_api.repository.RecepcionistaRepository;
import com.diego.hospital.hospital_api.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private RecepcionistaRepository recepcionistaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private EnfermeiroRepository enfermeiroRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public User registrar(RegisterDTO dto){

        Optional<User> user = userRepository.findByEmail(dto.getEmail());

        if(user.isPresent()){
            new RuntimeException("Email já cadastrado");
        }

        switch (dto.getRole()) {
            case MEDICO:
                Medico medico = new Medico();

                medico.setCrm(dto.getCrm());
                medico.setEspecialidade(dto.getEspecialidade());
                medico.setGenero(dto.getGenero());
                medico.setNome(dto.getNome());
                medico.setEmail(dto.getEmail());
                medico.setRole(dto.getRole());
                medico.setSenha(dto.getSenha());
                medico.setCpf(dto.getCpf());
                medico.setTelefone(dto.getTelefone());                
                return medicoRepository.save(medico);
            
            case RECEPCIONISTA:
                Recepcionista recepcionista = new Recepcionista();
                recepcionista.setCpf(dto.getCpf());
                recepcionista.setEmail(dto.getEmail());
                recepcionista.setNome(dto.getNome());
                recepcionista.setRe(dto.getRe());
                recepcionista.setRole(dto.getRole());
                recepcionista.setSenha(dto.getSenha());
                recepcionista.setTelefone(dto.getTelefone());
                return recepcionistaRepository.save(recepcionista);

            case ENFERMEIRO:
                Enfermeiro enfermeiro = new Enfermeiro();
                enfermeiro.setCoren(dto.getCoren());
                enfermeiro.setCpf(dto.getCpf());
                enfermeiro.setEmail(dto.getEmail());
                enfermeiro.setGenero(dto.getGenero());
                enfermeiro.setNome(dto.getNome());
                enfermeiro.setRole(dto.getRole());
                enfermeiro.setSenha(dto.getSenha());
                enfermeiro.setTelefone(dto.getTelefone());
                return enfermeiroRepository.save(enfermeiro);
            case PACIENTE:
                Paciente paciente = new Paciente();
                paciente.setCpf(dto.getCpf());
                paciente.setDataDeNascimento(dto.getDataDeNascimento());
                paciente.setEmail(dto.getEmail());
                paciente.setEndereco(dto.getEndereco());
                paciente.setGenero(dto.getGenero());
                paciente.setNome(dto.getNome());
                paciente.setNomePlano(dto.getNomePlano());
                paciente.setNumeroDoPlano(dto.getNumeroDoPlano());
                paciente.setRole(dto.getRole());
                paciente.setSenha(dto.getSenha());
                paciente.setTelefone(dto.getTelefone());
                return pacienteRepository.save(paciente);
            default:
                throw new RuntimeException("Role inválido");
        }

    }

    public String login(LoginDTO dto){
        User user = userRepository.findByEmail(dto.getEmail())
        .orElseThrow(()->new RuntimeException("Usuario não encontrado"));

        if(!dto.getSenha().equals(user.getSenha())){
            throw new RuntimeException("Senha inválida");
        }
        return jwtService.gerarToken(user.getEmail(), user.getRole().name());
    }

}
