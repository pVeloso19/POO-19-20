package Controllers.Utilizador;

import Controllers.I_Controllers;
import Models.Loja.Loja;
import Models.Sistema.PedidoCompleto;
import Models.Sistema.PedidoTransportadora;
import Models.TrazAqui;
import Views.Utilizador.View_PedidosPendentes;
import Views.Utilizador.View_UtilizadorAvaliar;
import Views.Utilizador.View_UtilizadorGeraEncomenda;
import Views.View_Historico;

import java.util.List;

public class ControllerUtilizador implements I_Controllers {

    /**
     * Variaveis Instancia
     */
    private TrazAqui sistema;
    private String codigoUtilizador;

    /**
     * Construtor Parametrizado de ControllerUtilizador
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public ControllerUtilizador(TrazAqui s, String utilizador){
        this.sistema = s;
        this.codigoUtilizador = utilizador;
    }

    /**
     * Com base nos inputs do Utilizador, processar as diferentes opcoes possiveis num Utilizador
     *
     * @param opcao correspondente a lista de opcoes inseridas pelo Utilizador
     */
    public void processa(List<String> opcao){
        switch (opcao.get(0)){
            case "NumeroMensagens" : {
                break;
            }

            case "CriaEncomenda" : {
                List<Loja> lojas = this.sistema.getLojasDisponiveis();
                View_UtilizadorGeraEncomenda viewAux = new View_UtilizadorGeraEncomenda(lojas,new Controller_UtilizadorGeraEncomenda(this.sistema,this.codigoUtilizador));
                viewAux.run();
                break;
            }

            case "Avaliar" : {
                List<PedidoCompleto> ut = this.sistema.getTransportadoresParaAvaliar(this.codigoUtilizador);
                View_UtilizadorAvaliar viewAux = new View_UtilizadorAvaliar(ut,new Controller_UtilizadorAvalia(this.codigoUtilizador,this.sistema));
                viewAux.run();
                break;
            }

            case "Historico" : {
                List<String> historico = this.sistema.getHistoricoUtilizador(this.codigoUtilizador);
                View_Historico viewAux = new View_Historico(historico);
                viewAux.run();
                break;
            }

            case "MensagensPendentes" : {
                List<String> l = this.sistema.getNotificacoesUtilizador(this.codigoUtilizador);
                List<PedidoTransportadora> lt = this.sistema.getPedidosTransportadorasPendentes(this.codigoUtilizador);
                View_PedidosPendentes viewAux = new View_PedidosPendentes(lt,new Controller_UtilizadorAceitaPendentes(this.sistema,this.codigoUtilizador),l);
                viewAux.run();
                this.sistema.limpaNotificacoesUtilizador(this.codigoUtilizador);
                break;
            }
        }
    }
}
