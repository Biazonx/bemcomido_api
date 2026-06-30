package com.bemcomido.api.repository;

import com.bemcomido.api.model.CategoriaPrato;
import com.bemcomido.api.model.Prato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long> {

    List<Prato> findByCategoria(CategoriaPrato categoria);

    List<Prato> findByDisponivel(Boolean disponivel);

    List<Prato> findByCategoriaAndDisponivel(CategoriaPrato categoria, Boolean disponivel);
}
