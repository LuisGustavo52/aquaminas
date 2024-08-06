<%-- 
    Document   : CadastroFornecedor
    Created on : 8 de jul. de 2024, 21:42:09
    Author     : tulio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastro de Fornecedors - Aquaminas</title>
        <link rel="stylesheet" href="styles.css">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    </head>

    <script>

        function submitForm(opcaoValue) {

            document.getElementById("opcao").value = opcaoValue;
            document.getElementById("cadastroForm").submit();
        }


    </script>


    <body>
        <h1>Cadastro Fornecedor</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="idFornecedor" value="${idFornecedor}" />
                <p><label>Nome</label> <input type="text" name="nome" value="${nome}" size="40" /> </p>
                <p><label>CPF:</label> <input type="text" name="cpf" value="${cpf}" size="50" /> </p>
                <p><label>Telefone:</label> <input type="text" name="telefone" value="${telefone}" size="11" maxlength="10"/> </p>
                <td> 
                    <input type="submit" value="Salvar" name="Salvar"  /> 
                </td>
            </form>

            <form  name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar"  />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty fornecedores}">
                <tr>
                    <th>IDs</th>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Telefone</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="fornecedor" items="${fornecedores}">
                <tr>
                    <td>${fornecedor.idFornecedor}</td>
                    <td>${fornecedor.nome}</td>
                    <td>${fornecedor.cpf}</td>
                    <td>${fornecedor.telefone}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador" method="get">
                            <input type="hidden" name="idFornecedor" value="${fornecedor.idFornecedor}" >
                            <input type="hidden" name="nome" value="${fornecedor.nome}" >
                            <input type="hidden" name="cpf" value="${fornecedor.cpf}" >
                            <input type="hidden" name="telefone" value="${fornecedor.telefone}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador" method="get">
                            <input type="hidden" name="idFornecedor" value="${fornecedor.idFornecedor}" >
                            <input type="hidden" name="nome" value="${fornecedor.nome}" >
                            <input type="hidden" name="cpf" value="${fornecedor.cpf}" >
                            <input type="hidden" name="telefone" value="${fornecedor.telefone}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>



        </table>

    </body>
</html>
