package com.jdbc;

public class Usuario
{    
    private String email;
    private String senha;
  
        public Usuario (String email, String senha) throws Exception
    {
        this.email = email;
        this.senha = senha;
    }
    
    public Usuario(){
        
    }

    public void setEmail (String email) throws Exception
    {
        this.email = email;
    }
    
    public void setSenha (String senha) throws Exception
    {
        this.senha = senha;
    }

    public String getEmail ()
    {
        return this.email;
    }

    public String getSenha ()
    {
        return this.senha;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", senha=" + senha+  "}";
    } 
}