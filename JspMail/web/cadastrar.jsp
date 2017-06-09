<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="m"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Cadastro</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="bootstrap/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    </head>
    
    <body>
        <jsp:useBean id="usuarioDao" scope="page" class="com.jdbc.Usuarios"/>
        <jsp:useBean id="usuario" scope="page" class="com.jdbc.Usuario"/>
        <jsp:useBean id="email" scope="page" class="com.jdbc.Email"/>
        <jsp:useBean id="emailDao" scope="page" class="com.jdbc.Emails"/>
        
        <c:if test = "${param.operation == 'voltar'}">
            <jsp:forward page="index.jsp" />
        </c:if>
        <c:if test = "${param.operation == 'cadastrar'}">
            ${usuario.setEmail(param.email)}
            ${usuario.setSenha(param.senha)}
            
            ${email.setEmailPrincipal(param.email)}
            ${email.setOutroEmail(param.email)}
            ${email.setSenha(param.senha)}
            ${email.setServidorRecebimento(param.servidorRecebimento)}
            ${email.setPortaRecebimento(Integer.parseInt(param.portaRecebimento))}
            ${email.setServidorEnvio(param.servidorEnvio)}
            ${email.setPortaEnvio(Integer.parseInt(param.portaEnvio))}
            <c:set scope="session" var="jaExiste" value="${usuarioDao.cadastradoEmail(param.email)}"/>
            
            <c:if test="${sessionScope.jaExiste}">
                <c:set scope="session" var="aviso_texto" value="esse usuario já existe!"/>
                <c:set scope="session" var="aviso_tipo" value="ERRO"/>
            </c:if>
            <c:if test="${!sessionScope.jaExiste}">
                <c:set scope="session" var="senhasIguais" value="${param.senha == param.confirmacao}"/>
                
                <c:if test="${sessionScope.senhasIguais}">


                    <c:set scope="session" var="usuarioCadastrado" value="${usuarioDao.incluir(usuario)}"/>

                    <c:if test="${sessionScope.usuarioCadastrado}">
                        <c:set scope="session" var="emailCadastrado" value="${emailDao.incluir(email)}"/>
                        
                        <c:if test="${sessionScope.emailCadastrado}">
                            <c:set scope="session" var="aviso_texto" value="Usuario cadastrado com sucesso!"/>
                            <c:set scope="session" var="aviso_tipo" value="SUCESSO"/>
                        </c:if>
                        <c:if test="${!sessionScope.emailCadastrado}">
                            <c:set scope="session" var="aviso_texto" value="Falha no cadastro de usuario."/>
                            <c:set scope="session" var="aviso_tipo" value="ERRO"/>
                        </c:if>
                    </c:if>
                    <c:if test="${!sessionScope.usuarioCadastrado}">
                        <c:set scope="session" var="aviso_texto" value="As senhas informadas não são iguais!"/>
                        <c:set scope="session" var="aviso_tipo" value="ERRO"/>
                    </c:if>

                </c:if>
                <c:if test="${!sessionScope.senhasIguais}">
                    <c:set scope="session" var="aviso_texto" value="As senhas informadas não são iguais!"/>
                    <c:set scope="session" var="aviso_tipo" value="ERRO"/>
                </c:if>
            </c:if>
            

        </c:if>
        
        <div class="panel panel-default container clearfix" style="width: 300px; padding: 30px">
            <form method="get" action="cadastrar.jsp">
            
            <m:titulo valor="Cadastro"/>
            <br>
            <m:campo nome="email" tipo="text" label="Email"/>
            <m:campo nome="senha" tipo="password" label="Senha"/>
            <m:campo nome="confirmacao" tipo="password" label="Confirmação de senha"/>
            <m:campo nome="servidorRecebimento" tipo="text" label="Servidor de Recebimento"/>
            <m:campo nome="portaRecebimento" tipo="number" label="Porta de Recebimento"/>
            <m:campo nome="servidorEnvio" tipo="text" label="Servidor de Envio"/>
            <m:campo nome="portaEnvio" tipo="number" label="Porta de Envio"/>
            <br>
            
            <div class="input-group-btn">
                <button style="width: 60%" type="submit" name="operation" value = "cadastrar" class="btn btn-default">Cadastrar</button>
                <button style="width: 40%" type="submit" name="operation" value = "voltar" class="btn btn-default">Voltar</button>
            
            </div>
            <br/>
            
            <m:aviso valor="${sessionScope.aviso_texto}" tipo="${sessionScope.aviso_tipo}"/>

            </form>
        </div>
    </body>
</html>
