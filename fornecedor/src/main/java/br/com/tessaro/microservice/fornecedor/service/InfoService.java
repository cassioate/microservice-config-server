package br.com.tessaro.microservice.fornecedor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tessaro.microservice.fornecedor.model.InfoFornecedor;
import br.com.tessaro.microservice.fornecedor.repository.InfoRepository;

@Service
public class InfoService {
	
	@Autowired
	private InfoRepository infoRepository;
	
	public InfoFornecedor getInfoPorEstado(String estado) {
		InfoFornecedor info = infoRepository.findByEstado(estado);
		return info;
	}

}
