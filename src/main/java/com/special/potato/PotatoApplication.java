package com.special.potato;

import com.special.potato.entity.*;
import com.special.potato.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PotatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PotatoApplication.class, args);
	}

	@Bean
	public CommandLineRunner runQueries(VideoRepository videoRepo, UsuarioRepository usuarioRepo) {
		return args -> {


			// --- 2. EXECUÇÃO DAS CONSULTAS ---
			System.out.println("==================================================");
			System.out.println("EXECUTANDO AS CONSULTAS SOLICITADAS...");
			System.out.println("==================================================");

			System.out.println("\n[CONSULTA 1] Buscar vídeos pelo título contendo 'Missão':");
			List<Video> videosPorTitulo = videoRepo.findByTituloContainingIgnoreCaseOrderByTituloAsc("Missão");
			videosPorTitulo.forEach(v -> System.out.println("  -> ID: " + v.getId() + ", Título: " + v.getTitulo()));

			System.out.println("\n[CONSULTA 2] Vídeos da categoria 'Ação' ordenados por título:");
			List<Video> videosPorCategoria = videoRepo.findByCategoriaNomeIgnoreCaseOrderByTituloAsc("Ação");
			videosPorCategoria.forEach(v -> System.out.println("  -> ID: " + v.getId() + ", Título: " + v.getTitulo()));

			System.out.println("\n[CONSULTA 3] Top 10 vídeos mais bem avaliados:");
			List<Video> topAvaliados = videoRepo.findTop10ByOrderByAvaliacaoMediaDesc(PageRequest.of(0, 10));
			topAvaliados.forEach(v -> System.out.println("  -> ID: " + v.getId() + ", Título: " + v.getTitulo()));

			System.out.println("\n[CONSULTA 4] Top 10 vídeos mais assistidos:");
			List<Video> topAssistidos = videoRepo.findTop10ByOrderByVisualizacoesDesc(PageRequest.of(0, 10));
			topAssistidos.forEach(v -> System.out.println("  -> ID: " + v.getId() + ", Título: " + v.getTitulo()));

			System.out.println("\n[CONSULTA 5] Usuário que mais assistiu vídeos:");
			List<Usuario> topUsuarios = usuarioRepo.findTopUsuarioByMostViews(PageRequest.of(0, 1));
			if (!topUsuarios.isEmpty()) {
				System.out.println("  -> ID: " + topUsuarios.get(0).getId() + ", Nome: " + topUsuarios.get(0).getNome());
			} else {
				System.out.println("  -> Nenhum usuário encontrado.");
			}
			System.out.println("\n==================================================");
			System.out.println("EXECUÇÃO FINALIZADA.");
			System.out.println("==================================================");
		};
	}
}
