package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.model.Cidade;
import com.charlesluxinger.foodtruck.api.domain.repository.CidadeRepository;
import com.charlesluxinger.foodtruck.api.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cidade findById(@PathVariable Long id) {
        return cidadeRepository.findById(id).get();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Cidade cidade) {
        try {
            cidade = cidadeService.save(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Cidade cidade) {
        Optional<Cidade> cidadeFound = cidadeRepository.findById(id);

        try {
            if (cidadeFound.isPresent()) {
                BeanUtils.copyProperties(cidade, cidadeFound.get(), "id");
                return ResponseEntity.ok(cidadeService.save(cidadeFound.get()));
            }
            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cidade> remove(@PathVariable Long id){
        try {
            cidadeService.remove(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
