package Views.Transportadores;

import Controllers.I_Controllers;
import Views.LeituraDados;

import java.util.ArrayList;
import java.util.List;

public class View_EncomendasParaRecolha {

    /**
     * Variaveis Instancia
     */
    private List<String> encomendasParaRecolha;
    private I_Controllers controller;

    /**
     * Construtor Parametrizado de View_EncomendasParaRecolha
     * Aceita como parámetros os valores para cada Variável de Instancia
     */
    public View_EncomendasParaRecolha(List<String> l, I_Controllers controller){
        this.encomendasParaRecolha = new ArrayList<>(l);
        this.controller = controller;
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
     * Apresenta no ecra as opcoes na seccao de Encomendas para Recolher
     *
     * @param totalPaginas representa total de paginas
     * @param paginaAtual representa a pagina atual
     */
    private void showOpcoes(int totalPaginas, int paginaAtual){
        if(totalPaginas<=1){
            System.out.println("Insira: S sair | Código da encomenda a aceitar.");
        }
        else {
            if(paginaAtual == 1){
                System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | S sair | Código da encomenda a aceitar.");
            }
            else{
                if(paginaAtual==totalPaginas){
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| - página anterior | S sair | Código da encomenda a aceitar.");
                }
                else{
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | - página anterior | S sair | Código da encomenda a aceitar.");
                }
            }

        }
    }

    /**
     * Apresenta no ecra o menu de Encomendas para Recolher
     *
     * @param index correspondente ao indice
     * @param tamPag correspondente ao tamanho da pagina
     * @param elem correspondente aos elementos
     */
    private void showMenu(int index, int tamPag, int elem){
        int pos = (index*tamPag);
        for (int i=0; i<tamPag; i++){
            if(pos<elem){
                System.out.println(this.encomendasParaRecolha.get(pos));
                pos++;
            }else{
                System.out.println("---");
            }
        }
    }

    /**
     * Quando nao houver informacao a ser apresentada sao apresentados tracos no ecra
     *
     * @param tamPag correspondente ao tamanho da pagina
     */
    private void showVazio(int tamPag){
        for (int i=0; i<tamPag; i++){
            System.out.println("---");
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
        int elem = this.encomendasParaRecolha.size();
        int totalPaginas = (elem<8)?1:((elem%8==0)?elem/8:(elem/8)+1);

        if(elem==0){
            do {
                this.showVazio(tamPag);
                this.showOpcoes(totalPaginas, index + 1);
                opcao = LeituraDados.lerString();
                opcao = opcao.toUpperCase();
            }
            while (!opcao.equals("S"));
        }
        else {
            do {
                this.showMenu(index, tamPag, elem);
                this.showOpcoes(totalPaginas, index + 1);
                opcao = LeituraDados.lerString();
                String opcaoTemp = opcao.toUpperCase();

                switch (opcaoTemp) {
                    case "+": {
                        index = this.avancaPagina(index, totalPaginas);
                        break;
                    }

                    case "-": {
                        index = this.recuaPagina(index);
                        break;
                    }

                    case "S": {
                        opcao = "S";
                        break;
                    }

                    default:{
                        if(opcao.charAt(0)=='e'){
                            List<String> l = new ArrayList<>();
                            l.add("PedeEncomenda");
                            l.add(opcao);
                            this.controller.processa(l);
                            opcao = "S";
                        }
                        else {
                            System.out.println("Opção Invalida");
                        }
                    }
                }
            }
            while (!opcao.equals("S"));
        }
    }
}
