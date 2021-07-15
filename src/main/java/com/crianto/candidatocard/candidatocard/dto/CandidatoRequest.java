package com.crianto.candidatocard.candidatocard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoRequest {

    // getters/setters sendo feitos com o Lombok... bem como construtores, toString, hashcode e equals

    private String nome;
    private String cpf;
    private String observacao;
    private Set<CartaoCreditoRequest> cartoes = new HashSet<>();
}