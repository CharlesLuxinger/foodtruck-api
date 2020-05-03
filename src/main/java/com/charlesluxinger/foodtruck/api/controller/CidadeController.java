package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.exception.DomainException;
import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.model.Cidade;
import com.charlesluxinger.foodtruck.api.domain.repository.CidadeRepository;
import com.charlesluxinger.foodtruck.api.domain.service.CidadeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
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

import java.util.List;

@RestController
@RequestMapping("/cidades")
@AllArgsConstructor
public class CidadeController {

    private CidadeRepository cidadeRepository;
    private CidadeService cidadeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade findById(@PathVariable Long id) {
        return cidadeService.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade save(@RequestBody Cidade cidade) {
        return saveCidade(cidade);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cidade update(@PathVariable Long id, @RequestBody Cidade cidade) {
        Cidade cidadeFound = cidadeService.findById(id);

        BeanUtils.copyProperties(cidade, cidadeFound, "id");

        return saveCidade(cidadeFound);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        cidadeService.remove(id);
    }

    private Cidade saveCidade(@RequestBody Cidade cidade) {
        try {
            return cidadeService.save(cidade);
        } catch (EntityNotFoundException e) {
            throw new DomainException(e.getMessage());
        }
    }
}
