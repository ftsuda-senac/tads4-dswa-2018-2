/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.controller;

import br.senac.tads4.dsw.exemplosspring.model.Categoria;
import br.senac.tads4.dsw.exemplosspring.model.Produto;
import br.senac.tads4.dsw.exemplosspring.service.CategoriaService;
import br.senac.tads4.dsw.exemplosspring.service.ProdutoService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService; // = new ProdutoServiceJPA();
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ModelAndView listar() {
        List<Produto> produtos = produtoService.findAll(0, 100);
        return new ModelAndView("produto/lista-bs4")
                .addObject("produtos", produtos);
    }

    @GetMapping("/novo")
    public ModelAndView incluirNovo() {
        return new ModelAndView("produto/formulario-bs4")
                .addObject("produto", new Produto());
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Produto produto = produtoService.findById(id);
        if (produto.getCategorias() != null 
                && !produto.getCategorias().isEmpty()) {
            List<Integer> idsCategorias = new ArrayList<>();
            for (Categoria cat : produto.getCategorias()) {
                idsCategorias.add(cat.getId());
            }
            produto.setIdsCategorias(idsCategorias);
        }
        return new ModelAndView("produto/formulario-bs4")
                .addObject("produto", produto);

    }

    @PostMapping("/salvar")
    public ModelAndView salvar(
            @ModelAttribute("produto") Produto produto,
            RedirectAttributes redirectAttributes) {

        if (produto.getIdsCategorias() != null 
                && !produto.getIdsCategorias().isEmpty()) {
            Set<Categoria> categoriasSelecionadas = new HashSet<>();
            for (Integer idCat : produto.getIdsCategorias()) {
                Categoria cat = categoriaService.findById(idCat);
                cat.setProdutos(new HashSet<>(Arrays.asList(produto)));
                categoriasSelecionadas.add(cat);
            }
            produto.setCategorias(categoriasSelecionadas);
        }

        produtoService.save(produto);
        redirectAttributes.addFlashAttribute("msg", "Produto ID " + produto.getId()
                + " salvo com sucesso");
        return new ModelAndView("redirect:/produto");
    }

    @ModelAttribute("categorias")
    public List<Categoria> getCategorias() {
        List<Categoria> categorias = categoriaService.findAll();
        return categorias;
    }
}
