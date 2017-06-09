package com.jdbc;

import java.sql.*;
import java.util.List;

public class Usuarios
{
    public boolean cadastrado (String email, String senha) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * FROM UsuarioMaligno WHERE email='"+email+"' AND senha='"+senha+"'";

            DAOs.getBD().prepareStatement (sql);

            MeuResultSet resultado = (MeuResultSet)DAOs.getBD().executeQuery ();

            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar usuário");
        }

        return retorno;
    }
    
    public boolean cadastrado (Usuario usuario) throws Exception
    {
    	boolean retorno = false;
    	
    	try
    	{
            String sql;
            sql = "SELECT email FROM UsuarioMaligno WHERE email='"+usuario.getEmail()+"' AND senha='"+usuario.getSenha()+"'";
            DAOs.getBD().prepareStatement(sql);
            MeuResultSet resultado = (MeuResultSet)DAOs.getBD().executeQuery ();
            
            retorno = resultado.first();
    	}
    	catch(SQLException erro)
    	{
    		throw new Exception ("Erro ao procurar usuario");
    	}
    	
    	return retorno;
    }
    
    public boolean cadastradoEmail(String email) throws Exception // no caso de alteração de preco
    {
    	boolean retorno = false;
    	
    	try
    	{
            String sql;
            sql = "SELECT email FROM UsuarioMaligno WHERE email='"+email+"'";
            DAOs.getBD().prepareStatement(sql);
            MeuResultSet resultado = (MeuResultSet)DAOs.getBD().executeQuery ();
            
            retorno = resultado.first();
    	}
    	catch(SQLException erro)
    	{
    		throw new Exception ("Erro ao procurar usuario.");
    	}
    	
    	return retorno;
    }

    public boolean incluir (Usuario usuario) throws Exception
    {
        if (usuario==null)
            throw new Exception ("Usuario nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO UsuarioMaligno (email,senha) VALUES ('"+usuario.getEmail()+"','"+usuario.getSenha()+"')";

            DAOs.getBD().prepareStatement (sql);

            DAOs.getBD().executeUpdate ();
            DAOs.getBD().commit        ();
            
            if (cadastrado(usuario))
                return true;
            
            return false;
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir usuario");
        }   
    }

    public boolean excluir (String email) throws Exception
    {
        if (!cadastradoEmail(email))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM UsuarioMaligno WHERE email='"+email+"'";

            DAOs.getBD().prepareStatement (sql);

            DAOs.getBD().executeUpdate ();
            DAOs.getBD().commit        (); 
            
            if (cadastradoEmail(email))
                return false;
            
            return true;
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir usuario");
        }
    }

    public boolean alterar (String email, Usuario usuario) throws Exception
    {
        if (usuario==null)
            throw new Exception ("Usuario nao fornecido");

        if (!cadastradoEmail(email))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE UsuarioMaligno SET email='"+usuario.getEmail()+"', senha='"+usuario.getSenha()+"' WHERE email='"+email+"'";

            DAOs.getBD().prepareStatement (sql);

            DAOs.getBD().executeUpdate ();
            DAOs.getBD().commit        ();
            
            if (cadastrado(usuario))
                return true;
            
            return false;
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados de usuario");
        }
    }
    
    public boolean alterarSenha (String email, String novaSenha) throws Exception // quando apenas altera o preço
    {
        if (!cadastradoEmail(email))
            throw new Exception ("Nao cadastrado");

        try
        {            
            String sql;

            sql = "UPDATE UsuarioMaligno SET senha='"+novaSenha+"' WHERE email='"+email+"'";

            DAOs.getBD().prepareStatement (sql);

            DAOs.getBD().executeUpdate ();
            DAOs.getBD().commit        ();
            
            if (cadastrado(email, novaSenha))
               return true;
            
            return false;
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados de usuario");
        }
    }
    
    public boolean alterarEmail (String email, String emailNovo) throws Exception // quando apenas altera o preço
    {
        if (!cadastradoEmail(email))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE UsuarioMaligno SET email='"+emailNovo+"' WHERE email='"+email+"'";

            DAOs.getBD().prepareStatement (sql);

            DAOs.getBD().executeUpdate ();
            DAOs.getBD().commit        ();
            
            if (cadastradoEmail(emailNovo))
                return true;
            
            return false;
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados de usuario");
        }
    }

    public Usuario getUsuario (String email) throws Exception
    {
        Usuario usuario = null;

        try
        {
            String sql;

            sql = "SELECT * FROM UsuarioMaligno WHERE email ='"+email+"'";

            DAOs.getBD().prepareStatement (sql);

            MeuResultSet resultado = (MeuResultSet)DAOs.getBD().executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            usuario = new Usuario (resultado.getString("email"), resultado.getString("senha"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar usuario");
        }

        return usuario;
    }

    public List getUsuarios () throws Exception
    {
        List<Usuario> lista = null;

        try
        {
            String sql;

            sql = "SELECT * FROM UsuarioMaligno";

            DAOs.getBD().prepareStatement (sql);

            MeuResultSet resultado = (MeuResultSet)DAOs.getBD().executeQuery ();
            
            if (resultado.first()){
                Usuario linha = new Usuario();
                linha.setEmail(resultado.getString(1));
                linha.setSenha(resultado.getString(2));
                lista.add(linha);
                
                while (resultado.next()){
                    linha.setEmail(resultado.getString(1));
                    linha.setSenha(resultado.getString(2));
                    lista.add(linha);
                }
            }
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar usuarios");
        }

        return lista;
    }
}