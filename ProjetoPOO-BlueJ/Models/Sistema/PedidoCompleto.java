package Models.Sistema;

import Models.Encomendas.I_Encomendas;
import Models.Encomendas.LinhaEncomenda;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PedidoCompleto implements Serializable {

    /**
     * Variaveis Instancia
     */
    private String codigoPedido;
    private I_PedidosTransportadores pedidosTransportadores;

    //Entregue em
    private LocalDateTime entregue;

    //Avaliado
    private boolean avaliado;

    /**
     * Construir um Pedido completo dado um PedidoTransportadora
     *
     * @param p correspodente ao PedidoTransportadora
     */
    public PedidoCompleto(I_PedidosTransportadores p) {
        this.codigoPedido = p.getCodigoPedido();
        this.pedidosTransportadores = p.clone();
        this.entregue = LocalDateTime.now();
        this.avaliado = false;
    }

    /**
     * Construtor de cópia de uma PedidoCompleto
     * Aceita como parametros outro PedidoCompleto e utiliza os métodos
     * de acesso aos valores das Variaveis de Instancia
     */
    public PedidoCompleto(PedidoCompleto p) {
        this.codigoPedido = p.getCodigoPedido();
        this.pedidosTransportadores = p.getPedidosTransportadores();
        this.entregue = p.getEntregue();
        this.avaliado = p.isAvaliado();
    }

    /**
     * Devolve os Pedidos de Transportadores
     *
     * @return PedidosTransportadores presentes no Pedido Completo
     */
    public I_PedidosTransportadores getPedidosTransportadores() {
        return this.pedidosTransportadores.clone();
    }

    /**
     * Definir novos pedidos transportadores
     *
     * @param pedidosTransportadores correspondente aos novos pedidos a definir
     */
    public void setPedidosTransportadores(I_PedidosTransportadores pedidosTransportadores) {
        this.pedidosTransportadores = pedidosTransportadores.clone();
    }

    /**
     * Devolve o numero de kilometros percorridos num Pedido
     *
     * @return kms percorridos nesse Pedido
     */
    public double getKmPercorridos() {
        return this.pedidosTransportadores.getKMPercorridos();
    }

    /**
     * Devolve o codigo do Pedido
     *
     * @return String do codigo do Produto
     */
    public String getCodigoPedido() {
        return this.codigoPedido;
    }

    /**
     * Definir novo codigo de Produto para o Pedido
     *
     * @param codigoPedido correspondente ao novo codigo do Pedido
     */
    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    /**
     * Devolve o Utilizador associado ao Pedido
     *
     * @return String correspondente ao Utilizador
     */
    public String getUtilizador() {
        return this.pedidosTransportadores.getUtilizador();
    }

    /**
     * Devolve a encomenda associada ao Pedido
     *
     * @return Encomenda presente no Pedido
     */
    public I_Encomendas getEncomenda() {
        return this.pedidosTransportadores.getEncomenda().clone();
    }

    /**
     * Deolve codigo da loja que tratou o Pedido
     *
     * @return string correspodente ao codigo da Loja
     */
    public String getLoja() {
        return this.pedidosTransportadores.getLoja();
    }

    /***
     * Devolve codigo do Transportador que transportou Pedido
     *
     * @return codigo do transportador
     */
    public String getCodigoTransportadora() {
        return this.pedidosTransportadores.getCodigoTransportador();
    }

    /**
     * Devolve nome da Transportadora que tratou do Pedido
     *
     * @return nome da Transportadora
     */
    public String getNomeDaTransportadora() {
        return this.pedidosTransportadores.getNome();
    }

    /**
     * Devolve qual o Preco sugerido do transporte do Pedido
     *
     * @return preco sugerido
     */
    public double getPrecoSugerido() {
        return this.pedidosTransportadores.getPreco();
    }

    /**
     * Devolve a data em que Pedido foi entregue
     *
     * @return data de entraga do Pedido
     */
    public LocalDateTime getEntregue() {
        return this.entregue;
    }

    /**
     * Definir nova data de entrega do Pedido
     *
     * @param entregue correspondente a nova data de entrega
     */
    public void setEntregue(LocalDateTime entregue) {
        this.entregue = entregue;
    }

    /**
     * Verifica se transportador do Pedido ja foi avaliado
     *
     * @return true se ja tiver sido avaliado
     */
    public boolean isAvaliado() {
        return this.avaliado;
    }

    /**
     * Definir se ja foi avaliado ou nao
     *
     * @param avaliado correspondente ao novo estado de avaliacao
     */
    public void setAvaliado(boolean avaliado) {
        this.avaliado = avaliado;
    }

    /**
     * Calcula o tempo de transporte e converte-o para String
     * de maneira a ser possivel apresentar no ecra essa informacao
     *
     * @return string com tempo de Transporte
     */
    public String calculaTempoTransorte(){
        long res = ChronoUnit.MINUTES.between(this.pedidosTransportadores.getDataAceite(), this.entregue);
        String unidades = "minutos";
        if(res>60){
            res = ChronoUnit.HOURS.between(this.pedidosTransportadores.getDataAceite(), this.entregue);
            unidades = "horas";
            if(res>24){
                res = ChronoUnit.DAYS.between(this.pedidosTransportadores.getDataAceite(), this.entregue);
                unidades = "dias";
                if(res>31){
                    res = ChronoUnit.MONTHS.between(this.pedidosTransportadores.getDataAceite(), this.entregue);
                    unidades = "meses";
                    if(res>366){
                        res = ChronoUnit.YEARS.between(this.pedidosTransportadores.getDataAceite(), this.entregue);
                        unidades = "anos";
                    }
                }
            }
        }
        return res+" "+unidades;
    }

    /**
     * Permite apresentar no ecra as informacoes necessarias
     *
     * @return String com informacao da Classe
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        double preco = this.pedidosTransportadores.getEncomenda().getLinhasEncomenda().parallelStream().mapToDouble(LinhaEncomenda::getValor).sum();
        sb.append("A encomenda: ").append(this.pedidosTransportadores.getEncomenda().toString()).append("| Preço Loja=").append(preco).append(" | Preço Transporte=").append(this.pedidosTransportadores.getPreco()).append(" | Iniciada em ").append(this.pedidosTransportadores.getDataAceite().toString()).append(" | Finalizada em ").append(this.entregue.toString());
        return sb.toString();
    }

    /**
     * Verifica se 2 Objects sao iguais
     *
     * @return true se Object for igual ao PedidoCompleto
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        PedidoCompleto p = (PedidoCompleto)o;

        return (this.codigoPedido.equals(p.getCodigoPedido()) &&
                this.pedidosTransportadores.equals(p.getPedidosTransportadores()) &&
                this.entregue.equals(p.getEntregue()) &&
                this.avaliado == p.isAvaliado());
    }

    /**
     * Cria copia do PedidoCompleto
     *
     * @return PedidoCompleto correspondente a sua Copia
     */
    public PedidoCompleto clone(){
        return new PedidoCompleto(this);
    }
}
