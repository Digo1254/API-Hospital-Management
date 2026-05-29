package com.diego.hospital.hospital_api.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diego.hospital.hospital_api.dto.ConsultaDTO;
import com.diego.hospital.hospital_api.model.consulta.Consulta;
import com.diego.hospital.hospital_api.service.ConsultaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @PreAuthorize("hasRole('RECEPCIONISTA') || hasRole('MEDICO')")
    @PostMapping
    public Consulta criarConsulta(@Valid @RequestBody ConsultaDTO dto){
        return service.criar(dto);
    }

    @PreAuthorize("hasRole('RECEPCIONISTA') || hasRole('MEDICO')")
    @GetMapping("/{id}")
    public Consulta buscarConsulta(@PathVariable Long id){
        return service.buscar(id);
    }

    @PreAuthorize("hasRole('RECEPCIONISTA') || hasRole('MEDICO')")
    @PutMapping("/{id}")
    public Consulta alterarConsulta(@Valid @RequestBody ConsultaDTO dto,@PathVariable Long id){
        return service.alterar(dto,id);

    }
    @PreAuthorize("hasRole('RECEPCIONISTA') || hasRole('MEDICO')")
    @DeleteMapping("/{id}")
    public void deletarConsulta(@PathVariable Long id){
        service.deletar(id);
    }

    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/medico/{id}")
    public Page<Consulta> buscarConsultaMedico(Pageable pageable,@PathVariable Long id){
        return service.buscarMedico(id,pageable);
    }

    @PreAuthorize("hasRole('MEDICO') || hasRole('PACIENTE')")
    @GetMapping("/paciente/{id}")
    public Page<Consulta> buscarConsultaPaciente(Pageable pageable,@PathVariable Long id){
        return service.buscarPaciente(id,pageable);
    }

    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/data")
    public Page<Consulta> buscarPorData(Pageable pageable,@RequestParam(required = true) LocalDate data){
        return service.buscarPorData(data,pageable);
    }

}
