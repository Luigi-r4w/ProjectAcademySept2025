package com.betacom.com.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DisegnoReq extends OggettoReq{
	private Integer id;
    private String supporto;
    private String tecnica;
    
}
