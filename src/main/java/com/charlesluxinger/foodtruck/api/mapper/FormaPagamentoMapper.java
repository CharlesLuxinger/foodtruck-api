package com.charlesluxinger.foodtruck.api.mapper;

import com.charlesluxinger.foodtruck.api.domain.entity.FormaPagamento;
import com.charlesluxinger.foodtruck.api.domain.model.FormaPagamentoModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.FormaPagamentoPayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoMapper {

	@Autowired
	private ModelMapper modelMapper;

	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		return modelMapper.map(formaPagamento, FormaPagamentoModel.class);
	}

	public List<FormaPagamentoModel> toCollectionModel(List<FormaPagamento> formasPagamentos) {
		return formasPagamentos.stream()
				.map(formaPagamento -> toModel(formaPagamento))
				.collect(Collectors.toList());
	}

	public FormaPagamento toDomainObject(FormaPagamentoPayload formaPagamentPayload) {
		return modelMapper.map(formaPagamentPayload, FormaPagamento.class);
	}

	public void copyToDomainObject(FormaPagamentoPayload formaPagamentPayload, FormaPagamento formaPagamento) {
		modelMapper.map(formaPagamentPayload, formaPagamento);
	}
}
