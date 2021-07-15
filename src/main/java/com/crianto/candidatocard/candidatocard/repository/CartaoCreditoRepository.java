package com.crianto.candidatocard.candidatocard.repository;

import com.crianto.candidatocard.candidatocard.model.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long> {
}
