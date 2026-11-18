package br.com.requisitos.service;

import br.com.requisitos.dao.UsuarioDAO;
import br.com.requisitos.model.Usuario;
import br.com.requisitos.util.Senha;
import java.sql.SQLException;
import java.util.List;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario autenticar(String email, String senha) throws SQLException {
        if (vazio(email) || vazio(senha)) {
            return null;
        }
        Usuario usuario = usuarioDAO.buscarPorEmail(email.trim());
        if (usuario == null || !usuario.isAtivo() || !Senha.confere(senha, usuario.getSenhaSalt(), usuario.getSenhaHash())) {
            return null;
        }
        return usuario;
    }

    public List<Usuario> listar() throws SQLException {
        return usuarioDAO.listar();
    }

    public Usuario buscarPorId(int id) throws SQLException {
        return usuarioDAO.buscarPorId(id);
    }

    public void salvar(Usuario usuario, String senha) throws SQLException {
        if (vazio(usuario.getNome()) || vazio(usuario.getEmail())) {
            throw new IllegalArgumentException("Informe o nome e o e-mail.");
        }
        if (!"ADMIN".equals(usuario.getPerfil()) && !"USER".equals(usuario.getPerfil())) {
            throw new IllegalArgumentException("Selecione um perfil válido.");
        }
        usuario.setNome(usuario.getNome().trim());
        usuario.setEmail(usuario.getEmail().trim().toLowerCase());

        Usuario existente = usuarioDAO.buscarPorEmail(usuario.getEmail());
        if (existente != null && existente.getId() != usuario.getId()) {
            throw new IllegalArgumentException("Já existe um usuário com este e-mail.");
        }

        boolean novo = usuario.getId() == 0;
        if (novo && vazio(senha)) {
            throw new IllegalArgumentException("Informe a senha.");
        }
        if (!vazio(senha)) {
            if (senha.length() < 6) {
                throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres.");
            }
            usuario.setSenhaSalt(Senha.gerarSalt());
            usuario.setSenhaHash(Senha.gerarHash(senha, usuario.getSenhaSalt()));
        } else {
            Usuario atual = usuarioDAO.buscarPorId(usuario.getId());
            usuario.setSenhaSalt(atual.getSenhaSalt());
            usuario.setSenhaHash(atual.getSenhaHash());
        }

        if (novo) {
            usuarioDAO.inserir(usuario);
        } else {
            usuarioDAO.atualizar(usuario);
        }
    }

    public void excluir(int id, Usuario logado) throws SQLException {
        if (id == logado.getId()) {
            throw new IllegalArgumentException("Você não pode excluir o seu próprio usuário.");
        }
        usuarioDAO.excluir(id);
    }

    private boolean vazio(String valor) {
        return valor == null || valor.isBlank();
    }
}
