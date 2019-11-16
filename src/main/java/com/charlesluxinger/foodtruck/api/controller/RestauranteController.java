package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.exception.EntityNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.model.Restaurante;
import com.charlesluxinger.foodtruck.api.domain.repository.RestauranteRepository;
import com.charlesluxinger.foodtruck.api.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> findAll() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> findById(@PathVariable Long id) {
        Restaurante restaurante = restauranteRepository.findById(id);

        if (restaurante == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(restaurante);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurante restaurante) {

        try {
            restaurante = restauranteService.save(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Restaurante restauranteFound = restauranteRepository.findById(id);

        try {
            if (restauranteFound != null) {
                BeanUtils.copyProperties(restaurante, restauranteFound, "id");
                restauranteFound = restauranteService.save(restauranteFound);
                return ResponseEntity.ok(restauranteFound);
            }
            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fieldsToUpdate){
        Restaurante restauranteFound = restauranteRepository.findById(id);

        if (restauranteFound == null) {
            return ResponseEntity.notFound().build();
        }

        merge(fieldsToUpdate, restauranteFound);

        return update(id, restauranteFound);
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<Restaurante> remove(@PathVariable Long id){
        try {
            restauranteService.remove(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    private void merge(Map<String, Object> fieldsToUpdate, Restaurante restauranteTarget) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteSource = objectMapper.convertValue(fieldsToUpdate, Restaurante.class);

        fieldsToUpdate.forEach((fieldName, fieldValue) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, fieldName);
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, restauranteSource);

            ReflectionUtils.setField(field, restauranteTarget, newValue);
        });
    }
}
