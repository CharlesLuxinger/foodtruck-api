package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.exception.ConstraintEntityViolationException;
import com.charlesluxinger.foodtruck.api.domain.exception.CozinhaNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.model.Cozinha;
import com.charlesluxinger.foodtruck.api.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CozinhaService {

    private CozinhaRepository cozinhaRepository;

    public Cozinha save(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void remove(Long id) {
        try {
            cozinhaRepository.deleteById(id);
        }catch (EmptyResultDataAccessException ex){
            throw new CozinhaNotFoundException(id, ex);
        }
        catch (DataIntegrityViolationException ex) {
           throw new ConstraintEntityViolationException(Cozinha.class, id);
        }
    }

    public Cozinha findById(Long id) {
        return  cozinhaRepository.findById(id).orElseThrow(() -> new CozinhaNotFoundException(id));
    }
}
