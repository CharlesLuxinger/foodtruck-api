package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.model.FormaPagamentoModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.FormaPagamentoPayload;
import com.charlesluxinger.foodtruck.api.domain.repository.FormaPagamentoRepository;
import com.charlesluxinger.foodtruck.api.domain.service.FormaPagamentoService;
import com.charlesluxinger.foodtruck.api.mapper.FormaPagamentoMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
@AllArgsConstructor
public class FormaPagamentoController {

	private final FormaPagamentoRepository formaPagamentoRepository;
	private final FormaPagamentoService formaPagamentoService;
	private final FormaPagamentoMapper formaPagamentoMapper;

	public List<FormaPagamentoModel> findAll() {
		return formaPagamentoMapper.toCollectionModel(formaPagamentoRepository.findAll());
	}

	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoModel findById(@PathVariable Long formaPagamentoId) {
		var formaPagamento = formaPagamentoService.findById(formaPagamentoId);

		return formaPagamentoMapper.toModel(formaPagamento);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel save(@RequestBody @Valid FormaPagamentoPayload formaPagamentoPayload) {
		var formaPagamento = formaPagamentoMapper.toDomainObject(formaPagamentoPayload);

		formaPagamento = formaPagamentoService.save(formaPagamento);

		return formaPagamentoMapper.toModel(formaPagamento);
	}

	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoModel update(@PathVariable Long formaPagamentoId,
	                                     @RequestBody @Valid FormaPagamentoPayload formaPagamentoPayload) {
		var formaPagamentoAtual = formaPagamentoService.findById(formaPagamentoId);

		formaPagamentoMapper.copyToDomainObject(formaPagamentoPayload, formaPagamentoAtual);

		formaPagamentoAtual = formaPagamentoService.save(formaPagamentoAtual);

		return formaPagamentoMapper.toModel(formaPagamentoAtual);
	}

	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long formaPagamentoId) {
		formaPagamentoService.remove(formaPagamentoId);
	}
}
