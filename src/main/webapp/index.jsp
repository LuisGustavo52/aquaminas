<%@page import="java.util.List"%>
<%@page import="com.linguagemII.aquaminas.modelo.dao.RacaoDAO"%>
<%@page import="com.linguagemII.aquaminas.modelo.entidade.Racao"%>
<%@page import="com.linguagemII.aquaminas.modelo.dao.FuncionarioDAO"%>
<%@page import="com.linguagemII.aquaminas.modelo.entidade.Funcionario"%>
<%@page import="com.linguagemII.aquaminas.modelo.dao.ClienteDAO"%>
<%@page import="com.linguagemII.aquaminas.modelo.entidade.Cliente"%>
<%@page import="com.linguagemII.aquaminas.modelo.dao.PeixeDAO"%>
<%@page import="com.linguagemII.aquaminas.modelo.entidade.Peixe"%>
<%@page import="com.linguagemII.aquaminas.modelo.dao.VendaDAO"%>
<%@page import="com.linguagemII.aquaminas.modelo.entidade.Venda"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Aquaminas</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f0f8ff;
            color: #000000;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #000080;
            border-radius: 5px;
            background-color: #ffffff;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"], input[type="number"], select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #000080;
            border-radius: 5px;
        }
        button {
            padding: 10px 15px;
            background-color: #80D9FF;
            color: #000000;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
        }
        button:hover {
            background-color: #011DBD;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Aquaminas</h1>

</body>
</html>