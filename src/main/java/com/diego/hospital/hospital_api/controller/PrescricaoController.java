package com.diego.hospital.hospital_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.hospital.hospital_api.dto.PrescricaoDTO;
import com.diego.hospital.hospital_api.model.prescricao.Prescricao;
import com.diego.hospital.hospital_api.service.PrescricaoService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/prescricao")
public class PrescricaoController {

    @Autowired
    PrescricaoService service;

    @PreAuthorize("hasRole('MEDICO')")
    @PostMapping
    public Prescricao criarPrescricao(@Valid @RequestBody PrescricaoDTO dto){
        return service.criar(dto);
    }
    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/{id}")
    public Prescricao buscarPrescricao(@PathVariable Long id){
        return service.buscar(id);
    }
    @PreAuthorize("hasRole('MEDICO')")
    @PutMapping("/{id}")
    public Prescricao alterarPrescricao(@Valid @PathVariable Long id, @RequestBody PrescricaoDTO dto){
        return service.alterar(id,dto);
    }
    @PreAuthorize("hasRole('MEDICO')")
    @DeleteMapping("/{id}")
    public void deletarPrescricao(@PathVariable Long id){
        service.deletar(id);
    }
    @PreAuthorize("hasRole('MEDICO') || hasRole('PACIENTE')")
    @GetMapping("/paciente/{id}")
    public Page<Prescricao> buscarPrescricaoPaciente(Pageable pageable,@PathVariable Long id) {
        return service.buscarPaciente(id,pageable);
    }
    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/medico/{id}")
    public Page<Prescricao> buscarPrescricaoMedico(Pageable pageable,@PathVariable Long id){
        return service.buscarMedico(id,pageable);
    }    
}
