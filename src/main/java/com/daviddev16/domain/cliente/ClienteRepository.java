package com.daviddev16.domain.cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ClienteRepository  {

    @Autowired
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Cliente> obterPorNome(String nome) {
        String jpql = "SELECT c FROM Cliente c WHERE c.nome ilike :nome";
        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);
        typedQuery.setParameter("nome", "%" + nome + "%");
        return typedQuery.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> obterTodos() {
        return entityManager
                .createQuery("FROM Cliente", Cliente.class)
                .getResultList();
    }

    @Transactional
    public Cliente salvarCliente(Cliente cliente) {
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente atualizarCliente(Cliente cliente) {
        return entityManager.merge(cliente);
    }

    @Transactional
    public void deletarCliente(Cliente cliente) {
        if (!entityManager.contains(cliente)) {
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional
    public void deletarPorId(Integer id) {
        Cliente cliente = entityManager.find(Cliente.class, id);
        deletarCliente(cliente);
    }


}
