package com.jdbc;

public class DAOs
{
    private static MeuPreparedStatement bd;

    private static Usuarios usuarios;
    //um como esse para cada classe DAO

    static
    {
        try
        {
            DAOs.bd = new MeuPreparedStatement (
                      "com.microsoft.sqlserver.jdbc.SQLServerDriver",
                      "jdbc:sqlserver://regulus:1433;databasename=BD15173", // XX eh seu RA
                      "BD15173", "BD15173");

            DAOs.usuarios = new Usuarios ();
            //um como esse para cada classe DAO
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }
    }

    public static MeuPreparedStatement getBD ()
    {
        return DAOs.bd;
    }

    public static Usuarios getUsuarios ()
    {
        return DAOs.usuarios;
    }
    //um como esse para cada classe DAO
}