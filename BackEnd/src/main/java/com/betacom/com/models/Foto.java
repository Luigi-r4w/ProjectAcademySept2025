package com.betacom.com.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "foto")
public class Foto extends Oggetto{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;

	private String device;
	
	@Column (name = "width_resolution")
	private Integer widthResolution;
	@Column (name = "height_resolution")
	private Integer heightResolution;
}
