package com.charlesluxinger.foodtruck.api.domain.model;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data // Inseri Get Set toString Equals Hashcode
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "NOME", length = 30, nullable = false)
	private String nome;

	@Column(name = "TAXA_FRETE", nullable = false)
	private BigDecimal taxaFrete;

	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

	@Embedded
	private Endereco endereco;

}
