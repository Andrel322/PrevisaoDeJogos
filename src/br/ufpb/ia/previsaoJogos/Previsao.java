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

    private static List<Selecao> selecoes = new LinkedList<Selecao>(); // Variavel geral para guarda o historico das seleçoes selecionadas.

    public static void main(String[] args) {
        String nomeA = JOptionPane.showInputDialog("Qual a seleçao?");
        if (nomeA != null) {
            nomeA = lerSelecao(nomeA);
        } else {
            System.out.println("Saindo do App");
            System.exit(0);
        }
        String anoA = JOptionPane.showInputDialog("Qual o ano da selecao?");
        Selecao selecaoA = new Selecao(nomeA, anoA);
        String nomeB = JOptionPane.showInputDialog("Qual seleçao a enfrentar?");
        if (nomeB != null) {
            nomeB = lerSelecao(nomeB);
        } else {
            System.out.println("Saindo do App");
            System.exit(0);
        }
        String anoB = JOptionPane.showInputDialog("Qual o ano da selecao?");
        Selecao selecaoB = new Selecao(nomeB, anoB);
        String estadio = JOptionPane.showInputDialog("Qual o estadio do jogo?");
        getPrevisao(selecaoA, selecaoB, estadio);
    }

    //Funçao que le o arquivo da selecao passada e guarda seu historico na variavel geral de Seleçoes.
    public static String lerSelecao(String nomeSelecao) {
        String linha; // Variavel para capturar as linhas do arquivo;
        String nome = null; // Variavel para capturar nome da seleçao.
        String ano; // Variavel para capturar ano da seleçao.
        String data; // Variavel para capturar data do jogo.
        String selecaoA; //  Variavel para capturar a primeira seleçao do placar
        String selecaoB; // Variavel para capturar a segunda seleçao do placar
        String placar; // Variavel para capturar o placar completo (com as seleçoes e resultado) do jogo
        String estadio;// Variavel para capturar o nome do estadio do jogo
        BufferedReader arq; // Variavel Buffer para o arquivo
        StringTokenizer token; // Variavel Token para uso do arquivo em geral.
        StringTokenizer tokenNome; // Variavel Token para organizar nome do arquivo.
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
                    selecao.getJogos().add(jogo);
                }
                selecoes.add(selecao);
            }
            arq.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Previsao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Previsao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nome;
    }

    //Funçao para gerar a previsao entre a selecao "A" e selecao "B" usando os jogos anteriores, com os jogadores e estadio.
    public static void getPrevisao(Selecao selecaoA, Selecao selecaoB, String estadio) {
        double porceA, porceB, porceS, resul;
        double maior = 0;
        Jogo jogoEscolhido = null;
        String ano = null;
        List<Selecao> selecoesA = buscaSelecaoNome(selecaoA.getNome());
        List<Selecao> selecoesB = buscaSelecaoNome(selecaoB.getNome());
        for (Selecao selecao : selecoesA) {
            for (Jogo jogo : selecao.getJogos()) {
                if (jogo.getSelecao().equalsIgnoreCase(selecaoB.getNome())) {
                    porceA = compararJogadores(selecao, selecaoA);
                    porceB = compararJogadores(buscaSelecaoAno(selecoesB, selecao.getAno()), selecaoB);
                    if(jogo.getEstadio().toLowerCase().contains(estadio.toLowerCase())){
                        porceS = 100.0;
                    }else{
                        porceS = 0;
                    }
                    resul = (double) (porceA+porceB+porceS)/3;
                    if (maior < resul) {
                        maior = resul;
                        jogoEscolhido = jogo;
                        ano = selecao.getAno();
                        
                    }
                }
            }
        }
       JOptionPane.showMessageDialog(null,maior + "% = " + "Placar: " + jogoEscolhido.getPlacar() + " Campeao: " + jogoEscolhido.getResultado());
    }

    //Funçao que busca uma selecao no geral e retorna uma lista com ela em todas as copas.
    public static List<Selecao> buscaSelecaoNome(String name) {
        List<Selecao> hSelecao = new LinkedList<Selecao>();
        for (int i = 0; i < selecoes.size(); i++) {
            if (name.equalsIgnoreCase(selecoes.get(i).getNome())) {
                hSelecao.add(selecoes.get(i));
            }
        }
        return hSelecao;
    }

    //Funçao que busca a seleçao exata do ano passado, em uma lista passada.
    public static Selecao buscaSelecaoAno(List<Selecao> selecoes, String ano) {
        for (Selecao selecao : selecoes) {
            if (selecao.getAno().equals(ano)) {
                return selecao;
            }
        }
        return null;
    }

    /**
     * Funçao que compara os jogadores de duas seleçoes, retornando a porcentagem de jogadores contidos.
     * Usado para comparar a seleçao selecionada com seu historico.
     **/
    public static double compararJogadores(Selecao selecaoA, Selecao selecaoB) {
        int i = 0;
        double result;
        List<Jogador> jogadoresA = selecaoA.getJogadores();
        for (Jogador jogadorA : jogadoresA) {
            for (Jogador jogadorB : selecaoB.getJogadores()) {
                if (jogadorA.getNome().equals(jogadorB.getNome())) {
                    i++;
                    break;
                }
            }
        }
        result = (double) i / jogadoresA.size();
        return (double) result * 100;
    }
}
