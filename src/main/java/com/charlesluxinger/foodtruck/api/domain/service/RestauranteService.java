package com.charlesluxinger.foodtruck.api.domain.service;

import com.charlesluxinger.foodtruck.api.domain.entity.Restaurante;
import com.charlesluxinger.foodtruck.api.domain.exception.ConstraintEntityViolationException;
import com.charlesluxinger.foodtruck.api.domain.exception.RestauranteNotFoundException;
import com.charlesluxinger.foodtruck.api.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CozinhaService cozinhaService;
    private final CidadeService cidadeService;
    private final FormaPagamentoService formaPagamentoService;

    public Restaurante findById(Long id) {
        return  restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNotFoundException(id));
    }

    @Transactional
    public Restaurante save(Restaurante restaurante){
        var cozinha = cozinhaService.findById(restaurante.getCozinha().getId());
        var cidade = cidadeService.findById(restaurante.getEndereco().getCidade().getId());

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);

        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void remove(Long id){
        try {
            restauranteRepository.deleteById(id);
            restauranteRepository.flush();
        }catch (EmptyResultDataAccessException ex){
            throw new RestauranteNotFoundException(id, ex);
        }
        catch (DataIntegrityViolationException ex) {
            throw new ConstraintEntityViolationException(Restaurante.class, id);
        }
    }

    @Transactional
    public void changeStatus(Long restauranteId, Boolean status){
        var restaurante = findById(restauranteId);
        restaurante.setAtivo(status);
    }

    @Transactional
    public void formaPagamentoRemove(Long restauranteId, Long formasPagamentoId) {
        var restaurante = findById(restauranteId);
        var formaPagamento = formaPagamentoService.findById(formasPagamentoId);

        restaurante.getFormasPagamento().removeIf(f -> f.equals(formaPagamento));
    }

    @Transactional
    public void formaPagamentoAdd(Long restauranteId, Long formaPagamentoId) {
        var restaurante = findById(restauranteId);
        var formaPagamento = formaPagamentoService.findById(formaPagamentoId);

        restaurante.getFormasPagamento().add(formaPagamento);
    }

    @Transactional
    public void abrir(Long restauranteId) {
        findById(restauranteId).abrir();
    }

    @Transactional
    public void fechar(Long restauranteId) {
        findById(restauranteId).fechar();
    }

}