<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizar</title>
        <script src="bootstrap/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        
<style>
.corpo{
    position: absolute;
    top: 0;
    right: 0;
    width: 88%;
}
.content{
    background-color: white;
}
.exibicao{
    padding: 1em 2em;
}
.espaco
{
    padding: 1em 2em;
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
    <body style="background-color: #EEE">        
    <div class="vertical-menu">
        <a href="dashboard.jsp" class="active">Caixa de Entrada</a>
        <a href="enviarMensagem.jsp">Enviar Mensagem</a>
        <a href="gerenciarEmails.jsp">Gerenciar Emails</a>
        <a href="index.jsp?operation=sair">Sair</a>
    </div>
    <div class = "corpo">
        <jsp:useBean id="provider" scope="page" class="com.email.EmailProvider"/>
        <c:if test="${param.operation == 'excluir'}">
            <c:set var="mensagemExcluida" scope="page" value="${provider.deleteMessage(sessionScope.mensagem, autoClose)}"/>
            <c:if test="${pageScope.mensagemExcluida}">
                <c:set scope="session" var="aviso_texto" value="Email excluído com sucesso!"/>
                <c:set scope="session" var="aviso_tipo" value="SUCESSO"/>
            </c:if>
            <c:if test="${!pageScope.mensagemExcluida}">
                <c:set scope="session" var="aviso_texto" value="Falha na exclusão da mensagem."/>
                <c:set scope="session" var="aviso_tipo" value="ERRO"/> 
            </c:if>
        </c:if>
        <c:if test="${sessionScope.mensagem != null}">
            <form action="verMensagem.jsp">
            <div class="exibicao">
                <div class="form-group"><label>Assunto:</label>  <div class="content espaco"> <c:out value="${sessionScope.mensagem.getAssunto()}"/> </div> </div>
                <div class="form-group"><label>Remetentes:  </label><div class="content espaco"> <c:out value="${sessionScope.mensagem.getRemetente()}"/> </div> </div>
                <br> 
                <div class="form-group"><label>Conteúdo: </label>
                <br>
                <div class="content espaco"> <c:out value="${sessionScope.mensagem.getConteudo().toString()}" escapeXml="false" /> </div>
                </div> 
                
                <c:if test="${sessionScope.anexosNome.size() > 0}">                
                <div style="margin-top:15px;">Anexos: </div> 
                <br>
                
                <div class="content espaco"> 
                    <c:set var="anexosNome" scope="session" value="${sessionScope.mensagem.getAttachmentsName()}" /> 
                    <c:set var="anexos" scope="session" value="${sessionScope.mensagem.getAttachments()}" />

                        <c:forEach var="i" begin="0" end="${sessionScope.anexosNome.size() - 1}">
                            <a href="anexos.jsp?arquivo=${i}"><c:out value="${sessionScope.anexosNome.get(i)}" /></a>
                        </c:forEach>

                </div>
                </c:if>

                <button type="submit" style="margin-top: 15px; width: 100%; float: right" name="operation" value = "excluir" class="btn btn-default">Excluir Email</button>
            </div>
                    <m:aviso valor="${sessionScope.aviso_texto}" tipo="${sessionScope.aviso_tipo}"/>
            </form>
        </c:if>
    </div>
    </body>
</html>
