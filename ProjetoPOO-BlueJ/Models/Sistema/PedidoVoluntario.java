package Models.Sistema;

import Models.Encomendas.I_Encomendas;
import Models.Encomendas.LinhaEncomenda;
import Models.Utilizador.GPS;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PedidoVoluntario implements Serializable, I_PedidosTransportadores {

    /**
     * Variaveis Instancia
     */
    private String codigoPedido;

    private PedidoLoja pedidoLoja;

    //Models.Sistema Atribuiu
    private String codigoVoluntario;
    private String nomeDoVoluntario;
    private double classificacao;
    private double kmPercorridos;
    private LocalDateTime dataSubmissaoResposta;

    //Aceite Models.Voluntario
    private LocalDateTime dataAceite;
    private boolean aceite;

    /**
     * Construtor Parametrizado de PedidoVoluntario
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public PedidoVoluntario(PedidoLoja p, String codigoVoluntario, String nomeDoVoluntario, LocalDateTime dataSubmissaoResposta, LocalDateTime dataAceite, boolean aceite,double classificacao, double km) {
        this.codigoPedido = p.getCodigoPedido();

        this.pedidoLoja = p.clone();

        this.classificacao = classificacao;
        this.kmPercorridos = km;

        this.codigoVoluntario = codigoVoluntario;
        this.nomeDoVoluntario = nomeDoVoluntario;
        this.dataSubmissaoResposta = dataSubmissaoResposta;
        this.dataAceite = dataAceite;
        this.aceite = aceite;
    }

    /**
     * Construtor de cópia de uma PedidoVoluntario
     * Aceita como parametros outro PedidoVoluntario e utiliza os métodos
     * de acesso aos valores das Variaveis de Instancia
     */
    public PedidoVoluntario(PedidoVoluntario p) {
        this.codigoPedido = p.getCodigoPedido();
        this.codigoPedido = p.getCodigoPedido();
        this.pedidoLoja = p.getPedidoLoja();
        this.classificacao = p.getClassificacao();
        this.kmPercorridos = p.getKMPercorridos();
        this.codigoVoluntario = p.getCodigoTransportador();
        this.nomeDoVoluntario = p.getNome();
        this.dataSubmissaoResposta = p.getDataSubmissaoResposta();
        this.dataAceite = p.getDataAceite();
        this.aceite = p.isAceite();
    }

    /**
     * Devolve preco associado a encomenda
     *
     * @return 0 porque voluntario nunca lucra
     */
    public double getPreco(){
        return 0;
    }

    /**
     * Devolve Pedido da Loja
     *
     * @return Pedido Loja
     */
    public PedidoLoja getPedidoLoja() {
        return this.pedidoLoja.clone();
    }

    /**
     * Definir novo Pedido Loja
     *
     * @param pedidoLoja correspodente ao novo Pedido Loja
     */
    public void setPedidoLoja(PedidoLoja pedidoLoja) {
        this.pedidoLoja = pedidoLoja.clone();
    }

    /**
     * Devolve classificacao do PedidoVoluntario
     *
     * @return valor correspondente a classifiacacao
     */
    public double getClassificacao() {
        return classificacao;
    }

    /**
     * Definir classificacao Pedido
     *
     * @param classificacao correspodente a nova classificacao Pedido
     */
    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    /**
     * Devolve Kms percorridos pelo Voluntario
     *
     * @return valor correspondente ao numero Kms
     */
    public double getKMPercorridos() {
        return kmPercorridos;
    }

    /**
     * Definir kms percorridos
     *
     * @param kmPercorridos correspodente aos kms percorridos novos
     */
    public void setKmPercorridos(double kmPercorridos) {
        this.kmPercorridos = kmPercorridos;
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
     * @return data de criacao do Pedido Voluntario
     */
    public LocalDateTime getDataCriacao() {
        return this.pedidoLoja.getDataCriacao();
    }

    /**
     * Devolve utilizador que fez Pedido
     *
     * @return utilizador
     */
    public String getUtilizador() {
        return this.pedidoLoja.getUtilizador();
    }

    /**
     * Definir utilizador que fez pedido
     *
     * @param utilizador correspondente ao novo Utilizador
     */
    public void setUtilizador(String utilizador) {
        this.pedidoLoja.setUtilizador(utilizador);
    }

    /**
     * Devolve localizacao do Utilizador que fez Pedido
     *
     * @return localizacao do Utilizador
     */
    public GPS getGpsUtilizador() {
        return this.pedidoLoja.getGpsUtilizador().clone();
    }

    /**
     * Devolve encomenda associada ao Pedido
     *
     * @return encomenda associada ao Pedido Voluntario
     */
    public I_Encomendas getEncomenda() {
        return this.pedidoLoja.getEncomenda().clone();
    }

    /**
     * Definir a encomenda associada ao Pedido
     *
     * @param e correspondente a nova Encomenda
     */
    public void setEncomenda(I_Encomendas e) {
        this.pedidoLoja.setEncomenda(e.clone());
    }

    /**
     * Devolve data de aceitacao do Pedido por Parte da loja
     *
     * @return data de aceitacao da loja
     */
    public LocalDateTime getDataAceitePelaLoja() {
        return this.pedidoLoja.getDataAceitePelaLoja();
    }

    /**
     * Devolve loja que tratou o pedido
     *
     * @return loja
     */
    public String getLoja() {
        return this.pedidoLoja.getLoja();
    }

    /**
     * Definir loja que tratou o Pedido
     *
     * @param loja correspondente a nova Loja
     */
    public void setLoja(String loja) {
        this.pedidoLoja.setLoja(loja);
    }

    /**
     * Devolve localizacao da loja que tratou o Pedido
     *
     * @return localizacao da loja
     */
    public GPS getGpsLoja() {
        return this.pedidoLoja.getGpsLoja().clone();
    }

    /**
     * Devolve codigo do Transportador que transporta pedido
     *
     * @return codigo Voluntario
     */
    public String getCodigoTransportador() {
        return this.codigoVoluntario;
    }

    /**
     * Definir codigo da Voluntario que trata Pedido
     *
     * @param codigoVoluntario correspondente a novo Voluntario
     */
    public void setCodigoVoluntario(String codigoVoluntario) {
        this.codigoVoluntario = codigoVoluntario;
    }

    /**
     * Devolve nome da Voluntario
     *
     * @return nome Voluntario
     */
    public String getNome() {
        return this.nomeDoVoluntario;
    }

    /**
     * Definir nome do Voluntario que trata Pedido
     *
     * @param nomeDoVoluntario correspondente ao nome Voluntario
     */
    public void setNomeDoVoluntario(String nomeDoVoluntario) {
        this.nomeDoVoluntario = nomeDoVoluntario;
    }

    /**
     * Devolve data de submissao de Resposta do pedido
     *
     * @return data submissao resposta
     */
    public LocalDateTime getDataSubmissaoResposta() {
        return this.dataSubmissaoResposta;
    }

    /**
     * Definir data submissao resposta do Pedido
     *
     * @param dataSubmissaoResposta correspondente a nova data submissao resposta
     */
    public void setDataSubmissaoResposta(LocalDateTime dataSubmissaoResposta) {
        this.dataSubmissaoResposta = dataSubmissaoResposta;
    }

    /**
     * Devolve data de aceitacao do Pedido
     *
     * @return data de aceitacao
     */
    public LocalDateTime getDataAceite() {
        return this.dataAceite;
    }

    /**
     * Definir data de aceitacao do Pedido
     *
     * @param dataAceite correspondente a data de aceitacao do Pedido
     */
    public void setDataAceite(LocalDateTime dataAceite) {
        this.dataAceite = dataAceite;
    }

    /**
     * Verifica se Pedido foi aceite ou nao
     *
     * @return true se tiver sido aceite
     */
    public boolean isAceite() {
        return this.aceite;
    }

    /**
     * Definir nova data de aceitacao do Pedido
     *
     * @param aceite correspondente a nova data de aceitacao
     */
    public void setAceite(boolean aceite) {
        this.aceite = aceite;
    }

    /**
     * Permite apresentar no ecra as informacoes necessarias
     *
     * @return String com informacao da Classe
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Encomenda: ").append(this.codigoPedido).append(" | ").append(this.pedidoLoja.getEncomenda().toString()).append(" | Aceite: ").append(this.aceite);
        return sb.toString();
    }
    
    /**
     * Permite apresentar no ecra as informacoes necessarias a uma loja
     *
     * @return String com informacao da Classe a apresentar a uma loja
     */
    public String toStringLoja(){
        StringBuffer sb = new StringBuffer();
        sb.append("Encomenda: ").append(this.codigoPedido).append(" | ")
                .append(this.pedidoLoja.getEncomenda().toString()).append(" | Aceite: ").append(this.aceite)
                .append(" | Preço=").append(this.pedidoLoja.getEncomenda().getLinhasEncomenda().parallelStream().mapToDouble(LinhaEncomenda::getValor).sum());
        return sb.toString();
    }

    /**
     * Verifica se 2 Objects sao iguais
     *
     * @return true se Object for igual ao PedidoVoluntario
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        PedidoVoluntario p = (PedidoVoluntario) o;

        return (this.codigoPedido.equals(p.getCodigoPedido()) &&
                this.pedidoLoja.equals(p.getPedidoLoja()) &&
                this.codigoVoluntario.equals(p.getCodigoTransportador()) &&
                this.nomeDoVoluntario.equals(p.getNome()) &&
                this.classificacao == p.getClassificacao() &&
                this.kmPercorridos == p.getKMPercorridos() &&
                this.dataSubmissaoResposta.equals(p.getDataSubmissaoResposta()) &&
                this.dataAceite.equals(p.getDataAceite()) &&
                this.aceite == p.isAceite());
    }

    /**
     * Cria copia do PedidoVoluntario
     *
     * @return PedidoVoluntario correspondente a sua Copia
     */
    public PedidoVoluntario clone(){
        return new PedidoVoluntario(this);
    }
}
