<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="managed" scope="page" class="com.web.AnexosManagedBean" />
${managed.setArq(sessionScope.anexos)}
${managed.setNomes(sessionScope.anexosNome)}

${managed.baixar(param.arquivo, pageContext.response)}

<jsp:forward page="/tmp/${sessionScope.anexosNome.get(param.arquivo)}" />