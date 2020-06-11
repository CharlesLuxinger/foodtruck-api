package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.exception.ConstraintEntityViolationException;
import com.charlesluxinger.foodtruck.api.domain.exception.RestauranteNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.entity.Cozinha;
import com.charlesluxinger.foodtruck.api.domain.entity.Restaurante;
import com.charlesluxinger.foodtruck.api.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RestauranteService {

    private RestauranteRepository restauranteRepository;
    private CozinhaService cozinhaService;

    public Restaurante findById(Long id) {
        return  restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNotFoundException(id));
    }

    @Transactional
    public Restaurante save(Restaurante restaurante){
        Cozinha cozinha = cozinhaService.findById(restaurante.getCozinha().getId());
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void remove(Long id){
        try {
            restauranteRepository.deleteById(id);
            restauranteRepository.flush();
        }catch (EmptyResultDataAccessException ex){
            throw new RestauranteNotFoundException(id, ex);
        }
        catch (DataIntegrityViolationException ex) {
            throw new ConstraintEntityViolationException(Restaurante.class, id);
        }
    }

    @Transactional
    public void changeStatus(Long restauranteId, Boolean status){
        var restaurante = findById(restauranteId);
        restaurante.setStatus(status);
    }
}
