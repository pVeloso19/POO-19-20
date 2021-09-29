package Models.Sistema;

import Models.Encomendas.I_Encomendas;
import Models.Encomendas.LinhaEncomenda;
import Models.Utilizador.GPS;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoUtilizador implements Serializable {

    /**
     * Variaveis Instancia
     */
    private String codigoPedido;

    //UtilizadorCriou
    private LocalDateTime dataCriacao;
    private String utilizador;
    private GPS gpsUtilizador;

    //Encomenda
    private I_Encomendas e;

    /**
     * Construtor Parametrizado de PedidoTransportadora
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public PedidoUtilizador(I_Encomendas e, GPS gpsUtilizador, LocalDateTime t) {

        this.codigoPedido = e.getCodEncomenda();
        this.utilizador = e.getCodUtilizador();
        this.gpsUtilizador = gpsUtilizador.clone();
        this.e = e.clone();

        this.dataCriacao = t;
    }

    /**
     * Construtor de cópia de uma PedidoTransportadora
     * Aceita como parametros outro PedidoTransportadora e utiliza os métodos
     * de acesso aos valores das Variaveis de Instancia
     */
    public PedidoUtilizador(PedidoUtilizador p) {
        this.codigoPedido = p.getCodigoPedido();
        this.utilizador = p.getUtilizador();
        this.gpsUtilizador = p.getGpsUtilizador();
        this.e = p.getEncomenda();
        this.dataCriacao = p.getDataCriacao();
    }

    /**
     * Devolve codigo do Pedido
     *
     * @return codigo Pedido
     */
    public String getCodigoPedido() {
        return codigoPedido;
    }

    /**
     * Definir novo codigo do Pedido
     *
     * @param codigoPedido correspondente ao novo codigo
     */
    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    /**
     * Devolve data de criacao do pedido
     *
     * @return data de criacao do Pedido Utilizador
     */
    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    /**
     * Definir nova data Criacao
     *
     * @param dataCriacao correspondente a nova data
     */
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    /**
     * Devolve utilizador que fez Pedido
     *
     * @return utilizador
     */
    public String getUtilizador() {
        return this.utilizador;
    }

    /**
     * Definir utilizador que fez pedido
     *
     * @param utilizador correspondente ao novo Utilizador
     */
    public void setUtilizador(String utilizador) {
        this.utilizador = utilizador;
    }

    /**
     * Devolve localizacao do Utilizador que fez Pedido
     *
     * @return localizacao do Utilizador
     */
    public GPS getGpsUtilizador() {
        return gpsUtilizador.clone();
    }

    /**
     * Definir localizacao do Utilizador
     *
     * @param gpsUtilizador correspondente a nova Localizacao
     */
    public void setGpsUtilizador(GPS gpsUtilizador) {
        this.gpsUtilizador = gpsUtilizador.clone();
    }

    /**
     * Devolve encomenda associada ao Pedido
     *
     * @return encomenda associada ao Pedido Utilizador
     */
    public I_Encomendas getEncomenda() {
        return e.clone();
    }

    /**
     * Definir a encomenda associada ao Pedido
     *
     * @param e correspondente a nova Encomenda
     */
    public void setEncomenda(I_Encomendas e) {
        this.e = e.clone();
    }

    /**
     * Devolve preco associado a encomenda
     *
     * @return preco da encomenda
     */
    public double getPrecoTotal(){
        return this.e.getLinhasEncomenda().parallelStream().mapToDouble(LinhaEncomenda::getValor).sum();
    }

    /**
     * Permite apresentar no ecra as informacoes necessarias
     *
     * @return String com informacao da Classe
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        double preco = this.e.getLinhasEncomenda().parallelStream().mapToDouble(LinhaEncomenda::getValor).sum();
        List<LinhaEncomenda> l = this.e.getLinhasEncomenda();
        sb.append("Encomenda: ").append(l.toString())
          .append(" | Preço Total: ").append(preco).append(" euros.");

        return sb.toString();
    }

    /**
     * Verifica se 2 Objects sao iguais
     *
     * @return true se Object for igual ao PedidoUtilizador
     */
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        PedidoUtilizador p = (PedidoUtilizador) o;

        return (this.codigoPedido.equals(p.getCodigoPedido()) &&
                this.dataCriacao.equals(p.getDataCriacao()) &&
                this.utilizador.equals(p.getUtilizador()) &&
                this.gpsUtilizador.equals(p.getGpsUtilizador()) &&
                this.e.equals(p.getEncomenda()));
    }

    /**
     * Cria copia do PedidoUtilizador
     *
     * @return PedidoUtilizador correspondente a sua Copia
     */
    public PedidoUtilizador clone(){
        return new PedidoUtilizador(this);
    }
}
