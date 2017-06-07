package br.cotuca.unicamp;

import java.sql.*;

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

    public void incluir (Usuario usuario) throws Exception
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
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir usuario");
        }
    }

    public void excluir (String email) throws Exception
    {
        if (!cadastradoEmail(email))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM UsuarioMaligno WHERE email='"+email+"'";

            DAOs.getBD().prepareStatement (sql);

            DAOs.getBD().executeUpdate ();
            DAOs.getBD().commit        ();        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir usuario");
        }
    }

    public void alterar (String email, Usuario usuario) throws Exception
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
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados de usuario");
        }
    }
    
    public void alterar (String email, String senha, String emailNovo, boolean ehSenha) throws Exception // quando apenas altera o preço
    {
        if (!cadastradoEmail(email))
            throw new Exception ("Nao cadastrado");

        try
        {
            if (ehSenha){
                String sql;

                sql = "UPDATE UsuarioMaligno SET senha='"+senha+"' WHERE email='"+email+"'";

                DAOs.getBD().prepareStatement (sql);

                DAOs.getBD().executeUpdate ();
                DAOs.getBD().commit        ();
            }
            else{
                String sql;

                sql = "UPDATE UsuarioMaligno SET email='"+emailNovo+"' WHERE email='"+email+"'";

                DAOs.getBD().prepareStatement (sql);

                DAOs.getBD().executeUpdate ();
                DAOs.getBD().commit        ();
            }
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

    public MeuResultSet getUsuarios () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * FROM UsuarioMaligno";

            DAOs.getBD().prepareStatement (sql);

            resultado = (MeuResultSet)DAOs.getBD().executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar usuarios");
        }

        return resultado;
    }
}