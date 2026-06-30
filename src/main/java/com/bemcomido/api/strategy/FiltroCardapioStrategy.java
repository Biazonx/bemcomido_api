package com.bemcomido.api.strategy;

import com.bemcomido.api.model.Prato;
import java.util.List;

/**
 * Padrão de Projeto GoF: STRATEGY
 *
 * Define a interface comum para diferentes estratégias de filtragem de pratos.
 * Permite trocar o algoritmo de filtragem em tempo de execução,
 * sem alterar o código que o utiliza.
 */
public interface FiltroCardapioStrategy {
    List<Prato> filtrar(List<Prato> pratos);
}
