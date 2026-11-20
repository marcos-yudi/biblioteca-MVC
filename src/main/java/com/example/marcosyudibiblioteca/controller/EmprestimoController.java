package com.example.marcosyudibiblioteca.controller;

import com.example.marcosyudibiblioteca.model.Emprestimo;
import com.example.marcosyudibiblioteca.service.EmprestimoService;
import com.example.marcosyudibiblioteca.service.LivroService;
import com.example.marcosyudibiblioteca.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;
    private final LivroService livroService;
    private final UsuarioService usuarioService;

    public EmprestimoController(EmprestimoService emprestimoService, LivroService livroService,
                                UsuarioService usuarioService) {
        this.emprestimoService = emprestimoService;
        this.livroService = livroService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emprestimos", emprestimoService.listar());
        return "emprestimos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataRetirada(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(14));
        return formulario(model, emprestimo);
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        return formulario(model, emprestimoService.buscar(id));
    }

    @PostMapping
    public String salvar(@ModelAttribute Emprestimo emprestimo) {
        emprestimoService.salvar(emprestimo);
        return "redirect:/emprestimos";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        emprestimoService.excluir(id);
        return "redirect:/emprestimos";
    }

    private String formulario(Model model, Emprestimo emprestimo) {
        model.addAttribute("emprestimo", emprestimo);
        model.addAttribute("livros", livroService.listar().stream()
                .filter(livro -> livro.isDisponivel() || livro == emprestimo.getLivro())
                .toList());
        model.addAttribute("usuarios", usuarioService.listar());
        return "emprestimos/form";
    }
}
