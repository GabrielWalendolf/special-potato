package com.special.potato.config;

import com.special.potato.entity.*;
import com.special.potato.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
@Profile("init-db") // Este bean só será ativado quando o perfil "init-db" estiver ativo
public class DataInitializer {

    @Bean
    public CommandLineRunner initializeDatabase(UsuarioRepository usuarioRepo, CategoriaRepository categoriaRepo,
                                                VideoRepository videoRepo, PerfilRepository perfilRepo,
                                                VisualizacaoRepository visualizacaoRepo, AvaliacaoRepository avaliacaoRepo) {
        return args -> {
            // Verificação para não inserir dados duplicados
            if (usuarioRepo.count() > 0) {
                System.out.println("O banco de dados já parece estar populado. A inicialização de dados foi ignorada.");
                return;
            }

            System.out.println("==================================================");
            System.out.println("PERFIL 'init-db' ATIVO: INICIANDO A INSERÇÃO DE DADOS...");
            System.out.println("==================================================");

            // Criando Categorias
            Categoria acao = new Categoria();
            acao.setNome("Ação");
            Categoria comedia = new Categoria();
            comedia.setNome("Comédia");
            Categoria ficcao = new Categoria();
            ficcao.setNome("Ficção Científica");
            categoriaRepo.saveAll(Arrays.asList(acao, comedia, ficcao));
            System.out.println("-> Categorias salvas.");

            // Criando Vídeos
            Video missaoImpossivel = new Video();
            missaoImpossivel.setTitulo("Missão: Impossível");
            missaoImpossivel.setDescricao("Um agente secreto em uma missão perigosa.");
            missaoImpossivel.setDuracao(7200);
            missaoImpossivel.setCategoria(acao);

            Video oProtetor = new Video();
            oProtetor.setTitulo("O Protetor");
            oProtetor.setDescricao("Um homem com um passado misterioso protege os indefesos.");
            oProtetor.setDuracao(7500);
            oProtetor.setCategoria(acao);

            Video genteGrande = new Video();
            genteGrande.setTitulo("Gente Grande");
            genteGrande.setDescricao("Amigos de infância se reúnem após anos.");
            genteGrande.setDuracao(6500);
            genteGrande.setCategoria(comedia);

            Video interestelar = new Video();
            interestelar.setTitulo("Interestelar");
            interestelar.setDescricao("Uma equipe de exploradores viaja através de um buraco de minhoca.");
            interestelar.setDuracao(10140);
            interestelar.setCategoria(ficcao);
            videoRepo.saveAll(Arrays.asList(missaoImpossivel, oProtetor, genteGrande, interestelar));
            System.out.println("-> Vídeos salvos.");

            // Criando Usuários e Perfis
            Usuario gabriel = new Usuario();
            gabriel.setNome("Gabriel Walendolf");
            gabriel.setEmail("gabriel@email.com");
            gabriel.setSenha("senha123");
            gabriel.setDataCadastro(LocalDateTime.now());
            usuarioRepo.save(gabriel);

            Perfil perfilGabriel = new Perfil();
            perfilGabriel.setNomePerfil("Gabs");
            perfilGabriel.setUsuario(gabriel);
            perfilRepo.save(perfilGabriel);

            Usuario ana = new Usuario();
            ana.setNome("Ana Silva");
            ana.setEmail("ana@email.com");
            ana.setSenha("senha456");
            ana.setDataCadastro(LocalDateTime.now().minusDays(10));
            usuarioRepo.save(ana);

            Perfil perfilAna = new Perfil();
            perfilAna.setNomePerfil("Aninha");
            perfilAna.setUsuario(ana);
            perfilRepo.save(perfilAna);
            System.out.println("-> Usuários e Perfis salvos.");

            // Criando Visualizações
            // Gabriel assistiu 3 vídeos, Ana assistiu 2
            Visualizacao v1 = new Visualizacao(); v1.setPerfil(perfilGabriel); v1.setVideo(missaoImpossivel); v1.setDataHora(LocalDateTime.now()); v1.setProgresso(100);
            Visualizacao v2 = new Visualizacao(); v2.setPerfil(perfilGabriel); v2.setVideo(oProtetor); v2.setDataHora(LocalDateTime.now().minusDays(1)); v2.setProgresso(80);
            Visualizacao v3 = new Visualizacao(); v3.setPerfil(perfilGabriel); v3.setVideo(interestelar); v3.setDataHora(LocalDateTime.now().minusHours(2)); v3.setProgresso(100);
            Visualizacao v4 = new Visualizacao(); v4.setPerfil(perfilAna); v4.setVideo(missaoImpossivel); v4.setDataHora(LocalDateTime.now().minusHours(5)); v4.setProgresso(100);
            Visualizacao v5 = new Visualizacao(); v5.setPerfil(perfilAna); v5.setVideo(genteGrande); v5.setDataHora(LocalDateTime.now().minusDays(2)); v5.setProgresso(50);
            visualizacaoRepo.saveAll(Arrays.asList(v1, v2, v3, v4, v5));
            System.out.println("-> Visualizações salvas.");

            // Criando Avaliações
            // Média de Missão: Impossível = 4.5
            // Média de Interestelar = 5.0
            // Média de O Protetor = 3.0
            Avaliacao a1 = new Avaliacao(); a1.setPerfil(perfilGabriel); a1.setVideo(missaoImpossivel); a1.setNota(5); a1.setComentario("Excelente!");
            Avaliacao a2 = new Avaliacao(); a2.setPerfil(perfilAna); a2.setVideo(missaoImpossivel); a2.setNota(4); a2.setComentario("Muito bom.");
            Avaliacao a3 = new Avaliacao(); a3.setPerfil(perfilGabriel); a3.setVideo(oProtetor); a3.setNota(3); a3.setComentario("Razoável.");
            Avaliacao a4 = new Avaliacao(); a4.setPerfil(perfilGabriel); a4.setVideo(interestelar); a4.setNota(5); a4.setComentario("Obra-prima!");
            avaliacaoRepo.saveAll(Arrays.asList(a1, a2, a3, a4));
            System.out.println("-> Avaliações salvas.");
            System.out.println("\nDados de teste inseridos com sucesso!\n");

            System.out.println("\nDados de teste inseridos com sucesso!\n");
        };
    }
}
