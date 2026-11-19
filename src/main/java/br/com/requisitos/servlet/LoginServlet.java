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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            Usuario usuario = usuarioService.autenticar(request.getParameter("email"), request.getParameter("senha"));
            if (usuario != null) {
                request.getSession().invalidate();
                request.getSession(true).setAttribute("usuarioLogado", usuario);
                response.sendRedirect(request.getContextPath() + "/menu.jsp");
                return;
            }
            request.setAttribute("erro", "E-mail ou senha inválidos.");
        } catch (SQLException e) {
            System.err.println("Erro ao autenticar: " + e.getMessage());
            request.setAttribute("erro", "Não foi possível conectar ao banco de dados.");
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
