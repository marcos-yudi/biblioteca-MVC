package com.example.marcosyudibiblioteca.service;

import com.example.marcosyudibiblioteca.model.Autor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutorService {

    private final List<Autor> autores = new ArrayList<>();
    private long proximoId = 1;

    public AutorService() {
        salvar(new Autor("Machado de Assis", "Brasileira"));
        salvar(new Autor("Aluísio Azevedo", "Brasileira"));
        salvar(new Autor("Graciliano Ramos", "Brasileira"));
    }

    public List<Autor> listar() {
        return autores;
    }

    public Autor buscar(Long id) {
        return autores.stream()
                .filter(autor -> autor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void salvar(Autor autor) {
        if (autor.getId() == null) {
            autor.setId(proximoId++);
            autores.add(autor);
        } else {
            Autor existente = buscar(autor.getId());
            existente.setNome(autor.getNome());
            existente.setNacionalidade(autor.getNacionalidade());
        }
    }

    public void excluir(Long id) {
        autores.removeIf(autor -> autor.getId().equals(id));
    }
}
