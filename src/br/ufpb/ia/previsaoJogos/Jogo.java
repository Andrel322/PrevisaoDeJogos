/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.ia.previsaoJogos;

import java.util.StringTokenizer;

/**
 *
 * @author andrel
 */
public class Jogo {
    private String selecao;
    private String estadio;
    private String data;
    private String placar;
    private String resultado;

    public Jogo(String data, String selecao, String placar, String estadio) {
        this.selecao = selecao;
        this.estadio = estadio;
        this.data = data;
        this.placar = placar;
        this.resultado = getVencedor(placar);
    }

    public String getPlacar() {
        return placar;
    }

    public void setPlacar(String placar) {
        this.placar = placar;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getSelecao() {
        return selecao;
    }

    public void setSelecao(String selecao) {
        this.selecao = selecao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }
    
    public static String getVencedor(String placar){
        StringTokenizer token = new StringTokenizer(placar);
        String selecaoA = token.nextToken();
        String resul = token.nextToken();
        char resulA = resul.charAt(0);
        char resulB = resul.charAt(2);
        String selecaoB = token.nextToken();
        if((int) resulA > (int) resulB){
            return selecaoA;
        }
        else if((int) resulA == (int) resulB){
            return "Empate";
        }
        return selecaoB;
    }
}