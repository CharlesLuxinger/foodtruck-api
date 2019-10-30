package com.charlesluxinger.foodtruck.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.charlesluxinger.foodtruck.api.domain.model.Cozinha;
import com.charlesluxinger.foodtruck.api.domain.repository.CozinhaRepository;
import com.charlesluxinger.foodtruck.api.model.CozinhasRepresetationModel;

@RestController // Possui ambas anotacoes @ResponseBody @Controller
//@RequestMapping(value = "/cozinhas", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@GetMapping
	public List<Cozinha> findAll() {
		return cozinhaRepository.findAll();
	}

	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasRepresetationModel findAllXML() {
		return new CozinhasRepresetationModel(cozinhaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> findById(@PathVariable("id") Long id) {
		Cozinha cozinha = cozinhaRepository.findById(id);

		if (cozinha == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(cozinha);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha save(@RequestBody Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

}
