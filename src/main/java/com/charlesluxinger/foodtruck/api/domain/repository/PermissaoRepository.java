package com.charlesluxinger.foodtruck.api.domain.repository;

import com.charlesluxinger.foodtruck.api.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}
