package br.com.tessaro.microservice.loja.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.tessaro.microservice.loja.model.dto.InfoEntregaDTO;
import br.com.tessaro.microservice.loja.model.dto.VoucherDTO;

@FeignClient("transportador")
public interface TransportadorClient {
	
//	@RequestMapping("/info/{estado}")
//	VoucherDTO getInfoPorEstado(@PathVariable String estado);

	@RequestMapping(method = RequestMethod.POST, value = "/entrega")
	VoucherDTO reservaEntrega(InfoEntregaDTO entregaDto);

}
