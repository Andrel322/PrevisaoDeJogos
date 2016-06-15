/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.ia.previsaoJogos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author andrel
 */
public class Previsao {
    private static List <Selecao> selecoes = new LinkedList <Selecao>();

    public static void main(String[] args) {
        String selecao = JOptionPane.showInputDialog("Qual a seleçao?");
        if (selecao != null) {
            lerSelecao(selecao);
        } else {
            System.out.println("Saindo do App");
        }
    }

    public static void lerSelecao(String nomeSelecao) {
        String linha;
        String nome;
        String ano;
        BufferedReader arq;
        StringTokenizer token;
        try {
            arq = new BufferedReader(new FileReader("./selecao/" + nomeSelecao.toLowerCase() + ".txt"));
            linha = arq.readLine();
            token = new StringTokenizer(linha);
            nome = token.nextToken();
            arq.readLine();

            while(arq.ready()) {
                linha = arq.readLine();
                token = new StringTokenizer(linha);
                ano = token.nextToken();
                Selecao selecao = new Selecao(nome, ano);
                while((linha = arq.readLine()).length() > 1 && arq.ready()){
                    token= new StringTokenizer(linha);
                    Jogo jogo;
                }
            }
            arq.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Previsao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Previsao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
