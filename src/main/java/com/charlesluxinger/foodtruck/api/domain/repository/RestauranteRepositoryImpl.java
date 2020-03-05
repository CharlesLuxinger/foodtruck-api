package com.charlesluxinger.foodtruck.api.domain.repository;

import com.charlesluxinger.foodtruck.api.domain.model.Restaurante;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl {

    @PersistenceContext
    private EntityManager manager;

    public List<Restaurante> find(String nome, BigDecimal taxaFreteInit, BigDecimal taxaFreteFinal) {
        var jpql = new StringBuilder().append("from Restaurantes where nome like :nome ")
                                        .append("and taxaFrete between :taxaFreteInit and :taxaFreteFinal")
                                        .toString();

        return manager.createQuery(jpql, Restaurante.class)
                .setParameter("nome", "%" + nome + "%")
                .setParameter("taxaFreteInit", taxaFreteInit)
                .setParameter("taxaFreteFinal", taxaFreteFinal)
                .getResultList();


    }
}
