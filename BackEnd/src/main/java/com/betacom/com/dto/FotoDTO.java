package com.betacom.com.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class FotoDTO {
	
	private OggettoDTO oggetto;
	private Integer id;
	private String device;
	private Integer widthResolution;
	private Integer heightResolution;
}
