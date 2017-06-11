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


</style>
</head>

<body>
<jsp:useBean id="dashboard" scope="page" class="com.web.DashboardManagedBean"/>
<div class="vertical-menu">
  <a href="#" class="active">Caixa de Entrada</a>
  <a href="#">Emails Cadastrados</a>
  <a href="#">Sair</a>
</div>

<table class="listaMensagens">
<!-- percorre contatos montando as linhas da tabela -->
<thead>
    <tr bgcolor="2980b9" style="height:  43px">
        <td></td><td></td><td></td>
    </tr>
</thead>
<tbody >
    <c:forEach varStatus="id" var="mensagem" items="${dashboard.getCaixaEntrada('matheus@gmail.com')}">
    <tr bgcolor="DADFE1" > 
        <td class = "td_assunto"><a href="dashboard.jsp">${mensagem.assunto}</a></td> 
        <td  class = "td_botao"><a href="dashboard.jsp">Responder </a></td> 
        <td  class = "td_botao"><a href="dashboard.jsp">Encaminhar </a></td> 
    </tr> 
    </c:forEach>
</tbody>
<tfoot>
    <tr bgcolor="2980b9">
        <td style="border: 0"></td>
        <td style="border: 0"></td>
        <td style="float: right; border: 0">

                <a href="dashboard.jsp">
                    <<
                </a>
                
                <a href="dashboard.jsp">
                    >>
                </a>
   
        </td> 
    </tr>
</tfoot>
</table>

</body>
</html>