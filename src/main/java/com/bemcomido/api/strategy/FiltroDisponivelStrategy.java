package com.bemcomido.api.strategy;

import com.bemcomido.api.model.Prato;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Estratégia: retorna somente os pratos marcados como disponíveis.
 */
@Component
public class FiltroDisponivelStrategy implements FiltroCardapioStrategy {

    @Override
    public List<Prato> filtrar(List<Prato> pratos) {
        return pratos.stream()
                .filter(Prato::getDisponivel)
                .collect(Collectors.toList());
    }
}
