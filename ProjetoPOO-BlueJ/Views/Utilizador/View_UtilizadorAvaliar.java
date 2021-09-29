package Views.Utilizador;

import Controllers.I_Controllers;
import Models.Sistema.PedidoCompleto;
import Views.LeituraDados;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class View_UtilizadorAvaliar {

    /**
     * Variaveis Instancia
     */
    private I_Controllers controller;
    private List<PedidoCompleto> transportadoresParaAvaliar;

    /**
     * Construtor Parametrizado de View_UtilizadorAvaliar
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public View_UtilizadorAvaliar(List<PedidoCompleto> t, I_Controllers controller){
        this.transportadoresParaAvaliar = new ArrayList<>(t);
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
     * Apresenta no ecra as opcoes do Utilizador na seccao de Avaliacao de Transportadores
     *
     * @param totalPaginas representa total de paginas
     * @param paginaAtual representa a pagina atual
     */
    private void showOpcoes(int totalPaginas, int paginaAtual){
        if(totalPaginas<=1){
            System.out.println("Insira: Codigo para avaliar | S sair");
        }
        else {
            if(paginaAtual == 1){
                System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima pÃ¡gina | Codigo para avaliar | S sair");
            }
            else{
                if(paginaAtual==totalPaginas){
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| - página anterior | Codigo para avaliar | S sair");
                }
                else{
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | - página anterior | Codigo para avaliar | S sair");
                }
            }

        }
    }

    /**
     * Apresenta no ecra o menu dos transportadores por avaliar
     *
     * @param index correspondente ao indice
     * @param tamPag correspondente ao tamanho da pagina
     * @param elem correspondente aos elementos
     */
    private void showMenu(int index, int tamPag, int elem){
        int pos = (index*tamPag);
        for (int i=0; i<tamPag; i++){
            if(pos<elem){

                PedidoCompleto p = this.transportadoresParaAvaliar.get(pos);
                String s = p.getCodigoTransportadora();
                if (s.charAt(0) == 'v') {
                    String tempo = p.calculaTempoTransorte();
                    System.out.println(String.format("Voluntario: %s -> Entregou em %s",p.getCodigoTransportadora(),tempo));
                } else {
                    String tempo = p.calculaTempoTransorte();
                    System.out.println(String.format("Transportadora: %s -> Entregou em %s por %f euros",p.getCodigoTransportadora(),tempo,p.getPrecoSugerido()));
                }

                pos++;
            }else{
                System.out.println("---");
            }
        }
    }

    /**
     * Verifica se o codigo do transportador introduzido pelo utilizador existe na lista
     * de transportadores por avaliar
     *
     * @param cod correspondente ao codigo do transportador
     * @return true se codigo existir na lista
     */
    private boolean codigoValido(String cod){
        boolean res = false;
        Iterator<PedidoCompleto> it = this.transportadoresParaAvaliar.iterator();
        PedidoCompleto s;

        while (it.hasNext() && !res){
            s = it.next();
            res = s.getCodigoTransportadora().equals(cod);
        }

        return res;
    }

    /**
     * Funcao que corre a view com todas as funcoes anterioes, de maneira
     * a interligar os diferentes processos
     */
    public void run(){
        String opcao;
        int index = 0;
        int tamPag = 8;
        int elem = this.transportadoresParaAvaliar.size();
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
                    if(this.codigoValido(opcao)){
                        List<String> l = new ArrayList<>();
                        l.add("Avalia");
                        l.add(opcao);
                        System.out.println("Insira a avaliação a atribuir :");
                        l.add(LeituraDados.lerIntComoString());
                        this.controller.processa(l);
                        opcao = "S";
                    }
                    else{
                        System.out.println("Codigo invalido.");
                    }
                    break;
                }
            }

        }
        while (!opcao.equals("S"));
    }
}
