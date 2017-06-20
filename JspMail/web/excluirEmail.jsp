<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="m"%>
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
    <jsp:useBean id="email" scope="page" class="com.jdbc.Email"/>
    <jsp:useBean id="emailDao" scope="page" class="com.jdbc.Emails"/>
    
    <c:set scope="session" var="aviso_texto" value=""/>

    <c:if test = "${param.operation == 'voltar'}">
        <jsp:forward page="gerenciarEmails.jsp" />
    </c:if>
    <c:if test = "${param.operation == 'excluir'}">
        ${email.setOutroEmail(sessionScope.usuario)} <!-- Email em que clicou -->

        <c:set scope="session" var="existe" value="${emailDao.temOutroEmail(sessionScope.emailEditar)}"/>

        <c:if test="${!sessionScope.existe}">
            <c:set scope="session" var="aviso_texto" value="Esse email não está cadastrado!"/>
            <c:set scope="session" var="aviso_tipo" value="ERRO"/>
        </c:if>
        <c:if test="${sessionScope.existe}">
            <c:set scope="session" var="emailExcluido" value="${emailDao.excluirOutroEmail(sessionScope.emailEditar)}"/>  <!-- Setar para o email logado -->

            <c:if test="${sessionScope.emailExcluido}">
                <c:set scope="session" var="aviso_texto" value="Email excluído com sucesso!"/>
                <c:set scope="session" var="aviso_tipo" value="SUCESSO"/>
            </c:if>
            <c:if test="${!sessionScope.emailExcluido}">
                <c:set scope="session" var="aviso_texto" value="Falha na exclusão de Email."/>
                <c:set scope="session" var="aviso_tipo" value="ERRO"/>
            </c:if>
        </c:if>
    </c:if>
            
<div class="vertical-menu">
  <a href="dashboard.jsp">Caixa de Entrada</a>
  <a href="enviarMensagem.jsp">Enviar Mensagem</a>
  <a href="gerenciarEmails.jsp" class="active">Gerenciar Emails</a>
  <a href="index.jsp?operation=sair">Sair</a>
</div>    

    <div id="corpo">
        <form method="get" action="excluirEmail.jsp">

        <div class="input-group-btn">
            <button style="width: 60%" type="submit" name="operation" value = "excluir" class="btn btn-default">Excluir</button>
            <button style="width: 40%" type="submit" name="operation" value = "voltar" class="btn btn-default">Voltar</button>

        </div>
        <br/>

        <m:aviso valor="${sessionScope.aviso_texto}" tipo="${sessionScope.aviso_tipo}"/>

        </form>
    </div>
</body>
</html>