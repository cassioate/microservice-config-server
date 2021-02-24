package br.com.tessaro.microservice.loja.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.tessaro.microservice.loja.model.enumeration.CompraState;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long pedidoId;
	
	private Integer tempoDePreparo;
	
	private String enderecoDestino;

	private LocalDate dataParaEntrega;
	
	private Long voucher;
	
	@Enumerated(EnumType.STRING)
	private CompraState state;
}
