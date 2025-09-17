package com.betacom.com.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.com.models.Utente;

@Repository
public interface IUtenteRepository extends JpaRepository<Utente, Integer>{
    
    Optional<Utente> findByEmail(String email);
    
}
