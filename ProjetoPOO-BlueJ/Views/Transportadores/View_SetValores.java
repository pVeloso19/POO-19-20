package Views.Transportadores;

import Controllers.I_Controllers;
import Models.Transportadores.I_Transportadores;
import Models.Transportadores.Transportadora;
import Models.Transportadores.Voluntario;
import Views.LeituraDados;

import java.util.ArrayList;
import java.util.List;

public class View_SetValores {

    /**
     * Variaveis Instancia
     */
    private I_Transportadores transportador;
    private I_Controllers controller;

    /**
     * Construtor Parametrizado de View_SetValores
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public View_SetValores(I_Transportadores t, I_Controllers c){
        this.transportador = t;
        this.controller = c;
    }

    /**
     * Funcao que corre a view com todas as funcoes anterioes, de maneira
     * a interligar os diferentes processos, inserindo o Voluntario
     * informacoes sobre si para ajudar em futuros calculos e decisoes
     */
    private void runVoluntario(){
        String opcao;
        do {
            double vm = this.transportador.getVelocidadeMedia();
            double r = this.transportador.getRaio();
            System.out.println("1-> Inserir nova velocidade media. ("+vm+")");
            System.out.println("2-> Inserir novo raio de acao. ("+r+")");
            System.out.println("S-> Sair.");
            opcao = LeituraDados.lerString();
            opcao = opcao.toUpperCase();

            List<String> l = new ArrayList<>();
            switch (opcao){
                case "1" :{
                    l.add("MudarVelocidade");
                    System.out.println("Insira a nova velocidade media:");
                    l.add(LeituraDados.lerDoubleComoString());
                    this.controller.processa(l);
                    opcao = "S";
                    break;
                }

                case "2" :{
                    l.add("MudarRaio");
                    System.out.println("Insira o novo raio de acao:");
                    l.add(LeituraDados.lerDoubleComoString());
                    this.controller.processa(l);
                    opcao = "S";
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

    /**
     * Funcao que corre a view com todas as funcoes anterioes, de maneira
     * a interligar os diferentes processos, inserindo a Transportadora
     * informacoes sobre si para ajudar em futuros calculos e decisoes
     */
    private void runTransportadora(){
        String opcao;
        do {
            double precoKm = this.transportador.getPrecoPorKm();
            double vm = this.transportador.getVelocidadeMedia();
            double r = this.transportador.getRaio();
            System.out.println("1-> Inserir novo preço medio por kilometro. ("+precoKm+")");
            System.out.println("2-> Inserir nova velocidade media. ("+vm+")");
            System.out.println("3-> Inserir novo raio de acao. ("+r+")");
            System.out.println("S-> Sair.");
            opcao = LeituraDados.lerString();
            opcao = opcao.toUpperCase();

            List<String> l = new ArrayList<>();
            switch (opcao){
                case "1" :{
                    l.add("MudarPreco");
                    System.out.println("Insira o novo preço:");
                    l.add(LeituraDados.lerDoubleComoString());
                    this.controller.processa(l);
                    opcao = "S";
                    break;
                }

                case "2" :{
                    l.add("MudarVelocidade");
                    System.out.println("Insira a nova velocidade media:");
                    l.add(LeituraDados.lerDoubleComoString());
                    this.controller.processa(l);
                    opcao = "S";
                    break;
                }

                case "3" :{
                    l.add("MudarRaio");
                    System.out.println("Insira o novo raio de ação:");
                    l.add(LeituraDados.lerDoubleComoString());
                    this.controller.processa(l);
                    opcao = "S";
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

    /**
     * Funcao que corre a view com todas as funcoes anterioes, de maneira
     * a interligar os diferentes processos, caso seja Transportadora ou Voluntario
     */
    public void run() {
        if(this.transportador instanceof Transportadora) runTransportadora();
        if(this.transportador instanceof Voluntario) runVoluntario();
    }
}
