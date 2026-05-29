package com.diego.hospital.hospital_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.diego.hospital.hospital_api.dto.InternacaoDTO;
import com.diego.hospital.hospital_api.model.internacao.Internacao;
import com.diego.hospital.hospital_api.service.InternacaoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/internacao")
public class InternacaoController {

    @Autowired
    private InternacaoService service;

    @PreAuthorize("hasRole('RECEPCIONISTA') || hasRole('MEDICO')")
    @PostMapping
    public Internacao criarInternacao(@Valid @RequestBody InternacaoDTO dto){
        return service.criar(dto);
    }
    @PreAuthorize("hasRole('RECEPCIONISTA') || hasRole('MEDICO')")
    @GetMapping("/{id}")
    public Internacao buscarInternacao(@PathVariable Long id){
        return service.buscar(id);
    }
    @PreAuthorize("hasRole('RECEPCIONISTA') || hasRole('MEDICO')")
    @PutMapping("/{id}")
    public Internacao alterarInternacao(@Valid @PathVariable Long id, @RequestBody InternacaoDTO dto) {
        return service.alterar(id, dto);
    }
    @PreAuthorize("hasRole('RECEPCIONISTA') || hasRole('MEDICO')")
    @DeleteMapping("/{id}")
    public void deletarInternacao(@PathVariable Long id){

        service.deletar(id);

    }
    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/medico/{id}")
    public Page<Internacao> buscarInternacaoMedico(Pageable pageable,@PathVariable Long id){
        return service.buscarMedico(id,pageable);
    }
    @PreAuthorize("hasRole('ENFERMEIRO') || hasRole('MEDICO')")
    @GetMapping("/enfermeiro/{id}")
    public Page<Internacao> buscarInternacaoEnfermeiro(Pageable pageable,@PathVariable Long id){
        return service.buscarEnfermeiro(id,pageable);
    }
    @PreAuthorize("hasRole('RECEPCIONISTA') || hasRole('MEDICO') || hasRole('ENFERMEIRO')")
    @GetMapping("/active")
    public Page<Internacao> buscarInternacaoAtiva(Pageable pageable){
        return service.buscarAtiva(pageable);
    }

    @PreAuthorize("hasRole('MEDICO')")
    @PatchMapping("/{id}/alta")
    public void darAltaInternacao(@PathVariable Long id){
        service.darAlta(id);
    }



}
