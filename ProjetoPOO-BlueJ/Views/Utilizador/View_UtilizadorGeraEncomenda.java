package Views.Utilizador;

import Controllers.I_Controllers;
import Models.Loja.Loja;
import Views.LeituraDados;

import java.util.ArrayList;
import java.util.List;

public class View_UtilizadorGeraEncomenda {

    /**
     * Variaveis Instancia
     */
    private List<Loja> lojas;
    private I_Controllers controller;

    /**
     * Construtor Parametrizado de View_UtilizadorGeraEncomenda
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public View_UtilizadorGeraEncomenda(List<Loja> lojas, I_Controllers controller){
        this.lojas = new ArrayList<>(lojas);
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
     * Apresenta no ecra as opcoes do Utilizador na seccao de gerar uma Encomenda
     *
     * @param totalPaginas representa total de paginas
     * @param paginaAtual representa a pagina atual
     */
    private void showOpcoes(int totalPaginas, int paginaAtual){
        if(totalPaginas<=1){
            System.out.println("Insira: Codigo da loja escolhida | S sair");
        }
        else {
            if(paginaAtual == 1){
                System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | Codigo da loja escolhida | S sair");
            }
            else{
                if(paginaAtual==totalPaginas){
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| - página anterior | Codigo da loja escolhida | S sair");
                }
                else{
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | - página anterior | Codigo da loja escolhida | S sair");
                }
            }

        }
    }

    /**
     * Apresenta no ecra o menu de Gerar uma Encomenda
     *
     * @param index correspondente ao indice
     * @param tamPag correspondente ao tamanho da pagina
     * @param elem correspondente aos elementos
     */
    private void showMenu(int index, int tamPag, int elem){
        int pos = (index*tamPag);
        for (int i=0; i<tamPag; i++){
            if(pos<elem){
                System.out.println(this.lojas.get(pos).toString());
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
        int elem = this.lojas.size();
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
                    opcao = "S";
                    break;
                }

                default:{
                    if(opcao.charAt(0) == 'l'){
                        List<String> l = new ArrayList<>();
                        l.add("CriaLinhaEncomenda");
                        l.add(opcao);
                        this.controller.processa(l);
                        opcao = "S";
                    }
                    else{
                        System.out.println("Codigo Invalido.");
                    }
                    break;
                }
            }

        }
        while (!opcao.equals("S"));
    }

}
