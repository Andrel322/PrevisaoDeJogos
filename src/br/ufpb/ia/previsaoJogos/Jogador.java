/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.ia.previsaoJogos;

/**
 *
 * @author andrel
 */
public class Jogador {
    
    private String nome;
    private String time;
    private String numero;
    private String posicao;

    public Jogador(String numero, String posicao, String nome, String time) {
        this.numero = numero;
        this.posicao = posicao;
        this.nome = nome;
        this.time = time;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
}
