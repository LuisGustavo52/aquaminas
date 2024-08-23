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
        <h1>Efetuar Venda</h1>
        <form  id="vendaForm" name="vendaForm"action="${pageContext.request.contextPath}${URL_BASE}/VendaControlador" method="post">
            <input type="hidden" name="opcao" value="${opcao}" />
            <input type="hidden" name="idRacao" value="${idRacao}" />
            <div class="form-group">
                <label for="vendedor">Vendedor:</label>
                <select id="vendedor" name="vendedor">
                    <c:forEach var="vendedor" items="${vendedores}">
                            <c:choose>
                                <c:when test="${vendedor.idFuncionario eq funcionario}">
                                    <option selected value="${vendedor.idFuncionario}">${vendedor.nome}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${vendedor.idFuncionario}">${vendedor.nome}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="cliente">Cliente:</label>
                <select name="cliente">
                     <c:forEach var="cliente" items="${clientes}">
                         <c:choose> 
                            
                            <c:when test="${cliente.idCliente eq cliente}">
                                <option selected value="${cliente.codigoCliente}">${cliente.nomeCliente}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${cliente.codigoCliente}">${cliente.nomeCliente}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="tipoProduto">Tipo de Produto:</label>
                <select id="tipoProduto" name="tipoProduto" onchange="atualizarSelecaoProduto()">
                    <option value="">Selecione</option>
                    <option value="peixe">Peixe</option>
                    <option value="racao">Ração</option>
                </select>
            </div>
            <div class="form-group" id="produtoPeixeGroup" style="display:none;">
                <label for="produtoPeixe">Produto - Peixe:</label>
                 <c:forEach var="cliente" items="${clientes}">
                         <c:choose> 
                            
                            <c:when test="${cliente.idCliente eq cliente}">
                                <option selected value="${cliente.codigoCliente}">${cliente.nomeCliente}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${cliente.codigoCliente}">${cliente.nomeCliente}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
            </div>
            <div class="form-group" id="produtoRacaoGroup" style="display:none;">
                <label for="produtoRacao">Produto - Ração:</label>
                 <c:forEach var="cliente" items="${clientes}">
                         <c:choose> 
                            
                            <c:when test="${cliente.idCliente eq cliente}">
                                <option selected value="${cliente.codigoCliente}">${cliente.nomeCliente}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${cliente.codigoCliente}">${cliente.nomeCliente}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
            </div>
            <div class="form-group">
                <label for="quantidade">Quantidade (kg):</label>
                <input type="number" id="quantidade" name="quantidade" required>
            </div>
            <div class="form-group">
                <label for="preco">Preço por kg (R$):</label>
                <input type="text" id="preco" name="preco" required>
            </div>
            <div class="form-group">
                <label for="total">Total (R$):</label>
                <input type="text" id="total" name="total" readonly>
            </div>
            <button type="submit">Concluir Venda</button>
        </form>
        <br>
        
        <c:if test="${not empty param.vendaConcluida and param.vendaConcluida eq 'true'}">
            <button onclick="gerarEtiqueta()">Gerar Etiqueta</button>
        </c:if>
    </div>

    <script>
        // Script para atualizar a seleção do produto com base no tipo selecionado
        function atualizarSelecaoProduto() {
            const tipoProduto = document.getElementById('tipoProduto').value;
            const produtoPeixeGroup = document.getElementById('produtoPeixeGroup');
            const produtoRacaoGroup = document.getElementById('produtoRacaoGroup');

            produtoPeixeGroup.style.display = 'none';
            produtoRacaoGroup.style.display = 'none';

            if (tipoProduto === 'peixe') {
                produtoPeixeGroup.style.display = 'block';
            } else if (tipoProduto === 'racao') {
                produtoRacaoGroup.style.display = 'block';
            }
        }

        // Script para calcular o total da venda
        const quantidadeInput = document.getElementById('quantidade');
        const precoInput = document.getElementById('preco');
        const totalInput = document.getElementById('total');

        quantidadeInput.addEventListener('input', calcularTotal);
        precoInput.addEventListener('input', calcularTotal);

        function calcularTotal() {
            const quantidade = parseFloat(quantidadeInput.value) || 0;
            const preco = parseFloat(precoInput.value) || 0;
            const total = quantidade * preco;
            totalInput.value = total.toFixed(2);
        }

        // Função para gerar a etiqueta
        function gerarEtiqueta() {
            // Lógica para gerar e imprimir a etiqueta
            alert('Etiqueta gerada com sucesso!');
        }
    </script>
</body>
</html>