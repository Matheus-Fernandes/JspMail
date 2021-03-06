<%@taglib tagdir="/WEB-INF/tags/" prefix="m"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="bootstrap/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <title>Enviar</title>
<style>
html{
    height: 100%;
}
body{
    height: 100%;
    background-color: #eee;
}

.corpo{
    position: absolute;
    top : 0;
    display: inline-block;
    float: right;
    width: 87%;
    padding: 30px;
}

.botoes{
    right: 0;
    float: right;
    width: 100%;
}

.vertical-menu {
    float: left;
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
    <jsp:useBean id="managed" scope="page" class="com.web.EnviarMensagemManagedBean" />
    <jsp:useBean id="provider" scope="page" class="com.email.EmailProvider"/>
    <jsp:useBean id="emails" scope="page" class="com.jdbc.Emails"/>
    ${provider.setEmail(sessionScope.novoEmail)}
    
    <c:if test="${param.operation == 'enviar'}">
        <c:set scope="page" var="mensagemEnviada" value="${provider.sendMessage(param.assunto, managed.gerarVetorDestinatario(param.destinatario), param.conteudo, null)}"/><%-- params de sendMessage--%>
        <c:if test="${pageScope.mensagemEnviada}">
            <c:set scope="session" var="aviso_texto" value="Email enviado com sucesso!"/>
            <c:set scope="session" var="aviso_tipo" value="SUCESSO"/>
        </c:if>
        <c:if test="${!pageScope.mensagemEnviada}">
            <c:set scope="session" var="aviso_texto" value="Falha no envio da mensagem."/>
            <c:set scope="session" var="aviso_tipo" value="ERRO"/>  
        </c:if>
    </c:if>
        
    <div class="vertical-menu">
        <a href="dashboard.jsp">Caixa de Entrada</a>
        <a href="enviarMensagem.jsp"  class="active">Enviar Mensagem</a>
        <a href="gerenciarEmails.jsp">Gerenciar Emails</a>
        <a href="index.jsp?operation=sair">Sair</a>
    </div>
        
    <form action="enviarMensagem.jsp">
        <div class = "corpo">
            <m:campo label="Destinatário" nome="destinatario" tipo="text"/>
            <m:campo label="Assunto" nome="assunto" tipo="text"/>

            <div class="form-group">
                <label for="texto"></label>
                <textarea class="form-control" 
                          id="conteudo" name="conteudo" rows="20"></textarea>
            </div>

            <div class="btn-group  botoes">
                <button type="submit" style="float: right; width: 100%" name="operation" value = "enviar" class="btn btn-default">Enviar</button>
            </div>
        </div>
            
            <m:aviso valor="${sessionScope.aviso_texto}" tipo="${sessionScope.aviso_tipo}"/>
    </form>
    
</body>
    

</html>
