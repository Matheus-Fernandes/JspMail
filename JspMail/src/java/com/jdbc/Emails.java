package br.cotuca.unicamp;

import java.sql.*;

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
            sql = "SELECT email FROM EmailCadastrado WHERE email='"+email.getEmailPrincipal()+"' AND outroEmail='"+email.getOutroEmail()+"' senha='"+email.getSenha()+"'";
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

    public void incluir (Email email) throws Exception
    {
        if (email==null)
            throw new Exception ("Email nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO EmailCadastrado (emailPrincipal, outroEmail ,senha) VALUES ('"+email.getEmailPrincipal()+"', '"+ email.getOutroEmail()+"', '"+email.getSenha()+"')";

            DAOs.getBD().prepareStatement (sql);

            DAOs.getBD().executeUpdate ();
            DAOs.getBD().commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir email");
        }
    }

    public void excluir (String email) throws Exception
    {       
        if (temOutroEmail(email)){
            try
            {
                String sql;

                sql = "DELETE FROM EmailCadastrado WHERE outroEmail='"+email+"'";

                DAOs.getBD().prepareStatement (sql);

                DAOs.getBD().executeUpdate ();
                DAOs.getBD().commit        ();        }
            catch (SQLException erro)
            {
                throw new Exception ("Erro ao excluir email");
            }
        }
        else{
            if (temEmailPrincipal(email)){
                try
                {
                    String sql;

                    sql = "DELETE FROM EmailCadastrado WHERE emailPrincipal='"+email+"'";

                    DAOs.getBD().prepareStatement (sql);

                    DAOs.getBD().executeUpdate ();
                    DAOs.getBD().commit        ();        }
                catch (SQLException erro)
                {
                    throw new Exception ("Erro ao excluir email");
                }
            }
            else
                throw new Exception ("Nao cadastrado");
        }

        
    }

    public void alterar(String emailPrincipal, Email email) throws Exception
    {
        if (email==null)
            throw new Exception ("Email nao fornecido");

        if (!temEmailPrincipal(emailPrincipal))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE EmailCadastrado SET outroEmail='"+email.getOutroEmail()+"', senha='"+email.getSenha()+"' WHERE emailPrincipal='"+emailPrincipal+"'";

            DAOs.getBD().prepareStatement (sql);

            DAOs.getBD().executeUpdate ();
            DAOs.getBD().commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados de email");
        }
    }
    
    public void alterar(String emailPrincipal, String novoEmailPrincipal) throws Exception
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

            email = new Email (resultado.getString("emailPrincipal"), resultado.getString("outroEmail"), resultado.getString("senha"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar email");
        }

        return email;
    }

    public MeuResultSet getEmails () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * FROM EmailCadastrado";

            DAOs.getBD().prepareStatement (sql);

            resultado = (MeuResultSet)DAOs.getBD().executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar emails");
        }

        return resultado;
    }
}