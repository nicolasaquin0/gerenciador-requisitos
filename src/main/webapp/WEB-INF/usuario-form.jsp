<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Usuário</title>
        <link rel="stylesheet" href="css/estilo.css">
    </head>
    <body>
        <%@ include file="topo.jspf" %>
        <main class="conteudo estreito">
            <h1>${usuario.id == 0 ? 'Novo usuário' : 'Editar usuário'}</h1>
            <form action="usuarios" method="post">
                <input type="hidden" name="id" value="${usuario.id == 0 ? '' : usuario.id}">
                <label for="nome">Nome</label>
                <input id="nome" name="nome" maxlength="100" value="<c:out value='${usuario.nome}' />" required>
                <label for="email">E-mail</label>
                <input id="email" type="email" name="email" maxlength="120" value="<c:out value='${usuario.email}' />" required>
                <label for="senha">Senha</label>
                <input id="senha" type="password" name="senha" minlength="6" ${usuario.id == 0 ? 'required' : ''}>
                <c:if test="${usuario.id != 0}">
                    <p class="dica">Deixe em branco para manter a senha atual.</p>
                </c:if>
                <label for="perfil">Perfil</label>
                <select id="perfil" name="perfil">
                    <option value="USER" ${usuario.perfil == 'USER' ? 'selected' : ''}>User</option>
                    <option value="ADMIN" ${usuario.perfil == 'ADMIN' ? 'selected' : ''}>Admin</option>
                </select>
                <label class="checkbox">
                    <input type="checkbox" name="ativo" ${usuario.ativo ? 'checked' : ''}> Ativo
                </label>
                <p class="erro"><c:out value="${erro}" /></p>
                <button type="submit">Salvar</button>
                <a href="usuarios">Cancelar</a>
            </form>
        </main>
    </body>
</html>
