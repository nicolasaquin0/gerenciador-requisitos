<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Usuários</title>
        <link rel="stylesheet" href="css/estilo.css">
    </head>
    <body>
        <%@ include file="topo.jspf" %>
        <main class="conteudo">
            <h1>Usuários</h1>
            <c:if test="${param.msg == 'salvo'}"><p class="sucesso">Usuário salvo com sucesso.</p></c:if>
            <c:if test="${param.msg == 'excluido'}"><p class="sucesso">Usuário excluído com sucesso.</p></c:if>
            <c:if test="${not empty sessionScope.erroUsuarios}">
                <p class="erro"><c:out value="${sessionScope.erroUsuarios}" /></p>
                <c:remove var="erroUsuarios" scope="session" />
            </c:if>
            <a class="botao" href="usuarios?acao=novo">Novo usuário</a>

            <table>
                <thead>
                    <tr><th>Nome</th><th>E-mail</th><th>Perfil</th><th>Situação</th><th>Ações</th></tr>
                </thead>
                <tbody>
                    <c:forEach var="u" items="${usuarios}">
                        <tr>
                            <td><c:out value="${u.nome}" /></td>
                            <td><c:out value="${u.email}" /></td>
                            <td>${u.admin ? 'Admin' : 'User'}</td>
                            <td>${u.ativo ? 'Ativo' : 'Inativo'}</td>
                            <td class="acoes">
                                <a href="usuarios?acao=editar&id=${u.id}">Editar</a>
                                <form class="form-excluir" action="usuarios" method="post"
                                      onsubmit="return confirm('Deseja excluir este usuário?');">
                                    <input type="hidden" name="acao" value="excluir">
                                    <input type="hidden" name="id" value="${u.id}">
                                    <button class="link" type="submit">Excluir</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty usuarios}">
                        <tr><td colspan="5" class="vazio">Nenhum usuário cadastrado.</td></tr>
                    </c:if>
                </tbody>
            </table>
        </main>
    </body>
</html>
