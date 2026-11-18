package br.com.requisitos.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/menu.jsp", "/projeto.jsp", "/requisito.jsp", "/usuarios"})
public class AutenticacaoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (request.getSession(false) == null || request.getSession(false).getAttribute("usuarioLogado") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
        chain.doFilter(req, res);
    }
}
