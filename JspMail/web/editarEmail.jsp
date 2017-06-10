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
<div class="vertical-menu">
  <a href="#">Caixa de Entrada</a>
  <a href="#" class="active">Emails Cadastrados</a>
  <a href="#">Sair</a>
</div>
    <jsp:useBean id="email" scope="page" class="com.jdbc.Email"/>
    <jsp:useBean id="emailDao" scope="page" class="com.jdbc.Emails"/>

    <c:if test = "${param.operation == 'voltar'}">
        <jsp:forward page="gerenciarEmails.jsp" />
    </c:if>
    <c:if test = "${param.operation == 'editarEmail'}">
        ${email.setEmailPrincipal()} <!-- Setar para o email logado -->
        ${email.setOutroEmail(param.email)}
        ${email.setSenha(param.senha)}
        ${email.setServidorRecebimento(param.servidorRecebimento)}
        ${email.setPortaRecebimento(Integer.parseInt(param.portaRecebimento))}
        ${email.setServidorEnvio(param.servidorEnvio)}
        ${email.setPortaEnvio(Integer.parseInt(param.portaEnvio))}
        <c:set scope="session" var="jaExiste" value="${emailDao.temOutroEmail(email.outroEmail)}"/>

        <c:if test="${!sessionScope.jaExiste}">
            <c:set scope="session" var="aviso_texto" value="Esse email n�o est� cadastrado!"/>
            <c:set scope="session" var="aviso_tipo" value="ERRO"/>
        </c:if>
        <c:if test="${sessionScope.jaExiste}">
            <c:set scope="session" var="emailAlterado" value="${emailDao.alterar(emailPrincipal, email)}"/>  <!-- Setar para o email logado -->

            <c:if test="${sessionScope.emailAlterado}">
                <c:set scope="session" var="aviso_texto" value="Email alterado com sucesso!"/>
                <c:set scope="session" var="aviso_tipo" value="SUCESSO"/>
            </c:if>
            <c:if test="${!sessionScope.emailAlterado}">
                <c:set scope="session" var="aviso_texto" value="Falha na altera��o de Email."/>
                <c:set scope="session" var="aviso_tipo" value="ERRO"/>
            </c:if>
        </c:if>
    </c:if>

    <div class="panel panel-default container clearfix" style="width: 300px; padding: 30px">
        <form method="get" action="cadastrarEmail.jsp">

        <m:titulo valor="Edi��o"/>
        <br>
        <!-- Colocar os valores do email que se deseja alterar nos campos! -->
        <m:campo nome="email" tipo="text" label="Email"/> 
        <m:campo nome="senha" tipo="password" label="Senha"/>
        <m:campo nome="servidorRecebimento" tipo="text" label="Servidor de Recebimento"/>
        <m:campo nome="portaRecebimento" tipo="number" label="Porta de Recebimento"/>
        <m:campo nome="servidorEnvio" tipo="text" label="Servidor de Envio"/>
        <m:campo nome="portaEnvio" tipo="number" label="Porta de Envio"/>
        <br>

        <div class="input-group-btn">
            <button style="width: 60%" type="submit" name="operation" value = "cadastrar" class="btn btn-default">Salvar</button>
            <button style="width: 40%" type="submit" name="operation" value = "voltar" class="btn btn-default">Voltar</button>

        </div>
        <br/>

        <m:aviso valor="${sessionScope.aviso_texto}" tipo="${sessionScope.aviso_tipo}"/>

        </form>
    </div>
</body>
</html>