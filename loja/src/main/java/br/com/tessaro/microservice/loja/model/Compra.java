package br.com.tessaro.microservice.loja.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Compra {

	@Id
	private Long pedidoId;
	
	private Integer tempoDePreparo;
	
	private String enderecoDestino;

}
