package com.charlesluxinger.foodtruck.api.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data // Inseri Get Set toString Equals Hashcode
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	@Column(name = "NOME", length = 30)
	private String nome;

	@Column(name = "TAXA_FRETE")
	private BigDecimal taxaFrete;

}
