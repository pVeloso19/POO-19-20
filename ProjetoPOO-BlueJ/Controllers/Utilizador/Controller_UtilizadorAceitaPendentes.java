package Controllers.Utilizador;

import Controllers.I_Controllers;
import Models.TrazAqui;

import java.util.List;

public class Controller_UtilizadorAceitaPendentes implements I_Controllers {

    /**
     * Variaveis Instancia
     */
    private TrazAqui sistema;
    private String codUtilizador;

    /**
     * Construtor Parametrizado de Controller_UtilizadorAceitaPendentes
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public Controller_UtilizadorAceitaPendentes(TrazAqui s, String cod){
        this.sistema = s;
        this.codUtilizador = cod;
    }

    /**
     * Com base nos inputs do Utilizador, tratar de Aceitar Encomendas Pendentes
     *
     * @param opcao correspondente a lista de opcoes inseridas pelo Utilizador
     */
    public void processa(List<String> opcao){
        switch (opcao.get(0)){
            case "Aceita" :{
                this.sistema.aceitaTransportadoraPendente(this.codUtilizador,opcao.get(1));
                break;
            }
            case "Rejeita" :{
                this.sistema.rejeitaTransportadoraPendente(this.codUtilizador,opcao.get(1));
                break;
            }
        }
    }
}
