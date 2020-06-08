package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.model.CozinhaModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.CozinhaPayload;
import com.charlesluxinger.foodtruck.api.domain.repository.CozinhaRepository;
import com.charlesluxinger.foodtruck.api.domain.service.CozinhaService;
import com.charlesluxinger.foodtruck.api.mapper.CozinhaMapper;
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
@RequestMapping(value = "/cozinhas")
@AllArgsConstructor
public class CozinhaController {

    private final CozinhaRepository cozinhaRepository;
    private final CozinhaService cozinhaService;
    private final CozinhaMapper cozinhaMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CozinhaModel> findAll() {
        return cozinhaMapper.toCollectionModel(cozinhaRepository.findAll());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CozinhaModel findById(@PathVariable("id") Long id) {
        return cozinhaMapper.toModel(cozinhaService.findById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel save(@Valid @RequestBody CozinhaPayload cozinhaPayload) {
        var cozinha = cozinhaMapper.toDomainObject(cozinhaPayload);
        return cozinhaMapper.toModel(cozinhaService.save(cozinha));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CozinhaModel update(@PathVariable("id") Long id, @Valid @RequestBody CozinhaPayload cozinhaPayload) {
        var cozinhaFound = cozinhaService.findById(id);

        cozinhaMapper.copyToDomainObject(cozinhaPayload, cozinhaFound);

        return cozinhaMapper.toModel(cozinhaService.save(cozinhaFound));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        cozinhaService.remove(id);
    }

}
