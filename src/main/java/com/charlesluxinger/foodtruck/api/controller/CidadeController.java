package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.entity.Cidade;
import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.exception.EstadoNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.model.CidadeModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.CidadePayload;
import com.charlesluxinger.foodtruck.api.domain.repository.CidadeRepository;
import com.charlesluxinger.foodtruck.api.domain.service.CidadeService;
import com.charlesluxinger.foodtruck.api.mapper.CidadeMapper;
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
@RequestMapping("/cidades")
@AllArgsConstructor
public class CidadeController {

    private final CidadeRepository cidadeRepository;
    private final CidadeService cidadeService;
    private final CidadeMapper cidadeMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CidadeModel> findAll() {
        return cidadeMapper.toCollectionModel(cidadeRepository.findAll());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CidadeModel findById(@PathVariable Long id) {
        return cidadeMapper.toModel(cidadeService.findById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel save(@Valid @RequestBody CidadePayload cidadePayload) {
        var cidade = cidadeMapper.toDomainObject(cidadePayload);
       return cidadeMapper.toModel(saveCidade(cidade));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CidadeModel update(@PathVariable Long id, @Valid @RequestBody CidadePayload cidadePayload) {
        var cidadeFound = cidadeService.findById(id);

        cidadeMapper.copyToDomainObject(cidadePayload, cidadeFound);

        return cidadeMapper.toModel(saveCidade(cidadeFound));
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        cidadeService.remove(id);
    }

    private Cidade saveCidade(@RequestBody Cidade cidade) {
        try {
            return cidadeService.save(cidade);
        } catch (EstadoNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage(), e);
        }
    }
}
