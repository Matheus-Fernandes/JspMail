package com.jdbc;

public class Email
{

    @Override
    public String toString() {
        return "Email{" + "email principal=" + emailPrincipal + ", email" + outroEmail + ", senha=" + senha+  "}";
    }
    
    private String emailPrincipal;
    private String outroEmail;
    private String senha;
    private String servidor;

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }
    private int    porta;
  

    public void setEmailPrincipal (String email) throws Exception
    {
        this.emailPrincipal = email;
    }
    
    public void setOutroEmail (String email) throws Exception
    {
        this.outroEmail = email;
    }
    
    public void setSenha (String senha) throws Exception
    {
        this.senha = senha;
    }

    public String getEmailPrincipal ()
    {
        return this.emailPrincipal;
    }
    
    public String getOutroEmail ()
    {
        return this.outroEmail;
    }

    public String getSenha ()
    {
        return this.senha;
    }

    public Email (String principal, String email, String senha, String server, int porta) throws Exception
    {
        this.setEmailPrincipal(principal);
        this.setOutroEmail (email);
        this.setSenha (senha);
        this.setServidor(server);
        this.setPorta(porta);
    }
    
    public Email () throws Exception
    {
        
    }
    
    // � claro que os m�todos obrigat�rios deveriam ser feitos
    // para a implementa��o ficar completa
}