package com.charlesluxinger.foodtruck.api.domain.model;

import com.charlesluxinger.foodtruck.api.domain.model.Groups.CozinhaId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@ConvertGroup(to = CozinhaId.class)
	@NotBlank
	@Column(name = "nome", length = 30, nullable = false)
	private String nome;

	@ConvertGroup(to = CozinhaId.class)
	@PositiveOrZero
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

	@Valid
	@ConvertGroup(to = CozinhaId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_id", referencedColumnName = "id")
	private Endereco endereco;

	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
				joinColumns = @JoinColumn(name = "restaurante_id"),
					inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento> formasPagamento = new ArrayList<>();

	@CreationTimestamp
	@Column(name = "data_cadastro")
	private OffsetDateTime dataCadastro;

	@UpdateTimestamp
	@Column(name = "data_atualizacao")
	private OffsetDateTime dataAtualizacao;

	@OneToMany(mappedBy = "restaurante")
	List<Produto> produtos = new ArrayList<>();

}