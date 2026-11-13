<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Projetos</title>
        <link rel="stylesheet" href="css/estilo.css">
    </head>
    <body>
        <%@ include file="WEB-INF/topo.jspf" %>
        <main class="conteudo">
            <h1>Cadastro de projetos</h1>
            <form method="post">
                <label for="nome">Nome</label>
                <input id="nome" name="nome" maxlength="120" required>
                <label for="descricao">Descrição</label>
                <textarea id="descricao" name="descricao" rows="4"></textarea>
                <div class="linha">
                    <div>
                        <label for="dataInicio">Data de início</label>
                        <input id="dataInicio" type="date" name="dataInicio" required>
                    </div>
                    <div>
                        <label for="responsavel">Responsável</label>
                        <select id="responsavel" name="responsavel">
                            <option value="">Selecione</option>
                        </select>
                    </div>
                    <div>
                        <label for="situacao">Situação</label>
                        <select id="situacao" name="situacao">
                            <option value="ATIVO">Ativo</option>
                            <option value="CONCLUIDO">Concluído</option>
                            <option value="ARQUIVADO">Arquivado</option>
                        </select>
                    </div>
                </div>
                <button type="submit">Salvar</button>
                <a href="menu.jsp">Cancelar</a>
            </form>

            <h2>Projetos cadastrados</h2>
            <table>
                <thead>
                    <tr><th>Nome</th><th>Data de início</th><th>Responsável</th><th>Situação</th><th>Ações</th></tr>
                </thead>
                <tbody>
                    <tr><td colspan="5" class="vazio">Nenhum projeto cadastrado.</td></tr>
                </tbody>
            </table>
        </main>
    </body>
</html>
