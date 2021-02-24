package br.com.tessaro.microservice.loja.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.tessaro.microservice.loja.client.FornecedorClient;
import br.com.tessaro.microservice.loja.client.TransportadorClient;
import br.com.tessaro.microservice.loja.model.Compra;
import br.com.tessaro.microservice.loja.model.dto.InfoEntregaDTO;
import br.com.tessaro.microservice.loja.model.dto.InfoFornecedorDTO;
import br.com.tessaro.microservice.loja.model.dto.InfoPedidoDTO;
import br.com.tessaro.microservice.loja.model.dto.VoucherDTO;
import br.com.tessaro.microservice.loja.model.dto.compraDTO;
import br.com.tessaro.microservice.loja.repository.CompraRepository;

@Service
public class CompraService {

	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);
	
	@Autowired
	private TransportadorClient transportadorClient;
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private FornecedorClient fornecedorClient;
	
	@HystrixCommand(threadPoolKey = "getByIdThreadPool")
	public Compra getById(Long id) {
		return compraRepository.findById(id).orElse(new Compra());
	}
	
	@HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompraThreadPool")
	public Compra realizaCompra(compraDTO compra) {
		
		LOG.info("Buscando informações do fornecedor de {}", compra.getEndereco().getEstado());
		InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		
		LOG.info("Realizando um pedido");
		InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());
			
		InfoEntregaDTO entregaDto = new InfoEntregaDTO();
		entregaDto.setPedidoId(pedido.getId());
		entregaDto.setDataParaEntrega(LocalDate.now().plusDays(pedido.getTempoDePreparo()));
		entregaDto.setEnderecoOrigem(info.getEndereco());
		entregaDto.setEnderecoDestino(compra.getEndereco().toString());
		
		VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDto);
		
		
		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(pedido.getId());
		compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		compraSalva.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
		compraSalva.setVoucher(voucher.getNumero());
		
		compraRepository.save(compraSalva);
		
		/* codigo comentado abaixo foi utilizado para exemplificar a utilização do Hystrix na hora de fazer chamado no postman*/
////		Time.sleep(2000);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return compraSalva;
		
	}
	
	
	public Compra realizaCompraFallback(compraDTO compra) {
		Compra compraFallBack = new Compra();
		return compraFallBack;
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
