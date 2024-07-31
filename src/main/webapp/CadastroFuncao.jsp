
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
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
        <h1>Cadastro Funcao</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PeixeControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="idFuncao" value="${idFuncao}" />
      
                <p><label>Funcao:</label> <input type="text" name="Funcao" value="${Funcao}" size="5" /> </p>
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
            <c:if test="${not empty funcaos}">
                <tr>
                    <th>IdFuncao</th>
                    <th>Funcao</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="funcao" items="${funcaos}">
                <tr>
                    
                    <td>${funcao.idFuncao}</td>
                    <td>${funcao.Funcao}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PeixeControlador" method="get">
                            <input type="hidden" name="idFuncao" value="${funcao.idFuncao}" >
                            <input type="hidden" name="Funcao" value="${funcao.Funcao}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PeixeControlador" method="get">
                            <input type="hidden" name="idFuncao" value="${funcao.idFuncao}" >
                            <input type="hidden" name="Funcao" value="${funcao.Funcao}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>



        </table>

    </body>
</html>
