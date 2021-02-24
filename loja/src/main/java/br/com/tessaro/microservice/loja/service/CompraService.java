package br.com.tessaro.microservice.loja.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.tessaro.microservice.loja.client.FornecedorClient;
import br.com.tessaro.microservice.loja.model.Compra;
import br.com.tessaro.microservice.loja.model.dto.InfoFornecedorDTO;
import br.com.tessaro.microservice.loja.model.dto.InfoPedidoDTO;
import br.com.tessaro.microservice.loja.model.dto.compraDTO;


@Service
public class CompraService {

	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);
	
	@Autowired
	private FornecedorClient fornecedorClient;
	
	public Compra realizaCompra(compraDTO compra) {
		
		LOG.info("Buscando informações do fornecedor de {}", compra.getEndereco().getEstado());
		InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		
		LOG.info("Realizando um pedido");
		InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());
			
		System.out.println(info.getEndereco());
		
		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(pedido.getId());
		compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		
		return compraSalva;
		
	}
	
	/* utilização do LoadBalancer sem o FeignClients */
	
//	@Autowired
//	private RestTemplate client;
//	
//	@Autowired
//	private DiscoveryClient eurekaClient;
//	
//	public void realizaCompra(compraDTO compra) {
//		ResponseEntity<InfoFornecedorDTO> exchange =
//				client.exchange("http://fornecedores/info/"+compra.getEndereco().getEstado(), 
//						HttpMethod.GET, 
//						null, 
//						InfoFornecedorDTO.class);
//		
//		//Verificando o Load Balancer, para saber as portas das instancias (localhost:8080, localhost:8081...) referente a fornecedores.
//		eurekaClient.getInstances("fornecedores").stream()
//		.forEach(fornecedor -> {
//			System.out.println(fornecedor.getHost()+fornecedor.getPort());
//		});
//		
//		System.out.println(exchange.getBody().getEndereco());
//		
//	}
	
}
