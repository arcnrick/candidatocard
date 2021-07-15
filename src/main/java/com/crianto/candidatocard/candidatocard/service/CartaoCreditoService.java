package com.crianto.candidatocard.candidatocard.service;

import com.crianto.candidatocard.candidatocard.dto.CartaoCreditoRequest;
import com.crianto.candidatocard.candidatocard.exception.runtime.ObjectSystemException;
import com.crianto.candidatocard.candidatocard.model.CartaoCredito;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import static com.crianto.candidatocard.candidatocard.util.Funcoes.validaCartao;

@Service
public class CartaoCreditoService {

    public CartaoCredito create(CartaoCreditoRequest request) {

        CartaoCredito model = new CartaoCredito();

        //passa tudo para a model, com exeção da chave e dos campos que serão validados
        PropertyMap<CartaoCreditoRequest, CartaoCredito> propertyMap = new PropertyMap<CartaoCreditoRequest, CartaoCredito>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getCandidato());
                skip(destination.getNumeroCartao());
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(propertyMap);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(request, model);

        model.setNumeroCartao(validaNumeroCartao(request.getNumeroCartao()));

        return model;
    }

    public String validaNumeroCartao(String numeroCartao) {
        String numeroCartaoTemp = validaCartao(numeroCartao);

        if (numeroCartaoTemp.equals("invalido")) {
            throw new ObjectSystemException("Número de cartão inválido (" + numeroCartao + ")");
        } else {
            //return numeroCartaoTemp + "1234";
            return numeroCartaoTemp;
        }
    }
}