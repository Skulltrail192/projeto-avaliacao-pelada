package com.avaliacao.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "inscricao")
public class Inscricao{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    @NotNull
    private int id;

    @Column(name = "usuario_id")
    private int usuarioId;

    @Column(name = "pelada_id")
    private int peladaId;

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getPeladaId() {
        return peladaId;
    }

    public void setPeladaId(int peladaId) {
        this.peladaId = peladaId;
    }
}