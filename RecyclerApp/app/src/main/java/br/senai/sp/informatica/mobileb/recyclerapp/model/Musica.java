package br.senai.sp.informatica.mobileb.recyclerapp.model;
import android.support.annotation.NonNull;

public class Musica implements Comparable<Musica>{
    private Long id;
    private String titulo;
    private String artista;

    public Musica(Long id, String titulo, String artista){
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
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

    @Override
    public int compareTo(@NonNull Musica musica) {
        return 0;
    }

    @Override
    public String toString(){
        return "Filme{" + "id = " + id + ", titulo = " + titulo + '\'' +
                ", artista = " + artista + '\'' + '}';
    }
}
