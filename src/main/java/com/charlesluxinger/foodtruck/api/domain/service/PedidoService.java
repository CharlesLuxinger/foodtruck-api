package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.entity.Pedido;
import com.charlesluxinger.foodtruck.api.domain.entity.StatusPedido;
import com.charlesluxinger.foodtruck.api.domain.exception.DomainException;
import com.charlesluxinger.foodtruck.api.domain.exception.PedidoNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final RestauranteService restauranteService;
	private final CidadeService cidadeService;
	private final UsuarioService usuarioService;
	private final ProdutoService produtoService;
	private final FormaPagamentoService formaPagamentoService;

	public Pedido findById(Long pedidoId) {
		return pedidoRepository.findById(pedidoId).orElseThrow(() -> new PedidoNotFoundException(pedidoId));
	}

	@Transactional
	public Pedido emitir(Pedido pedido) {
		validarPedido(pedido);
		validarItens(pedido);

		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
		pedido.getValorTotal();

		return pedidoRepository.save(pedido);
	}

	@Transactional
	public void confirmar(Long pedidoId){
		var pedido = findById(pedidoId);

		if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
			throw new DomainException(String.format("Status do pedido %d não pode ser alterado de %s para %s", pedido.getId(), pedido.getStatus(), StatusPedido.CONFIRMADO.getDescricao()));
		}

		pedido.setStatus(StatusPedido.CONFIRMADO);
		pedido.setDataConfirmacao(OffsetDateTime.now());
	}

	private void validarPedido(Pedido pedido) {
		var cidade = cidadeService.findById(pedido.getEndereco().getCidade().getId());
		var cliente = usuarioService.findById(pedido.getCliente().getId());
		var restaurante = restauranteService.findById(pedido.getRestaurante().getId());
		var formaPagamento = formaPagamentoService.findById(pedido.getFormaPagamento().getId());

		pedido.getEndereco().setCidade(cidade);
		pedido.setCliente(cliente);
		pedido.setRestaurante(restaurante);
		pedido.setFormaPagamento(formaPagamento);

		if (!restaurante.formaPagamentoValida(formaPagamento)) {
			throw new DomainException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.", formaPagamento.getDescricao()));
		}
	}

	private void validarItens(Pedido pedido) {
		pedido.getItens().forEach(item -> {
			var produto = produtoService.findByRestauranteIdAndProdutoId(pedido.getRestaurante().getId(), item.getProduto().getId());

			item.setPedido(pedido);
			item.setProduto(produto);
			item.setPrecoUnitario(produto.getPreco());
		});
	}

}