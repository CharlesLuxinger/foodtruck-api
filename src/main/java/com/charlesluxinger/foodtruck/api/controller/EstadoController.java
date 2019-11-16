package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.exception.ConstraintEntityViolationException;
import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.model.Estado;
import com.charlesluxinger.foodtruck.api.domain.repository.EstadoRepository;
import com.charlesluxinger.foodtruck.api.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Possui ambas anotacoes @ResponseBody @Controller
@RequestMapping(value = "/estados", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private EstadoService estadoService;

	@GetMapping
	public List<Estado> findAll() {
		return estadoRepository.findAll();
	}

	@GetMapping("/{id}")
	public Estado findById(@PathVariable("id") Long id) {
		return estadoRepository.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado save(@RequestBody Estado estado){
		return estadoRepository.save(estado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Estado> update(@PathVariable Long id, @RequestBody Estado estado){
		Estado estadoFound = estadoRepository.findById(id);

		if (estadoFound != null) {
			BeanUtils.copyProperties(estado, estadoFound, "id");
			estadoFound = estadoService.save(estadoFound);
			return ResponseEntity.ok(estadoFound);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Estado> remove(@PathVariable Long id){
		try {
			estadoService.remove(id);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (ConstraintEntityViolationException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
