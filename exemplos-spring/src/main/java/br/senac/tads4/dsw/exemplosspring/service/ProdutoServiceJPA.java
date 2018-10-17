/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.service;

import br.senac.tads4.dsw.exemplosspring.model.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fernando.tsuda
 */
public class ProdutoServiceJPA implements ProdutoService {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Produto> findAll(int offset, int quantidade) {
        Query queryJPQL = 
                entityManager.createQuery(
                        "SELECT p FROM Produto p");
        List<Produto> resultados = queryJPQL.getResultList();
        return resultados;
    }

    @Override
    public Produto findById(long id) {
        Query queryJPQL = 
                entityManager.createQuery(
                        "SELECT p FROM Produto p WHERE p.id = :idProd");
        queryJPQL.setParameter("idProd", id);
        Produto resultado = (Produto) queryJPQL.getSingleResult();
        return resultado;
    }

    @Override
    public Produto save(Produto produto) {
        if (produto.getId() == null) {
            // ID nulo == produto nao existe -> Inclui novo
            entityManager.persist(produto);
        } else {
            // ID nao nulo == produto já existe -> Atualiza
            produto = entityManager.merge(produto);
        }
        return produto;
    }
    
}
