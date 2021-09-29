package Models.Loja;

import Excepitions.ProdutoNotFoundException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stock implements Serializable {

    /**
     * Variaveis de Instancia
     */
    private Map<String,Produto> stock;

    /**
     * Construtor por Omissao
     */
    public Stock() {
        this.stock = new HashMap<>();
    }

    /**
     * Construtor Parametrizado de Stock
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public Stock(Map<String, Produto> stock) {
        this.setStock(stock);
    }

    /**
     * Construtor de copia de uma Stock
     * Aceita como parametros outra Stock e utiliza os métodos
     * de acesso aos valores das Variaveis de Instancia
     */
    public Stock(Stock s){
        this.stock = s.getStock();
    }

    /**
     * Devolve o Stock de Produtos, representado por um Map onde
     * as Keys sao o codigo do Produto e os Values sao os Produtos
     *
     * @return Map com os Produtos e os seus codigos
     */
    public Map<String, Produto> getStock() {
        HashMap<String,Produto> res = new HashMap<>();
        for (Map.Entry<String,Produto> s : this.stock.entrySet()){
            res.put(s.getKey(),s.getValue().clone());
        }

        return res;
    }

    /**
     * Definir o novo Stock
     *
     * @param stock correspondente ao novo Map que representara o Stock
     */
    public void setStock(Map<String, Produto> stock) {
        this.stock = new HashMap<>();
        for (Map.Entry<String,Produto> s : stock.entrySet()){
            this.stock.put(s.getKey(),s.getValue().clone());
        }
    }

    /**
     * Dado um codigo de um Produto tentar devolver o Produto associado a esse codigo
     *
     * @param codProduto correspondente ao codigo do produto
     * @return Produto associado ao codigo fornecido
     * @throws ProdutoNotFoundException caso o codigo do produto nao exista nao stock
     */
    public Produto getProduto(String codProduto) throws ProdutoNotFoundException{
        try {
            return this.stock.get(codProduto).clone();
        }catch (NullPointerException e){
            throw new ProdutoNotFoundException("Produto inexistente");
        }
    }

    /**
     * Adicionar um Produto ao stock
     *
     * @param p correspondente ao Produto a adiconar
     */
    public void addStock(Produto p){
        this.stock.put(p.getCodigoProduto(),p.clone());
    }

    /**
     * Dado um codigo de um Produto, vai ao Stock e caso esse codigo
     * esteja associado a um Produto ira remove-lo do Stock
     *
     * @param cod correspondente ao codigo do Produto que se pretende remover
     * @return se porduto foi removido com sucesso ou se na existe
     */
    public String removeProduto(String cod){
        String s = "";
        if(this.stock.containsKey(cod)) {
            this.stock.remove(cod);
            s = "Produto removido do Stock com sucesso.";

        }else {
            s = "Produto não existe em Stock.";
        }

        return s;
    }

    /**
     * Devolve tamanho do Stock
     *
     * @return tamanho do Stock
     */
    public int tamStock(){
        return this.stock.size();
    }

    /**
     * Devolve lista com todos os produtos presentes no Stock
     *
     * @return lista com todos Produtos
     */
    public List<Produto> getListaProdutos(){
        return this.stock.values().stream().map(Produto::clone).collect(Collectors.toList());
    }

    /**
     * Devolve lista com todos os Produtos que sao medicamentos no Stock
     *
     * @return lista com todos os Medicamentos
     */
    public List<Produto> getMedicamentos(){
        return this.stock.values().stream().filter(Produto::isMedicamento).map(Produto::clone).collect(Collectors.toList());
    }

    /**
     * Permite apresentar no ecra as informacoes necessarias
     *
     * @return String com informacao da Classe
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        int i = 1;

        List<Produto> medicamentos = this.stock.values().parallelStream().filter(Produto::isMedicamento).collect(Collectors.toList());
        List<Produto> produtos = this.stock.values().parallelStream().filter(p->!p.isMedicamento()).collect(Collectors.toList());

        if(!medicamentos.isEmpty()){
            sb.append("Medicamentos:\n");
            for (Produto m : medicamentos) {
                sb.append(i + "º -> ").append(m.getCodigoProduto() + " | ").append(m.toString() + "\n");
                i++;
            }

            i=1;
            sb.append("\n");
        }

        if(!produtos.isEmpty()){
            sb.append("Produtos:\n");
            for (Produto p : produtos) {
                sb.append(i + "º -> ").append(p.getCodigoProduto() + " | ").append(p.toString() + "\n");
                i++;
            }
        }

        if(produtos.isEmpty() && medicamentos.isEmpty()){
            sb.append("Não existem produtos disponiveis na loja\n");
        }

        return sb.toString();
    }

    /**
     * Verifica se 2 Objects sao iguais
     *
     * @return true se Object for igual ao Stock
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Stock s = (Stock) o;

        return (this.stock.equals(s.getStock()));
    }

    /**
     * FunÃ§ao que clona um Stock
     * @return clone do Stock
     */
    public Stock clone(){
        return new Stock(this);
    }
}

