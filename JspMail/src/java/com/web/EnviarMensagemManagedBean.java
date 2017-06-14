package com.web;


public class EnviarMensagemManagedBean 
{
    public String[] gerarVetorDestinatario(String destinatarios)
    {
        String[] vet = destinatarios.split(",");
        
        for (String each : vet)
            each = each.trim();
        
        return vet;
    }
}
