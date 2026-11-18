<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login</title>
        <link rel="stylesheet" href="css/estilo.css">
    </head>
    <body>
        <main class="caixa login">
            <h1>Gerenciador de Requisitos</h1>
            <p class="subtitulo">Entre com seu e-mail e senha.</p>
            <form action="login" method="post">
                <label for="email">E-mail</label>
                <input id="email" type="email" name="email" required autofocus>
                <label for="senha">Senha</label>
                <input id="senha" type="password" name="senha" required>
                <p class="erro">${erro}</p>
                <button type="submit">Entrar</button>
            </form>
            <div class="acesso">
                <p><strong>Acesso de teste</strong></p>
                <p>E-mail: admin@requisitos.com</p>
                <p>Senha: admin123</p>
            </div>
        </main>
    </body>
</html>
