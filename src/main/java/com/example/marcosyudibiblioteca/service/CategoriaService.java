package com.example.marcosyudibiblioteca.service;

import com.example.marcosyudibiblioteca.model.Categoria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    private final List<Categoria> categorias = new ArrayList<>();
    private long proximoId = 1;

    public CategoriaService() {
        salvar(new Categoria("Romance", "Narrativas de ficção em prosa"));
        salvar(new Categoria("Poesia", "Obras escritas em verso"));
        salvar(new Categoria("Tecnologia", "Computação, programação e engenharia"));
    }

    public List<Categoria> listar() {
        return categorias;
    }

    public Categoria buscar(Long id) {
        return categorias.stream()
                .filter(categoria -> categoria.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void salvar(Categoria categoria) {
        if (categoria.getId() == null) {
            categoria.setId(proximoId++);
            categorias.add(categoria);
        } else {
            Categoria existente = buscar(categoria.getId());
            existente.setNome(categoria.getNome());
            existente.setDescricao(categoria.getDescricao());
        }
    }

    public void excluir(Long id) {
        categorias.removeIf(categoria -> categoria.getId().equals(id));
    }
}
