package com.diego.hospital.hospital_api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.diego.hospital.hospital_api.dto.InternacaoDTO;
import com.diego.hospital.hospital_api.model.internacao.Internacao;
import com.diego.hospital.hospital_api.model.user.Enfermeiro;
import com.diego.hospital.hospital_api.model.user.Medico;
import com.diego.hospital.hospital_api.model.user.Paciente;
import com.diego.hospital.hospital_api.repository.EnfermeiroRepository;
import com.diego.hospital.hospital_api.repository.InternacaoRepository;
import com.diego.hospital.hospital_api.repository.MedicoRepository;
import com.diego.hospital.hospital_api.repository.PacienteRepository;

@Service
public class InternacaoService {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private EnfermeiroRepository enfermeiroRepository;
    @Autowired
    private InternacaoRepository internacaoRepository;

    public Internacao criar(InternacaoDTO dto){

        Medico medico = medicoRepository.findById(dto.getIdMedico())
        .orElseThrow(()->new RuntimeException("Médico não encontrado"));

        Paciente paciente = pacienteRepository.findById(dto.getIdPaciente())
        .orElseThrow(()->new RuntimeException("Paciente não encontrado"));

        Enfermeiro enfermeiro = enfermeiroRepository.findById(dto.getIdEnfermeiro())
        .orElseThrow(()->new RuntimeException("Enfermeiro não encontrado")); 

        Internacao internacao = new Internacao();

        internacao.setDataDaInternacao(dto.getDataDaInternacao());
        internacao.setDataDeAlta(dto.getDataDeAlta());
        internacao.setPaciente(paciente);
        internacao.setMedicoResponsavel(medico);
        internacao.setEnfermeiroResponsavel(enfermeiro);
        internacao.setMotivoDaInternacao(dto.getMotivoDaInternacao());
        internacao.setRgAcompanhante(dto.getRgAcompanhante());
        internacao.setUrgencia(dto.getUrgencia());
        internacao.setTelefoneAcompanhante(dto.getTelefoneAcompanhante());
        internacao.setApresentaSintomasDeSaudeAcompanhante(dto.getApresentaSintomasDeSaudeAcompanhante());
        internacao.setGeneroAcompanhante(dto.getGeneroAcompanhante());
        internacao.setEnderecoAcompanhante(dto.getEnderecoAcompanhante());
        internacao.setDataDeNascimentoAcompanhante(dto.getDataDeNascimentoAcompanhante());
        internacao.setVinculoComPacienteAcompanhante(dto.getVinculoComPacienteAcompanhante());
        
        return internacaoRepository.save(internacao);

    }

    public Internacao buscar(Long id) {
        return internacaoRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Internação não encontrada"));
    }

    public Internacao alterar(Long id,InternacaoDTO dto){

        Medico medico = medicoRepository.findById(dto.getIdMedico())
        .orElseThrow(()->new RuntimeException("Médico não encontrado"));

        Paciente paciente = pacienteRepository.findById(dto.getIdPaciente())
        .orElseThrow(()->new RuntimeException("Paciente não encontrado"));

        Enfermeiro enfermeiro = enfermeiroRepository.findById(dto.getIdEnfermeiro())
        .orElseThrow(()->new RuntimeException("Enfermeiro não encontrado"));        

        Internacao internacao = internacaoRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Internação não encontrada"));

        internacao.setMedicoResponsavel(medico);
        internacao.setPaciente(paciente);
        internacao.setEnfermeiroResponsavel(enfermeiro);
        internacao.setApresentaSintomasDeSaudeAcompanhante(dto.getApresentaSintomasDeSaudeAcompanhante());
        internacao.setDataDaInternacao(dto.getDataDaInternacao());
        internacao.setDataDeAlta(dto.getDataDeAlta());
        internacao.setDataDeNascimentoAcompanhante(dto.getDataDeNascimentoAcompanhante());
        internacao.setEnderecoAcompanhante(dto.getEnderecoAcompanhante());
        internacao.setGeneroAcompanhante(dto.getGeneroAcompanhante());
        internacao.setMotivoDaInternacao(dto.getMotivoDaInternacao());
        internacao.setRgAcompanhante(dto.getRgAcompanhante());
        internacao.setTelefoneAcompanhante(dto.getTelefoneAcompanhante());
        internacao.setUrgencia(dto.getUrgencia());
        internacao.setVinculoComPacienteAcompanhante(dto.getVinculoComPacienteAcompanhante());

        return internacaoRepository.save(internacao);

    }

    public void deletar(Long id){

        internacaoRepository.deleteById(id);

    }

    public Page<Internacao> buscarMedico(Long id,Pageable pageable){
        Medico medico = medicoRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Medico não encontrado"));
        Page<Internacao> internacoes = internacaoRepository.findByMedicoResponsavelId(id,pageable);
        return internacoes;
    }

    public Page<Internacao> buscarEnfermeiro(Long id,Pageable pageable){
        Enfermeiro enfermeiro = enfermeiroRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Enfermeiro não encontrado"));
        Page<Internacao> internacoes = internacaoRepository.findByEnfermeiroResponsavelId(id,pageable);
        return internacoes;
    }

    public Page<Internacao> buscarAtiva(Pageable pageable){
        return internacaoRepository
        .findByDataDaInternacaoBeforeAndDataDeAltaAfter(LocalDateTime.now(), LocalDateTime.now(), pageable);
    }

    public void darAlta(Long id){
        Internacao internacao = internacaoRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Internação não encontrada"));

        internacao.setDataDeAlta(LocalDateTime.now());
        
    }


}
