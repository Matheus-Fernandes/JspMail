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
    public List<Mensagem> getCaixaEntrada(String email){
        List<Mensagem> mensagens = new ArrayList<>();
        
        mensagens.add(new Mensagem(email, "", "Formulário de inscrição - DevCamp", ""));
        mensagens.add(new Mensagem(email, "", "Parabéns, temos um presente pra você", ""));
        mensagens.add(new Mensagem(email, "", "Conta de luz", ""));
        mensagens.add(new Mensagem(email, "", "Orçamento de gesso - resposta", ""));
        mensagens.add(new Mensagem(email, "", "Bom diaaaa", ""));
        mensagens.add(new Mensagem(email, "", "Formulário de inscrição - DevCamp", ""));
        mensagens.add(new Mensagem(email, "", "Parabéns, temos um presente pra você", ""));
        mensagens.add(new Mensagem(email, "", "Conta de luz", ""));
        mensagens.add(new Mensagem(email, "", "Orçamento de gesso - resposta", ""));
        
        return mensagens;
    }
}
