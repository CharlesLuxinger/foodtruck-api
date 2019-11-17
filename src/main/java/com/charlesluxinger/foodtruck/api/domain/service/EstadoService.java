package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.exception.ConstraintEntityViolationException;
import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.model.Estado;
import com.charlesluxinger.foodtruck.api.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado save(Estado estado){
        return estadoRepository.save(estado);
    }

    public void remove(Long id){
        try {
            estadoRepository.deleteById(id);
        }catch (EmptyResultDataAccessException ex){
            throw new EntityNotFoundException(String.format("Estado de ID: %d não encontrada.", id));
        }
        catch (DataIntegrityViolationException ex) {
            throw new ConstraintEntityViolationException(String.format("Estado de ID: %d não pode ser removida, pois está em uso.", id));
        }
    }
}
