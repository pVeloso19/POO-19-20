package Controllers.Utilizador;

import Controllers.I_Controllers;
import Excepitions.ProdutoNotFoundException;
import Models.Encomendas.Encomenda;
import Models.Encomendas.I_Encomendas;
import Models.Encomendas.LinhaEncomenda;
import Models.Loja.Produto;
import Models.TrazAqui;
import Views.View_Erro;

import java.util.ArrayList;
import java.util.List;

public class Controller_UtilizadorGeraLinhaEncomenda implements I_Controllers {

    /**
     * Variaveis Instancia
     */
    private TrazAqui trazAqui;
    private String codLoja;
    private String codUtilizador;
    private Double peso;
    private List<LinhaEncomenda> linhaCriada;

    /**
     * Construtor Parametrizado de Controller_UtilizadorGeraLinhaEncomenda
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public Controller_UtilizadorGeraLinhaEncomenda(TrazAqui s, String u, String l){
        this.trazAqui = s;
        this.codLoja = l;
        this.codUtilizador = u;
        this.linhaCriada = new ArrayList<>();
        this.peso = 0.0;
    }

    /**
     * Com base nos inputs do Utilizador, tratar de gerar uma linha de Encomenda
     *
     * @param opcao correspondente a lista de opcoes inseridas pelo Utilizador
     */
    public void processa(List<String> opcao){

        switch (opcao.get(0)){
            case "Adiciona" :{
                try {
                    Produto p = this.trazAqui.getProdutoDaLoja(this.codLoja,opcao.get(1));
                    double quantidade = Double.parseDouble(opcao.get(2));
                    double preco = p.getPreco()*quantidade;
                    this.peso += p.getPeso();
                    LinhaEncomenda l = new LinhaEncomenda(p.getCodigoProduto(),p.getNomeProduto(),quantidade,preco);
                    this.linhaCriada.add(l);
                }catch (ProdutoNotFoundException e){
                    View_Erro view_erro = new View_Erro(e.getMessage());
                    view_erro.run();
                }
                break;
            }
            case "Finaliza" :{
                if(!linhaCriada.isEmpty()){
                    I_Encomendas e = new Encomenda("", this.codUtilizador, this.codLoja, peso, this.linhaCriada);
                    e.setCodEncomenda(this.trazAqui.geraCodigoEncomenda());
                    this.trazAqui.utilizadorToLoja(e);
                }
                break;
            }
        }
    }
}
