package Controllers.Transportadores;

import Controllers.I_Controllers;
import Excepitions.CodigoNotFoundException;
import Excepitions.EncomendaNotFoundException;
import Models.TrazAqui;
import Views.View_Erro;

import java.util.List;

public class Controller_AceitarEncomenda implements I_Controllers {

    /**
     * Variaveis Instancia
     */
    private TrazAqui sistema;
    private String codigoTransportador;

    /**
     * Construtor Parametrizado de Controller_AceitarEncomenda
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public Controller_AceitarEncomenda(TrazAqui s, String cod){
        this.sistema = s;
        this.codigoTransportador = cod;
    }

    /**
     * Com base nos inputs do Utilizador, tratamos de Aceitar Encomendas
     *
     * @param opcao correspondente a lista de opcoes inseridas pelo Utilizador
     */
    public void processa(List<String> opcao){

        if ("PedeEncomenda".equals(opcao.get(0))) {

            try {
                boolean foiAceite = this.sistema.aceitaIntecaoDeEntrega(this.codigoTransportador, opcao.get(1));
                if (foiAceite) {
                    View_Erro resposta = new View_Erro("Pedido de entrega Aceite pelo sitema.");
                    resposta.run();
                } else {
                    View_Erro resposta = new View_Erro("Pedido de entrega Rejeitado pelo sitema.");
                    resposta.run();
                }
            }catch (NullPointerException | EncomendaNotFoundException | CodigoNotFoundException e){
                View_Erro erro = new View_Erro("Encomenda não existe para entrega.");
                erro.run();
            }
        }
    }
}
