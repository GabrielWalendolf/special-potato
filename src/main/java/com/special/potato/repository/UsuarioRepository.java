package com.special.potato.repository;

import com.special.potato.entity.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Consulta 5: Encontrar o usuário que mais assistiu vídeos.
     * A consulta JPQL junta Usuario, Perfil e Visualizacao, agrupa por usuário
     * e ordena pela contagem de visualizações em ordem decrescente.
     * O Pageable é usado para pegar apenas o primeiro resultado (top 1).
     */
    @Query("SELECT u FROM Usuario u JOIN u.perfis p JOIN p.visualizacoes v GROUP BY u.id ORDER BY COUNT(v.id) DESC")
    List<Usuario> findTopUsuarioByMostViews(Pageable pageable);
}
