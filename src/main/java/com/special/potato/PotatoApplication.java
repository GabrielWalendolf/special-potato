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
	public CommandLineRunner run(UsuarioRepository usuarioRepo, CategoriaRepository categoriaRepo,
								 VideoRepository videoRepo, PerfilRepository perfilRepo,
								 VisualizacaoRepository visualizacaoRepo, AvaliacaoRepository avaliacaoRepo) {
		return args -> {

			// =================================================================================
			//  PARA CONTROLAR A EXECUÇÃO:
			//  - Na primeira vez, deixe o bloco "INSERÇÃO DE DADOS" ativo.
			//  - Após a primeira execução, comente o bloco "INSERÇÃO DE DADOS"
			//    para executar apenas  as consultas.
			// =================================================================================


			// --- 1. INSERÇÃO DE DADOS DE EXEMPLO ---
			System.out.println("==================================================");
			System.out.println("INICIANDO A INSERÇÃO DE DADOS DE TESTE...");
			System.out.println("==================================================");

			// Criando Categorias
			Categoria acao = new Categoria(); acao.setNome("Ação");
			Categoria comedia = new Categoria(); comedia.setNome("Comédia");
			Categoria ficcao = new Categoria(); ficcao.setNome("Ficção Científica");
			categoriaRepo.saveAll(Arrays.asList(acao, comedia, ficcao));
			System.out.println("-> Categorias salvas.");

			// Criando Vídeos
			Video missaoImpossivel = new Video(); missaoImpossivel.setTitulo("Missão: Impossível"); missaoImpossivel.setDescricao("Um agente secreto em uma missão perigosa."); missaoImpossivel.setDuracao(7200); missaoImpossivel.setCategoria(acao);
			Video oProtetor = new Video(); oProtetor.setTitulo("O Protetor"); oProtetor.setDescricao("Um homem com um passado misterioso protege os indefesos."); oProtetor.setDuracao(7500); oProtetor.setCategoria(acao);
			Video genteGrande = new Video(); genteGrande.setTitulo("Gente Grande"); genteGrande.setDescricao("Amigos de infância se reúnem após anos."); genteGrande.setDuracao(6500); genteGrande.setCategoria(comedia);
			Video interestelar = new Video(); interestelar.setTitulo("Interestelar"); interestelar.setDescricao("Uma equipe de exploradores viaja através de um buraco de minhoca."); interestelar.setDuracao(10140); interestelar.setCategoria(ficcao);
			videoRepo.saveAll(Arrays.asList(missaoImpossivel, oProtetor, genteGrande, interestelar));
			System.out.println("-> Vídeos salvos.");

			// Criando Usuários e Perfis
			Usuario gabriel = new Usuario(); gabriel.setNome("Gabriel Walendolf"); gabriel.setEmail("gabriel@email.com"); gabriel.setSenha("senha123"); gabriel.setDataCadastro(LocalDateTime.now());
			usuarioRepo.save(gabriel);
			Perfil perfilGabriel = new Perfil(); perfilGabriel.setNomePerfil("Gabs"); perfilGabriel.setUsuario(gabriel);
			perfilRepo.save(perfilGabriel);

			Usuario ana = new Usuario(); ana.setNome("Lucas Borghezam"); ana.setEmail("lucas@email.com"); ana.setSenha("senha456"); ana.setDataCadastro(LocalDateTime.now().minusDays(10));
			usuarioRepo.save(ana);
			Perfil perfilAna = new Perfil(); perfilAna.setNomePerfil("Aninha"); perfilAna.setUsuario(ana);
			perfilRepo.save(perfilAna);
			System.out.println("-> Usuários e Perfis salvos.");

			// Criando Visualizações
			Visualizacao v1 = new Visualizacao(); v1.setPerfil(perfilGabriel); v1.setVideo(missaoImpossivel); v1.setDataHora(LocalDateTime.now()); v1.setProgresso(100);
			Visualizacao v2 = new Visualizacao(); v2.setPerfil(perfilGabriel); v2.setVideo(oProtetor); v2.setDataHora(LocalDateTime.now().minusDays(1)); v2.setProgresso(80);
			Visualizacao v3 = new Visualizacao(); v3.setPerfil(perfilGabriel); v3.setVideo(interestelar); v3.setDataHora(LocalDateTime.now().minusHours(2)); v3.setProgresso(100);
			Visualizacao v4 = new Visualizacao(); v4.setPerfil(perfilAna); v4.setVideo(missaoImpossivel); v4.setDataHora(LocalDateTime.now().minusHours(5)); v4.setProgresso(100);
			Visualizacao v5 = new Visualizacao(); v5.setPerfil(perfilAna); v5.setVideo(genteGrande); v5.setDataHora(LocalDateTime.now().minusDays(2)); v5.setProgresso(50);
			visualizacaoRepo.saveAll(Arrays.asList(v1, v2, v3, v4, v5));
			System.out.println("-> Visualizações salvas.");

			// Criando Avaliações
			Avaliacao a1 = new Avaliacao(); a1.setPerfil(perfilGabriel); a1.setVideo(missaoImpossivel); a1.setNota(5); a1.setComentario("Excelente!");
			Avaliacao a2 = new Avaliacao(); a2.setPerfil(perfilAna); a2.setVideo(missaoImpossivel); a2.setNota(4); a2.setComentario("Muito bom.");
			Avaliacao a3 = new Avaliacao(); a3.setPerfil(perfilGabriel); a3.setVideo(oProtetor); a3.setNota(3); a3.setComentario("Razoável.");
			Avaliacao a4 = new Avaliacao(); a4.setPerfil(perfilGabriel); a4.setVideo(interestelar); a4.setNota(5); a4.setComentario("Obra-prima!");
			avaliacaoRepo.saveAll(Arrays.asList(a1, a2, a3, a4));
			System.out.println("-> Avaliações salvas.");
			System.out.println("\nDados de teste inseridos com sucesso!\n");


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
