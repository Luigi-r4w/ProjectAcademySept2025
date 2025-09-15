package com.betacom.com.models;

import java.time.LocalDate;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "illustrazioni")
@Getter
@Setter
public class Illustrazione extends Oggetto{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/*
	@OneToOne(
			cascade=CascadeType.REMOVE)
	@JoinColumn(
            name="oggetto_id",
            referencedColumnName = "id")
	private Oggetto oggetto;
	*/
	
	@Column(name="url_illustrazione", length=5000, unique=true/*, nullable = false*/)
	private String urlIllustrazione;
	
	@Column(length=100)
	private String stile;
	@Column (
			name="data_illustrazione",
			nullable=false
			)
	private LocalDate dataIllustrazione;
	
}
