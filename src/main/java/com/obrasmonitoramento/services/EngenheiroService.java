package com.obrasmonitoramento.services;

import com.obrasmonitoramento.entities.Engenheiro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EngenheiroService {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("obrasPU");
    private static final EntityManager em = emf.createEntityManager();

    // Salvar Engenheiro
    public void salvar(Engenheiro engenheiro) {
        em.getTransaction().begin();
        em.persist(engenheiro);
        em.getTransaction().commit();
    }

    // Listar todos os engenheiros
    public List<Engenheiro> listarTodos() {
        return em.createQuery("SELECT e FROM Engenheiro e", Engenheiro.class).getResultList();
    }

    // Atualizar Engenheiro
    public void atualizar(Engenheiro engenheiro) {
        em.getTransaction().begin();
        em.merge(engenheiro);
        em.getTransaction().commit();
    }

    // Excluir Engenheiro
    public void excluir(int id) {
        em.getTransaction().begin();
        Engenheiro engenheiro = em.find(Engenheiro.class, id);
        if (engenheiro != null) {
            em.remove(engenheiro);
        }
        em.getTransaction().commit();
    }
}
