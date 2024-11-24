package com.obrasmonitoramento.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Comercial")
public class ObraComercial extends Obra {

    private String empresaResponsavel;

    public ObraComercial() {}

    public ObraComercial(String nome, String descricao, String endereco, String empresaResponsavel) {
        super(nome, descricao, endereco);
        this.empresaResponsavel = empresaResponsavel;
    }

    // Getters e Setters


    public String getEmpresaResponsavel() {
        return empresaResponsavel;
    }

    public void setEmpresaResponsavel(String empresaResponsavel) {
        this.empresaResponsavel = empresaResponsavel;
    }

    @Override
    public String toString() {
        return super.toString() + ", Empresa Responsável: " + empresaResponsavel;
    }
}
