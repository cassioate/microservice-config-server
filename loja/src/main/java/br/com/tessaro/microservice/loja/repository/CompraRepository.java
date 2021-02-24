package br.com.tessaro.microservice.loja.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.tessaro.microservice.loja.model.Compra;

public interface CompraRepository extends CrudRepository<Compra, Long> {

}
