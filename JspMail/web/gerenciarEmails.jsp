<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!Doctype html>
<html>
<style>
html{
    height: 100%;
}
body{
    height: 100%;
}

.vertical-menu {
    width: 200px;
    height: 100%;
    background-color: #eee;
}

.vertical-menu a {
    background-color: #eee;
    color: black;
    display: block;
    padding: 12px;
    text-decoration: none;
    text-transform: uppercase;
}

.vertical-menu a:hover {
    background-color: #ccc;
}

.vertical-menu a.active {
    background-color: #4CAF50;
    color: white;


</style>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="bootstrap/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>

<body>
<jsp:useBean id="emailDao" scope="page" class="com.jdbc.Emails"/>
<c:if test = "${param.operation == 'cadastrarEmail'}">
    <c:redirect url="cadastrarEmail.jsp" />
</c:if>
<c:if test = "${param.operation == 'editarEmail'}">
    <c:set scope="session" var="isCadastrado" value="${emailDao.temOutroEmail(emailEditar)}"/>
    <c:if test="${sessionScope.isCadastrado}">
        <jsp:forward page="editarEmail.jsp"/>
    </c:if>
</c:if>
<c:if test = "${param.operation == 'excluirEmail'}">
    <c:set scope="session" var="isCadastrado" value="${emailDao.temOutroEmail(emailEditar)}"/>
    <c:if test="${sessionScope.isCadastrado}">
        <jsp:forward page="excluirEmail.jsp"/>
    </c:if>
</c:if>

<div class="vertical-menu">
  <a href="#">Caixa de Entrada</a>
  <a href="#" class="active">Emails Cadastrados</a>
  <a href="#">Sair</a>
</div>

<form action="gerenciarEmails.jsp">
    <table border = "1">
    <!-- percorre emails montando as linhas da tabela -->
    <td><button type="submit"  name="operation" value="cadastrarEmail" class="btn btn-default">Cadastrar Email</button></td> 
    <c:out value="${emailDao.setEmailP(sessionScope.usuario)}"/>
    <c:forEach var="email" items="${emailDao.emails}">
        <tr> 
            <c:set scope="session" var="emailEditar" value="${email.outroEmail}"/>
            <td>${email.outroEmail}</td>
            <td><button type="submit"  name="operation" value="editarEmail" class="btn btn-default">Editar</button></td> 
            <td><button type="submit"  name="operation" value="excluirEmail" class="btn btn-default">Excluir</button></td> 
        </tr> 
    </c:forEach>
    </table>
</form>

</body>
</html>