<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/icone.ico">
    </head>
    <body>
       
        <nav>
            <div class="image-logo">
                <img height="45px" src="${pageContext.request.contextPath}/assets/logo.png" alt="alt"/>
            </div>
            <ul>
                <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/PeixeControlador?opcao=cancelar">Peixe</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador?opcao=cancelar">Fornecedor</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador?opcao=cancelar">Cliente</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador?opcao=cancelar">Funcion�rio</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/RacaoControlador?opcao=cancelar">Ra��o</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/FuncaoControlador?opcao=cancelar">Fun��o</a></li>
                <li><a href="${pageContext.request.contextPath}/login.jsp">Login</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/LogoutControlador">Logout</a></li>
                
            </ul>
        </nav>
    </body>
</html>
