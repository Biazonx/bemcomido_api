package com.bemcomido.api.dto;

import com.bemcomido.api.model.CategoriaPrato;
import com.bemcomido.api.model.Prato;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO de resposta — expõe apenas o que o frontend precisa ver.
 */
@Data
public class PratoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private CategoriaPrato categoria;
    private String urlImagem;
    private Boolean disponivel;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    // Converte entidade → DTO
    public static PratoResponseDTO fromEntity(Prato prato) {
        PratoResponseDTO dto = new PratoResponseDTO();
        dto.setId(prato.getId());
        dto.setNome(prato.getNome());
        dto.setDescricao(prato.getDescricao());
        dto.setPreco(prato.getPreco());
        dto.setCategoria(prato.getCategoria());
        dto.setUrlImagem(prato.getUrlImagem());
        dto.setDisponivel(prato.getDisponivel());
        dto.setCriadoEm(prato.getCriadoEm());
        dto.setAtualizadoEm(prato.getAtualizadoEm());
        return dto;
    }
}
