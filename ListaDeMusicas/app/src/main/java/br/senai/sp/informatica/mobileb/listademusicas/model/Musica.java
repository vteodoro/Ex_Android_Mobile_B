package br.senai.sp.informatica.mobileb.listademusicas.model;

import android.support.annotation.NonNull;

public class Musica implements Comparable<Musica>{
    private Long id;
    private String titulo;
    private String artista;
    private String album;
    private String dtInclusao;
    private byte[] capa;

    public Musica(){}

    public Musica(Long id){ this.id = id;}

    public Musica (Long id, String titulo, String artista, String album, String dtInclusao){
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.dtInclusao = dtInclusao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(String dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public byte[] getCapa() {
        return capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Musica musica = (Musica) o;

        return id.equals(musica.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(@NonNull Musica x) {
        return titulo.toLowerCase().compareTo(x.titulo.toLowerCase());
    }
}
