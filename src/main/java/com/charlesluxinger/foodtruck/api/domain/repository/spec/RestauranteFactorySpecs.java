package com.charlesluxinger.foodtruck.api.domain.repository.spec;

import com.charlesluxinger.foodtruck.api.domain.entity.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteFactorySpecs {

    public static Specification<Restaurante> comFreteGratis(){
        return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> porNome(String nome){
        return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
    }


}
