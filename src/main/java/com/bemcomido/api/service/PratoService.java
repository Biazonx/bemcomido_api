package com.bemcomido.api.service;

import com.bemcomido.api.dto.PratoRequestDTO;
import com.bemcomido.api.dto.PratoResponseDTO;
import com.bemcomido.api.model.CategoriaPrato;
import com.bemcomido.api.model.Prato;
import com.bemcomido.api.repository.PratoRepository;
import com.bemcomido.api.strategy.FiltroCardapioStrategy;
import com.bemcomido.api.strategy.FiltroDisponivelStrategy;
import com.bemcomido.api.strategy.FiltroFaixaPrecoStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PratoService {

    private final PratoRepository pratoRepository;
    private final FiltroDisponivelStrategy filtroDisponivelStrategy;

    // ─── CREATE ───────────────────────────────────────────────────────────────

    public PratoResponseDTO criar(PratoRequestDTO dto) {
        Prato prato = new Prato();
        mapearDtoParaEntidade(dto, prato);
        return PratoResponseDTO.fromEntity(pratoRepository.save(prato));
    }

    // ─── READ ─────────────────────────────────────────────────────────────────

    public List<PratoResponseDTO> listarTodos() {
        return pratoRepository.findAll()
                .stream()
                .map(PratoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public PratoResponseDTO buscarPorId(Long id) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prato não encontrado com id: " + id));
        return PratoResponseDTO.fromEntity(prato);
    }

    public List<PratoResponseDTO> listarPorCategoria(CategoriaPrato categoria) {
        return pratoRepository.findByCategoria(categoria)
                .stream()
                .map(PratoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Usa o padrão Strategy para retornar apenas pratos disponíveis.
     */
    public List<PratoResponseDTO> listarDisponiveis() {
        List<Prato> todos = pratoRepository.findAll();
        FiltroCardapioStrategy estrategia = filtroDisponivelStrategy;
        return estrategia.filtrar(todos)
                .stream()
                .map(PratoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Usa o padrão Strategy para filtrar por faixa de preço.
     */
    public List<PratoResponseDTO> listarPorFaixaPreco(BigDecimal min, BigDecimal max) {
        List<Prato> todos = pratoRepository.findAll();
        FiltroCardapioStrategy estrategia = new FiltroFaixaPrecoStrategy(min, max);
        return estrategia.filtrar(todos)
                .stream()
                .map(PratoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // ─── UPDATE ───────────────────────────────────────────────────────────────

    public PratoResponseDTO atualizar(Long id, PratoRequestDTO dto) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prato não encontrado com id: " + id));
        mapearDtoParaEntidade(dto, prato);
        return PratoResponseDTO.fromEntity(pratoRepository.save(prato));
    }

    // ─── DELETE ───────────────────────────────────────────────────────────────

    public void deletar(Long id) {
        if (!pratoRepository.existsById(id)) {
            throw new RuntimeException("Prato não encontrado com id: " + id);
        }
        pratoRepository.deleteById(id);
    }

    // ─── HELPER ───────────────────────────────────────────────────────────────

    private void mapearDtoParaEntidade(PratoRequestDTO dto, Prato prato) {
        prato.setNome(dto.getNome());
        prato.setDescricao(dto.getDescricao());
        prato.setPreco(dto.getPreco());
        prato.setCategoria(dto.getCategoria());
        prato.setUrlImagem(dto.getUrlImagem());
        prato.setDisponivel(dto.getDisponivel() != null ? dto.getDisponivel() : true);
    }
}
