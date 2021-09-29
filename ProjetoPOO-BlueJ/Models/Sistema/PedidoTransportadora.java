package Models.Sistema;

import Models.Encomendas.I_Encomendas;
import Models.Encomendas.LinhaEncomenda;
import Models.Utilizador.GPS;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PedidoTransportadora implements Serializable, I_PedidosTransportadores {

    /**
     * Variaveis Instancia
     */
    private String codigoPedido;

    private PedidoLoja pedidoLoja;

    //SistemaAtribuiu
    private String codigoTransportadora;
    private String nomeDaTransportadora;
    private double precoSugerido;
    private String tempoPrevisto;
    private double classificacao;
    private double kmPercorridos;

    private LocalDateTime dataSubmissaoResposta;

    //Aceite pelo Models.Utilizador
    private LocalDateTime dataAceite;
    private boolean aceite;

    /**
     * Construtor Parametrizado de PedidoTransportadora
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public PedidoTransportadora(PedidoLoja p, String codigoTransportadora, String nomeDaTransportadora, double precoSugerido, LocalDateTime dataSubmissaoResposta, LocalDateTime dataAceite, boolean aceite, String tempoPrevisto, double classificacao, double km) {

        this.codigoPedido = p.getCodigoPedido();
        this.pedidoLoja = p.clone();
        this.codigoTransportadora = codigoTransportadora;
        this.nomeDaTransportadora = nomeDaTransportadora;
        this.precoSugerido = precoSugerido;
        this.tempoPrevisto = tempoPrevisto;
        this.classificacao = classificacao;
        this.kmPercorridos = km;
        this.dataSubmissaoResposta = dataSubmissaoResposta;
        this.dataAceite = dataAceite;
        this.aceite = aceite;
    }

    /**
     * Construtor de cópia de uma PedidoTransportadora
     * Aceita como parametros outro PedidoTransportadora e utiliza os métodos
     * de acesso aos valores das Variaveis de Instancia
     */
    public PedidoTransportadora(PedidoTransportadora p){

        this.codigoPedido = p.getCodigoPedido();
        this.codigoPedido = p.getCodigoPedido();
        this.pedidoLoja = p.getPedidoLoja().clone();
        this.codigoTransportadora = p.getCodigoTransportador();
        this.nomeDaTransportadora = p.getNome();
        this.precoSugerido = p.getPreco();
        this.tempoPrevisto = p.getTempoPrevisto();
        this.kmPercorridos = p.getKMPercorridos();
        this.classificacao = p.getClassificacao();
        this.dataSubmissaoResposta = p.getDataSubmissaoResposta();
        this.dataAceite = p.getDataAceite();
        this.aceite = p.isAceite();
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
     * Devolve Kms percorridos pela Transportadora
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
     * Devolve classificacao do PedidoTransportadora
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
     * Devolve tempo previsto transporte do Pedido
     *
     * @return tempo de transporte
     */
    public String getTempoPrevisto() {
        return tempoPrevisto;
    }

    /**
     * Definir novo tempo Previsto transporte
     *
     * @param tempoPrevisto correspondente ao novo tempo previsto
     */
    public void setTempoPrevisto(String tempoPrevisto) {
        this.tempoPrevisto = tempoPrevisto;
    }

    /**
     * Verifica se pedido tem medicamentos
     *
     * @return true se tiver medicamentos
     */
    public boolean isTemMedicamentos() {
        return this.pedidoLoja.isTemMedicamentos();
    }

    /**
     * Devolve o estado da fila de espera na loja
     *
     * @return estado da fila de espera
     */
    public String getEstadoLoja() {
        return this.pedidoLoja.getEstadoEsperaLoja();
    }

    /**
     * Devolve codigo do Pedido
     *
     * @return codigo Pedido
     */
    public String getCodigoPedido() {
        return this.codigoPedido;
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
     * @return data de criacao do Pedido Transportadora
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
     * @return encomenda associada ao Pedido Transportadora
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
     * @return codigo Transportadora
     */
    public String getCodigoTransportador() {
        return this.codigoTransportadora;
    }

    /**
     * Definir codigo da Transportadora que trata Pedido
     *
     * @param codigoTransportadora correspondente a nova Transportadora
     */
    public void setCodigoTransportadora(String codigoTransportadora) {
        this.codigoTransportadora = codigoTransportadora;
    }

    /**
     * Devolve nome da Transportadora
     *
     * @return nome Transportadora
     */
    public String getNome() {
        return this.nomeDaTransportadora;
    }

    /**
     * Definir nome da Transportadora que trata Pedido
     *
     * @param nomeDaTransportadora correspondente ao nome Transportadora
     */
    public void setNomeDaTransportadora(String nomeDaTransportadora) {
        this.nomeDaTransportadora = nomeDaTransportadora;
    }

    /**
     * Devolve preco associado ao transporte pedido
     *
     * @return preco Transporte sugerido
     */
    public double getPreco() {
        return this.precoSugerido;
    }

    /**
     * Definir novo preco sugerido
     *
     * @param precoSugerido correspondente ao novo preco
     */
    public void setPrecoSugerido(double precoSugerido) {
        this.precoSugerido = precoSugerido;
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
     * Devolve Pedido da Loja
     *
     * @return Pedido Loja
     */
    public PedidoLoja getPedidoLoja(){
        return this.pedidoLoja.clone();
    }

    /**
     * Permite apresentar no ecra as informacoes necessarias
     *
     * @return String com informacao da Classe
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Encomenda: ").append(this.codigoPedido).append(" | ")
                .append(this.pedidoLoja.getEncomenda().toString()).append(" | Aceite: ").append(this.aceite)
                .append(" | Preço=").append(this.precoSugerido);
        return sb.toString();
    }

    /**
     * Permite apresentar no ecra as informacoes necessarias ao Utilizador
     *
     * @return String com informacao da Classe
     */
    public String toStringUtilizador() {
        StringBuffer sb = new StringBuffer();
        sb.append("Encomenda: ").append(this.codigoPedido).append("\n");
        sb.append("Detalhes da encomenda: ").append(this.pedidoLoja.getEncomenda().toString()).append("\n");
        sb.append("Solução: ").append("Trasportadora ").append(this.codigoTransportadora+" | ")
                .append(this.nomeDaTransportadora).append(" | Tempo Previsto: ")
                .append(this.tempoPrevisto).append(" | Classificação: ").append(this.classificacao)
                .append("\n");
        sb.append("Preço = ").append(this.precoSugerido).append(" euros");

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
     * @return true se Object for igual ao PedidoTransportadora
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        PedidoTransportadora p = (PedidoTransportadora) o;

        return (this.codigoPedido.equals(p.getCodigoPedido()) &&
                this.pedidoLoja.equals(p.getPedidoLoja()) &&
                this.codigoTransportadora.equals(p.getCodigoTransportador()) &&
                this.nomeDaTransportadora.equals(p.getNome()) &&
                this.precoSugerido == p.getPreco() &&
                this.tempoPrevisto.equals(p.getTempoPrevisto()) &&
                this.classificacao == p.getClassificacao() &&
                this.kmPercorridos == p.getKMPercorridos() &&
                this.dataSubmissaoResposta.equals(p.getDataSubmissaoResposta()) &&
                this.dataAceite.equals(p.getDataAceite()) &&
                this.aceite == p.isAceite());
    }

    /**
     * Cria copia do PedidoTransportadora
     *
     * @return PedidoTransportadora correspondente a sua Copia
     */
    public PedidoTransportadora clone(){
        return new PedidoTransportadora(this);
    }

}
