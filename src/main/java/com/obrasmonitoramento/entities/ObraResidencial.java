package com.obrasmonitoramento.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Residencial")
public class ObraResidencial extends Obra {

    private int numeroApartamentos;

    public ObraResidencial() {}

    public ObraResidencial(String nome, String descricao, String endereco, int numeroApartamentos) {
        super(nome, descricao, endereco);
        this.numeroApartamentos = numeroApartamentos;
    }

    // Getters e Setters


    public int getNumeroApartamentos() {
        return numeroApartamentos;
    }

    public void setNumeroApartamentos(int numeroApartamentos) {
        this.numeroApartamentos = numeroApartamentos;
    }

    @Override
    public String toString() {
        return super.toString() + ", Número de Apartamentos: " + numeroApartamentos;
    }
}
