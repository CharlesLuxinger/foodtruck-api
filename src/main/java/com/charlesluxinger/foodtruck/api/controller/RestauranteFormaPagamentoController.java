package com.charlesluxinger.foodtruck.api.controller;

import com.charlesluxinger.foodtruck.api.domain.model.FormaPagamentoModel;
import com.charlesluxinger.foodtruck.api.domain.service.RestauranteService;
import com.charlesluxinger.foodtruck.api.mapper.FormaPagamentoMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/formas-pagamento")
@AllArgsConstructor
public class RestauranteFormaPagamentoController {

    private RestauranteService restauranteService;
    private final FormaPagamentoMapper formaPagamentoMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FormaPagamentoModel> findAll(@PathVariable Long restauranteId) {
        var restaurante = restauranteService.findById(restauranteId);
        return formaPagamentoMapper.toCollectionModel(restaurante.getFormasPagamento());
    }

    @PutMapping(path = "/{formasPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long restauranteId, @PathVariable Long formasPagamentoId){
        restauranteService.formaPagamentoAdd(restauranteId, formasPagamentoId);
    }

    @DeleteMapping(path = "/{formasPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long restauranteId, @PathVariable Long formasPagamentoId){
        restauranteService.formaPagamentoRemove(restauranteId, formasPagamentoId);
    }

}