package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.model.Cozinha;
import com.charlesluxinger.foodtruck.api.domain.model.Restaurante;
import com.charlesluxinger.foodtruck.api.domain.repository.CozinhaRepository;
import com.charlesluxinger.foodtruck.api.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante save(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId);

        if (cozinha == null){
            throw new EntityNotFoundException(String.format("Cozinha ID: %d não encontrada.", cozinhaId));
        }

        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public void remove(Long id){
        try {
            restauranteRepository.remove(id);
        }catch (EmptyResultDataAccessException ex){
            throw new EntityNotFoundException(String.format("Restaurante de ID: %d não encontrada.", id));
        }
    }
}
