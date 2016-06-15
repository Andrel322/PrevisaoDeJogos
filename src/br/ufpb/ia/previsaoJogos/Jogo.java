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
public class Jogo {
    private String selecao;
    private String estadio;
    private String data;

    public Jogo(String selecao, String estadio, String data) {
        this.selecao = selecao;
        this.estadio = estadio;
        this.data = data;
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
}
