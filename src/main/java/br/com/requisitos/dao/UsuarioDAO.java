package br.com.requisitos.dao;

import br.com.requisitos.model.Usuario;
import br.com.requisitos.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private static final String CAMPOS = "id_usuario, nome, email, senha_hash, senha_salt, perfil, ativo";

    public List<Usuario> listar() throws SQLException {
        String sql = "SELECT " + CAMPOS + " FROM usuario ORDER BY nome";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conexao = Conexao.obterConexao();
                PreparedStatement comando = conexao.prepareStatement(sql);
                ResultSet resultado = comando.executeQuery()) {
            while (resultado.next()) {
                usuarios.add(montar(resultado));
            }
        }
        return usuarios;
    }

    public Usuario buscarPorId(int id) throws SQLException {
        return buscar("SELECT " + CAMPOS + " FROM usuario WHERE id_usuario = ?", id);
    }

    public Usuario buscarPorEmail(String email) throws SQLException {
        return buscar("SELECT " + CAMPOS + " FROM usuario WHERE LOWER(email) = LOWER(?)", email);
    }

    public void inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, email, senha_hash, senha_salt, perfil, ativo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conexao = Conexao.obterConexao(); PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getEmail());
            comando.setString(3, usuario.getSenhaHash());
            comando.setString(4, usuario.getSenhaSalt());
            comando.setString(5, usuario.getPerfil());
            comando.setBoolean(6, usuario.isAtivo());
            comando.executeUpdate();
        }
    }

    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha_hash = ?, senha_salt = ?, perfil = ?, ativo = ? WHERE id_usuario = ?";
        try (Connection conexao = Conexao.obterConexao(); PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getEmail());
            comando.setString(3, usuario.getSenhaHash());
            comando.setString(4, usuario.getSenhaSalt());
            comando.setString(5, usuario.getPerfil());
            comando.setBoolean(6, usuario.isAtivo());
            comando.setInt(7, usuario.getId());
            comando.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        try (Connection conexao = Conexao.obterConexao();
                PreparedStatement comando = conexao.prepareStatement("DELETE FROM usuario WHERE id_usuario = ?")) {
            comando.setInt(1, id);
            comando.executeUpdate();
        }
    }

    private Usuario buscar(String sql, Object parametro) throws SQLException {
        try (Connection conexao = Conexao.obterConexao(); PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setObject(1, parametro);
            try (ResultSet resultado = comando.executeQuery()) {
                return resultado.next() ? montar(resultado) : null;
            }
        }
    }

    private Usuario montar(ResultSet resultado) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(resultado.getInt("id_usuario"));
        usuario.setNome(resultado.getString("nome"));
        usuario.setEmail(resultado.getString("email"));
        usuario.setSenhaHash(resultado.getString("senha_hash"));
        usuario.setSenhaSalt(resultado.getString("senha_salt"));
        usuario.setPerfil(resultado.getString("perfil"));
        usuario.setAtivo(resultado.getBoolean("ativo"));
        return usuario;
    }
}
