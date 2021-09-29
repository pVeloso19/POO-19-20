package Views;

import Controllers.I_Controllers;

import java.util.ArrayList;
import java.util.List;

public class View_Sistema {

    /**
     * Variaveis Instancia
     */
    private I_Controllers controller;

    /**
     * Construtor Parametrizado de View_Historico
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public View_Sistema(I_Controllers controller){
        this.controller = controller;
    }

    /**
     * Apresenta no ecra o menu da seccao Sistema
     */
    private String showMenu(){
        String opcao = "";

        System.out.println("Bem vindo ao Sistema");
        System.out.println("1-> Total faturado por uma empresa (loja/Transportadora).");
        System.out.println("2-> Top 10 utilizadores.");
        System.out.println("3-> Top 10 empresas transportadoras.");
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
            opcao = this.showMenu();
            switch (opcao){
                case "1" :{
                    List<String> l = new ArrayList<>();
                    l.add("Faturado");
                    System.out.println("Insira o codigo:");
                    l.add(LeituraDados.lerString());

                    this.controller.processa(l);
                    break;
                }

                case "2" :{
                    List<String> l = new ArrayList<>();
                    l.add("10Utilizadores");
                    this.controller.processa(l);
                    break;
                }

                case "3" :{
                    List<String> l = new ArrayList<>();
                    l.add("10Transportadoras");
                    this.controller.processa(l);
                    break;
                }

                case "S" :{
                    break;
                }

                default:{
                    System.out.println("Opção Inválida.");
                    break;
                }
            }
        }
        while (!opcao.equals("S"));
    }
}
