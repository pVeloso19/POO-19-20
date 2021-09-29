package Models.Utilizador;

import Models.Sistema.PedidoCompleto;
import Models.Sistema.PedidoTransportadora;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utilizador implements Serializable {

    /**
     * Variaveis de Instancia
     */
    private String codUtilizador;
    private String nome;
    private GPS gps;

    private Map<String, PedidoTransportadora> pedidosPendentes;
    private List<String> notificacoes;
    private Map<String, PedidoCompleto> historico;

    /**
     * Construtor dadas Variaveis de Instancia
     *
     * @param codUtilizador correposndente ao Codigo do Utilizador
     * @param nome correspodente ao nome do Utilizador
     * @param gps correspondente as coordenadas do Utilizador
     */
    public Utilizador(String codUtilizador, String nome, GPS gps) {
        this.codUtilizador = codUtilizador;
        this.nome = nome;
        this.gps = gps.clone();

        this.pedidosPendentes = new HashMap<>();
        this.notificacoes = new ArrayList<>();
        this.historico = new HashMap<>();
    }

    /**
     * Contrustor apartir de um Utilizador
     *
     * @param u correspondente ao Utilizador do qual vamos obter informacoes
     */
    public Utilizador(Utilizador u){
        this.codUtilizador = u.getCodUtilizador();
        this.nome = u.getNome();
        this.gps = u.getGps();

        this.pedidosPendentes = u.getPedidosPendentes();
        this.notificacoes = u.getNotificacoes();
        this.historico = u.getHistorico();
    }

    /**
     * Metodo que devolve o Historico do Utilizador
     *
     * @return de um Map onde a Key correspondede ao codigo do pedido
     * e o Value ao PedidoCompleto
     */
    public Map<String, PedidoCompleto> getHistorico() {
        Map<String,PedidoCompleto> r = new HashMap<>();
        for (PedidoCompleto p: this.historico.values()){
            r.put(p.getCodigoPedido(),p.clone());
        }
        return r;
    }

    /**
     * Definir um novo historico do Utilizador
     *
     * @param historico referente ao novo Historico
     */
    public void setHistorico(Map<String, PedidoCompleto> historico) {
        this.historico = new HashMap<>();
        for (PedidoCompleto p: historico.values()){
            this.historico.put(p.getCodigoPedido(),p.clone());
        }
    }

    /**
     * Metodo que devolve o codigo do Utilizador
     *
     * @return String que representa o codigo do Utilizador
     */
    public String getCodUtilizador() {
        return codUtilizador;
    }

    /**
     * Definir o novo codigo do Utilizador
     *
     * @param codUtilizador referente a esse novo codigo
     */
    public void setCodUtilizador(String codUtilizador) {
        this.codUtilizador = codUtilizador;
    }

    /**
     * Metodo que devolve o nome do Utilizador
     *
     * @return String que representa o nome do Utilizador
     */
    public String getNome() {
        return nome;
    }

    /**
     * Definir o novo nome do Utlizador
     *
     * @param nome correspodente ao novo nome deste
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo que devolve a localizacao do Utilizador
     *
     * @return informacao referente a localizacao do Utilizador
     */
    public GPS getGps() {
        return gps.clone();
    }

    /**
     * Definir nova localizacao do Utilizador
     *
     * @param gps referente a nova localizacao
     */
    public void setGps(GPS gps) {
        this.gps = gps.clone();
    }

    /**
     * Metodo que devolve quais os Pedidos que ainda nao foram finalizados
     *
     * @return de um Map com informacoes acerca do codigo desses pedidos, bem
     * como as informacoes do Pedido
     */
    public Map<String, PedidoTransportadora> getPedidosPendentes() {
        Map<String, PedidoTransportadora> res = new HashMap<>();
        for (Map.Entry<String, PedidoTransportadora> e : this.pedidosPendentes.entrySet()){
            res.put(e.getKey(),e.getValue().clone());
        }
        return res;
    }

    /**
     * Definir novo Map de Pedidos pendentes
     *
     * @param pedidosPendentes referente ao novo conjunto de pedidos pendentes
     */
    public void setPedidosPendentes(Map<String, PedidoTransportadora> pedidosPendentes) {
        this.pedidosPendentes = new HashMap<>();
        for (Map.Entry<String, PedidoTransportadora> e : pedidosPendentes.entrySet()){
            this.pedidosPendentes.put(e.getKey(),e.getValue().clone());
        }
    }

    /**
     * Metodo que devolve lista com as notificacoes do Utilizador.
     * Nesta lista irao estar os codigos dos pedidos que o Utilizador
     * ainda tem que aceitar (quando o pedido, por exemplo, e atribuido
     * a uma transportadora e utilizador tem decidir se aceita ou nao)
     *
     * @return lista com codigos desses pedidos
     */
    public List<String> getNotificacoes() {
        List<String> res = new ArrayList<>();

        for (String s : this.notificacoes){
            res.add(s);
        }

        return res;
    }

    /**
     * Metodo que quando não houver mais notificacoes para um utilizador
     * apaga as notificacoes que la estavam
     */
    public void limpaNotificacoes(){
        this.notificacoes = new ArrayList<>();
    }

    /**
     * Definir nova lista de notificacoes para o utilizador
     *
     * @param notificacoes correspondente aas novas notificacoes do Utilizador
     */
    public void setNotificacoes(List<String> notificacoes) {
        this.notificacoes = new ArrayList<>(notificacoes);
    }

    /**
     * Devolve informacao dos pedidos finalizados, ordenados por ordem
     * crescente da hora a que foram concluidos
     *
     * @return lista com codigos dos pedidos ja finalizados
     */
    public List<String> getListaHistorico(){
        return this.historico.values().parallelStream().sorted((p1,p2)->(p1.getEntregue().compareTo(p2.getEntregue())==0)? 1 : p1.getEntregue().compareTo(p2.getEntregue()))
                   .map(PedidoCompleto::toString).collect(Collectors.toList());
    }

    /**
     * Metodo que vai ao historico dos pedidos finalizados e
     * devolve uma lista com os Pedidos completos nos quais a transportadora
     * que tratou de um Pedido ainda nao foi avaliada
     *
     * @return lista dos pedidos onde transportadora ainda nao foi avaliada
     */
    public List<PedidoCompleto> getTransportadoresPorAvaliar(){
        List<PedidoCompleto> res = new ArrayList<>();
        for (PedidoCompleto p : this.historico.values()){
            if(!p.isAvaliado()){
                res.add(p.clone());
            }
        }
        return res;
    }

    /**
     * Adiciona um Pedido finalizado ao historico
     *
     * @param p correspondente ao PedidoCompleto que iremos inserir no historico
     */
    public void addHistorico(PedidoCompleto p){
        this.historico.put(p.getCodigoPedido(),p.clone());
    }

    /**
     * Adicona uma notificao as notificacoes
     *
     * @param notificacao referente a notificacao que iremos adicionar
     */
    public void addNotificacao(String notificacao){
        this.notificacoes.add(notificacao);
    }

    /**
     * Adiciona um PedidoTransportadora aos Pedidos Pendentes(quando, por exemplo,
     * e criado um novo pedido pelo utilizador)
     *
     * @param p correspondente ao Pedido a adicionar
     */
    public void addPedidosPendentes(PedidoTransportadora p){
        this.pedidosPendentes.put(p.getCodigoPedido(),p);
    }

    /**
     * Metodo que trata de aceitar o transporte do Pedido por uma
     * Transportadora. Ira colocar o pedido como aceite, definir a data
     * de quando foi aceite, remover esse pedido dos Pedidos pendentes e
     * devolver esse mesmo Pedido(para futuramente poder ser manipulado)
     *
     * @param codEnc correspondente ao codigo da encomenda que se vai aceitar
     * @return do Pedido associado a esse codigo
     */
    public PedidoTransportadora aceitaTrasnportadora(String codEnc){
        PedidoTransportadora p = this.pedidosPendentes.get(codEnc);
        p.setAceite(true);
        p.setDataAceite(LocalDateTime.now());
        p = p.clone();
        this.pedidosPendentes.remove(codEnc);
        return p;
    }

    /**
     * Determina o numero de notificacoes que um Utilizador tem
     *
     * @return comprimento da lista de Notificacoes, ou seja, o numero de notificacoes
     */
    public int getNumeroNotificacaoes(){
        return this.notificacoes.size()+this.pedidosPendentes.size();
    }

    /**
     * Quando uma Transportadora ja tiver sido avaliada, trata de sinalizar esta como avaliada
     *
     * @param codTransportador correspondente ao codigo da transportadora que foi avaliada
     */
    public void removeDeAvaliacao(String codTransportador){
        for (PedidoCompleto p : this.historico.values()){
           if(p.getCodigoTransportadora().equals(codTransportador)){
               p.setAvaliado(true);
           }
        }
    }

    /**
     * Maneira como sera apresentada a informacao do utilizador no ecra
     *
     * @return informacoes do utilizador
     */
    public String toString(){
        return "Codigo: " + this.codUtilizador +
               "Nome: " + this.nome +
               "GPS" + this.gps.toString() +
               "Pedidos Pendentes: " + this.pedidosPendentes.toString() +
               "Notificacoes: " + this.notificacoes.toString() +
               "Historico: " + this.historico.toString();

    }

    /**
     * Verifica se um objecto e igual a um Utilizador
     *
     * @param o correspondente ao objecto
     * @return true tiverem as mesmas informaces
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Utilizador u = (Utilizador)o;

        return (this.codUtilizador.equals(u.getCodUtilizador()) &&
                this.getNome().equals(u.getNome()) &&
                this.getGps().equals(u.getGps()) &&
                this.pedidosPendentes.equals(u.getPedidosPendentes()) &&
                this.notificacoes.equals(u.getNotificacoes()) &&
                this.historico.equals(u.getHistorico()));
    }

    /**
     * Cria uma copia de um Utilizador
     *
     * @return da copia do Utilizador
     */
    public Utilizador clone(){
        return new Utilizador(this);
    }

}
