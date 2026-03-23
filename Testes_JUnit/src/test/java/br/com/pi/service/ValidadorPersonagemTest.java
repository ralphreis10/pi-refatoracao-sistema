package br.com.pi.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidadorPersonagemTest {

    private ValidadorPersonagem validador;

    @BeforeEach
    void setUp() {
        validador = new ValidadorPersonagem();
    }

    @Test
    @DisplayName("Deve permitir cadastro quando nome e raça forem válidos")
    void devePermitirCadastroComDadosValidos() {
        assertTrue(validador.podeCadastrar("Geralt", "Bruxo"));
    }

    @Test
    @DisplayName("Não deve permitir cadastro com nome vazio")
    void naoDevePermitirCadastroComNomeVazio() {
        assertFalse(validador.podeCadastrar("", "Bruxo"));
    }

    @Test
    @DisplayName("Não deve permitir cadastro com nome muito curto")
    void naoDevePermitirCadastroComNomeCurto() {
        assertFalse(validador.podeCadastrar("Al", "Humano"));
    }

    @Test
    @DisplayName("Não deve permitir cadastro com raça vazia")
    void naoDevePermitirCadastroComRacaVazia() {
        assertFalse(validador.podeCadastrar("Ciri", ""));
    }
}
