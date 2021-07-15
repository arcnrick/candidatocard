package com.crianto.candidatocard.candidatocard.dto;

import com.crianto.candidatocard.candidatocard.model.CartaoCredito;

import java.util.Objects;

public class CartaoCreditoDTO {

    private String descricao;
    private String numeroCartao;

    public CartaoCreditoDTO() {
    }

    public CartaoCreditoDTO(String descricao, String numeroCartao) {
        this.descricao = descricao;
        this.numeroCartao = numeroCartao;
    }

    public CartaoCreditoDTO(CartaoCredito cartaoCredito) {
        // só alimenta os campos que irá mostrar em listas, evitando carregar toda a model
        this.descricao = cartaoCredito.getDescricao();
        this.numeroCartao = cartaoCredito.getNumeroCartao();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    @Override
    public String toString() {
        return "CartaoCreditoDTO{" +
                "descricao='" + descricao + '\'' +
                ", numeroCartao='" + numeroCartao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartaoCreditoDTO that = (CartaoCreditoDTO) o;
        return Objects.equals(descricao, that.descricao) &&
                Objects.equals(numeroCartao, that.numeroCartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, numeroCartao);
    }
}