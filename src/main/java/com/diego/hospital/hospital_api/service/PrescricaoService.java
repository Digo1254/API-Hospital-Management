package com.diego.hospital.hospital_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.diego.hospital.hospital_api.dto.PrescricaoDTO;
import com.diego.hospital.hospital_api.model.prescricao.Prescricao;
import com.diego.hospital.hospital_api.model.user.Medico;
import com.diego.hospital.hospital_api.model.user.Paciente;
import com.diego.hospital.hospital_api.repository.MedicoRepository;
import com.diego.hospital.hospital_api.repository.PacienteRepository;
import com.diego.hospital.hospital_api.repository.PrescricaoRepository;

@Service
public class PrescricaoService {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private PrescricaoRepository prescricaoRepository;

    public Prescricao criar(PrescricaoDTO dto){

        Medico medico = medicoRepository.findById(dto.getMedicoId())
        .orElseThrow(()->new RuntimeException("Médico não encontrado"));

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
        .orElseThrow(()->new RuntimeException("Paciente não encontrado"));

        Prescricao prescricao = new Prescricao();

        prescricao.setDataDaEmissao(dto.getDataDaEmissao());
        prescricao.setDosagem(dto.getDosagem());
        prescricao.setEnderecoHospital(dto.getEnderecoHospital());
        prescricao.setFormaFarmaceutica(dto.getFormaFarmaceutica());
        prescricao.setMedico(medico);
        prescricao.setModoDeUso(dto.getModoDeUso());
        prescricao.setNomeDoFarmaco(dto.getNomeDoFarmaco());
        prescricao.setNomeHospital(dto.getNomeHospital());
        prescricao.setPaciente(paciente);
        prescricao.setQuantidadeTotal(dto.getQuantidadeTotal());

        return prescricaoRepository.save(prescricao);
    }

    public Prescricao buscar(Long id){
        Prescricao prescricao = prescricaoRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Prescrição não encontrada"));

        return prescricao;
    }

    public Prescricao alterar(Long id,PrescricaoDTO dto){
        Medico medico = medicoRepository.findById(dto.getMedicoId())
        .orElseThrow(()->new RuntimeException("Médico não encontrado"));

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
        .orElseThrow(()->new RuntimeException("Paciente não encontrado"));

        Prescricao prescricao = prescricaoRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Prescrição não encontrada"));

        prescricao.setDataDaEmissao(dto.getDataDaEmissao());
        prescricao.setDosagem(dto.getDosagem());
        prescricao.setEnderecoHospital(dto.getEnderecoHospital());
        prescricao.setFormaFarmaceutica(dto.getFormaFarmaceutica());
        prescricao.setMedico(medico);
        prescricao.setModoDeUso(dto.getModoDeUso());
        prescricao.setNomeDoFarmaco(dto.getNomeDoFarmaco());
        prescricao.setNomeHospital(dto.getNomeHospital());
        prescricao.setPaciente(paciente);
        prescricao.setQuantidadeTotal(dto.getQuantidadeTotal());

        return prescricaoRepository.save(prescricao);
    }

    public void deletar(Long id){
        prescricaoRepository.deleteById(id);
    }

    public Page<Prescricao> buscarPaciente(Long id,Pageable pageable){
        Page<Prescricao> prescricao = prescricaoRepository.findByPacienteId(id,pageable);
        if(prescricao == null){
            throw new RuntimeException("Prescrições não encontradas");
        }
        return prescricao;
    }

    public Page<Prescricao> buscarMedico(Long id,Pageable pageable){
        Page<Prescricao> prescricao = prescricaoRepository.findByMedicoId(id,pageable);
        if(prescricao == null){
            throw new RuntimeException("Prescrições não encontradas");
        }
        return prescricao;
    }

}
