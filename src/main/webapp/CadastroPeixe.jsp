<%-- 
    Document   : CadastroPeixe
    Created on : 8 de jul. de 2024, 21:42:09
    Author     : tulio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="index.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastro de Peixes - Aquaminas</title>
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
        <h1>Cadastro Peixe</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PeixeControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="idPeixe" value="${idPeixe}" />
                <p><label>Especie</label> <input type="text" name="especie" value="${especie}" size="40" /> </p>
                <p><label>Nome Científico:</label> <input type="text" name="nome_cientifico" value="${nome_cientifico}" size="50" /> </p>
                <p><label>Valor Unitário:</label> <input type="number" name="valor_unidade" value="${valor_unidade}" size="10" /> </p>
                <td> 
                    <input type="submit" value="Salvar" name="Salvar"  /> 
                </td>
            </form>

            <form  name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PeixeControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar"  />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty peixes}">
                <tr>
                    <th>IDs</th>
                    <th>Espécie</th>
                    <th>Nome Científico</th>
                    <th>Valor Unitário</th>
                    <th>Valor </th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="peixe" items="${peixes}">
                <tr>
                    <td>${peixe.idPeixe}</td>
                    <td>${peixe.especie}</td>
                    <td>${peixe.nome_cientifico}</td>
                    <td>${peixe.getValor_Unid()}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PeixeControlador" method="get">
                            <input type="hidden" name="idPeixe" value="${peixe.idPeixe}" >
                            <input type="hidden" name="especie" value="${peixe.especie}" >
                            <input type="hidden" name="nome_cientifico" value="${peixe.nome_cientifico}" >
                            <input type="hidden" name="valor_unidade" value="${peixe.getValor_Unid()}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PeixeControlador" method="get">
                            <input type="hidden" name="idPeixe" value="${peixe.idPeixe}" >
                            <input type="hidden" name="especie" value="${peixe.especie}" >
                            <input type="hidden" name="nome_cientifico" value="${peixe.nome_cientifico}" >
                            <input type="hidden" name="valor_unidade" value="${peixe.getValor_Unid()}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>



        </table>

    </body>
</html>
