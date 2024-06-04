<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Senha Atualizada</title>
</head>
<body>
    <h1>Resultado:</h1>
    <%
        // Recupere o atributo 'result' do objeto request
        String result = (String) request.getAttribute("result");
        
        // Exiba os resultados na página
        out.println("<pre>" + result + "</pre>");
    %>
    <br>
    <br>
    <%
        // Recupere os atributos 'nome' e 'senha' do objeto request
        String nome = (String) request.getAttribute("nome");
        String senha = (String) request.getAttribute("senha");
    %>
    <p class="texto">Usuário: <%= nome != null ? nome : "Nome não disponível" %></p> <br>
    <p class="texto">Senha atualizada com sucesso.</p> <br>

    <br>
    <br>
    <a href="\">Voltar</a>
</body>
</html>
