<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!Doctype html>
<html>
<style>
html{
    height: 100%;
}
body{
    height: 100%;
}

.vertical-menu {
    width: 200px;
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
    background-color: #4CAF50;
    color: white;


</style>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="bootstrap/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>

<body>
<jsp:useBean id="dashboard" scope="page" class="com.web.DashboardManagedBean"/>
<div class="vertical-menu">
  <a href="#" class="active">Caixa de Entrada</a>
  <a href="#">Emails Cadastrados</a>
  <a href="#">Sair</a>
</div>

<table border = "1">
<!-- percorre contatos montando as linhas da tabela -->
<c:forEach var="mensagem" items="${dashboard.getCaixaEntrada('matheus@gmail.com')}">
    <tr> 
        <td>${mensagem.assunto}</td> 
        <td><button type="submit"  class="btn btn-default">Responder</button></td> 
        <td><button type="submit"  class="btn btn-default">Encaminhar</button></td> 
    </tr> 
</c:forEach>
</table>

</body>
</html>