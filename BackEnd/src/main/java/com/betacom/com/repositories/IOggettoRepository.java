package com.betacom.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.com.models.Oggetto;

@Repository
public interface IOggettoRepository extends JpaRepository<Oggetto, Integer>{

}
