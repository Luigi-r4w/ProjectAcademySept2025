package com.betacom.com.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class IllustrazioneDTO {
	private Integer id;
	private OggettoDTO oggetto;
	private String urlIllustrazione;
	private String stile;
	private LocalDate dataIllustrazione;
}
