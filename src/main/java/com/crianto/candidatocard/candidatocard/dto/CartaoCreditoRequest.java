package com.crianto.candidatocard.candidatocard.dto;

import java.util.Objects;

public class CartaoCreditoRequest {

    private Long cartaoCreditoCandidatoId;
    private String descricao;
    private String numeroCartao;

    public CartaoCreditoRequest() {
    }

    public CartaoCreditoRequest(Long cartaoCreditoCandidatoId, String descricao, String numeroCartao) {
        this.cartaoCreditoCandidatoId = cartaoCreditoCandidatoId;
        this.descricao = descricao;
        this.numeroCartao = numeroCartao;
    }

    public Long getCartaoCreditoCandidatoId() {
        return cartaoCreditoCandidatoId;
    }

    public void setCartaoCreditoCandidatoId(Long cartaoCreditoCandidatoId) {
        this.cartaoCreditoCandidatoId = cartaoCreditoCandidatoId;
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
        return "CartaoCreditoRequest{" +
                "cartaoCreditoCandidatoId=" + cartaoCreditoCandidatoId +
                ", descricao='" + descricao + '\'' +
                ", numeroCartao='" + numeroCartao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartaoCreditoRequest that = (CartaoCreditoRequest) o;
        return Objects.equals(cartaoCreditoCandidatoId, that.cartaoCreditoCandidatoId) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(numeroCartao, that.numeroCartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartaoCreditoCandidatoId, descricao, numeroCartao);
    }
}