package com.bemcomido.api.dto;

import com.bemcomido.api.model.CategoriaPrato;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO (Data Transfer Object) para receber dados do cliente.
 * Separa a camada de transporte da entidade de domínio.
 */
@Data
public class PratoRequestDTO {

    @NotBlank(message = "O nome do prato é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "A categoria é obrigatória")
    private CategoriaPrato categoria;

    private String urlImagem;

    private Boolean disponivel = true;
}
