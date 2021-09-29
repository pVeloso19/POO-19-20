package Models.Loja;

import java.io.Serializable;

public class Produto implements Serializable {

    /**
     * Variaveis de Inst√¢ncia
     */
    private String codigoProduto;
    private String nomeProduto;

    private double peso;
    private double preco;

    private boolean isMedicamento;

    /**
     * Construtor por Omissao
     */
    public Produto() {
        this.codigoProduto = "";
        this.nomeProduto = "";
        this.peso = 0;
        this.preco = 0;
        this.isMedicamento = false;
    }

    /**
     * Construtor Parametrizado de Produto
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public Produto(String codigoProduto, String nomeProduto, double peso, double preco, boolean isMedicamento) {
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.peso = peso;
        this.preco = preco;
        this.isMedicamento = isMedicamento;
    }

    /**
     * Construtor de cÛpia de uma Produto
     * Aceita como parametros outra Produto e utiliza os metodos
     * de acesso aos valores das Variaveis de Instancia
     */
    public Produto(Produto p) {
        this.codigoProduto = p.getCodigoProduto();
        this.nomeProduto = p.getNomeProduto();
        this.peso = p.getPeso();
        this.preco = p.getPreco();
        this.isMedicamento = p.isMedicamento();
    }

    /**
     * Funcao que devolve o codigo do produto
     * @return codigo do produto
     */
    public String getCodigoProduto() {
        return this.codigoProduto;
    }

    /**
     * Funcao que atualiza o codigo do produto
     * @param codigoProduto novo codigo do produto
     */
    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    /**
     * Funcao que devolve o nome do produto
     * @return nome do produto
     */
    public String getNomeProduto() {
        return this.nomeProduto;
    }

    /**
     * Funcao que atualiza o nome do produto
     * @param nomeProduto novo nome do produto
     */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    /**
     * Funcao que devolve o peso do produto
     * @return peso do produto
     */
    public double getPeso() {
        return this.peso;
    }

    /**
     * Funcao que atualiza o peso do produto
     * @param peso novo peso do produto
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * funcao que devolve o preco do produto
     * @return pre√ßo do produto
     */
    public double getPreco() {
        return this.preco;
    }

    /**
     * funcao que atualiza o preco do produto
     * @param preco novo preÁo do produto
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Funcao que diz se o produto È um medicamento
     * @return boolean com o facto de ser ou nao medicamento
     */
    public boolean isMedicamento() {
        return this.isMedicamento;
    }

    /**
     * Funcao que atualiza o facto do produto ser ou nao medicamento
     * @param medicamento nova veracidade sobre o produto ser ou nao mediacemnto
     */
    public void setMedicamento(boolean medicamento) {
        this.isMedicamento = medicamento;
    }

    /**
     * Permite apresentar no ecra as informacoes necessarias
     *
     * @return String com informacao da Classe
     */
    public String toString(){
        return "Codigo: " + this.codigoProduto +
               "Nome: " + this.nomeProduto +
               "Peso: " + this.peso +
               "Preco: " + this.preco +
               "Medicamento: " +this.isMedicamento;
    }

    /**
     * Verifica se 2 Objects sao iguais
     *
     * @return true se Object for igual a Loja
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Produto p = (Produto) o;

        return (this.codigoProduto.equals(p.getNomeProduto()) &&
                this.nomeProduto.equals(p.getNomeProduto()) &&
                this.peso == p.getPeso() &&
                this.preco == p.getPreco() &&
                this.isMedicamento == p.isMedicamento());
    }

    /**
     * Fun√ßao que clona um produto
     * @return clone do produto
     */
    public Produto clone(){
        return new Produto(this);
    }
}
