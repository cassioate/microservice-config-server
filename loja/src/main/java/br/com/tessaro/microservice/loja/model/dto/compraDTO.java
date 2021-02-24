package br.com.tessaro.microservice.loja.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class compraDTO {
	
	private Long compraId;

	private List<ItemDaCompraDTO> itens;

	private EnderecoDTO endereco;

}
