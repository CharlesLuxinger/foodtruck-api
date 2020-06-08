package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.exception.ConstraintEntityViolationException;
import com.charlesluxinger.foodtruck.api.domain.exception.EstadoNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.entity.Estado;
import com.charlesluxinger.foodtruck.api.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class EstadoService {

    private EstadoRepository estadoRepository;

    public Estado findById(Long id) {
        return  estadoRepository.findById(id).orElseThrow(() -> new EstadoNotFoundException(id));
    }

    @Transactional
    public Estado save(Estado estado){
        return estadoRepository.save(estado);
    }

    @Transactional
    public void remove(Long id){
        try {
            estadoRepository.deleteById(id);
            estadoRepository.flush();
        }catch (EmptyResultDataAccessException ex){
            throw new EstadoNotFoundException(id, ex);
        }
        catch (DataIntegrityViolationException ex) {
            throw new ConstraintEntityViolationException(Estado.class, id);
        }
    }

}