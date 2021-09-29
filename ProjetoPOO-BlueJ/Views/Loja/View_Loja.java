package Views.Loja;

import Controllers.I_Controllers;
import Models.Loja.Loja;
import Models.Loja.LojaComFilasEspera;
import Models.TrazAqui;
import Views.LeituraDados;

import java.util.ArrayList;
import java.util.List;

public class View_Loja {

    /**
     * Variaveis Instancia
     */
    private I_Controllers controller;
    private int listaEspera;

    private TrazAqui sistema;
    private Loja loja;
    private String cod;
    private String nome;

    /**
     * Construtor Parametrizado de View_Loja
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public View_Loja(I_Controllers controller,TrazAqui s, String cod, String nome){
        this.controller = controller;
        this.sistema = s;
        this.loja = this.sistema.getLoja(cod);
        this.listaEspera = this.loja.getListaEspera().size();
        this.nome = nome;
        this.cod = cod;
    }

    /**
     * Recarrega as informacoes nas variaveis de instancia da Loja
     */
    private void relload(){
        this.loja = sistema.getLoja(this.cod);
        this.listaEspera = this.loja.getListaEspera().size();
    }

    /**
     * Apresenta no ecra o menu principal da Loja
     */
    private String showMenu(){
        String opcao = "";

        System.out.println("Bem Vindo : " + this.nome + "\n");
        System.out.println("1-> Adicionar Produto.");
        System.out.println("2-> Remover Produto.");
        System.out.println("3-> Estatisticas Da Loja.");
        if(this.listaEspera>0) System.out.println("4-> Pedidos Pendentes ("+this.listaEspera+").");
        if(this.loja instanceof LojaComFilasEspera) System.out.println("5-> Indicar pessoas na fila.");
        System.out.println(" ");
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

            if(opcao.equals("4") && this.listaEspera<=0) opcao = "I";
            if(opcao.equals("5") && !(this.loja instanceof LojaComFilasEspera)) opcao = "I";

            switch (opcao){
                case "1" :{
                    List<String> l = new ArrayList<>();
                    System.out.println("Insira o nome do Produto:");
                    String nome = LeituraDados.lerString();
                    System.out.println("Insira o peso do Produto:");
                    String peso = LeituraDados.lerDoubleComoString();
                    System.out.println("Insira o preço do Produto:");
                    String preco = LeituraDados.lerDoubleComoString();
                    System.out.println("O Produto é um medicamento: (S/N)");
                    String ismedicamento = LeituraDados.lerString();
                    boolean isMedicamento = ismedicamento.toUpperCase().equals("S");

                    if(isMedicamento){
                        l.add("AdicionarM");
                        l.add(nome);
                        l.add(peso);
                        l.add(preco);
                        this.controller.processa(l);
                    }
                    else{
                        l.add("Adicionar");
                        l.add(nome);
                        l.add(peso);
                        l.add(preco);
                        this.controller.processa(l);
                    }
                    break;
                }

                case "2" :{
                    List<String> l = new ArrayList<>();
                    l.add("RemoverP");
                    this.controller.processa(l);
                    break;
                }

                case "3" :{
                    List<String> l = new ArrayList<>();
                    l.add("Historico");
                    this.controller.processa(l);
                    break;
                }

                case "4" :{
                    List<String> l = new ArrayList<>();
                    l.add("AceitarPedidos");
                    this.controller.processa(l);
                    break;
                }

                case "5" :{
                    List<String> l = new ArrayList<>();
                    l.add("Espera");
                    System.out.println("Numero de pessoas:");
                    l.add(LeituraDados.lerIntComoString());
                    this.controller.processa( l);
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
