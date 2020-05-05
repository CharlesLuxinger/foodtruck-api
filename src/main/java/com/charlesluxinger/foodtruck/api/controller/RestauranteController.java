package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.exception.CozinhaNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.exception.DomainException;
import com.charlesluxinger.foodtruck.api.domain.model.Restaurante;
import com.charlesluxinger.foodtruck.api.domain.repository.RestauranteRepository;
import com.charlesluxinger.foodtruck.api.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static com.charlesluxinger.foodtruck.api.domain.repository.spec.RestauranteFactorySpecs.comFreteGratis;
import static com.charlesluxinger.foodtruck.api.domain.repository.spec.RestauranteFactorySpecs.porNome;

@RestController
@RequestMapping(value = "/restaurantes")
@AllArgsConstructor
public class RestauranteController {

    private RestauranteRepository restauranteRepository;
    private RestauranteService restauranteService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurante> findAll() {
        return restauranteRepository.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurante findById(@PathVariable Long id) {
        return restauranteService.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante save(@RequestBody Restaurante restaurante) {
        return saveRestaurante(restaurante);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurante update(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Restaurante restauranteFound = restauranteService.findById(id);

        BeanUtils.copyProperties(restaurante, restauranteFound, "id",
                "formasPagamento",
                "endereco",
                "dataCadastro",
                "produtos");

        return saveRestaurante(restauranteFound);
    }

    @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurante partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fieldsToUpdate, HttpServletRequest request) {
        Restaurante restauranteFound = restauranteService.findById(id);

        merge(fieldsToUpdate, restauranteFound, request);

        return update(id, restauranteFound);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        restauranteService.remove(id);
    }

    @GetMapping("/comFreteGratis")
    public List<Restaurante> restaurantesComFreteGratis(String nome) {
        return restauranteRepository.findAll(comFreteGratis().and(porNome(nome)));
    }

    private void merge(Map<String, Object> fieldsToUpdate, Restaurante restauranteTarget, HttpServletRequest request) {
        ServletServerHttpRequest httpRequest = new ServletServerHttpRequest(request);

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurante restauranteSource = objectMapper.convertValue(fieldsToUpdate, Restaurante.class);

            fieldsToUpdate.forEach((fieldName, fieldValue) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, fieldName);
                field.setAccessible(true);

                Object newValue = ReflectionUtils.getField(field, restauranteSource);

                ReflectionUtils.setField(field, restauranteTarget, newValue);
            });

        } catch (IllegalArgumentException ex) {
            Throwable cause = ExceptionUtils.getRootCause(ex);
            throw new HttpMessageNotReadableException(ex.getMessage(), cause, httpRequest);
        }
    }

    private Restaurante saveRestaurante(Restaurante restauranteFound) {
        try {
            return restauranteService.save(restauranteFound);
        } catch (CozinhaNotFoundException e) {
            throw new DomainException(e.getMessage(), e);
        }
    }
}