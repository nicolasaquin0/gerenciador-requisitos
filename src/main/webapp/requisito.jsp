<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Requisitos</title>
        <link rel="stylesheet" href="css/estilo.css">
    </head>
    <body>
        <%@ include file="WEB-INF/topo.jspf" %>
        <main class="conteudo">
            <h1>Cadastro de requisitos</h1>
            <form method="post">
                <div class="linha">
                    <div>
                        <label for="projeto">Projeto</label>
                        <select id="projeto" name="projeto">
                            <option value="">Selecione</option>
                        </select>
                    </div>
                    <div>
                        <label for="tipo">Tipo</label>
                        <select id="tipo" name="tipo" required>
                            <option value="RF">Funcional (RF)</option>
                            <option value="RNF">Não funcional (RNF)</option>
                        </select>
                    </div>
                </div>
                <label for="titulo">Título</label>
                <input id="titulo" name="titulo" maxlength="150" required>
                <label for="descricao">Descrição</label>
                <textarea id="descricao" name="descricao" rows="5" required></textarea>
                <div class="linha">
                    <div>
                        <label for="prioridade">Prioridade</label>
                        <select id="prioridade" name="prioridade">
                            <option value="">Selecione</option>
                        </select>
                    </div>
                    <div>
                        <label for="complexidade">Complexidade</label>
                        <select id="complexidade" name="complexidade">
                            <option value="">Selecione</option>
                        </select>
                    </div>
                    <div>
                        <label for="status">Status</label>
                        <select id="status" name="status">
                            <option value="">Selecione</option>
                        </select>
                    </div>
                </div>
                <button type="submit">Salvar</button>
                <a href="menu.jsp">Cancelar</a>
            </form>

            <h2>Requisitos cadastrados</h2>
            <table>
                <thead>
                    <tr><th>Código</th><th>Título</th><th>Prioridade</th><th>Complexidade</th><th>Status</th><th>Versão</th><th>Ações</th></tr>
                </thead>
                <tbody>
                    <tr><td colspan="7" class="vazio">Nenhum requisito cadastrado.</td></tr>
                </tbody>
            </table>
        </main>
    </body>
</html>
