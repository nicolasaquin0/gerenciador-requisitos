package br.com.requisitos.model;

public class Usuario {

    private String nome;
    private String email;
    private String perfil;

    public Usuario(String nome, String email, String perfil) {
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPerfil() {
        return perfil;
    }
}
