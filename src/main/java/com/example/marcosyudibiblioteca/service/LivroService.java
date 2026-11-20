package com.example.marcosyudibiblioteca.service;

import com.example.marcosyudibiblioteca.model.Livro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivroService {

    private final List<Livro> livros = new ArrayList<>();
    private long proximoId = 1;

    public LivroService() {
        salvar(new Livro("Dom Casmurro", "Machado de Assis", "978-85-00-00000-0", "Romance"));
        salvar(new Livro("O Cortiço", "Aluísio Azevedo", "978-85-00-00001-7", "Romance"));
        salvar(new Livro("Vidas Secas", "Graciliano Ramos", "978-85-00-00002-4", "Romance"));
    }

    public List<Livro> listar() {
        return livros;
    }

    public Livro buscar(Long id) {
        return livros.stream()
                .filter(livro -> livro.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void salvar(Livro livro) {
        if (livro.getId() == null) {
            livro.setId(proximoId++);
            livros.add(livro);
        } else {
            Livro existente = buscar(livro.getId());
            existente.setTitulo(livro.getTitulo());
            existente.setAutor(livro.getAutor());
            existente.setIsbn(livro.getIsbn());
            existente.setCategoria(livro.getCategoria());
        }
    }

    public void excluir(Long id) {
        livros.removeIf(livro -> livro.getId().equals(id));
    }
}
