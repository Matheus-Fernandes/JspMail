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

.listaEmails{
    width: 100%;
    background-color: #DADFE1;

}

.listaEmails tbody, th, td {
    border-bottom: 1px solid #eee;
}

.botao button{
    right: 0;
    float: right;
    margin: 5px; 
}

.conteudo {
    padding: 10px;
}

.botao{
    width: 10px;
}

#corpo{
    position: absolute;
    display: block;
    float:right;
    width: 88%;
    top: 0;
    right: 0;
}

.vertical-menu {
    width: 12%;
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
    background-color: #2980b9;
    color: white;
}
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
  <a href="dashboard.jsp" class="active">Caixa de Entrada</a>
  <a href="enviarMensagem.jsp">Enviar Mensagem</a>
  <a href="gerenciarEmails.jsp">Gerenciar Emails</a>
  <a href="index.jsp?operation=sair">Sair</a>
</div>
<div id="corpo">
<form action="gerenciarEmails.jsp">
    <table class = "listaEmails">
    <!-- percorre emails montando as linhas da tabela -->
    <thead>
        <tr bgcolor="2980b9" style="height:  43px">
            <td></td>
            <td></td>
            <td>
                <button type="submit"  name="operation" value="cadastrarEmail" class="btn btn-default" style=" margin-right: 5px;  float: right; right: 0" hidden="cadastrar email"> 
                    <span class="glyphicon glyphicon-plus"></span>
                </button>
            </td>
        </tr>

     </thead>
    <c:out value="${emailDao.setEmailP(sessionScope.usuario)}"/>
    <c:forEach var="email" items="${emailDao.emails}">
        <tr> 
            <c:set scope="session" var="emailEditar" value="${email.outroEmail}"/>
            <td class = "conteudo">${email.outroEmail}</td>
            <td class = "botao">
                <button type="submit"  name="operation" value="editarEmail" class="btn btn-default ">
                    <span class="glyphicon glyphicon-pencil"></span> 
                </button>
            </td> 
            <td class = "botao">
                <button type="submit"  name="operation" value="excluirEmail" class="btn btn-default">
                    <span class="glyphicon glyphicon-trash"></span>
                </button>
            </td> 
        </tr> 
    </c:forEach>
    </table>
</form>
</div>

</body>
</html>