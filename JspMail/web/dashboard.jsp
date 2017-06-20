<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!Doctype html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="bootstrap/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    
<style>
html{
    height: 100%;
}
body{
    height: 100%;
    background-color: #eee;
}

.listaMensagens{
    float: right;
    position: absolute;
    top: 0px;
    right: 0;
    width: 88%;


}
.listaMensagens  a{
    color: black;
}


.listaMensagens tfoot a{
    color: whitesmoke;
    margin-right: 20px;
}

.listaMensagens tbody, th, td {
    border-bottom: 1px solid #eee;
}



.listaMensagens .td_assunto{
   padding: 12px;
   width: 90%;
}

.listaMensagens .td_assunto a{
   margin-left: 5px;
}

.listaMensagens .td_botao{
   width: 5%;
   padding-right: 10px;
}

.listaMensagens tbody{
    width: 100%;
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
</head>

<body>
<jsp:useBean id="dashboard" scope="page" class="com.web.DashboardManagedBean"/>
<jsp:useBean id="emails" scope="page" class="com.jdbc.Emails" />
${dashboard.setEmail(emails.getEmailPrincipal(sessionScope.usuario))}
<div class="vertical-menu">
  <a href="dashboard.jsp" class="active">Caixa de Entrada</a>
  <a href="enviarMensagem.jsp">Enviar Mensagem</a>
  <a href="gerenciarEmails.jsp">Gerenciar Emails</a>
  <a href="index.jsp?operation=sair">Sair</a>
</div>

<c:if test="${param.operation == 'verMensagem'}">
    <c:set scope="session" var="mensagem" value="${dashboard.getMensagem(param.id)}"/>
    <c:redirect url="verMensagem.jsp"/>
</c:if>
<c:if test="${param.operation == 'responderMensagem'}">
    <c:set scope="session" var="mensagem" value="${dashboard.getMensagem(param.id)}"/>
    <c:redirect url="responderMensagem.jsp"/>
</c:if>
<c:if test="${param.operation == 'encaminharMensagem'}">
    <c:set scope="session" var="mensagem" value="${dashboard.getMensagem(param.id)}"/>
    <c:redirect url="encaminharMensagem.jsp"/>
</c:if>

<c:if test="${empty param.pagina}">
    <c:set scope="page" var="pagina" value="1"/>
</c:if>
<c:if test="${not empty param.pagina}">
    <c:set scope="page" var="pagina" value="${param.pagina}"/>
</c:if>

<table class="listaMensagens">
<!-- percorre contatos montando as linhas da tabela -->
<thead>
    <tr bgcolor="2980b9" style="height:  43px">
        <td></td><td></td><td></td>
    </tr>
</thead>
<tbody >
    <c:forEach varStatus="id" var="mensagem" items="${dashboard.getCaixaEntrada(pageScope.pagina)}">
    <tr bgcolor="DADFE1" > 
        <td class = "td_assunto"><a href="dashboard.jsp?operation=verMensagem&id=${mensagem.id}">${mensagem.assunto}</a></td> 
        <td  class = "td_botao"><a href="dashboard.jsp?operation=responderMensagem&id=${mensagem.id}">Responder </a></td> 
        <td  class = "td_botao"><a href="dashboard.jsp?operation=encaminharMensagem&id=${mensagem.id}">Encaminhar </a></td> 
    </tr> 
    </c:forEach>
</tbody>

<tfoot>
    <tr bgcolor="2980b9">
        <td style="border: 0"></td>
        <td style="border: 0"></td>
        <td style="float: right; border: 0;"> 
            <c:out value = "${dashboard.inicio(pageScope.pagina)} - ${dashboard.fim(pageScope.pagina)} de ${dashboard.countMensagens()}"/>
            
            <div style="margin-left: 10px">
                <c:if test="${dashboard.paginaValida(pageScope.pagina - 1)}">
                <a href="dashboard.jsp?pagina=${pageScope.pagina - 1}">
                    <<
                </a>
                </c:if>

                <c:if test="${!dashboard.paginaValida(pageScope.pagina - 1)}">
                <a href="dashboard.jsp?pagina=${pageScope.pagina}">
                    <<
                </a>
                </c:if>

                <c:if test="${dashboard.paginaValida(pageScope.pagina + 1)}">
                <a href="dashboard.jsp?pagina=${pageScope.pagina + 1}">
                    >>
                </a>
                </c:if>  

                <c:if test="${!dashboard.paginaValida(pageScope.pagina + 1)}">
                <a href="dashboard.jsp?pagina=${pageScope.pagina}">
                    >>
                </a>
                </c:if>  
            </div>
        </td> 
    </tr>
</tfoot>
</table>

</body>
</html>