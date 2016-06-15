/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.ia.previsaoJogos;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author andrel
 */
public class Selecao {
    private List <Jogador> jogadores;
    private String nome;
    private String ano;
    private List <Jogo> jogos;

    public Selecao(String nome, String ano) {
        this.nome = nome;
        this.ano = ano;
        jogadores = new LinkedList<Jogador>();
        jogos = new LinkedList<Jogo>();
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
        
}
