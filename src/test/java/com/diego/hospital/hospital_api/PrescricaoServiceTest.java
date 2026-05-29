package com.diego.hospital.hospital_api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.diego.hospital.hospital_api.dto.PrescricaoDTO;
import com.diego.hospital.hospital_api.model.prescricao.Prescricao;
import com.diego.hospital.hospital_api.model.user.Medico;
import com.diego.hospital.hospital_api.model.user.Paciente;
import com.diego.hospital.hospital_api.repository.MedicoRepository;
import com.diego.hospital.hospital_api.repository.PacienteRepository;
import com.diego.hospital.hospital_api.repository.PrescricaoRepository;
import com.diego.hospital.hospital_api.service.PrescricaoService;

@ExtendWith(MockitoExtension.class)
public class PrescricaoServiceTest {

    @Mock
    PrescricaoRepository prescricaoRepository;

    @Mock
    MedicoRepository medicoRepository;

    @Mock 
    PacienteRepository pacienteRepository;

    @InjectMocks
    PrescricaoService prescricaoService;
    
    @Test
    public void erroprescricaoNaoEncontrada(){
        Prescricao prescricao = new Prescricao();

        prescricao.setNomeDoFarmaco("Tilenol");
        prescricao.setId(1l);

        when(prescricaoRepository.findById(1l))
        .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,()->prescricaoService.buscar(1l));
    }

    @Test
    public void erroMedicoNaoEncontrado(){
        PrescricaoDTO prescricaoDTO = new PrescricaoDTO();

        prescricaoDTO.setMedicoId(3l);

        when(medicoRepository.findById(3l))
        .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,()->prescricaoService.criar(prescricaoDTO));
    }

    @Test
    public void erroPacienteNaoEncontrado(){
        PrescricaoDTO prescricaoDTO = new PrescricaoDTO();

        prescricaoDTO.setPacienteId(3l);
        prescricaoDTO.setMedicoId(3l);

        Medico medico =new Medico();

        medico.setCpf("12831");

        when(medicoRepository.findById(3l))
        .thenReturn(Optional.of(medico));

        when(pacienteRepository.findById(3l))
        .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, ()->prescricaoService.criar(prescricaoDTO));
    }

    @Test
    public void prescricaoCriadaComSucesso(){
        PrescricaoDTO prescricaoDTO = new PrescricaoDTO();
        prescricaoDTO.setNomeDoFarmaco("Advil");
        prescricaoDTO.setMedicoId(3l);
        prescricaoDTO.setPacienteId(3l);

        Prescricao prescricao = new Prescricao();
        prescricao.setNomeDoFarmaco("Advil");

        Medico medico = new Medico();
        medico.setCpf("123");

        Paciente paciente = new Paciente();
        paciente.setCpf("123123");

        when(medicoRepository.findById(3l))
        .thenReturn(Optional.of(medico));

        when(pacienteRepository.findById(3l))
        .thenReturn(Optional.of(paciente));

        when(prescricaoRepository.save(any(Prescricao.class)))
        .thenReturn(prescricao);

        assertNotNull(prescricaoService.criar(prescricaoDTO));
    }

}
