package com.betacom.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.com.models.Foto;

@Repository
public interface IFotoRepository extends JpaRepository<Foto, Integer>{

}
