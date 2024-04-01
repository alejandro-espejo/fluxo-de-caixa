package com.caixa.fluxoDeCaixa.dao;

import com.caixa.fluxoDeCaixa.models.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaixaDao extends JpaRepository<Caixa, Integer> {

}
