package com.obrasmonitoramento.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String descricao;
    private Date dataPrevista;
    private Date dataRealizada;

    @ManyToOne
    @JoinColumn(name = "obra_id")
    private Obra obra;

    public Tarefa() {}

    public Tarefa(String descricao, Date dataPrevista, Date dataRealizada, Obra obra) {
        this.descricao = descricao;
        this.dataPrevista = dataPrevista;
        this.dataRealizada = dataRealizada;
        this.obra = obra;
    }

    // Getters e Setters

    @Override
    public String toString() {
        return "Tarefa: " +
                "\nID: " + id +
                "\nDescricao: " + descricao +
                "\nData Prevista: " + dataPrevista +
                "\nData Realizada: " + dataRealizada +
                "\nObra: " + obra.getNome();
    }
}
