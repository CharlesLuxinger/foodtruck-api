package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.exception.ConstraintEntityViolationException;
import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.model.Cozinha;
import com.charlesluxinger.foodtruck.api.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha save(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void remove(Long id) {
        try {
            cozinhaRepository.remove(id);
        }catch (EmptyResultDataAccessException ex){
            throw new EntityNotFoundException(String.format("Cozinha de ID: %d não encontrada.", id));
        }
        catch (DataIntegrityViolationException ex) {
           throw new ConstraintEntityViolationException(String.format("Cozinha de ID: %d não pode ser removida, pois está em uso.", id));
        }
    }
}
