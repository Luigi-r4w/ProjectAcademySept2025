package com.betacom.com.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IllustrazioneReq extends OggettoReq{
	private Integer id;
	//private Integer id_oggetto;
	private String urlIllustrazione;
	private String stile;
	private LocalDate dataIllustrazione;
}
