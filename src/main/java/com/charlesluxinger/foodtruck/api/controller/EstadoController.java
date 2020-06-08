package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.model.EstadoModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.EstadoPayload;
import com.charlesluxinger.foodtruck.api.domain.repository.EstadoRepository;
import com.charlesluxinger.foodtruck.api.domain.service.EstadoService;
import com.charlesluxinger.foodtruck.api.mapper.EstadoMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/estados")
@AllArgsConstructor
public class EstadoController {

    private final EstadoRepository estadoRepository;
    private final EstadoService estadoService;
    private final EstadoMapper estadoMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EstadoModel> findAll() {
        return estadoMapper.toCollectionModel(estadoRepository.findAll());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EstadoModel findById(@PathVariable("id") Long id) {
        return estadoMapper.toModel(estadoService.findById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel save(@Valid @RequestBody EstadoPayload estadoPayload) {
        var estado = estadoMapper.toDomainObject(estadoPayload);
        return estadoMapper.toModel(estadoService.save(estado));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EstadoModel update(@PathVariable Long id, @Valid @RequestBody EstadoPayload estadoPayload) {
        var estadoFound = estadoService.findById(id);

        estadoMapper.copyToDomainObject(estadoPayload, estadoFound);

        return estadoMapper.toModel(estadoService.save(estadoFound));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        estadoService.remove(id);
    }

}