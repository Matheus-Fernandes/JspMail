package com.jdbc;

public class Email
{

    @Override
    public String toString() {
        return "Email: { email principal="+this.emailPrincipal+", "
                + "outro email="+this.outroEmail+", "
                + "senha="+this.senha+", "
                + "servidor de recebimento=" + this.servidorRecebimento+", "
                + "porta de recebimento="+this.portaRecebimento+", "
                + "servidor de envio="+this.servidorEnvio+", "
                + "porta de envio="+this.portaEnvio+"}";
    }
    
    private String emailPrincipal;
    private String outroEmail;
    private String senha;
    private String servidorRecebimento;
    private int    portaRecebimento;
    private String servidorEnvio;
    private int    portaEnvio;

    public String getServidorRecebimento() {
        return servidorRecebimento;
    }

    public void setServidorRecebimento(String servidor) {
        this.servidorRecebimento = servidor;
    }
    
    public String getServidorEnvio() {
        return servidorEnvio;
    }

    public void setServidorEnvio(String servidor) {
        this.servidorEnvio = servidor;
    }

    public int getPortaRecebimento() {
        return portaRecebimento;
    }

    public void setPortaRecebimento(int porta) {
        this.portaRecebimento = porta;
    }
    
    public int getPortaEnvio() {
        return portaEnvio;
    }

    public void setPortaEnvio(int porta) {
        this.portaEnvio = porta;
    }
  

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

    public Email (String principal, String email, String senha, String serverRecebimento, int portaRecebimento, String serverEnvio, int portaEnvio) throws Exception
    {
        this.setEmailPrincipal(principal);
        this.setOutroEmail (email);
        this.setSenha (senha);
        this.setServidorRecebimento(serverRecebimento);
        this.setPortaRecebimento(portaRecebimento);
        this.setServidorEnvio(serverEnvio);
        this.setPortaEnvio(portaEnvio);
    }
    
    public Email () throws Exception
    {
        
    }
    
    // � claro que os m�todos obrigat�rios deveriam ser feitos
    // para a implementa��o ficar completa
}