package br.com.requisitos.servlet;

import br.com.requisitos.model.Usuario;
import br.com.requisitos.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario logado = (Usuario) request.getSession().getAttribute("usuarioLogado");
        if (logado == null || !logado.isAdmin()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acesso permitido apenas ao administrador.");
            return;
        }
        request.setCharacterEncoding("UTF-8");
        super.service(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        try {
            if ("novo".equals(acao)) {
                request.setAttribute("usuario", new Usuario());
                request.getRequestDispatcher("/WEB-INF/usuario-form.jsp").forward(request, response);
            } else if ("editar".equals(acao)) {
                Usuario usuario = usuarioService.buscarPorId(Integer.parseInt(request.getParameter("id")));
                if (usuario == null) {
                    response.sendRedirect(request.getContextPath() + "/usuarios");
                    return;
                }
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("/WEB-INF/usuario-form.jsp").forward(request, response);
            } else {
                request.setAttribute("usuarios", usuarioService.listar());
                request.getRequestDispatcher("/WEB-INF/usuarios.jsp").forward(request, response);
            }
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario logado = (Usuario) request.getSession().getAttribute("usuarioLogado");
        try {
            if ("excluir".equals(request.getParameter("acao"))) {
                usuarioService.excluir(Integer.parseInt(request.getParameter("id")), logado);
                response.sendRedirect(request.getContextPath() + "/usuarios?msg=excluido");
                return;
            }

            Usuario usuario = new Usuario();
            String id = request.getParameter("id");
            usuario.setId(id == null || id.isBlank() ? 0 : Integer.parseInt(id));
            usuario.setNome(request.getParameter("nome"));
            usuario.setEmail(request.getParameter("email"));
            usuario.setPerfil(request.getParameter("perfil"));
            usuario.setAtivo(request.getParameter("ativo") != null);
            try {
                usuarioService.salvar(usuario, request.getParameter("senha"));
                response.sendRedirect(request.getContextPath() + "/usuarios?msg=salvo");
            } catch (IllegalArgumentException e) {
                request.setAttribute("erro", e.getMessage());
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("/WEB-INF/usuario-form.jsp").forward(request, response);
            }
        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute("erroUsuarios", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/usuarios");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
