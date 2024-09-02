
<%@page import="java.util.List"%>
<%@page import="com.linguagemII.aquaminas.modelo.dao.EnderecoDAO"%>
<%@page import="com.mycompany.aquaminas.modelo.entidade.Endereco"%>
<%@page import="com.linguagemII.aquaminas.modelo.dao.ClienteDAO"%>
<%@page import="com.linguagemII.aquaminas.modelo.entidade.Cliente"%>

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
        <h1>Cadastro Funcionário</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/EnderecoControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="idEndereco" value="${idEndereco}" />
                <p><label>Numero:</label> <input type="text" name="numero" value="${numero}" size="40" required/> </p>
                <p><label>Cidade</label> <input type="number" name="cidade" value="${cidade}" size="10" required/> </p>
                <p><label>Telefone</label> <input type="date" name="complemento" value="${complemento}"  /> </p>
                <p><label>Endereco</label>
                    <select name="enderecoEndereco">
                     <c:forEach var="endereco" items="${enderecos}">
                         <c:choose> 
                            
                            <c:when test="${endereco.idCliente eq enderecoEndereco}">
                                <option selected value="${endereco.idCliente}">${endereco.idCliente}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${endereco.idCliente}">${endereco.idCliente}</option>
                            </c:otherwise>

                        </c:choose>
                    </c:forEach>
                </select>
                </p>
                <td> 
                    <input type="submit" value="Salvar" name="Salvar"  /> 
                </td>
            </form>

            <form  name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/EnderecoControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar"  />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty enderecos}">
                <tr>
                    <th>IDs</th>
                    <th>Numero</th>
                    <th>Cidade</th>
                    <th>Complemento</th>
                    <th>Numero</th>
                    <th>Cliente</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="endereco" items="${enderecos}">
                <tr>
                    <td>${endereco.idEndereco}</td>
                    <td>${endereco.numero}</td>
                    <td>${endereco.complemento}</td>
                    <td>${endereco.clienteEndereco.idCliente}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/EnderecoControlador" method="get">
                            <input type="hidden" name="idEndereco" value="${endereco.idEndereco}" >
                            <input type="hidden" name="numero" value="${endereco.numero}" >
                            <input type="hidden" name="idcliente" value="${endereco.clienteEndereco.idCliente}" >
                              <input type="hidden" name="complemento" value="${endereco.complemento}">
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/EnderecoControlador" method="get">
                            <input type="hidden" name="idEndereco" value="${endereco.idEndereco}" >
                            <input type="hidden" name="numero" value="${endereco.numero}" >
                            <input type="hidden" name="cliente" value="${endereco.clienteEndereco.idCliente}" >
                              <input type="hidden" name="complemento" value="${endereco.complemento}">
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>



        </table>

    </body>
</html>
