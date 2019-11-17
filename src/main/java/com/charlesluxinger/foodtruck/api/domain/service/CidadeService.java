package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.model.Cidade;
import com.charlesluxinger.foodtruck.api.domain.model.Estado;
import com.charlesluxinger.foodtruck.api.domain.repository.CidadeRepository;
import com.charlesluxinger.foodtruck.api.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade save(Cidade cidade){
        Long estadoId = cidade.getEstado().getId();
        Optional<Estado> estado = estadoRepository.findById(estadoId);

        if (estado.isEmpty()) {
            throw new EntityNotFoundException(String.format("Estado ID: %d não encontrado.", estadoId));
        }

        cidade.setEstado(estado.get());
        return cidadeRepository.save(cidade);
    }

    public void remove(Long id) {
        try {
            cidadeRepository.deleteById(id);
        }catch (EmptyResultDataAccessException ex){
            throw new EntityNotFoundException(String.format("Cidade de ID: %d não encontrada.", id));
        }
    }
}
