package com.example.marcosyudibiblioteca.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Emprestimo {

    private Long id;
    private Livro livro;
    private Usuario usuario;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataRetirada;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataDevolucao;

    private boolean devolvido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDate dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
}
