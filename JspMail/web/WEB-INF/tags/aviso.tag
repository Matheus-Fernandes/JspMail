<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="nodificar uma mensagem no formato aviso" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="valor"%>
<%@attribute name="tipo"%>

<%-- any content can be specified here e.g.: --%>
<c:if test="${tipo == 'ERRO'}">
    <center>
    <span style="color: red; font-weight: bolder ">
        <c:out value="${valor}"/>
    </span>
    </center>
</c:if>
<c:if test="${tipo == 'SUCESSO'}">
    <center>
    <span style="color: greenyellow; font-weight: bolder ">
       <c:out value="${valor}"/>
    </span>
    </center>
</c:if>
