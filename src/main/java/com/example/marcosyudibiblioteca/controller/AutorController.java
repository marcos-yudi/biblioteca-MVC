package com.example.marcosyudibiblioteca.controller;

import com.example.marcosyudibiblioteca.model.Autor;
import com.example.marcosyudibiblioteca.service.AutorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("autores", autorService.listar());
        return "autores/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("autor", new Autor());
        return "autores/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("autor", autorService.buscar(id));
        return "autores/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Autor autor) {
        autorService.salvar(autor);
        return "redirect:/autores";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        autorService.excluir(id);
        return "redirect:/autores";
    }
}
