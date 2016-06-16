/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.ia.previsaoJogos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrel
 */
public class Selecao {

    private List <Jogador> jogadores;
    private String nome;
    private String ano;
    private List<Jogo> jogos;

    public Selecao(String nome, String ano) {
        this.nome = nome;
        this.ano = ano;
        jogos = new LinkedList<Jogo>();
        jogadores = new LinkedList<Jogador>();
        lerJogadores();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public void lerJogadores() {
        String linha;
        BufferedReader arq;
        StringTokenizer token;
        StringTokenizer tokenNome;
        Jogador jogador;
        String nome;
        String time;
        String numero;
        String posicao;
        int numJogadores;

        try {
            arq = new BufferedReader(new FileReader("./selecao/squads/" + this.nome.toLowerCase() + "-squads.txt"));
            arq.readLine();
            arq.readLine();
            while(!ano.equals(arq.readLine()) && arq.ready()){
            }
            arq.readLine();

            while((linha = arq.readLine()).length() > 1 && arq.ready()){
                token = new StringTokenizer(linha);
                numero = token.nextToken();
                posicao = token.nextToken();
                nome = token.nextToken("#");
                time = "";
                tokenNome = new StringTokenizer(nome);
                nome = tokenNome.nextToken();
                while(tokenNome.hasMoreTokens()){
                    nome += " " + tokenNome.nextToken();
                }
                token.nextToken(" #");
                if(token.hasMoreTokens()){
                    time = token.nextToken("");
                }
                jogador = new Jogador(numero, posicao, nome, time);
                jogadores.add(jogador);
            }
            arq.close();
        } catch (IOException ex) {
            Logger.getLogger(Selecao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
