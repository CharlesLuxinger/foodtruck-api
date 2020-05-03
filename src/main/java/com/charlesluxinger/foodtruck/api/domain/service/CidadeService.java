package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.exception.CidadeNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.exception.ConstraintEntityViolationException;
import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.model.Cidade;
import com.charlesluxinger.foodtruck.api.domain.model.Cozinha;
import com.charlesluxinger.foodtruck.api.domain.model.Estado;
import com.charlesluxinger.foodtruck.api.domain.repository.CidadeRepository;
import com.charlesluxinger.foodtruck.api.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CidadeService {

    private CidadeRepository cidadeRepository;
    private EstadoService estadoService;

    public Cidade findById(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new CidadeNotFoundException(id));
    }

    public Cidade save(Cidade cidade){
        Estado estado = estadoService.findById(cidade.getEstado().getId());
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public void remove(Long id) {
        try {
            cidadeRepository.deleteById(id);
        }catch (EmptyResultDataAccessException ex){
            throw new CidadeNotFoundException(id, ex);
        }
        catch (DataIntegrityViolationException ex) {
            throw new ConstraintEntityViolationException(Cidade.class, id);
        }
    }
}
