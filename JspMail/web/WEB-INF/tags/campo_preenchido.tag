<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="nome"%>
<%@attribute name="label"%>
<%@attribute name="tipo" %>
<%@attribute name="value" %>
<%-- any content can be specified here e.g.: --%>
<div class="form-group">
    <label for="${nome}">${label}</label>
    <input type="${tipo}" class="form-control" 
           id="${nome}" name="${nome}" value="${value}" readonly="true">
</div>