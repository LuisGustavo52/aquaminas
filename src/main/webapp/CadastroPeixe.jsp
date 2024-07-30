<%-- 
    Document   : CadastroPeixe
    Created on : 8 de jul. de 2024, 21:42:09
    Author     : tulio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                <input type="hidden" name="codigoPeixe" value="${idPeixe}" />
                <p><label>Peixe:</label> <input type="text" name="nomePeixe" value="${nomePeixe}" size="40" /> </p>
                <p><label>UF:</label> <input type="text" name="ufPeixe" value="${ufPeixe}" size="5" /> </p>
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
            <c:if test="${not empty cidades}">
                <tr>
                    <th>Código</th>
                    <th>Peixe</th>
                    <th>Uf</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="cidade" items="${cidades}">
                <tr>
                    <td>${cidade.codigoPeixe}</td>
                    <td>${cidade.nomePeixe}</td>
                    <td>${cidade.ufPeixe}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PeixeControlador" method="get">
                            <input type="hidden" name="codigoPeixe" value="${cidade.codigoPeixe}" >
                            <input type="hidden" name="nomePeixe" value="${cidade.nomePeixe}" >
                            <input type="hidden" name="ufPeixe" value="${cidade.ufPeixe}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PeixeControlador" method="get">
                            <input type="hidden" name="codigoPeixe" value="${cidade.codigoPeixe}" >
                            <input type="hidden" name="nomePeixe" value="${cidade.nomePeixe}" >
                            <input type="hidden" name="ufPeixe" value="${cidade.ufPeixe}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>



        </table>

    </body>
</html>
