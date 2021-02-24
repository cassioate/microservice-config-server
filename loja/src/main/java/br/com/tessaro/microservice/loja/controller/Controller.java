package br.com.tessaro.microservice.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tessaro.microservice.loja.model.Compra;
import br.com.tessaro.microservice.loja.model.dto.compraDTO;
import br.com.tessaro.microservice.loja.service.CompraService;

@RestController
@RequestMapping("/compra")
public class Controller {
	
	@Autowired
	private CompraService compraService;
	
	@GetMapping("/{id}")
	public Compra geteById(@PathVariable("id") Long id) {
		return compraService.getById(id);
	}
	
	@PostMapping
	public Compra realizaCompra(@RequestBody compraDTO compra) {
		return compraService.realizaCompra(compra);	
	}

}
