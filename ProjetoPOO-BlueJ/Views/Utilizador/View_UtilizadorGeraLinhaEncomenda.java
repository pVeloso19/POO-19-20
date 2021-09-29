package Views.Utilizador;

import Controllers.I_Controllers;
import Models.Loja.Produto;
import Views.LeituraDados;

import java.util.ArrayList;
import java.util.List;

public class View_UtilizadorGeraLinhaEncomenda {

    /**
     * Variaveis Instancia
     */
    private List<Produto> produtos;
    private I_Controllers controller;

    /**
     * Construtor Parametrizado de View_UtilizadorGeraLinhaEncomenda
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public View_UtilizadorGeraLinhaEncomenda(List<Produto> produtos, I_Controllers controller){
        this.produtos = produtos;
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
     * Apresenta no ecra as opcoes do Utilizador na seccao de gerar uma linha de Encomenda
     *
     * @param totalPaginas representa total de paginas
     * @param paginaAtual representa a pagina atual
     */
    private void showOpcoes(int totalPaginas, int paginaAtual){
        if(totalPaginas<=1){
            System.out.println("Insira: Codigo do produto desejado | S finalizar encomenda");
        }
        else {
            if(paginaAtual == 1){
                System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | Codigo do produto desejado | S finalizar encomenda");
            }
            else{
                if(paginaAtual==totalPaginas){
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| - página anterior | Codigo do produto desejado | S finalizar encomenda");
                }
                else{
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | - página anterior | Codigo do produto desejado | S finalizar encomenda");
                }
            }
        }
    }

    /**
     * Apresenta no ecra o menu de Gerar uma Linha Encomenda
     *
     * @param index correspondente ao indice
     * @param tamPag correspondente ao tamanho da pagina
     * @param elem correspondente aos elementos
     */
    private void showMenu(int index, int tamPag, int elem){
        int pos = (index*tamPag);
        for (int i=0; i<tamPag; i++){
            if(pos<elem){
                Produto p = this.produtos.get(pos);
                System.out.println(p.getCodigoProduto()+" -> "+p.getNomeProduto()+" ("+p.getPreco()+" euros)");
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
        int elem = this.produtos.size();
        int totalPaginas = (elem<8)?1:((elem%8==0)?elem/8:(elem/8)+1);

        do {
            this.showMenu(index,tamPag,elem);
            this.showOpcoes(totalPaginas,index+1);
            opcao = LeituraDados.lerString();
            String opcaoTemp = opcao.toUpperCase();

            switch (opcaoTemp){
                case "+" :{
                    index = this.avancaPagina(index,totalPaginas);
                    break;
                }

                case "-" :{
                    index = this.recuaPagina(index);
                    break;
                }

                case "S" :{
                    List<String> l = new ArrayList<>();
                    l.add("Finaliza");
                    this.controller.processa(l);
                    System.out.println("Encomenda finalizada com sucesso.");
                    opcao = "S";
                    break;
                }

                default:{
                    if(opcao.charAt(0)=='p'){
                        List<String> l = new ArrayList<>();
                        l.add("Adiciona");
                        l.add(opcao);
                        System.out.println("Insira a quantidade desejada:");
                        l.add(LeituraDados.lerDoubleComoString());
                        this.controller.processa(l);
                    }
                    else{
                        System.out.println("Codigo produto invalido.");
                    }
                    break;
                }
            }

        }
        while (!opcao.equals("S"));
    }
}
