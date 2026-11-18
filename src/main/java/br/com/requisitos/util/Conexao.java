package br.com.requisitos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = valor("DB_URL", "jdbc:postgresql://localhost:5432/gerenciador_requisitos");
    private static final String USUARIO = valor("DB_USER", "postgres");
    private static final String SENHA = valor("DB_PASSWORD", "postgres");

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver do PostgreSQL não encontrado.", e);
        }
    }

    private Conexao() {
    }

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    private static String valor(String variavel, String padrao) {
        String valor = System.getenv(variavel);
        return valor == null || valor.isBlank() ? padrao : valor;
    }
}
