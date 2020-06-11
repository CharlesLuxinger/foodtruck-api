package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.entity.Restaurante;
import com.charlesluxinger.foodtruck.api.domain.model.payload.StatusPayload;
import com.charlesluxinger.foodtruck.api.domain.exception.CozinhaNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.exception.DomainException;
import com.charlesluxinger.foodtruck.api.domain.model.RestauranteModel;
import com.charlesluxinger.foodtruck.api.domain.model.payload.RestaurantePayload;
import com.charlesluxinger.foodtruck.api.domain.repository.RestauranteRepository;
import com.charlesluxinger.foodtruck.api.domain.service.RestauranteService;
import com.charlesluxinger.foodtruck.api.mapper.RestauranteMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.SmartValidator;
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

import javax.validation.Valid;
import java.util.List;

import static com.charlesluxinger.foodtruck.api.domain.repository.spec.RestauranteFactorySpecs.comFreteGratis;
import static com.charlesluxinger.foodtruck.api.domain.repository.spec.RestauranteFactorySpecs.porNome;

@RestController
@RequestMapping(value = "/restaurantes")
@AllArgsConstructor
public class RestauranteController {

    private RestauranteRepository restauranteRepository;
    private RestauranteService restauranteService;
    private SmartValidator validator;
    private RestauranteMapper restauranteMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestauranteModel> findAll() {
        return restauranteMapper.toCollectionModel(restauranteRepository.findAll());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestauranteModel findById(@PathVariable Long id) {
        return restauranteMapper.toModel(restauranteService.findById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel save(@RequestBody @Valid RestaurantePayload restaurante) {
        var restauranteSaved = saveRestaurante(restauranteMapper.toDomainObject(restaurante));
        return restauranteMapper.toModel(restauranteSaved);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestauranteModel update(@PathVariable Long id, @Valid @RequestBody RestaurantePayload restaurantePayload) {
        Restaurante restauranteFound = restauranteService.findById(id);

        restauranteMapper.copyToDomainObject(restaurantePayload, restauranteFound);

        return restauranteMapper.toModel(saveRestaurante(restauranteFound));
    }

    @PatchMapping(path = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void statusChange(@PathVariable Long id, @Valid @RequestBody StatusPayload statusPayload){
        restauranteService.changeStatus(id, statusPayload.getStatus());
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

    private Restaurante saveRestaurante(Restaurante restaurante) {
        try {
            return restauranteService.save(restaurante);
        } catch (CozinhaNotFoundException e) {
            throw new DomainException(e.getMessage(), e);
        }
    }
}