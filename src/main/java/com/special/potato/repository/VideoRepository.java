package com.special.potato.repository;

import com.special.potato.entity.Video;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Integer> {

    /**
     * Consulta 1: Buscar vídeos cujo título contenha a string fornecida,
     * ignorando maiúsculas/minúsculas, e ordenando pelo título em ordem ascendente.
     * Exemplo: "Missão" -> "Missão Impossível", "Missão Impossível 2", etc.
     */
    List<Video> findByTituloContainingIgnoreCaseOrderByTituloAsc(String titulo);

    /**
     * Consulta 2: Buscar todos os vídeos de uma determinada categoria,
     * ignorando maiúsculas/minúsculas no nome da categoria, e ordenando pelo título do vídeo.
     */
    List<Video> findByCategoriaNomeIgnoreCaseOrderByTituloAsc(String nomeCategoria);

    /**
     * Consulta 3: Buscar os vídeos com a maior média de notas.
     * A anotação @Query é usada para uma consulta JPQL mais complexa.
     * O Pageable limita o resultado aos 'top N' (neste caso, 10).
     */
    @Query("SELECT v FROM Video v JOIN v.avaliacoes a GROUP BY v.id ORDER BY AVG(a.nota) DESC")
    List<Video> findTop10ByOrderByAvaliacaoMediaDesc(Pageable pageable);

    /**
     * Consulta 4: Buscar os vídeos com o maior número de visualizações.
     * A anotação @Query conta as visualizações para cada vídeo.
     * O Pageable limita o resultado aos 'top N' (neste caso, 10).
     */
    @Query("SELECT v FROM Video v JOIN v.visualizacoes vis GROUP BY v.id ORDER BY COUNT(vis.id) DESC")
    List<Video> findTop10ByOrderByVisualizacoesDesc(Pageable pageable);
}
