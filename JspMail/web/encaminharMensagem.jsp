<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Encaminhar</title>
    </head>
    <body>
    <c:if test="${sessionScope.mensagem != null}">
        <c:out value="${sessionScope.mensagem.toString()}"/>
    </c:if>
    </body>
</html>
