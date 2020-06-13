package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.entity.Cozinha;
import com.charlesluxinger.foodtruck.api.domain.exception.ConstraintEntityViolationException;
import com.charlesluxinger.foodtruck.api.domain.exception.CozinhaNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CozinhaService {

    private final CozinhaRepository cozinhaRepository;

    @Transactional
    public Cozinha save(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void remove(Long id) {
        try {
            cozinhaRepository.deleteById(id);
            cozinhaRepository.flush();
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
