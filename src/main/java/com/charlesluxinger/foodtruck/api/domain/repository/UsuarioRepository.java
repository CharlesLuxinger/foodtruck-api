package com.charlesluxinger.foodtruck.api.domain.repository;

import com.charlesluxinger.foodtruck.api.domain.entity.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {}