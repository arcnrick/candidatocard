package com.crianto.candidatocard.candidatocard.repository;

import com.crianto.candidatocard.candidatocard.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
}
