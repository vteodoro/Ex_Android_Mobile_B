package br.senai.sp.informatica.mobileb.listadejogos.model;

/**
 * Created by vivi on 31/10/2017.
 */

public class Jogo {

    private Long id;
    private String nome;
    private String genero;

    public Jogo(Long id){}

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
}
