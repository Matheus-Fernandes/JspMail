/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import com.model.Mensagem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class DashboardManagedBean {
    private List<Mensagem> mensagens;
    private final int MENSAGENS_PAGINA = 19;
    
    public DashboardManagedBean(){
        mensagens = new ArrayList<>();
        
        mensagens.add(new Mensagem(1, "email", "", "Formulário de inscrição - DevCamp", "Olá, por favor confirme a inscrição em www.devcamp.com.br"));
        mensagens.add(new Mensagem(2, "email", "", "Parabéns, temos um presente pra você", ""));
        mensagens.add(new Mensagem(3, "email", "", "Conta de luz", ""));
        mensagens.add(new Mensagem(4, "email", "", "Orçamento de gesso - resposta", ""));
        mensagens.add(new Mensagem(5, "email", "", "Bom diaaaa", ""));
        mensagens.add(new Mensagem(6, "email", "", "Formulário de inscrição - DevCamp", ""));
        mensagens.add(new Mensagem(7, "email", "", "Parabéns, temos um presente pra você", ""));
        mensagens.add(new Mensagem(8, "email", "", "Conta de luz", ""));
        mensagens.add(new Mensagem(9, "email", "", "Orçamento de gesso - resposta", ""));
        mensagens.add(new Mensagem(10, "email", "", "Parabéns, temos um presente pra você", ""));
        mensagens.add(new Mensagem(11, "email", "", "Conta de luz", ""));
        mensagens.add(new Mensagem(12, "email", "", "Orçamento de gesso - resposta", ""));
        mensagens.add(new Mensagem(13, "email", "", "Bom diaaaa", ""));
        mensagens.add(new Mensagem(14, "email", "", "Formulário de inscrição - DevCamp", ""));
        mensagens.add(new Mensagem(15, "email", "", "Parabéns, temos um presente pra você", ""));
        mensagens.add(new Mensagem(16, "email", "", "Conta de luz", ""));
        mensagens.add(new Mensagem(17, "email", "", "Orçamento de gesso - resposta", ""));
        mensagens.add(new Mensagem(18, "email", "", "Parabéns, temos um presente pra você", ""));
        mensagens.add(new Mensagem(19, "email", "", "Conta de luz", ""));
        mensagens.add(new Mensagem(20, "email", "", "Orçamento de gesso - resposta", ""));
        mensagens.add(new Mensagem(21, "email", "", "Parabéns, temos um presente pra você", ""));
        mensagens.add(new Mensagem(22, "email", "", "Conta de luz", ""));
        mensagens.add(new Mensagem(23, "email", "", "Orçamento de gesso - resposta", ""));
        mensagens.add(new Mensagem(24, "email", "", "Bom diaaaa", ""));
        mensagens.add(new Mensagem(25, "email", "", "Formulário de inscrição - DevCamp", ""));
        mensagens.add(new Mensagem(26, "email", "", "Parabéns, temos um presente pra você", ""));
        mensagens.add(new Mensagem(27, "email", "", "Conta de luz", ""));
        mensagens.add(new Mensagem(28, "email", "", "Orçamento de gesso - resposta", ""));
    }
    public List<Mensagem> getCaixaEntrada(String email){
        return this.mensagens;
    }
    
    public List<Mensagem> getCaixaEntrada(String email, int pagina){
        List<Mensagem> retorno = new ArrayList<Mensagem>();
        
        if (pagina < 0)
            return retorno;
        
        int inicio = pagina * MENSAGENS_PAGINA;
        int fim = inicio + MENSAGENS_PAGINA;
        if (fim > mensagens.size())
            fim = mensagens.size();
        
        for (int i = inicio; i < fim; i++){
            retorno.add(this.mensagens.get(i));
        }
        
        return retorno;
    }
    
    public Mensagem getMensagem(int id){
        for (Mensagem mensagem : this.mensagens){
            if (mensagem.getId() == id)
                return mensagem;
        }
        
        return null;
    }
    
    public int countMensagens(){
        return mensagens.size();
    }
    
    public boolean paginaValida(int pagina){
        int inicio = pagina * MENSAGENS_PAGINA + 1;
        
        return pagina >= 0 && inicio <= mensagens.size();
    }
    
    public int inicio(int pagina){
        int inicio = pagina * MENSAGENS_PAGINA + 1;
        
        if (inicio > mensagens.size())
            return mensagens.size();
        
        return inicio;
    }
    
    public int fim (int pagina){
        int fim = inicio(pagina) + MENSAGENS_PAGINA - 1;;
        
        if (fim > mensagens.size())
            return mensagens.size();
        
        return fim;
    }
}
