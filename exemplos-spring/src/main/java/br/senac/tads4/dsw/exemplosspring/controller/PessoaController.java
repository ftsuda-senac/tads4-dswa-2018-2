/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.controller;

import br.senac.tads4.dsw.exemplosspring.model.Pessoa;
import br.senac.tads4.dsw.exemplosspring.service.PessoaService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/mvc/pessoa")
public class PessoaController {

    @GetMapping
    public ModelAndView listar() {
        PessoaService service = new PessoaService();
        List<Pessoa> lista = service.listar();
        return new ModelAndView("pessoa/lista")
                .addObject("pessoas", lista);
    }

    @GetMapping("/{id}")
    public ModelAndView obter(@PathVariable("id") Long id) {
        PessoaService service = new PessoaService();
        Pessoa pessoa = service.obter(id);
        return new ModelAndView("pessoa/detalhe")
                .addObject("pessoa", pessoa);
    }

    @GetMapping("/formulario")
    public ModelAndView abrirFormulario() {
        return new ModelAndView("pessoa/formulario")
                .addObject("pessoa", new Pessoa());
    }
    
    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute("pessoa") Pessoa pessoa) {
        return new ModelAndView("pessoa/resultado")
                .addObject("pessoa", pessoa);
    }

    @PostMapping("/salvar2")
    public ModelAndView salvarComPostRedirectGet(
            @ModelAttribute("pessoa") Pessoa pessoa,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("pessoa", pessoa);
        redirectAttributes.addFlashAttribute("msg", "Pessoa adicionada com sucesso");
        return new ModelAndView("redirect:/mvc/pessoa/formulario");
    }
    
    @GetMapping("/resultado")
    public ModelAndView mostrarResultado() {
        return new ModelAndView("pessoa/resultado");
    }
}
