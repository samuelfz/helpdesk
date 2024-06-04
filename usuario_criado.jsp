<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Usuario Criado</title>
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

    <p class="texto"> Usuario: <%= request.getAttribute("nome") %></p> <br>
    <p class="texto"> Data Nascimento: <%= request.getAttribute("data_nascimento") %></p> <br>

    <br>
    <br>
    <a href="\">Voltar</a>
</body>
</html>
