package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.exception.ConstraintEntityViolationException;
import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.model.Cozinha;
import com.charlesluxinger.foodtruck.api.domain.model.Restaurante;
import com.charlesluxinger.foodtruck.api.domain.repository.CozinhaRepository;
import com.charlesluxinger.foodtruck.api.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestauranteService {

    private RestauranteRepository restauranteRepository;
    private CozinhaService cozinhaService;

    public Restaurante findById(Long id) {
        return  restauranteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Restaurante.class, id));
    }

    public Restaurante save(Restaurante restaurante){
        Cozinha cozinha = cozinhaService.findById(restaurante.getCozinha().getId());
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public void remove(Long id){
        try {
            restauranteRepository.deleteById(id);
        }catch (EmptyResultDataAccessException ex){
            throw new EntityNotFoundException(Restaurante.class, id);
        }
        catch (DataIntegrityViolationException ex) {
            throw new ConstraintEntityViolationException(Restaurante.class, id);
        }
    }
}
