<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Início</title>
        <link rel="stylesheet" href="css/estilo.css">
    </head>
    <body>
        <%@ include file="WEB-INF/topo.jspf" %>
        <main class="caixa">
            <h1>Início</h1>
            <p>Olá, ${sessionScope.usuarioLogado.nome}.</p>
            <p><a class="botao" href="projeto.jsp">Cadastro de projetos</a></p>
            <p><a class="botao" href="requisito.jsp">Cadastro de requisitos</a></p>
            <c:if test="${sessionScope.usuarioLogado.admin}">
                <p><a class="botao" href="usuarios">Cadastro de usuários</a></p>
            </c:if>
        </main>
    </body>
</html>
