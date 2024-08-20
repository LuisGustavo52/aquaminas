<%@page import="java.util.List"%>
<%@page import="com.linguagemII.aquaminas.modelo.dao.RacaoDAO"%>
<%@page import="com.linguagemII.aquaminas.modelo.entidade.Racao"%>
<%@page import="com.linguagemII.aquaminas.modelo.dao.FornecedorDAO"%>
<%@page import="com.linguagemII.aquaminas.modelo.entidade.Fornecedor"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Ração</title>
    </head>
    <body>
        <h1>Cadastro de Ração</h1>
        <table>
            <form id="cadastroRacaoForm" name="cadastroRacaoForm" action="${pageContext.request.contextPath}${URL_BASE}/RacaoControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="idRacao" value="${idRacao}" />
                <p><label>Nome:</label> <input type="text" name="nome" value="${nome}" size="40" required/> </p>
                <p><label>Peso:</label> <input type="number" name="peso" value="${peso}" size="10" required/> </p>
                <p><label>Valor:</label> <input type="number" name="valor" value="${valor}" size="10" required/> </p>
                <p><label>Fornecedor:</label>
                    <select name="fornecedor">
                        <c:forEach var="fornecedor" items="${fornecedores}">
                            <c:choose>
                                <c:when test="${fornecedor.idFornecedor eq fornecedor}">
                                    <option selected value="${fornecedor.idFornecedor}">${fornecedor.nome}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${fornecedor.idFornecedor}">${fornecedor.nome}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </p>
                <td> 
                    <input type="submit" value="Salvar" name="Salvar"  /> 
                </td>
            </form>

            <form name="cadastroRacaoForm" action="${pageContext.request.contextPath}${URL_BASE}/RacaoControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar"  />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty racoes}">
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Peso</th>
                    <th>Valor</th>
                    <th>Fornecedor</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="racao" items="${racoes}">
                <tr>
                    <td>${racao.idRacao}</td>
                    <td>${racao.nome}</td>
                    <td>${racao.peso}</td>
                    <td>${racao.valor}</td>
                    <td>${racao.fornecedor.nome}</td>
                    <td>
                        <form name="cadastroRacaoForm" action="${pageContext.request.contextPath}${URL_BASE}/RacaoControlador" method="get">
                            <input type="hidden" name="idRacao" value="${racao.idRacao}" >
                            <input type="hidden" name="nome" value="${racao.nome}" >
                            <input type="hidden" name="peso" value="${racao.peso}" >
                            <input type="hidden" name="valor" value="${racao.valor}" >
                            <input type="hidden" name="fornecedor" value="${racao.fornecedor.idFornecedor}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroRacaoForm" action="${pageContext.request.contextPath}${URL_BASE}/RacaoControlador" method="get">
                            <input type="hidden" name="idRacao" value="${racao.idRacao}" >
                            <input type="hidden" name="nome" value="${racao.nome}" >
                            <input type="hidden" name="peso" value="${racao.peso}" >
                            <input type="hidden" name="valor" value="${racao.valor}" >
                            <input type="hidden" name="fornecedor" value="${racao.fornecedor.idFornecedor}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
