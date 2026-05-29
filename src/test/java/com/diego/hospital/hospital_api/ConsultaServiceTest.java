package com.diego.hospital.hospital_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.diego.hospital.hospital_api.dto.ConsultaDTO;
import com.diego.hospital.hospital_api.model.consulta.Consulta;
import com.diego.hospital.hospital_api.model.user.Medico;
import com.diego.hospital.hospital_api.model.user.Paciente;
import com.diego.hospital.hospital_api.repository.ConsultaRepository;
import com.diego.hospital.hospital_api.repository.MedicoRepository;
import com.diego.hospital.hospital_api.repository.PacienteRepository;
import com.diego.hospital.hospital_api.service.ConsultaService;

@ExtendWith(MockitoExtension.class)
public class ConsultaServiceTest {

    @Mock 
    private ConsultaRepository consultaRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private ConsultaService consultaService;

    @Test
    public void erroConsultaNaoEncontrada(){
        Consulta consulta = new Consulta();

        consulta.setMotivoDaConsulta("Doente");
        consulta.setId(1l);

        when(consultaRepository.findById(1l))
        .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,()->consultaService.buscar(1l));
    }

    @Test
    public void erroMedicoNaoEncontrado(){
        ConsultaDTO consultaDTO = new ConsultaDTO();

        consultaDTO.setIdMedico(3l);

        when(medicoRepository.findById(3l))
        .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,()->consultaService.criar(consultaDTO));
    }

    @Test
    public void erroPacienteNaoEncontrado(){
        ConsultaDTO consultaDTO = new ConsultaDTO();

        consultaDTO.setIdPaciente(3l);
        consultaDTO.setIdMedico(3l);

        Medico medico =new Medico();

        medico.setCpf("12831");

        when(medicoRepository.findById(3l))
        .thenReturn(Optional.of(medico));

        when(pacienteRepository.findById(3l))
        .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, ()->consultaService.criar(consultaDTO));
    }

    @Test
    public void consultaCriadaComSucesso(){
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setMotivoDaConsulta("Doente");
        consultaDTO.setIdMedico(3l);
        consultaDTO.setIdPaciente(3l);

        Consulta consulta = new Consulta();
        consulta.setMotivoDaConsulta("Doente");

        Medico medico = new Medico();
        medico.setCpf("123");

        Paciente paciente = new Paciente();
        paciente.setCpf("123123");

        when(medicoRepository.findById(3l))
        .thenReturn(Optional.of(medico));

        when(pacienteRepository.findById(3l))
        .thenReturn(Optional.of(paciente));

        when(consultaRepository.save(any(Consulta.class)))
        .thenReturn(consulta);

        assertNotNull(consultaService.criar(consultaDTO));
    }

    @Test
    public void deveRetornarConsultasDoMedico() {
        Consulta consulta1 = new Consulta();
        Consulta consulta2 = new Consulta();
        List<Consulta> consultas = List.of(consulta1, consulta2);

        Medico medico = new Medico();
        when(medicoRepository.findById(3L))
        .thenReturn(Optional.of(medico));

        when(consultaRepository.findByMedicoResponsavelId(3L, Pageable.unpaged()))
            .thenReturn(new PageImpl<>(consultas));

        Page<Consulta> resultado = consultaService.buscarMedico(3L, Pageable.unpaged());

        assertEquals(2, resultado.getTotalElements());
    }

}
