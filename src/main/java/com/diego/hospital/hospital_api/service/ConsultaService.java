package com.diego.hospital.hospital_api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.diego.hospital.hospital_api.dto.ConsultaDTO;
import com.diego.hospital.hospital_api.model.consulta.Consulta;
import com.diego.hospital.hospital_api.model.user.Medico;
import com.diego.hospital.hospital_api.model.user.Paciente;
import com.diego.hospital.hospital_api.repository.ConsultaRepository;
import com.diego.hospital.hospital_api.repository.MedicoRepository;
import com.diego.hospital.hospital_api.repository.PacienteRepository;

@Service
public class ConsultaService {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta criar(ConsultaDTO dto) {

        Consulta consultaMesmaData = consultaRepository.findByDataDaConsulta(dto.getDataDaConsulta());

        Medico medico = medicoRepository.findById(dto.getIdMedico())
        .orElseThrow(()->new RuntimeException("Médico não encontrado"));

        if(consultaMesmaData != null && consultaMesmaData.getMedicoResponsavel() == medico){
            throw new RuntimeException("Este médico já tem uma consulta nesta data e horário");
        }

        Paciente paciente = pacienteRepository.findById(dto.getIdPaciente())
        .orElseThrow(()->new RuntimeException("Paciente não encontrado"));
    
        Consulta consulta = new Consulta();

        consulta.setDataDaConsulta(dto.getDataDaConsulta());
        consulta.setMedicoResponsavel(medico);
        consulta.setMotivoDaConsulta(dto.getMotivoDaConsulta());
        consulta.setPaciente(paciente);
        consulta.setRgAcompanhante(dto.getRgAcompanhante());
        consulta.setTelefoneAcompanhante(dto.getTelefoneAcompanhante());
        consulta.setVinculoComPacienteAcompanhante(dto.getVinculoComPacienteAcompanhante());

        return consultaRepository.save(consulta);
        
    }

    public Consulta buscar(Long id){
        return consultaRepository.findById(id).orElseThrow(()->new RuntimeException("Consulta não encontrada"));
    }

    public void deletar(Long id) {
        consultaRepository.deleteById(id);
    }

    public Consulta alterar(ConsultaDTO dto,Long id){

        Consulta consulta = consultaRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Consulta não encontrada"));

        Medico medico = medicoRepository.findById(dto.getIdMedico())
        .orElseThrow(()->new RuntimeException("Médico não encontrado"));

        Paciente paciente = pacienteRepository.findById(dto.getIdPaciente())
        .orElseThrow(()->new RuntimeException("Paciente não encontrado"));

        consulta.setDataDaConsulta(dto.getDataDaConsulta());
        consulta.setMedicoResponsavel(medico);
        consulta.setMotivoDaConsulta(dto.getMotivoDaConsulta());
        consulta.setPaciente(paciente);
        consulta.setRgAcompanhante(dto.getRgAcompanhante());
        consulta.setTelefoneAcompanhante(dto.getTelefoneAcompanhante());
        consulta.setVinculoComPacienteAcompanhante(dto.getVinculoComPacienteAcompanhante());

        return consultaRepository.save(consulta);
        

    }

    public Page<Consulta> buscarMedico(Long id,Pageable pageable){
        Medico medico = medicoRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Medico não encontrado"));
        Page<Consulta> consultas = consultaRepository.findByMedicoResponsavelId(id,pageable);
        if(consultas == null){
            throw new RuntimeException("Sem consultas");
        }
        return consultas;
    }

    public Page<Consulta> buscarPaciente(Long id,Pageable pageable){
        Paciente paciente = pacienteRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Paciente não encontrado"));

        Page<Consulta> consultas = consultaRepository.findByPacienteId(id,pageable);
        if(consultas == null){
            throw new RuntimeException("Sem consultas");
        }
        return consultas;
    }

    public Page<Consulta> buscarPorData(LocalDate data,Pageable pageable) {
        LocalDateTime comeco = data.atStartOfDay();
        LocalDateTime fim = data.atTime(23,59,59);
        return consultaRepository.findByDataDaConsultaBetween(comeco, fim,pageable);
    }

}
