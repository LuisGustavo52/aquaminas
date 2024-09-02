
<%@page import="java.util.List"%>
<%@page import="com.linguagemII.aquaminas.modelo.dao.RacaoDao"%>
<%@page import="com.mycompany.aquaminas.modelo.entidade.Racao"%>
<%@page import="com.linguagemII.aquaminas.modelo.dao.FornecedorDao"%>
<%@page import="com.linguagemII.aquaminas.modelo.entidade.Fornecedor"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>


    <body>
        <h1>Interface de Vendas</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="idFuncionario" value="${idFuncionario}" />
                <p><label>Nome:</label> <input type="text" name="nomeFuncionario" value="${nome}" size="40" required/> </p>
                <p><label>Salário:</label> <input type="number" name="valorFuncionario" value="${valor}" size="10" required/> </p>
                <p><label>Nascimento:</label> <input type="date" name="telefoneFuncionario" value="${telefone}"  /> </p>
                <p><label>Cidade:</label>
                    <select name="funcionarioFuncionario">
                     <c:forEach var="funcionario" items="${funcionarios}">
                         <c:choose> 
                            
                            <c:when test="${funcionario.idFuncao eq funcionarioFuncionario}">
                                <option selected value="${funcionario.idFuncao}">${funcionario.idFuncao}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${funcionario.idFuncao}">${funcionario.idFuncao}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </p>
                <td> 
                    <input type="submit" value="Salvar" name="Salvar"  /> 
                </td>
            </form>

            <form  name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar"  />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty funcionarios}">
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Valor</th>
                    <th>Telefone</th>
                    <th>Cpf</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="funcionario" items="${funcionarios}">
                <tr>
                    <td>${funcionario.idFuncionario}</td>
                    <td>${funcionario.nome}</td>
                    <td>${funcionario.cpf}</td>
                    <td>${funcionario.telefone}</td>
                    <td>${funcionario.funcaoFuncionario.idFuncao}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador" method="get">
                            <input type="hidden" name="idFuncionario" value="${funcionario.idFuncionario}" >
                            <input type="hidden" name="nome" value="${funcionario.nome}" >
                            <input type="hidden" name="cpf" value="${funcionario.cpf}" >
                              <input type="hidden" name="telefone" value="${funcionario.telefone}">
                              <input type="hidden" name="idFuncao" value="${funcionario.funcaoFuncionario.idFuncao}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador" method="get">
                            <input type="hidden" name="idFuncionario" value="${funcionario.idFuncionario}" >
                            <input type="hidden" name="nome" value="${funcionario.nome}" >
                            <input type="hidden" name="cpf" value="${funcionario.cpf}" >
                              <input type="hidden" name="telefone" value="${funcionario.telefone}">
                            <input type="hidden" name="idFuncao" value="${funcionario.funcaoFuncionario.idFuncao}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>



        </table>

    </body>
</html>
