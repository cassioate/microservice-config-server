package br.com.tessaro.microservice.fornecedor.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.tessaro.microservice.fornecedor.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long>{

}
