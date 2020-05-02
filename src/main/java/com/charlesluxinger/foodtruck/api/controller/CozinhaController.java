package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.exception.ConstraintEntityViolationException;
import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.model.Cozinha;
import com.charlesluxinger.foodtruck.api.domain.repository.CozinhaRepository;
import com.charlesluxinger.foodtruck.api.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Possui ambas anotacoes @ResponseBody @Controller
//@RequestMapping(value = "/cozinhas", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping
    public List<Cozinha> findAll() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> findById(@PathVariable("id") Long id) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);

        if (cozinha.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cozinha.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha save(@RequestBody Cozinha cozinha) {
        return cozinhaService.save(cozinha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> update(@PathVariable("id") Long id, @RequestBody Cozinha cozinha) {
        Optional<Cozinha> cozinhaFound = cozinhaRepository.findById(id);

        if (cozinhaFound.isPresent()) {
            BeanUtils.copyProperties(cozinha, cozinhaFound.get(), "id");
            return ResponseEntity.ok(cozinhaService.save(cozinhaFound.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
            cozinhaService.remove(id);
    }

}
