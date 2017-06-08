<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="m"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="bootstrap/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        
        <jsp:useBean id="usuarioDao" scope="page" class="com.jdbc.Usuarios"/>

        <c:if test = "${param.operation == 'cadastrar'}">
            <c:redirect url="cadastrar.jsp" />
        </c:if>
        <c:if test = "${param.operation == 'logar'}">
            <c:set scope="session" var="isCadastrado" value="${usuarioDao.cadastrado(param.email, param.senha)}"/>
            <c:if test="${sessionScope.isCadastrado}">
                <jsp:forward page="dashboard.jsp"/>
            </c:if>
        </c:if>
                
    </head>
    
    <body>
        
        <div class="panel panel-default container clearfix" style="width: 300px; padding: 30px">
            <form method="get" action="index.jsp">
            
            <m:titulo valor="Email Maligno" />
            <br>
            <m:campo nome="email" tipo="text" label="Email"/>
            <m:campo nome="senha" tipo="password" label="Senha"/>
            <br>
            
            <div class="input-group-btn">
                <button style="width: 60%" type="submit" name="operation" value = "logar" class="btn btn-default">Logar</button>
                <button style="width: 40%" type="submit" name="operation" value = "cadastrar" class="btn btn-default">Cadastrar</button>               
            </div>
           
            <m:aviso valor="${param.aviso_texto}" tipo="${param.aviso_tipo}"/>

            </form>
        </div>
    </body>
</html>
