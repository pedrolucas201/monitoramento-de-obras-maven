package com.obrasmonitoramento.services;

import com.obrasmonitoramento.entities.Obra;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ObrasIImplRepositorio {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("obrasPU");
    private static final EntityManager em = emf.createEntityManager();

    // Método para salvar a obra no banco
    public void salvar(Obra obra) {
        em.getTransaction().begin();
        em.persist(obra);
        em.getTransaction().commit();
    }

    // Método para listar todas as obras
    public List<Obra> listarTodas() {
        return em.createQuery("SELECT o FROM Obra o", Obra.class).getResultList();
    }

    // Método para excluir a obra
    public void excluir(int id) {
        em.getTransaction().begin();
        Obra obra = em.find(Obra.class, id);
        if (obra != null) {
            em.remove(obra);
        }
        em.getTransaction().commit();
    }

    // Método para atualizar a obra
    public void atualizar(Obra obra) {
        em.getTransaction().begin();
        em.merge(obra);
        em.getTransaction().commit();
    }
}
