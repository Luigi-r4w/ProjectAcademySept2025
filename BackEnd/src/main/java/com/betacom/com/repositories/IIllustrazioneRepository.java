package com.betacom.com.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.com.models.Illustrazione;

public interface IIllustrazioneRepository  extends JpaRepository<Illustrazione, Integer>{
	//Optional<Illustrazione> findByOggetto(Oggetto oggetto);
	Optional<Illustrazione> findById(Integer id);
}
