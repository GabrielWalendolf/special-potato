//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.special.potato.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_perfil", nullable = false, unique = true)
    private String nomePerfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "perfil")
    private List<Visualizacao> visualizacoes;

    @OneToMany(mappedBy = "perfil")
    private List<Avaliacao> avaliacoes;

    public Perfil() {
    }

    public Perfil(Integer id, String nomePerfil, Usuario usuario) {
        this.id = id;
        this.nomePerfil = nomePerfil;
        this.usuario = usuario;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomePerfil() {
        return this.nomePerfil;
    }

    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        return "Perfil{" +
                "id=" + id +
                ", nomePerfil='" + nomePerfil + '\'' +
                ", usuarioId=" + (usuario != null ? usuario.getId() : null) +
                '}';
    }
}
