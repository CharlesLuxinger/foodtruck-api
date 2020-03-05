package com.charlesluxinger.foodtruck.api.domain.repository;

import com.charlesluxinger.foodtruck.api.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> consultarPorNome();
    
}
