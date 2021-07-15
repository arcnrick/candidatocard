package com.crianto.candidatocard.candidatocard.service;

import com.crianto.candidatocard.candidatocard.dto.CandidatoDTO;
import com.crianto.candidatocard.candidatocard.dto.CandidatoRequest;
import com.crianto.candidatocard.candidatocard.dto.CartaoCreditoRequest;
import com.crianto.candidatocard.candidatocard.exception.runtime.ObjectNotFoundException;
import com.crianto.candidatocard.candidatocard.exception.runtime.ObjectSystemException;
import com.crianto.candidatocard.candidatocard.model.Candidato;
import com.crianto.candidatocard.candidatocard.model.CartaoCredito;
import com.crianto.candidatocard.candidatocard.repository.CandidatoRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionalException;
import java.util.*;

import static com.crianto.candidatocard.candidatocard.util.Funcoes.cpfValidado;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository repository;

    @Autowired
    private CartaoCreditoService cartaoCreditoService;

    public Candidato create(CandidatoRequest request) {

        Candidato model = new Candidato();

        PropertyMap<CandidatoRequest, Candidato> propertyMap = new PropertyMap<CandidatoRequest, Candidato>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getCartoes());
                skip(destination.getCpf());
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(propertyMap);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(request, model);

        model.setCpf(cpfValido(request.getCpf()));

        if (request.getCartoes() != null) {
            Set<CartaoCredito> lista = new HashSet<>();

            for (CartaoCreditoRequest cartaoCreditoRequest : request.getCartoes()) {
                CartaoCredito cartaoCredito = cartaoCreditoService.create(cartaoCreditoRequest);
                cartaoCredito.setCandidato(model);
                lista.add(cartaoCredito);
            }

            model.setCartoes(lista);
        }

        try {
            Candidato salvo = repository.save(model);

            if (salvo.getId() == null) {
                throw new EmptyResultDataAccessException(1);
            }

            return salvo;
        } catch (Exception e) {
            throw new TransactionalException(e.getMessage(), e.getCause());
        }
    }


    public String cpfValido(String numeroCpf) {
        return cpfValidado(numeroCpf);
    }

    public List<CandidatoDTO> findAllDTO() {
        List<CandidatoDTO> dtos = new ArrayList<>();

        List<Candidato> candidatos = repository.findAll();

        for (Candidato candidato : candidatos) {
            dtos.add(new CandidatoDTO(candidato));
        }

        return dtos;
    }

    public Candidato findById(Long id) {
        Optional<Candidato> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Candidato com id '" + id + "' não encontrado."));
    }

    public Candidato update(Long id, CandidatoRequest request) {

        Candidato modelOld = findById(id);

        PropertyMap<CandidatoRequest, Candidato> propertyMap = new PropertyMap<CandidatoRequest, Candidato>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getCartoes());
                skip(destination.getCpf());
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(propertyMap);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(request, modelOld);

        modelOld.setCpf(cpfValido(request.getCpf()));

        if (request.getCartoes() != null) {
            Set<CartaoCredito> lista = new HashSet<>();

            for (CartaoCreditoRequest cartaoCreditoRequest : request.getCartoes()) {
                CartaoCredito cartaoCredito = cartaoCreditoService.create(cartaoCreditoRequest);
                cartaoCredito.setCandidato(modelOld);
                lista.add(cartaoCredito);
            }

            //modelOld.getCartoes().removeAll(modelOld.getCartoes());
            modelOld.getCartoes().clear();
            modelOld.getCartoes().addAll(lista);
        }

        try {
            Candidato salvo = repository.save(modelOld);

            if (salvo.getId() == null) {
                throw new EmptyResultDataAccessException(1);
            }

            return salvo;
        } catch (ObjectSystemException e) {
            throw new ObjectSystemException(e.getMessage(), e.getCause());
        }
    }

    public void delete(Long id) {

        findById(id);

        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new TransactionalException(e.getMessage(), e.getCause());
        }
    }

}