package com.crianto.candidatocard.candidatocard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "cartao_credito_candidato")
public class CartaoCredito implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(value = AccessType.PROPERTY)
    private Long id;

    @Column(name = "descricao", length = 50)
    @Size(max = 50, message = "Descrição deve ter no máximo 50 caracteres")
    private String descricao;

    @Column(name = "numeroCartao", length = 16)
    @Size(max = 16, message = "Número de cartão deve ter no máximo 16 caracteres")
    private String numeroCartao;

    @CreationTimestamp
    @Column(name = "criacao", updatable = false)
    private LocalDateTime criacao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "candidatoId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_cartao_credito_candidato"))
    @Valid
    @NotNull(message = "Informe o candidato")
    private Candidato candidato;

    public CartaoCredito() {
    }

    public CartaoCredito(Long id, String descricao, String numeroCartao,
                         LocalDateTime criacao, Candidato candidato) {
        this.id = id;
        this.descricao = descricao;
        this.numeroCartao = numeroCartao;
        this.criacao = criacao;
        this.candidato = candidato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCriacao() {
        return criacao;
    }

    public void setCriacao(LocalDateTime criacao) {
        this.criacao = criacao;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    @Override
    public String toString() {
        return "CartaoCredito{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", criacao=" + criacao +
                ", candidato=" + candidato +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartaoCredito that = (CartaoCredito) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(numeroCartao, that.numeroCartao) &&
                Objects.equals(criacao, that.criacao) &&
                Objects.equals(candidato, that.candidato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, numeroCartao, criacao, candidato);
    }
}
