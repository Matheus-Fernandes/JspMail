package br.cotuca.unicamp;

public class Usuario
{

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", senha=" + senha+  "}";
    }
    
    private String email;
    private String senha;
  

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

    public Usuario (String email, String senha) throws Exception
    {
        this.setEmail (email);
        this.setSenha (senha);
    }

    // � claro que os m�todos obrigat�rios deveriam ser feitos
    // para a implementa��o ficar completa
}