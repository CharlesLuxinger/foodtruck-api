package com.charlesluxinger.foodtruck.api.domain.repository.spec;

import com.charlesluxinger.foodtruck.api.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RestaurantePorNomeSpec implements Specification<Restaurante> {

    public RestaurantePorNomeSpec(String nome) {
        this.nome = nome;
    }

    private String nome;

    @Override
    public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.like(root.get("nome"), "%" + nome + "%");
    }
}
