package com.crianto.candidatocard.candidatocard.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "candidato")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"cartoes"})
@EqualsAndHashCode(exclude = {"cartoes"})
public class Candidato implements Serializable {
    private static final Long serialVersionUID = 1L;

    // getters/setters sendo feitos com o Lombok... bem como construtores, toString, hashcode e equals

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(value = AccessType.PROPERTY)
    private Long id;

    @Column(name = "nome", length = 50)
    @Size(max = 100, message = "Nome do candidato deve ter no máximo 100 caracteres")
    private String nome;

    @Column(name = "cpf", length = 11)
    @Size(max = 11, message = "Número do cpf deve ter no máximo 11 caracteres")
    private String cpf;

    @Column(name = "observacao", length = 500)
    @Size(max = 500, message = "Observação deve ter no máximo 500 caracteres")
    private String observacao;

    @CreationTimestamp
    @Column(name = "criacao", updatable = false)
    private LocalDateTime criacao;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<CartaoCredito> cartoes = new HashSet<>();
}