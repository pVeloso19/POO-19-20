package Controllers.Loja;

import Controllers.I_Controllers;
import Excepitions.EncomendaNotFoundException;
import Models.Sistema.PedidoLoja;
import Models.TrazAqui;
import Views.View_Erro;

import java.util.List;

public class Controller_AceitaPedidos implements I_Controllers {

    /**
     * Variaveis Instancia
     */
    private TrazAqui sistema;
    private String codigoLoja;

    /**
     * Construtor Parametrizado de Controller_AceitaPedidos
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public Controller_AceitaPedidos(TrazAqui s, String codLoja){
        this.sistema = s;
        this.codigoLoja = codLoja;
    }

    /**
     * Com base nos inputs do Utilizador, tratamos de Aceitar um Pedido
     *
     * @param opcao correspondente a lista de opcoes inseridas pelo Utilizador
     */
    public void processa(List<String> opcao){
        if ("Aceitar".equals(opcao.get(0))) {
            try {
                PedidoLoja pl = this.sistema.getPedidoUtilizadorDaLoja(Integer.parseInt(opcao.get(1)),this.codigoLoja);
                if(this.sistema.atribuiEntregador(pl)) {
                    this.sistema.removeListaEspera(this.codigoLoja, Integer.parseInt(opcao.get(1)));
                    View_Erro view = new View_Erro("Encomenda aceite pelo sistema.");
                    view.run();
                }
                else {
                    View_Erro view = new View_Erro("Encomenda rejeitada pelo sistema. Sem transportadores disponiveis.");
                    view.run();
                }
            }catch (EncomendaNotFoundException e){
                View_Erro view = new View_Erro("Encomenda Inválida.");
                view.run();
            }
        }
    }
}
