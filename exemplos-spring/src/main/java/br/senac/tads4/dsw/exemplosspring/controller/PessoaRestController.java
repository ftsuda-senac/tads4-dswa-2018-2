/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.controller;

import br.senac.tads4.dsw.exemplosspring.model.Pessoa;
import br.senac.tads4.dsw.exemplosspring.service.PessoaService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fernando.tsuda
 */
@RestController
@RequestMapping("/rest/pessoa")
public class PessoaRestController {

    @GetMapping
    public ResponseEntity<List<Pessoa>> listar() {
        PessoaService service = new PessoaService();
        List<Pessoa> lista = service.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> obter(@PathVariable("id") Long id) {
        PessoaService service = new PessoaService();
        Pessoa pessoa = service.obter(id);
        return ResponseEntity.ok(pessoa);
    }

}
