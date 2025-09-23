//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.special.potato.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(length = 1000)
    private String descricao;

    @Column(nullable = false)
    private Integer duracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "video")
    private List<Visualizacao> visualizacoes;

    @OneToMany(mappedBy = "video")
    private List<Avaliacao> avaliacoes;

    public Video() {
    }

    public Video(Integer id, String titulo, String descricao, Integer duracao, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.categoria = categoria;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDuracao() {
        return this.duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Visualizacao> getVisualizacoes() {
        return this.visualizacoes;
    }

    public void setVisualizacoes(List<Visualizacao> visualizacoes) {
        this.visualizacoes = visualizacoes;
    }

    public List<Avaliacao> getAvaliacoes() {
        return this.avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", duracao=" + duracao +
                ", categoriaId=" + (categoria != null ? categoria.getId() : null) +
                '}';
    }
}
