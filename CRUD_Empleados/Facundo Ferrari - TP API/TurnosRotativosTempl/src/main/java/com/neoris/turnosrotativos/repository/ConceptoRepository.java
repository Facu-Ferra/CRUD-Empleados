package com.neoris.turnosrotativos.repository;

import com.neoris.turnosrotativos.entity.Concepto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConceptoRepository extends JpaRepository<Concepto,Integer> {
}
