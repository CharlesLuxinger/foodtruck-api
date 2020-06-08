package com.charlesluxinger.foodtruck.api.domain.repository;

import com.charlesluxinger.foodtruck.api.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
