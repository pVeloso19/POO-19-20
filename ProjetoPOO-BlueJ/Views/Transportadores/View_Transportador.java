package Views.Transportadores;

import Controllers.I_Controllers;
import Models.Transportadores.I_Transportadores;
import Models.Transportadores.Transportadora;
import Models.TrazAqui;
import Models.Transportadores.Voluntario;
import Views.LeituraDados;

import java.util.ArrayList;
import java.util.List;

public class View_Transportador {

    /**
     * Variaveis Instancia
     */
    private I_Controllers controller;

    private TrazAqui sistema;
    private I_Transportadores transportadore;
    private String nome;
    private String cod;

    private boolean estado;
    private boolean ocupado;

    /**
     * Construtor Parametrizado de View_Transportador
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public View_Transportador(TrazAqui s, String cod, I_Controllers controller){
        this.sistema = s;
        this.transportadore = s.getTransportador(cod);
        this.controller = controller;
        this.escolheTipoTransportadore();
        this.cod = cod;
    }

    /**
     * Funcao que descobre se se trata de uma transportadora ou voluntario, e conforme
     * o caso ira alterar as informacoes das variaveis de instancia
     */
    private void escolheTipoTransportadore(){
        if(this.transportadore instanceof Voluntario){
            Voluntario v = (Voluntario) this.transportadore;
            this.nome = v.getNomeVoluntario();
        }

        if(this.transportadore instanceof Transportadora){
            Transportadora t = (Transportadora) this.transportadore;
            this.nome = t.getNomeEmpresa();
        }
    }

    /**
     * Recarrega as informacoes nas variaveis de instancia caso seja Voluntario
     */
    private void rellodValoresVoluntario() {
        this.transportadore = sistema.getTransportador(this.cod);
        Voluntario v = (Voluntario) this.transportadore;
        this.estado = v.getDisponivel();
        this.ocupado = !v.getEncomendaAtual().equals("None");
    }

    /**
     * Recarrega as informacoes nas variaveis de instancia caso seja Transportadora
     */
    private void rellodValoresTransportadora() {
        this.transportadore = sistema.getTransportador(this.cod);
        Transportadora t = (Transportadora) this.transportadore;
        this.estado = t.getDisponivel();
        this.ocupado = !t.getCodEncomendaAtual().equals("None");
    }

    /**
     * Apresenta o menu caso seja Voluntario
     *
     * @return input do Voluntario com base nas informacoes que lhe foram apresentadas no ecra
     */
    private String showMenuVoluntario(){
        String opcao = "";

        System.out.println("Bem Vindo : " + this.nome + "   Disponivel? "+this.estado+"   Ocupado? "+this.ocupado+"\n");
        System.out.println("1 -> Ver o historico de encomendas aceites.");
        if(!this.ocupado) System.out.println("2 -> Mudar Estado.");
        if(this.estado && !this.ocupado) System.out.println("A -> Pedir Encomenda para entrega.");
        if(this.ocupado) System.out.println("F -> Finalizar entrega.");
        System.out.println("V -> Mudar Valores.");
        System.out.println(" ");
        System.out.println("S-> Sair");

        opcao = LeituraDados.lerString();
        return opcao.toUpperCase();
    }

    /**
     * Funcao que corre a view com todas as funcoes anterioes, de maneira
     * a interligar os diferentes processos, caso seja Voluntario
     */
    private void runVoluntario(){
        String opcao;
        do {
            this.rellodValoresVoluntario();
            opcao = this.showMenuVoluntario();

            if(opcao.equals("A") && this.ocupado) opcao = "I";
            if(opcao.equals("2") && this.ocupado) opcao = "I";
            if(opcao.equals("F") && !this.ocupado) opcao = "I";

            List<String> l = new ArrayList<>();
            switch (opcao){
                case "1" :{
                    l.add("Historico");
                    this.controller.processa(l);
                    break;
                }

                case "2" :{
                    l.add("MudarEstado");
                    this.controller.processa(l);
                    break;
                }

                case "A" :{
                    l.add("PedirEncomenda");
                    this.controller.processa(l);
                    break;
                }

                case "F" :{
                    l.add("Finaliza");
                    this.controller.processa(l);
                    System.out.println("Encomenda Entregue com sucesso.");
                    break;
                }

                case "V" :{
                    l.add("MudarValores");
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

    /**
     * Apresenta o menu caso seja Transportadora
     *
     * @return input da Transportadora com base nas informacoes que lhe foram apresentadas no ecra
     */
    private String showMenuTransportadora(){
        String opcao = "";

        System.out.println("Bem Vindo : " + this.nome + "   Disponivel? "+this.estado+"   Ocupado? "+this.ocupado+"\n");
        System.out.println("1 -> Ver o historico de encomendas aceites.");
        if(!this.ocupado) System.out.println("2 -> Mudar Estado.");
        if(this.estado && !this.ocupado) System.out.println("A -> Pedir encomenda para entrega.");
        if(this.ocupado && !this.estado) System.out.println("F -> Finalizar entrega.");
        System.out.println("V -> Mudar Valores.");
        System.out.println(" ");
        System.out.println("S-> Sair");

        opcao = LeituraDados.lerString();
        return opcao.toUpperCase();
    }

    /**
     * Funcao que corre a view com todas as funcoes anterioes, de maneira
     * a interligar os diferentes processos, caso seja Transportadora
     */
    private void runTransportadora(){
        String opcao;
        do {
            this.rellodValoresTransportadora();
            opcao = this.showMenuTransportadora();

            if(opcao.equals("A") && this.ocupado) opcao = "I";
            if(opcao.equals("2") && this.ocupado) opcao = "I";
            if(opcao.equals("F") && !this.ocupado && this.estado) opcao = "I";

            List<String> l = new ArrayList<>();
            switch (opcao){
                case "1" :{
                    l.add("Historico");
                    this.controller.processa(l);
                    break;
                }

                case "2" :{
                    l.add("MudarEstado");
                    this.controller.processa(l);
                    break;
                }

                case "A" :{
                    l.add("PedirEncomenda");
                    this.controller.processa(l);
                    break;
                }

                case "F" :{
                    l.add("Finaliza");
                    this.controller.processa(l);
                    System.out.println("Encomenda Entregue com sucesso.");
                    break;
                }

                case "V" :{
                    l.add("MudarValores");
                    this.controller.processa(l);
                    break;
                }

                case "S" :{
                    break;
                }

                default:{
                    System.out.println("Opçao Inválida.");
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
        if(this.transportadore instanceof Transportadora) runTransportadora();
        if(this.transportadore instanceof Voluntario) runVoluntario();
    }
}
