package Controllers.Loja;

import Controllers.I_Controllers;
import Excepitions.ProdutoNotFoundException;
import Models.TrazAqui;
import Views.View_Erro;

import java.util.List;

public class Controller_RemoverProdutos implements I_Controllers {

    /**
     * Variaveis Instancia
     */
    private TrazAqui sistema;
    private String codigoLoja;

    /**
     * Construtor Parametrizado de Controller_RemoverProdutos
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public Controller_RemoverProdutos(TrazAqui s, String codLoja){
        this.sistema = s;
        this.codigoLoja = codLoja;
    }

    /**
     * Com base nos inputs do Utilizador, tratamos de remover Produtos
     *
     * @param opcao correspondente a lista de opcoes inseridas pelo Utilizador
     */
    public void processa(List<String> opcao){
        if ("R".equals(opcao.get(0))) {
            try {
                this.sistema.removerProdutoLoja(this.codigoLoja, opcao.get(1));
            } catch (ProdutoNotFoundException e) {
                View_Erro erro = new View_Erro("Produto nao existe. Impossivel remover");
                erro.run();
            }
        }
    }
}
