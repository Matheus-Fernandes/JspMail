<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizar</title>
        
        <style>
            .content{
                background-color: white;
            }
            .exibicao{
                background-color: #AAA; 
                float: center;
                padding: 1em 2em;
            }
            .espaco
            {
                padding: 1em 2em;
            }
        </style>
    </head>
    <body style="background-color: #EEE">        
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
                <div>Assunto: </div> <div class="content"> <c:out value="${sessionScope.mensagem.getAssunto()}"/> </div>
                <div>Remetentes: </div> <div class="content"> <c:out value="${sessionScope.mensagem.getRemetente()}"/> </div>
                <br> 
                <div>Conteúdo: </div> 
                <br>
                <div class="content espaco"> <c:out value="${sessionScope.mensagem.getConteudo().toString()}" escapeXml="false" /> </div>
                <div>Anexos: </div> 
                <br>
                <div class="content espaco"> 
                    <c:set var="anexosNome" scope="session" value="${sessionScope.mensagem.getAttachmentsName()}" /> 
                    <c:set var="anexos" scope="session" value="${sessionScope.mensagem.getAttachments()}" />
                    <c:if test="${sessionScope.anexosNome.size() > 0}">
                        <c:forEach var="i" begin="0" end="${sessionScope.anexosNome.size() - 1}">
                            <a href="anexos.jsp?arquivo=${i}"><c:out value="${sessionScope.anexosNome.get(i)}" /></a>
                        </c:forEach>
                    </c:if>
                </div>

                <button type="submit" style="float: right; width: 100%" name="operation" value = "excluir" class="btn btn-default">Excluir Email</button>
            </div>
                    <m:aviso valor="${sessionScope.aviso_texto}" tipo="${sessionScope.aviso_tipo}"/>
            </form>
        </c:if>
    </body>
</html>
