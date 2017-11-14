package br.senai.sp.informatica.mobileb.listademusicas.model;


public class Musica {
    private Long id;
    private String titulo;
    private String artista;
    private String album;
    private String dtInclusao;

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

    public String getdtInclusao() {
        return dtInclusao;
    }

    public void setdtInclusao(String dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Musica musica = (Musica) o;

        if (!id.equals(musica.id)) return false;
        if (!titulo.equals(musica.titulo)) return false;
        if (!artista.equals(musica.artista)) return false;
        if (!album.equals(musica.album)) return false;
        return dtInclusao.equals(musica.dtInclusao);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + titulo.hashCode();
        result = 31 * result + artista.hashCode();
        result = 31 * result + album.hashCode();
        result = 31 * result + dtInclusao.hashCode();
        return result;
    }
}