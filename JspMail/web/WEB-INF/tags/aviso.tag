<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="nodificar uma mensagem no formato aviso" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="mensagem"%>
<%@attribute name="tipo"%>

<%-- any content can be specified here e.g.: --%>
<c:if test="${tipo == 'ERRO'}">
    <span style="color: red; font-weight: bolder ">
        <c:out value="${mensagem}"/>
    </span>
</c:if>
<c:if test="${tipo == 'SUCESSO'}">
    <span style="color: greenyellow; font-weight: bolder ">
       <c:out value="${mensagem}"/>
    </span>
</c:if>
