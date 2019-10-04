package com.charlesluxinger.foodtruck.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charlesluxinger.foodtruck.api.domain.model.Estado;
import com.charlesluxinger.foodtruck.api.domain.repository.EstadoRepository;

@RestController // Possui ambas anotacoes @ResponseBody @Controller
@RequestMapping(value = "/estados", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@GetMapping
	public List<Estado> findAll() {
		return estadoRepository.findAll();
	}

	@GetMapping("/{id}")
	public Estado findById(@PathVariable("id") Long id) {
		return estadoRepository.findById(id);
	}
}
