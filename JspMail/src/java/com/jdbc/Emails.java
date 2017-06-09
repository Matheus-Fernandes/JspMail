package com.jdbc;

import java.sql.*;
import java.util.List;

public class Emails
{
    public boolean cadastrado (String emailPrincipal, String outroEmail) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * FROM EmailCadastrado WHERE emailPrincipal='"+emailPrincipal+"' AND outroEmail='"+outroEmail+"'";

            DAOs.getBD().prepareStatement (sql);

            MeuResultSet resultado = (MeuResultSet)DAOs.getBD().executeQuery ();

            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar email");
        }

        return retorno;
    }
    
    public boolean cadastrado (String emailPrincipal, String outroEmail, String senha) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * FROM EmailCadastrado WHERE emailPrincipal='"+emailPrincipal+"' AND outroEmail='"+outroEmail+"' AND senha='"+senha+"'";

            DAOs.getBD().prepareStatement (sql);

            MeuResultSet resultado = (MeuResultSet)DAOs.getBD().executeQuery ();

            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar email");
        }

        return retorno;
    }
    
    public boolean temEmailPrincipal (String email) throws Exception
    {
        boolean retorno = false;
        
        try{
            String sql;
            sql = "SELECT * FROM EmailCadastrado WHERE emailPrincipal='"+email+"'";
            DAOs.getBD().prepareStatement(sql);
            MeuResultSet resultado = (MeuResultSet)DAOs.getBD().executeQuery ();
            
            retorno = resultado.first();
        }
        
        catch(SQLException erro)
    	{
    		throw new Exception ("Erro ao procurar email.");
    	}
    	
    	return retorno;
    }
    
    public boolean temOutroEmail (String email) throws Exception
    {
        boolean retorno = false;
        
        try{
            String sql;
            sql = "SELECT * FROM EmailCadastrado WHERE outroEmail='"+email+"'";
            DAOs.getBD().prepareStatement(sql);
            MeuResultSet resultado = (MeuResultSet)DAOs.getBD().executeQuery ();
            
            retorno = resultado.first();
        }
        
        catch(SQLException erro)
    	{
    		throw new Exception ("Erro ao procurar email.");
    	}
    	
    	return retorno;
    }
    
    public boolean cadastrado (Email email) throws Exception
    {
    	boolean retorno = false;
    	
    	try
    	{
            String sql;
            sql = "SELECT emailPrincipal FROM EmailCadastrado WHERE emailPrincipal='"+email.getEmailPrincipal()+"' AND outroEmail='"+email.getOutroEmail()+"' AND senha='"+email.getSenha()+"'";
            DAOs.getBD().prepareStatement(sql);
            MeuResultSet resultado = (MeuResultSet)DAOs.getBD().executeQuery ();
            
            retorno = resultado.first();
    	}
    	catch(SQLException erro)
    	{
            throw new Exception ("Erro ao procurar email");
    	}
    	
    	return retorno;
    }

    public boolean incluir (Email email) throws Exception
    {
        if (email==null)
            throw new Exception ("Email nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO EmailCadastrado (emailPrincipal, outroEmail, senha, servidorRecebimento, portaRecebimento, servidorEnvio, portaEnvio) VALUES ('"+email.getEmailPrincipal()+"', '"+ email.getOutroEmail()+"', '"+email.getSenha()+"', '"+email.getServidorRecebimento()+"', "+email.getPortaRecebimento()+", '"+email.getServidorEnvio()+"', "+email.getPortaEnvio()+")";

            DAOs.getBD().prepareStatement (sql);

            DAOs.getBD().executeUpdate ();
            DAOs.getBD().commit        ();
            
            if (cadastrado(email))
                return true;
            
            return false;
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir email");
        }
    }

    public boolean excluirOutroEmail (String outroEmail) throws Exception
    {       
        if (temOutroEmail(outroEmail)){
            try
            {
                String sql;

                sql = "DELETE FROM EmailCadastrado WHERE outroEmail='"+outroEmail+"'";

                DAOs.getBD().prepareStatement (sql);

                DAOs.getBD().executeUpdate ();
                DAOs.getBD().commit        ();  
                
                if (temOutroEmail(outroEmail))
                    return false;
                
                return true;
            }
            catch (SQLException erro)
            {
                throw new Exception ("Erro ao excluir email");
            }
        }
        
        else
            throw new Exception ("Nao cadastrado");
    }
        
    public boolean excluirEmailPrincipal (String email) throws Exception
    {       
        if (temEmailPrincipal(email)){
            try
            {
                String sql;

                sql = "DELETE FROM EmailCadastrado WHERE emailPrincipal='"+email+"'";

                DAOs.getBD().prepareStatement (sql);

                DAOs.getBD().executeUpdate ();
                DAOs.getBD().commit        ();        
                
                if (temEmailPrincipal(email))
                    return false;
                
                return true;
            }
            catch (SQLException erro)
            {
                throw new Exception ("Erro ao excluir email");
            }
        }
        else
            throw new Exception ("Nao cadastrado");
    }

    public boolean alterar(String emailPrincipal, Email email) throws Exception
    {
        if (email==null)
            throw new Exception ("Email nao fornecido");

        if (!temEmailPrincipal(emailPrincipal))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE EmailCadastrado SET emailPrincipal='"+email.getEmailPrincipal()+"', outroEmail='"+email.getOutroEmail()+"', senha='"+email.getSenha()+"', servidorRecebimento='"+email.getServidorRecebimento()+"', portaRecebimento='"+email.getPortaRecebimento()+"', servidorEnvio='"+email.getServidorEnvio()+"', portaEnvio='"+email.getPortaEnvio()+"' WHERE emailPrincipal='"+emailPrincipal+"'";

            DAOs.getBD().prepareStatement (sql);

            DAOs.getBD().executeUpdate ();
            DAOs.getBD().commit        ();
            
            if (cadastrado(email))
                return true;
            
            return false;
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados de email");
        }
    }
    
    public boolean alterar(String emailPrincipal, String novoEmailPrincipal) throws Exception
    {
        if (!temEmailPrincipal(emailPrincipal))
            throw new Exception ("Nao cadastrado");
        
        if (temEmailPrincipal(novoEmailPrincipal))
            throw new Exception ("Email Principal já cadastrado");

        try
        {
            String sql;

            sql = "UPDATE EmailCadastrado SET emailPrincipal='"+novoEmailPrincipal+"' WHERE emailPrincipal='"+emailPrincipal+"'";

            DAOs.getBD().prepareStatement (sql);

            DAOs.getBD().executeUpdate ();
            DAOs.getBD().commit        ();
            
            if (temEmailPrincipal(novoEmailPrincipal))
                return true;
            
            return false;
            
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados de email");
        }
    }

    public Email getEmail (String emailPrincipal) throws Exception
    {
        Email email = null;

        try
        {
            String sql;

            sql = "SELECT * FROM EmailCadastrado WHERE emailPrincipal ='"+emailPrincipal+"'";

            DAOs.getBD().prepareStatement (sql);

            MeuResultSet resultado = (MeuResultSet)DAOs.getBD().executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            email = new Email (resultado.getString("emailPrincipal"), 
                               resultado.getString("outroEmail"), 
                               resultado.getString("senha"), 
                               resultado.getString("servidorRecebimento"), 
                               Integer.parseInt(resultado.getString("portaRecebimento")), 
                               resultado.getString("servidorEnvio"), 
                               Integer.parseInt(resultado.getString("portaEnvio")));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar email");
        }

        return email;
    }

    public List getEmails () throws Exception
    {
        List<Email> lista = null;

        try
        {
            String sql;

            sql = "SELECT * FROM EmailCadastrado";

            DAOs.getBD().prepareStatement (sql);

            MeuResultSet resultado = (MeuResultSet)DAOs.getBD().executeQuery ();
            
            if (resultado.first()){
                Email linha = new Email();
                linha.setEmailPrincipal(resultado.getString(2));
                linha.setOutroEmail(resultado.getString(3));
                linha.setSenha(resultado.getString(4));
                linha.setServidorRecebimento(resultado.getString(5));
                linha.setPortaRecebimento(Integer.parseInt(resultado.getString(6)));
                linha.setServidorEnvio(resultado.getString(7));
                linha.setPortaEnvio(Integer.parseInt(resultado.getString(8)));
                lista.add(linha);
                
                while (resultado.next()){
                    linha.setEmailPrincipal(resultado.getString(2));
                    linha.setOutroEmail(resultado.getString(3));
                    linha.setSenha(resultado.getString(4));
                    linha.setServidorRecebimento(resultado.getString(5));
                    linha.setPortaRecebimento(Integer.parseInt(resultado.getString(6)));
                    linha.setServidorEnvio(resultado.getString(7));
                    linha.setPortaEnvio(Integer.parseInt(resultado.getString(8)));
                    lista.add(linha);
                }
            }
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar emails");
        }

        return lista;
    }
}