package com.example.marcosyudibiblioteca.controller;

import com.example.marcosyudibiblioteca.model.Livro;
import com.example.marcosyudibiblioteca.service.AutorService;
import com.example.marcosyudibiblioteca.service.CategoriaService;
import com.example.marcosyudibiblioteca.service.LivroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;
    private final AutorService autorService;
    private final CategoriaService categoriaService;

    public LivroController(LivroService livroService, AutorService autorService,
                           CategoriaService categoriaService) {
        this.livroService = livroService;
        this.autorService = autorService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("livros", livroService.listar());
        return "livros/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        return formulario(model, new Livro());
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        return formulario(model, livroService.buscar(id));
    }

    @PostMapping
    public String salvar(@ModelAttribute Livro livro) {
        livroService.salvar(livro);
        return "redirect:/livros";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        livroService.excluir(id);
        return "redirect:/livros";
    }

    private String formulario(Model model, Livro livro) {
        model.addAttribute("livro", livro);
        model.addAttribute("autores", autorService.listar());
        model.addAttribute("categorias", categoriaService.listar());
        return "livros/form";
    }
}
