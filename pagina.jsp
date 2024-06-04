<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Resultado da Consulta</title>
</head>
<body>
    <h1>Resultado da Consulta</h1>
    <%
        // Recupere o atributo 'result' do objeto request
        String result = (String) request.getAttribute("result");
        
        // Exiba os resultados na página
        out.println("<pre>" + result + "</pre>");
    %>
    <br>
    <br>
    <a href="\">Voltar</a>
</body>
</html>
