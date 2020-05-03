package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.exception.ConstraintEntityViolationException;
import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.exception.EstadoNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.model.Cozinha;
import com.charlesluxinger.foodtruck.api.domain.model.Estado;
import com.charlesluxinger.foodtruck.api.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EstadoService {

    private EstadoRepository estadoRepository;

    public Estado findById(Long id) {
        return  estadoRepository.findById(id).orElseThrow(() -> new EstadoNotFoundException(id));
    }

    public Estado save(Estado estado){
        return estadoRepository.save(estado);
    }

    public void remove(Long id){
        try {
            estadoRepository.deleteById(id);
        }catch (EmptyResultDataAccessException ex){
            throw new EstadoNotFoundException(id, ex);
        }
        catch (DataIntegrityViolationException ex) {
            throw new ConstraintEntityViolationException(Estado.class, id);
        }
    }

}