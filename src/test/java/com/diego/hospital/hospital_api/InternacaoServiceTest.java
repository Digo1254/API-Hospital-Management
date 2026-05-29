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

import com.diego.hospital.hospital_api.dto.InternacaoDTO;
import com.diego.hospital.hospital_api.dto.InternacaoDTO;
import com.diego.hospital.hospital_api.model.consulta.Consulta;
import com.diego.hospital.hospital_api.model.internacao.Internacao;
import com.diego.hospital.hospital_api.model.user.Enfermeiro;
import com.diego.hospital.hospital_api.model.user.Medico;
import com.diego.hospital.hospital_api.model.user.Paciente;
import com.diego.hospital.hospital_api.repository.EnfermeiroRepository;
import com.diego.hospital.hospital_api.repository.InternacaoRepository;
import com.diego.hospital.hospital_api.repository.MedicoRepository;
import com.diego.hospital.hospital_api.repository.PacienteRepository;
import com.diego.hospital.hospital_api.service.InternacaoService;

@ExtendWith(MockitoExtension.class)
public class InternacaoServiceTest {

    @Mock
    private InternacaoRepository internacaoRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private EnfermeiroRepository enfermeiroRepository;

    @InjectMocks
    private InternacaoService internacaoService;

    @Test
    public void erroInternacaoNaoEncontrada(){
        Consulta consulta = new Consulta();

        consulta.setMotivoDaConsulta("Doente");
        consulta.setId(1l);

        when(internacaoRepository.findById(1l))
        .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,()->internacaoService.buscar(1l));
    }

    @Test
    public void erroMedicoNaoEncontrado(){
        InternacaoDTO internacaoDTO = new InternacaoDTO();

        internacaoDTO.setIdMedico(3l);

        when(medicoRepository.findById(3l))
        .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,()->internacaoService.criar(internacaoDTO));
    }

    @Test
    public void erroPacienteNaoEncontrado(){
        InternacaoDTO internacaoDTO = new InternacaoDTO();

        internacaoDTO.setIdPaciente(3l);
        internacaoDTO.setIdMedico(3l);

        Medico medico =new Medico();

        medico.setCpf("12831");

        when(medicoRepository.findById(3l))
        .thenReturn(Optional.of(medico));

        when(pacienteRepository.findById(3l))
        .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, ()->internacaoService.criar(internacaoDTO));
    }

    @Test
    public void erroEnfermeiroNaoEncontrado(){
        InternacaoDTO internacaoDTO = new InternacaoDTO();

        internacaoDTO.setIdPaciente(3l);
        internacaoDTO.setIdMedico(3l);

        Medico medico =new Medico();

        medico.setCpf("12831");

        Paciente paciente = new Paciente();
        paciente.setCpf("123123");

        when(medicoRepository.findById(3l))
        .thenReturn(Optional.of(medico));

        when(pacienteRepository.findById(3l))
        .thenReturn(Optional.of(paciente));

        when(enfermeiroRepository.findById(3l))
        .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, ()->internacaoService.criar(internacaoDTO));
    }

    @Test
    public void internacaoCriadaComSucesso(){
        InternacaoDTO internacaoDTO = new InternacaoDTO();
        internacaoDTO.setMotivoDaInternacao("Doente");
        internacaoDTO.setIdMedico(3l);
        internacaoDTO.setIdPaciente(3l);
        internacaoDTO.setIdEnfermeiro(3l);

        Internacao internacao = new Internacao();
        internacao.setMotivoDaInternacao("Doente");

        Medico medico = new Medico();
        medico.setCpf("123");

        Paciente paciente = new Paciente();
        paciente.setCpf("123123");

        Enfermeiro enfermeiro = new Enfermeiro();
        enfermeiro.setCpf("123921");

        when(medicoRepository.findById(3l))
        .thenReturn(Optional.of(medico));

        when(pacienteRepository.findById(3l))
        .thenReturn(Optional.of(paciente));

        when(enfermeiroRepository.findById(3l))
        .thenReturn(Optional.of(enfermeiro));

        when(internacaoRepository.save(any(Internacao.class)))
        .thenReturn(internacao);

        assertNotNull(internacaoService.criar(internacaoDTO));
    }

    @Test
    public void deveRetornarInternacoesAtivas() {
        Internacao internacao1 = new Internacao();
        Internacao internacao2 = new Internacao();
        List<Internacao> internacoes = List.of(internacao1, internacao2);

        when(internacaoRepository.findByDataDaInternacaoBeforeAndDataDeAltaAfter(any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class)))
        .thenReturn(new PageImpl<>(internacoes));

        Page<Internacao> resultado = internacaoService.buscarAtiva(Pageable.unpaged());

        assertEquals(2, resultado.getTotalElements());
    }
}
