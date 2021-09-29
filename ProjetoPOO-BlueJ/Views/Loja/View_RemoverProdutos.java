package Views.Loja;

import Controllers.I_Controllers;
import Models.Loja.Produto;
import Views.LeituraDados;

import java.util.ArrayList;
import java.util.List;

public class View_RemoverProdutos {

    /**
     * Variaveis Instancia
     */
    private List<Produto> listaProdutos;
    private I_Controllers controller;

    /**
     * Construtor Parametrizado de View_Loja
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public View_RemoverProdutos(List<Produto> listaProdutos, I_Controllers controllers){
        this.listaProdutos = listaProdutos;
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
     * Apresenta no ecra as opcoes da Loja na seccao de Remover Produtos
     *
     * @param totalPaginas representa total de paginas
     * @param paginaAtual representa a pagina atual
     */
    private void showOpcoes(int totalPaginas, int paginaAtual){
        if(totalPaginas<=1){
            System.out.println("Insira: R remover produto | S sair");
        }
        else {
            if(paginaAtual == 1){
                System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | R remover produto | S sair");
            }
            else{
                if(paginaAtual==totalPaginas){
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| - página anterior | R remover produto | S sair");
                }
                else{
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | - página anterior | R remover produto | S sair");
                }
            }
        }
    }

    /**
     * Apresenta no ecra o menu da seccao de Remover Produtos
     *
     * @param index correspondente ao indice
     * @param tamPag correspondente ao tamanho da pagina
     * @param elem correspondente aos elementos
     */
    private void showMenu(int index, int tamPag, int elem){
        int pos = (index*tamPag);
        for (int i=0; i<tamPag; i++){
            if(pos<elem){
                Produto p = this.listaProdutos.get(pos);
                System.out.println(p.getCodigoProduto()+" -> "+p.getNomeProduto()+" ("+p.getPreco()+" â‚¬)");
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
        int elem = this.listaProdutos.size();
        int totalPaginas = (elem<8)?1:((elem%8==0)?elem/8:(elem/8)+1);

        do {
            this.showMenu(index,tamPag,elem);
            this.showOpcoes(totalPaginas,index+1);
            opcao = LeituraDados.lerString();
            String opcaoTemp = opcao.toUpperCase();

            switch (opcaoTemp.charAt(0)){
                case '+' :{
                    index = this.avancaPagina(index,totalPaginas);
                    break;
                }

                case '-' :{
                    index = this.recuaPagina(index);
                    break;
                }

                case 'S' :{
                    opcao = "S";
                    break;
                }

                case 'R' :{
                    List<String> l = new ArrayList<>();
                    l.add("R");
                    System.out.println("Insira o codigo do produto a remover.");
                    l.add(LeituraDados.lerString());
                    this.controller.processa(l);
                    opcao = "S";
                    break;
                }
            }

        }
        while (!opcao.equals("S"));
    }
}
