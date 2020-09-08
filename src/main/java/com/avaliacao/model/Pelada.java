package com.avaliacao.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "pelada")
public class Pelada{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    @NotNull
    private int id;

    @Column(name="nome_evento", nullable=false, unique=true)
    private String nomeEvento   ;

    @Column(name="data", unique=true)
    @Temporal(TemporalType.DATE)
    private Date data;

    @Column(name="hora", unique=true)
    @Temporal(TemporalType.TIME)
    private Date hora;

    @Column(name="local", nullable=false, unique=false)
    private String local;

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}