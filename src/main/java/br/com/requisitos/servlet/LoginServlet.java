package br.com.requisitos.servlet;

import br.com.requisitos.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if ("admin@requisitos.com".equals(email) && "admin123".equals(senha)) {
            request.getSession().setAttribute("usuarioLogado", new Usuario("Administrador", email, "ADMIN"));
            response.sendRedirect(request.getContextPath() + "/menu.jsp");
        } else {
            request.setAttribute("erro", "E-mail ou senha inválidos.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
