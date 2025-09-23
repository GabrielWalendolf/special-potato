//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.special.potato.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visualizacao")
public class Visualizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private Integer progresso;

    public Visualizacao() {
    }

    public Visualizacao(Integer id, Perfil perfil, Video video, LocalDateTime dataHora, Integer progresso) {
        this.id = id;
        this.perfil = perfil;
        this.video = video;
        this.dataHora = dataHora;
        this.progresso = progresso;
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

    public LocalDateTime getDataHora() {
        return this.dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getProgresso() {
        return this.progresso;
    }

    public void setProgresso(Integer progresso) {
        this.progresso = progresso;
    }

    @Override
    public String toString() {
        return "Visualizacao{" +
                "id=" + id +
                ", perfilId=" + (perfil != null ? perfil.getId() : null) +
                ", videoId=" + (video != null ? video.getId() : null) +
                ", dataHora=" + dataHora +
                ", progresso=" + progresso +
                '}';
    }
}
