<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="m"%>
<!Doctype html>
<html>
<style>
html{
    height: 100%;
}
body{
    height: 100%;
}

.listaEmails{
    width: 100%;
    background-color: #DADFE1;

}

.listaEmails tbody, th, td {
    border-bottom: 1px solid #eee;
}

.botao button{
    right: 0;
    float: right;
    margin: 5px; 
}

.conteudo {
    padding: 10px;
}

.botao{
    width: 10px;
}

#corpo{
    position: absolute;
    display: block;
    float:right;
    width: 88%;
    top: 0;
    right: 0;
    padding: 30px;
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
}
</style>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="bootstrap/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>

<body>
    <jsp:useBean id="email" scope="page" class="com.jdbc.Email"/>
    <jsp:useBean id="emailDao" scope="page" class="com.jdbc.Emails"/>
    <c:set scope="session" var="emailEditarTodo" value="${emailDao.getOutroEmail(sessionScope.emailEditar, sessionScope.usuario)}"/>
    <c:set scope="session" var="aviso_texto" value=""/>
    <c:set scope="session" var="aviso_tipo" value=""/>

    <c:if test = "${param.operation == 'voltar'}">
        <jsp:forward page="gerenciarEmails.jsp" />
    </c:if>
    <c:if test = "${param.operation == 'salvar'}">
    <c:set scope="page" var="camposPreenchidos" value="${param.email == '' || param.senha=='' || param.confirmaSenha=='' || param.servidorRecebimento=='' || param.portaRecebimento=='' || param.servidorEnvio=='' || param.portaEnvio==''}"/>
    <c:if test="${pageScope.camposPreenchidos}">
        <c:set scope="session" var="aviso_texto" value="Preencha todos os campos!"/>
        <c:set scope="session" var="aviso_tipo" value="ERRO"/>
    </c:if>
    <c:if test="${!pageScope.camposPreenchidos}">
        ${email.setEmailPrincipal(sessionScope.usuario)} <!-- Setar para o email logado -->
        ${email.setOutroEmail(param.email)}
        ${email.setSenha(param.senha)}
        ${email.setServidorRecebimento(param.servidorRecebimento)}
        ${email.setPortaRecebimento(Integer.parseInt(param.portaRecebimento))}
        ${email.setServidorEnvio(param.servidorEnvio)}
        ${email.setPortaEnvio(Integer.parseInt(param.portaEnvio))}
        <c:set scope="session" var="confirmaSenha" value="${param.senha == param.confirmaSenha}"/>
        <c:if test="${sessionScope.confirmaSenha}">
            <c:set scope="session" var="jaExiste" value="${emailDao.temOutroEmail(email.outroEmail)}"/>

            <c:if test="${!sessionScope.jaExiste}">
                <c:set scope="session" var="aviso_texto" value="Esse email não está cadastrado!"/>
                <c:set scope="session" var="aviso_tipo" value="ERRO"/>
            </c:if>
            <c:if test="${sessionScope.jaExiste}">
                <c:set scope="session" var="emailAlterado" value="${emailDao.alterar(sessionScope.usuario, email)}"/>  <!-- Setar para o email logado -->

                <c:if test="${sessionScope.emailAlterado}">
                    <c:set scope="session" var="aviso_texto" value="Email alterado com sucesso!"/>
                    <c:set scope="session" var="aviso_tipo" value="SUCESSO"/>
                </c:if>
                <c:if test="${!sessionScope.emailAlterado}">
                    <c:set scope="session" var="aviso_texto" value="Falha na alteração de Email."/>
                    <c:set scope="session" var="aviso_tipo" value="ERRO"/>
                </c:if>
            </c:if>
        </c:if>
        <c:if test="${!sessionScope.confirmaSenha}">
            <c:set scope="session" var="aviso_texto" value="Senhas não correspondentes"/>
            <c:set scope="session" var="aviso_tipo" value="ERRO"/>
            
        </c:if>
        </c:if>
    </c:if>
                
<div class="vertical-menu">
  <a href="dashboard.jsp">Caixa de Entrada</a>
  <a href="enviarMensagem.jsp">Enviar Mensagem</a>
  <a href="gerenciarEmails.jsp" class="active">Gerenciar Emails</a>
  <a href="index.jsp?operation=sair">Sair</a>
</div>

    <div id="corpo">
        <form method="get" action="editarEmail.jsp">
            
        <!-- Colocar os valores do email que se deseja alterar nos campos! -->
        <m:campo nome="email" tipo="text" label="Email" value="${sessionScope.emailEditar}"/> 
        <m:campo nome="senha" tipo="password" label="Senha" value="${sessionScope.emailEditarTodo.getSenha()}"/>
        <m:campo nome="confirmaSenha" tipo="password" label="Confirmar Senha" value=""/>
        <m:campo nome="servidorRecebimento" tipo="text" label="Servidor de Recebimento" value="${sessionScope.emailEditarTodo.getServidorRecebimento()}"/>
        <m:campo nome="portaRecebimento" tipo="number" label="Porta de Recebimento" value="${sessionScope.emailEditarTodo.getPortaRecebimento()}"/>
        <m:campo nome="servidorEnvio" tipo="text" label="Servidor de Envio" value="${sessionScope.emailEditarTodo.getServidorEnvio()}"/>
        <m:campo nome="portaEnvio" tipo="number" label="Porta de Envio" value="${sessionScope.emailEditarTodo.getPortaEnvio()}"/>
        <br>

        <div class="input-group-btn">
            <button style="width: 60%" type="submit" name="operation" value = "salvar" class="btn btn-default">Salvar</button>
            <button style="width: 40%" type="submit" name="operation" value = "voltar" class="btn btn-default">Voltar</button>

        </div>
        <br/>

        <m:aviso valor="${sessionScope.aviso_texto}" tipo="${sessionScope.aviso_tipo}"/>

        </form>
    </div>
</body>
</html>