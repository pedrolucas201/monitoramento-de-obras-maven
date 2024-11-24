package com.obrasmonitoramento.services;

import com.obrasmonitoramento.entities.Tarefa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TarefaService {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("obrasPU");
    private static final EntityManager em = emf.createEntityManager();

    // Salvar Tarefa
    public void salvar(Tarefa tarefa) {
        em.getTransaction().begin();
        em.persist(tarefa);
        em.getTransaction().commit();
    }

    // Listar todas as tarefas
    public List<Tarefa> listarTodas() {
        return em.createQuery("SELECT t FROM Tarefa t", Tarefa.class).getResultList();
    }

    // Atualizar Tarefa
    public void atualizar(Tarefa tarefa) {
        em.getTransaction().begin();
        em.merge(tarefa);
        em.getTransaction().commit();
    }

    // Excluir Tarefa
    public void excluir(int id) {
        em.getTransaction().begin();
        Tarefa tarefa = em.find(Tarefa.class, id);
        if (tarefa != null) {
            em.remove(tarefa);
        }
        em.getTransaction().commit();
    }
}
