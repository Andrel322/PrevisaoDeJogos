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
    private static List<Selecao> selecoes = new LinkedList<Selecao>();
    private static List<Selecao> historico = new LinkedList<Selecao>();

    public static void main(String[] args) {
        String nomeA = JOptionPane.showInputDialog("Qual a seleçao?");
        if (nomeA != null) {
            lerSelecao(nomeA);
        } else {
            System.out.println("Saindo do App");
            System.exit(0);
        }
        String nomeB = JOptionPane.showInputDialog("Qual seleçao a enfrentar?");
        String anoB = JOptionPane.showInputDialog("Qual o ano da selecao?");
        Selecao selecaoB = new Selecao(nomeB, anoB);
    }

    public static void lerSelecao(String nomeSelecao) {
        String linha;
        String nome; // Variavel para capturar nome da seleçao.
        String ano; // Variavel para capturar ano da seleçao.
        String data; // Variavel para capturar data do jogo.
        String selecaoA; //  Variavel para capturar a primeira seleçao do placar
        String selecaoB; // Variavel para capturar a segunda seleçao do placar
        String placar; // Variavel para capturar o placar completo (com as seleçoes e resultado) do jogo
        String estadio;// Variavel para capturar o nome do estadio do jogo
        BufferedReader arq;
        StringTokenizer token;
        StringTokenizer tokenNome;
        try {
            tokenNome = new StringTokenizer(nomeSelecao);
            nomeSelecao = tokenNome.nextToken();
            while (tokenNome.hasMoreTokens()) {
                nomeSelecao += tokenNome.nextToken();
            }
            arq = new BufferedReader(new FileReader("./selecao/" + nomeSelecao.toLowerCase() + ".txt"));
            linha = arq.readLine();
            token = new StringTokenizer(linha);
            nome = token.nextToken();
            arq.readLine();

            while (arq.ready()) {
                linha = arq.readLine();
                token = new StringTokenizer(linha);
                ano = token.nextToken();
                Selecao selecao = new Selecao(nome, ano);
                selecao.lerJogadores(nomeSelecao);
                System.out.println(ano);
                while ((linha = arq.readLine()).length() > 1 && arq.ready()) {
                    token = new StringTokenizer(linha, " @");
                    token.nextToken();
                    data = token.nextToken() + " " + token.nextToken();
                    selecaoA = token.nextToken();
                    placar = selecaoA + " " + token.nextToken();
                    selecaoB = token.nextToken();
                    if (selecaoA.equalsIgnoreCase(nome)) {
                        selecaoA = selecaoB;
                    }
                    placar += " " + selecaoB;
                    estadio = token.nextToken();
                    estadio += token.nextToken("");
                    Jogo jogo = new Jogo(data, selecaoA, placar, estadio);
                    System.out.println("Data: " + data + " seleçaoVs: " + selecaoA + " placar: " + placar + " vencedor:  " + jogo.getResultado() + " estadio: "+ estadio);
                    selecao.getJogos().add(jogo);
                }
            }
            arq.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Previsao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Previsao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List getJogosComB(String nomeA, String nomeB){
        List <Selecao> selecoesA = buscaSelecao(nomeA);
        List <Jogo> jogosB = new LinkedList<Jogo>();
        for(Selecao selecao: selecoesA){
            for(Jogo jogo: selecao.getJogos()){
                if(nomeB.equalsIgnoreCase(jogo.getSelecao())){
                    jogosB.add(jogo);
                }
            }
        }
        return jogosB;
    }
    
    public List buscaSelecao(String name){
        List <Selecao> hSelecao = new LinkedList <Selecao>();
        for(Selecao selecao: selecoes){
            if(name.equalsIgnoreCase(selecao.getNome())){
                hSelecao.add(selecao);
            }
        }
        return hSelecao;
    }
}
