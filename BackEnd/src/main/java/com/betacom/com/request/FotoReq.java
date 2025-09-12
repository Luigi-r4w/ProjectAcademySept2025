package com.betacom.com.request;

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
public class FotoReq extends OggettoReq{
	
	private Integer id;
	private String device;
	private Integer widthResolution;
	private Integer heightResolution;
}
