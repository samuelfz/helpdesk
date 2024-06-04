<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<% 
    List<Map<String, Object>> resultList = (List<Map<String, Object>>) request.getAttribute("resultList");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultados</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }
        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<table>
    <thead>
        <tr>
            <% if (resultList != null && !resultList.isEmpty()) { %>
                <% Map<String, Object> firstRow = resultList.get(0); %>
                <% for (String columnName : firstRow.keySet()) { %>
                    <th><%= columnName %></th>
                <% } %>
            <% } %>
        </tr>
    </thead>
    <tbody>
        <% if (resultList != null) { %>
            <% for (Map<String, Object> row : resultList) { %>
                <tr>
                    <% for (Object columnValue : row.values()) { %>
                        <td><%= columnValue %></td>
                    <% } %>
                </tr>
            <% } %>
        <% } %>
    </tbody>
</table>
<br>
<a href="\inicio.html">Voltar</a>

</body>
</html>
