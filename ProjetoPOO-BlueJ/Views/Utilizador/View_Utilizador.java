package Views.Utilizador;

import Controllers.I_Controllers;
import Models.TrazAqui;
import Models.Utilizador.Utilizador;
import Views.LeituraDados;

import java.util.ArrayList;
import java.util.List;

public class View_Utilizador {

    /**
     * Variaveis Instancia
     */
    private I_Controllers controller;
    private int notificacoes;

    private TrazAqui sistema;
    private Utilizador utilizador;
    private String nome;
    private String cod;

    /**
     * Construtor Parametrizado de View_Utilizador
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public View_Utilizador(I_Controllers controller,TrazAqui s, String cod, String nome){
        this.controller = controller;
        this.sistema = s;
        this.utilizador = s.getUtilizador(cod);
        this.notificacoes = utilizador.getNumeroNotificacaoes();
        this.nome = nome;
        this.cod = cod;
    }

    /**
     * Recarrega o menu do Utilizador ja com o seu nome e numero de notificacoes
     */
    private void relload(){
        this.utilizador = sistema.getUtilizador(this.cod);
        this.notificacoes = utilizador.getNumeroNotificacaoes();
    }

    /**
     * Apresenta o menu com informacoes importantes ao utilizador
     *
     * @return String correspondente ao que foi intruzido pelo utilizador no input
     */
    private String showMenu(){
        String opcao = "";

        System.out.println("Bem Vindo : " + this.nome + "\n");
        System.out.println("1-> Pedir Encomenda.");
        System.out.println("2-> Avaliar Voluntarios/Transportadoras.");
        System.out.println("3-> Histórico de Encomendas.");
        if(this.notificacoes>0) System.out.println("4-> Notificações ("+this.notificacoes+").");
        System.out.println("S-> Sair");

        opcao = LeituraDados.lerString();
        return opcao.toUpperCase();
    }

    /**
     * Funcao que corre a view com todas as funcoes anterioes, de maneira
     * a interligar os diferentes processos
     */
    public void run(){
        String opcao;
        do {
            this.relload();
            opcao = this.showMenu();

            if(opcao.equals("4") && this.notificacoes<=0) opcao = "I";

            List<String> l = new ArrayList<>();
            switch (opcao){
                case "1" :{
                    l.add("CriaEncomenda");
                    this.controller.processa(l);
                    break;
                }

                case "2" :{
                    l.add("Avaliar");
                    this.controller.processa(l);
                    break;
                }

                case "3" :{
                    l.add("Historico");
                    this.controller.processa(l);
                    break;
                }

                case "4" :{
                    l.add("MensagensPendentes");
                    this.controller.processa(l);
                    break;
                }

                case "S" :{
                    break;
                }

                default:{
                    System.out.println("Opção Invalida.");
                    break;
                }
            }

        }
        while (!opcao.equals("S"));
    }
}
