package com.bemcomido.api.strategy;

import com.bemcomido.api.model.Prato;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Estratégia: retorna pratos dentro de uma faixa de preço.
 */
@AllArgsConstructor
public class FiltroFaixaPrecoStrategy implements FiltroCardapioStrategy {

    private final BigDecimal precoMin;
    private final BigDecimal precoMax;

    @Override
    public List<Prato> filtrar(List<Prato> pratos) {
        return pratos.stream()
                .filter(p -> p.getPreco().compareTo(precoMin) >= 0
                          && p.getPreco().compareTo(precoMax) <= 0)
                .collect(Collectors.toList());
    }
}
