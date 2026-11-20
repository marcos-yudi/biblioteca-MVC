package com.example.marcosyudibiblioteca.service;

import com.example.marcosyudibiblioteca.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();
    private long proximoId = 1;

    public UsuarioService() {
        salvar(new Usuario("Ana Souza", "ana@email.com", "(43) 99999-0001"));
        salvar(new Usuario("Bruno Lima", "bruno@email.com", "(43) 99999-0002"));
    }

    public List<Usuario> listar() {
        return usuarios;
    }

    public Usuario buscar(Long id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void salvar(Usuario usuario) {
        if (usuario.getId() == null) {
            usuario.setId(proximoId++);
            usuarios.add(usuario);
        } else {
            Usuario existente = buscar(usuario.getId());
            existente.setNome(usuario.getNome());
            existente.setEmail(usuario.getEmail());
            existente.setTelefone(usuario.getTelefone());
        }
    }

    public void excluir(Long id) {
        usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }
}
