package Views.Loja;

import Controllers.I_Controllers;
import Views.LeituraDados;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class View_AceitarPedidos {

    /**
     * Variaveis Instancia
     */
    private List<String> listaPedidos;
    private I_Controllers controller;

    /**
     * Construtor Parametrizado de View_AceitarPedidos
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public View_AceitarPedidos(List<String> listaPedidos, I_Controllers controllers){
        this.listaPedidos = listaPedidos;
        this.controller = controllers;
    }

    /**
     * Devolve o resultado de andar com o indice de uma pagina
     * para a frente
     *
     * @param index correspondente ao indice
     * @param totalPaginas correspondente a um total de paginas
     * @return indice incrementado
     */
    private int avancaPagina(int index, int totalPaginas){
        if(index < totalPaginas-1) index++;
        return index;
    }

    /**
     * Devolve o resultado de andar uma pagina para tras
     *
     * @param index correspondente ao indice
     * @return indice decrementado
     */
    private int recuaPagina(int index){
        if(index > 0) index--;
        return index;
    }

    /**
     * Apresenta no ecra as opcoes da Loja na seccao de Aceitar Pedidos
     *
     * @param totalPaginas representa total de paginas
     * @param paginaAtual representa a pagina atual
     */
    private void showOpcoes(int totalPaginas, int paginaAtual){
        if(totalPaginas<=1){
            System.out.println("Insira: Press A para aceitar pedido | S sair");
        }
        else {
            if(paginaAtual == 1){
                System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | Press A para aceitar pedido | S sair");
            }
            else{
                if(paginaAtual==totalPaginas){
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| - página anterior | Press A para aceitar pedido | S sair");
                }
                else{
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | - página anterior | Press A para aceitar pedido | S sair");
                }
            }
        }
    }

    /**
     * Apresenta no ecra o menu da seccao de Aceitar Pedidos
     *
     * @param index correspondente ao indice
     * @param tamPag correspondente ao tamanho da pagina
     * @param elem correspondente aos elementos
     */
    private void showMenu(int index, int tamPag, int elem){
        int pos = (index*tamPag);
        for (int i=0; i<tamPag; i++){
            if(pos<elem){
                System.out.println(this.listaPedidos.get(pos));
                pos++;
            }else{
                System.out.println("---");
            }
        }
    }

    /**
     * Funcao que corre a view com todas as funcoes anterioes, de maneira
     * a interligar os diferentes processos
     */
    public void run(){
        String opcao;
        int index = 0;
        int tamPag = 8;
        int elem = this.listaPedidos.size();
        int totalPaginas = (elem<8)?1:((elem%8==0)?elem/8:(elem/8)+1);

        do {
            this.showMenu(index,tamPag,elem);
            this.showOpcoes(totalPaginas,index+1);
            opcao = LeituraDados.lerString();
            opcao = opcao.toUpperCase();

            switch (opcao){
                case "+" :{
                    index = this.avancaPagina(index,totalPaginas);
                    break;
                }

                case "-" :{
                    index = this.recuaPagina(index);
                    break;
                }

                case "S" :{
                    opcao = "S";
                    break;
                }

                case "A" :{
                    System.out.println("Insira o indice da encomenda a aceitar.");
                    try {
                        List<String> l = new ArrayList<>();
                        l.add("Aceitar");
                        l.add(LeituraDados.lerIntAnteriorComoString());
                        this.controller.processa(l);
                    }catch (InputMismatchException e){
                        System.out.println("Posição invalida.");
                    }
                    opcao = "S";
                    break;
                }
            }

        }
        while (!opcao.equals("S"));
    }
}
