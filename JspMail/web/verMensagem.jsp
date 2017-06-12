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
        <c:if test="${sessionScope.mensagem != null}">
            <div class="exibicao">
                <div>Assunto: </div> <div class="content"> <c:out value="${sessionScope.mensagem.getAssunto()}"/> </div>
                <div>Remetentes: </div> <div class="content"> <c:out value="${sessionScope.mensagem.getRemetente()}"/> </div>
                <br> 
                <div>Conteúdo: </div> 
                <br>
                <div class="content espaco"> <c:out value="${sessionScope.mensagem.getConteudo()}" escapeXml="false" /> </div>
                <div>Anexos: </div> 
                <br>
                <div class="content espaco"> 
                    <c:set var="anexosNome" scope="page" value="${sessionScope.mensagem.getAttachmentsName()}" /> 
                    <c:set var="anexos" scope="page" value="${sessionScope.mensagem.getAttachments()}" />
                    <c:forEach var="i" begin="0" end="${pageScope.anexosNome.size()}">
                        <c:out value="${pageScope.anexosNome.get(i)}" />
                    </c:forEach>
                </div>

                <button>Excluir Email</button>
            </div>
        </c:if>
    </body>
</html>
