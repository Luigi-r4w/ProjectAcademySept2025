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
public class IllustrazioneReq {
	private Integer id;
	//private Oggetto oggetto;
	private String urlIllustrazione;
	private String stile;
	private LocalDate dataIllustrazione;
}
