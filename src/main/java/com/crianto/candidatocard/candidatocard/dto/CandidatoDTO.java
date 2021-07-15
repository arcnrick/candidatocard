package com.crianto.candidatocard.candidatocard.dto;

import com.crianto.candidatocard.candidatocard.model.Candidato;
import com.crianto.candidatocard.candidatocard.model.CartaoCredito;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"cartoes"})
@EqualsAndHashCode(exclude = {"cartoes"})
public class CandidatoDTO {

    // getters/setters sendo feitos com o Lombok... bem como construtores, toString, hashcode e equals

    private Long id;
    private String nome;
    private String cpf;
    private Set<CartaoCreditoDTO> cartoes = new HashSet<>();

    public CandidatoDTO(Candidato candidato) {
        this.id = candidato.getId();
        this.nome = candidato.getNome();
        this.cpf = candidato.getCpf();
        this.cartoes = cartaoCreditoModelToDTO(candidato.getCartoes());
    }

    private Set<CartaoCreditoDTO> cartaoCreditoModelToDTO(Set<CartaoCredito> cartaoCreditos) {
        return cartaoCreditos.stream().map(CartaoCreditoDTO::new).collect(Collectors.toSet());
    }
}