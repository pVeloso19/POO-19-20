package Controllers.Loja;

import Controllers.I_Controllers;
import Excepitions.CodigoNotFoundException;
import Models.Loja.Produto;
import Models.TrazAqui;
import Views.Loja.View_AceitarPedidos;
import Views.Loja.View_RemoverProdutos;
import Views.View_Erro;
import Views.View_Historico;

import java.util.List;

public class Controller_Loja implements I_Controllers {

    /**
     * Variaveis Instancia
     */
    private TrazAqui sistema;
    private String codigoLoja;

    /**
     * Construtor Parametrizado de Controller_Loja
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public Controller_Loja(TrazAqui s, String codLoja){
        this.sistema = s;
        this.codigoLoja = codLoja;
    }

    /**
     * Com base nos inputs do Utilizador, tratamos nos orientar na nos Menus da Loja
     *
     * @param opcao correspondente a lista de opcoes inseridas pelo Utilizador
     */
    public void processa(List<String> opcao){
        switch (opcao.get(0)){
            case "AdicionarM" : {
                Produto p = new Produto(this.sistema.geraCodigoProduto(), opcao.get(1), Double.parseDouble(opcao.get(2)), Double.parseDouble(opcao.get(3)), true);
                this.sistema.adicionaProdutoLoja(this.codigoLoja,p);
                break;
            }

            case "Adicionar" : {
                Produto p = new Produto(this.sistema.geraCodigoProduto(), opcao.get(1), Double.parseDouble(opcao.get(2)), Double.parseDouble(opcao.get(3)), false);
                this.sistema.adicionaProdutoLoja(this.codigoLoja,p);
                break;
            }

            case "RemoverP" : {
                try {
                    List<Produto> ps = this.sistema.getProdutosLoja(this.codigoLoja);
                    View_RemoverProdutos viewAux = new View_RemoverProdutos(ps,new Controller_RemoverProdutos(this.sistema,this.codigoLoja));
                    viewAux.run();
                } catch (CodigoNotFoundException e) {
                    View_Erro erro = new View_Erro(e.getMessage());
                    erro.run();
                }
                break;
            }

            case "Historico" : {
                List<String> historico = this.sistema.getHistoricoLoja(this.codigoLoja);
                View_Historico viewAux = new View_Historico(historico);
                viewAux.run();
                break;
            }

            case "Espera" : {
                this.sistema.setPessoasEmEspera(this.codigoLoja,Integer.parseInt(opcao.get(1)));
                break;
            }

            case "AceitarPedidos" : {
                List<String> l = this.sistema.getListPedidosLoja(this.codigoLoja);
                View_AceitarPedidos view = new View_AceitarPedidos(l,new Controller_AceitaPedidos(this.sistema,this.codigoLoja));
                view.run();
                break;
            }
        }
    }
}
