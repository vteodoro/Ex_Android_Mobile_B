package br.senai.sp.informatica.authapp.model;

public class Usuario {
    private String id;
    private String email;
    private boolean logado = false;
    private String token;

    public Usuario() {
    }

    public Usuario(String uid, String email) {
        this.id = uid;
        this.email = email;
        this.logado = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
