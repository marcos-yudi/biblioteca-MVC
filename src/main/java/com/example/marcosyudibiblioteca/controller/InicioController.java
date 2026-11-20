package com.example.marcosyudibiblioteca.controller;

import com.example.marcosyudibiblioteca.service.AutorService;
import com.example.marcosyudibiblioteca.service.CategoriaService;
import com.example.marcosyudibiblioteca.service.EmprestimoService;
import com.example.marcosyudibiblioteca.service.LivroService;
import com.example.marcosyudibiblioteca.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    private final LivroService livroService;
    private final AutorService autorService;
    private final CategoriaService categoriaService;
    private final UsuarioService usuarioService;
    private final EmprestimoService emprestimoService;

    public InicioController(LivroService livroService, AutorService autorService,
                            CategoriaService categoriaService, UsuarioService usuarioService,
                            EmprestimoService emprestimoService) {
        this.livroService = livroService;
        this.autorService = autorService;
        this.categoriaService = categoriaService;
        this.usuarioService = usuarioService;
        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("totalLivros", livroService.listar().size());
        model.addAttribute("totalAutores", autorService.listar().size());
        model.addAttribute("totalCategorias", categoriaService.listar().size());
        model.addAttribute("totalUsuarios", usuarioService.listar().size());
        model.addAttribute("emprestimosAtivos", emprestimoService.listar().stream()
                .filter(emprestimo -> !emprestimo.isDevolvido())
                .count());
        return "inicio";
    }
}
