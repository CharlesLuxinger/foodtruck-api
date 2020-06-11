package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.entity.FormaPagamento;
import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.exception.FormaPagamentoNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.repository.FormaPagamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FormaPagamentoService {

	private static final String MSG_FORMA_PAGAMENTO_EM_USO
			= "Forma de pagamento de código %d não pode ser removida, pois está em uso";

	private final FormaPagamentoRepository formaPagamentoRepository;

	@Transactional
	public FormaPagamento save(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}

	@Transactional
	public void remove(Long formaPagamentoId) {
		try {
			formaPagamentoRepository.deleteById(formaPagamentoId);
			formaPagamentoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNotFoundException(formaPagamentoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntityNotFoundException(
					String.format(MSG_FORMA_PAGAMENTO_EM_USO, formaPagamentoId));
		}
	}

	public FormaPagamento findById(Long formaPagamentoId) {
		return formaPagamentoRepository.findById(formaPagamentoId)
				.orElseThrow(() -> new FormaPagamentoNotFoundException(formaPagamentoId));
	}
}
