package com.betacom.com.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "disegni")
@Getter
@Setter
public class Disegno extends Oggetto{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "supporto")
    private String supporto;

    @Column(name = "tecnica")
    private String tecnica;
    
}
