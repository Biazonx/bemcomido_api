package com.bemcomido.api.controller;

import com.bemcomido.api.dto.PratoRequestDTO;
import com.bemcomido.api.dto.PratoResponseDTO;
import com.bemcomido.api.model.CategoriaPrato;
import com.bemcomido.api.service.PratoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/pratos")
@RequiredArgsConstructor
public class PratoController {

    private final PratoService pratoService;

    // ─── POST /api/pratos ─────────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<PratoResponseDTO> criar(@Valid @RequestBody PratoRequestDTO dto) {
        PratoResponseDTO criado = pratoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // ─── GET /api/pratos ──────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<PratoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pratoService.listarTodos());
    }

    // ─── GET /api/pratos/{id} ─────────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pratoService.buscarPorId(id));
    }

    // ─── GET /api/pratos/disponiveis ──────────────────────────────────────────
    @GetMapping("/disponiveis")
    public ResponseEntity<List<PratoResponseDTO>> listarDisponiveis() {
        return ResponseEntity.ok(pratoService.listarDisponiveis());
    }

    // ─── GET /api/pratos/categoria/{categoria} ────────────────────────────────
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<PratoResponseDTO>> listarPorCategoria(
            @PathVariable CategoriaPrato categoria) {
        return ResponseEntity.ok(pratoService.listarPorCategoria(categoria));
    }

    // ─── GET /api/pratos/preco?min=10&max=50 ─────────────────────────────────
    @GetMapping("/preco")
    public ResponseEntity<List<PratoResponseDTO>> listarPorFaixaPreco(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return ResponseEntity.ok(pratoService.listarPorFaixaPreco(min, max));
    }

    // ─── PUT /api/pratos/{id} ─────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PratoRequestDTO dto) {
        return ResponseEntity.ok(pratoService.atualizar(id, dto));
    }

    // ─── DELETE /api/pratos/{id} ──────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pratoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
