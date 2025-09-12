package com.betacom.com.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class DisegnoDTO {
	private Integer id;
    private String supporto;
    private String tecnica;
    private OggettoDTO oggetto;
}
