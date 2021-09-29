package Controllers.Transportadores;

import Controllers.I_Controllers;
import Models.TrazAqui;
import Views.Transportadores.View_EncomendasParaRecolha;
import Views.Transportadores.View_SetValores;
import Views.View_Historico;

import java.util.List;

public class Controller_Voluntario implements I_Controllers {

    /**
     * Variaveis Instancia
     */
    private TrazAqui sistema;
    private String codigoTransportador;

    /**
     * Construtor Parametrizado de Controller_Voluntario
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public Controller_Voluntario(TrazAqui s, String cod){
        this.sistema = s;
        this.codigoTransportador = cod;
    }

    /**
     * Com base nos inputs do Utilizador, processar as diferentes opcoes possiveis num Voluntario
     *
     * @param opcao correspondente a lista de opcoes inseridas pelo Utilizador
     */
    public void processa(List<String> opcao){
        switch (opcao.get(0)){
            case "MudarEstado" : {
                this.sistema.mudaEstado(this.codigoTransportador);
                break;
            }

            case "Finaliza" : {
                this.sistema.finalizaTransportadores(this.codigoTransportador);
                break;
            }

            case "PedirEncomenda" : {
                List<String> encomendasNoSistema = this.sistema.encomendaParaEntrega(this.codigoTransportador);
                View_EncomendasParaRecolha viewAux = new View_EncomendasParaRecolha(encomendasNoSistema, new Controller_AceitarEncomenda(this.sistema,this.codigoTransportador));
                viewAux.run();
                break;
            }

            case "Historico" : {
                List<String> historico = this.sistema.getHistoricoTransportadores(this.codigoTransportador);
                View_Historico viewAux = new View_Historico(historico);
                viewAux.run();
                break;
            }

            case "MudarValores" : {
                View_SetValores view = new View_SetValores(this.sistema.getTransportador(this.codigoTransportador),new Controller_SetValores(this.sistema,this.codigoTransportador));
                view.run();
                break;
            }
        }
    }
}
