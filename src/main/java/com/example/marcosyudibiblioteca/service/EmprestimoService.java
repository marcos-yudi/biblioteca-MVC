package com.example.marcosyudibiblioteca.service;

import com.example.marcosyudibiblioteca.model.Emprestimo;
import com.example.marcosyudibiblioteca.model.Livro;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmprestimoService {

    private final List<Emprestimo> emprestimos = new ArrayList<>();
    private long proximoId = 1;

    private final LivroService livroService;
    private final UsuarioService usuarioService;

    public EmprestimoService(LivroService livroService, UsuarioService usuarioService) {
        this.livroService = livroService;
        this.usuarioService = usuarioService;

        Emprestimo exemplo = new Emprestimo();
        exemplo.setLivro(livroService.buscar(3L));
        exemplo.setUsuario(usuarioService.buscar(1L));
        exemplo.setDataRetirada(LocalDate.now());
        exemplo.setDataDevolucao(LocalDate.now().plusDays(14));
        salvar(exemplo);
    }

    public List<Emprestimo> listar() {
        return emprestimos;
    }

    public Emprestimo buscar(Long id) {
        return emprestimos.stream()
                .filter(emprestimo -> emprestimo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void salvar(Emprestimo dados) {
        Emprestimo emprestimo;

        if (dados.getId() == null) {
            emprestimo = new Emprestimo();
            emprestimo.setId(proximoId++);
            emprestimos.add(emprestimo);
        } else {
            emprestimo = buscar(dados.getId());
            emprestimo.getLivro().setDisponivel(true);
        }

        Livro livro = livroService.buscar(dados.getLivro().getId());
        livro.setDisponivel(dados.isDevolvido());

        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuarioService.buscar(dados.getUsuario().getId()));
        emprestimo.setDataRetirada(dados.getDataRetirada());
        emprestimo.setDataDevolucao(dados.getDataDevolucao());
        emprestimo.setDevolvido(dados.isDevolvido());
    }

    public void excluir(Long id) {
        Emprestimo emprestimo = buscar(id);

        if (emprestimo != null) {
            emprestimo.getLivro().setDisponivel(true);
            emprestimos.remove(emprestimo);
        }
    }
}
