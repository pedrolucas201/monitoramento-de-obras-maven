package com.obrasmonitoramento.entities;

import javax.persistence.*;

@Entity
public class Engenheiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String registroCREA;

    public Engenheiro() {}

    public Engenheiro(String nome, String registroCREA) {
        this.nome = nome;
        this.registroCREA = registroCREA;
    }

    // Getters e Setters

    @Override
    public String toString() {
        return "Engenheiro: " +
                "\nID:" + id +
                "\nNome: " + nome +
                "\nRegistro CREA: " + registroCREA;
    }
}
