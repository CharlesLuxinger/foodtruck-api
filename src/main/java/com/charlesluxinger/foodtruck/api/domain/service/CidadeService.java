package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.entity.Cidade;
import com.charlesluxinger.foodtruck.api.domain.entity.Estado;
import com.charlesluxinger.foodtruck.api.domain.exception.CidadeNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.exception.ConstraintEntityViolationException;
import com.charlesluxinger.foodtruck.api.domain.repository.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;
    private final EstadoService estadoService;

    public Cidade findById(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new CidadeNotFoundException(id));
    }

    @Transactional
    public Cidade save(Cidade cidade){
        Estado estado = estadoService.findById(cidade.getEstado().getId());
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void remove(Long id) {
        try {
            cidadeRepository.deleteById(id);
            cidadeRepository.flush();
        }catch (EmptyResultDataAccessException ex){
            throw new CidadeNotFoundException(id, ex);
        }
        catch (DataIntegrityViolationException ex) {
            throw new ConstraintEntityViolationException(Cidade.class, id);
        }
    }
}
