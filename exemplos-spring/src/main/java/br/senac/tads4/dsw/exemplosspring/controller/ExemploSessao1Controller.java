/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.controller;

import br.senac.tads4.dsw.exemplosspring.model.Contador;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@SessionAttributes("contador1")
@RequestMapping("/sessao-contador1")
// Ver https://www.baeldung.com/spring-mvc-session-attributes
public class ExemploSessao1Controller {

    @GetMapping("/{apelido}")
    public ModelAndView somar(
            @PathVariable("apelido") String nome,
            @ModelAttribute("contador1") Contador contador) {
        contador.adicionar(nome);
        return new ModelAndView("sessao-contador1");
    }

    @ModelAttribute("titulo")
    public String getTitulo() {
        return "Exemplo Sessao 1 - Uso do @SessionAttributes";
    }

    @ModelAttribute("contador1")
    public Contador contador() {
        return new Contador();
    }

}
