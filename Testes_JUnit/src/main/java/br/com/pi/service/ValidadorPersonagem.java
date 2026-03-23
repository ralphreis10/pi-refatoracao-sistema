package br.com.pi.service;

public class ValidadorPersonagem {

    public boolean nomeValido(String nome) {
        return nome != null && !nome.isBlank() && nome.trim().length() >= 3;
    }

    public boolean racaValida(String raca) {
        return raca != null && !raca.isBlank();
    }

    public boolean podeCadastrar(String nome, String raca) {
        return nomeValido(nome) && racaValida(raca);
    }
}
