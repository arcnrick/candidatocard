package com.crianto.candidatocard.candidatocard.controller;

import com.crianto.candidatocard.candidatocard.config.RestControllerPath;
import com.crianto.candidatocard.candidatocard.dto.CandidatoDTO;
import com.crianto.candidatocard.candidatocard.dto.CandidatoRequest;
import com.crianto.candidatocard.candidatocard.model.Candidato;
import com.crianto.candidatocard.candidatocard.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = RestControllerPath.CANDIDATO_PATH)
public class CandidatoController {

    @Autowired
    private CandidatoService service;

    @PostMapping("")
    public ResponseEntity<Candidato> create(
            @RequestBody @Valid CandidatoRequest request, HttpServletResponse response) {

        Candidato salvo = service.create(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(salvo.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidato> update(
            @PathVariable Long id, @Valid @RequestBody CandidatoRequest request) {

        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        service.delete(id);
    }

    @GetMapping("")
    public List<CandidatoDTO> findAll() {

        return service.findAllDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidato> findById(@PathVariable Long id) {

        return ResponseEntity.ok().body(service.findById(id));
    }
}