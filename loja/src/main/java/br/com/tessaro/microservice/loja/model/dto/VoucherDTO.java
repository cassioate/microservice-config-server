package br.com.tessaro.microservice.loja.model.dto;

import java.time.LocalDate;

import lombok.Setter;

import lombok.Getter;

@Getter @Setter
public class VoucherDTO {

	private Long numero;
	
	private LocalDate previsaoParaEntrega;

}
