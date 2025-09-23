//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.special.potato.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    @Column(nullable = false)
    private Integer nota;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    public Avaliacao() {
    }

    public Avaliacao(Integer id, Perfil perfil, Video video, Integer nota, String comentario) {
        this.id = id;
        this.perfil = perfil;
        this.video = video;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return this.perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Video getVideo() {
        return this.video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Integer getNota() {
        return this.nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "id=" + id +
                ", perfilId=" + (perfil != null ? perfil.getId() : null) +
                ", videoId=" + (video != null ? video.getId() : null) +
                ", nota=" + nota +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
