package br.senai.sp.informatica.mobileb.listadejogos.model;

import android.support.annotation.NonNull;

public class Jogo implements Comparable<Jogo>{
    private Long id;
    private String nome;
    private String genero;

    public Jogo() {
    }

    public Jogo(Long id) {
        this.id = id;
    }

    public Jogo(Long id, String nome, String genero) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jogo)) return false;

        Jogo jogo = (Jogo) o;

        if (!id.equals(jogo.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(@NonNull Jogo outro) {
        return nome.toLowerCase().compareTo(outro.nome.toLowerCase());
    }
}
