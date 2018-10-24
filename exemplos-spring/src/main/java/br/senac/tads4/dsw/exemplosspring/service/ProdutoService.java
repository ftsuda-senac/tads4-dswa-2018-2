/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.service;

import br.senac.tads4.dsw.exemplosspring.model.Produto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fernando.tsuda
 */
@Repository
public interface ProdutoService
        extends JpaRepository<Produto, Long> {
    
    List<Produto> findByNome(String nome);
    
    List<Produto> findByNomeAndDisponivelTrue(String nome);
    
    // Exemplo caso seja necessario customizar query JPQL
//    @Query("SELECT p FROM Produto p LEFT JOIN FETCH "
//            + "p.categorias WHERE p.nome = ?1")
//    Optional<Produto> findByNome(String nome);

}
