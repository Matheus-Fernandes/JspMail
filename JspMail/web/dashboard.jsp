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
    background-color: #2c3e50;
}

.listaMensagens{
    float: right;
    position: absolute;
    top: 44px;
    right: 0;
    width: 85%;
}



.listaMensagens .td_assunto{
   padding: 12px;
   width: 90%;
}

.listaMensagens .td_botao{
   width: 5%;
}

.listaMensagens tbody{
    width: 100%;
}

.vertical-menu {
    width: 15%;
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
<tbody>
    <c:forEach varStatus="id" var="mensagem" items="${dashboard.getCaixaEntrada('matheus@gmail.com')}">
    <tr bgcolor="#${id.count % 2 == 0 ? '2c3e50' : 'eee' }" > 
        <td class = "td_assunto" style="color : #${id.count % 2 == 0 ? 'ffffff' : '000000' }">${mensagem.assunto}</td> 
        <td  class = "td_botao"><button type="submit" value="responder" class="btn btn-default botao">Responder</button></td> 
        <td  class = "td_botao"><button type="submit"  class="btn btn-default botao">Encaminhar</button></td> 
    </tr> 
    </c:forEach>
</tbody>
<tfoot>
    <tr bgcolor="2980b9">
        <td></td>
        <td></td>
        <td style="float: right">

                <button  type="submit" style="margin:1px"  class="btn btn-toolbar">
                    <<
                </button>
                
                <button type="submit" style="margin:1px" class="btn btn-toolbar">
                    >>
                </button>
   
        </td> 
    </tr>
</tfoot>
</table>

</body>
</html>