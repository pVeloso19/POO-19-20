package Models.Transportadores;

import Excepitions.ValorErradoException;
import Models.Utilizador.GPS;
import Models.Sistema.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Voluntario implements Serializable, I_Transportadores {

    /**
     * Variaveis Instancia
     */
    private String codVoluntario;
    private String nomeVoluntario;
    private GPS cord;
    private double raio;
    private double velocidadeMedia;
    private double classificacoes;
    private double numAvaliacoes;

    private boolean disponivel;

    private String encomendaAtual;

    private Map<String, PedidoVoluntario> historico;

    /**
     * Construtor por omissao de Voluntario.
     */
    public Voluntario(){
        this.codVoluntario = "NotDefine";
        this.nomeVoluntario = "NotDefine";
        this.cord = new GPS();
        this.raio = 0;
        this.disponivel = true;
        this.velocidadeMedia = 0.0;

        this.classificacoes = 0.0;
        this.numAvaliacoes = 0.0;

        this.historico = new HashMap<>();

        this.encomendaAtual = "None";
    }

    /**
     * Construtor parametrizado de Voluntario.
     * Aceita como parametros o nome, coordenadas GPS, raio de a�ao do voluntario.
     */
    public Voluntario(String codVoluntario, String nomeVoluntario, GPS cord, double raio){
        this.codVoluntario = codVoluntario;
        this.nomeVoluntario = nomeVoluntario;
        this.cord = cord.clone();
        this.raio = raio;
        this.disponivel = true;
        this.velocidadeMedia = 50;
        this.classificacoes = 0;
        this.numAvaliacoes = 0;
        this.historico = new HashMap<>();

        this.encomendaAtual = "None";
    }

    /**
     * Construtor de copia de Voluntario.
     * Aceita como parametro outro Voluntario e utiliza os metodos
     * de acesso aos valores das variaveis de instancia.
     */
    public Voluntario(Voluntario voluntario){
        this.codVoluntario = voluntario.getCodigo();
        this.nomeVoluntario = voluntario.getNomeVoluntario();
        this.cord = voluntario.getGPS();
        this.raio = voluntario.getRaio();
        this.disponivel = voluntario.getDisponivel();
        this.velocidadeMedia = voluntario.getVelocidadeMedia();
        this.classificacoes = voluntario.getClassificacoes();
        this.numAvaliacoes = voluntario.getNumAvaliacoes();
        this.historico = voluntario.getHistorico();

        this.encomendaAtual = voluntario.getEncomendaAtual();
    }
    
    /**
     * Metodo responsavel por devolver a encomenad que o voluntario transporta
     */
    public String getEncomendaAtual() {
        return this.encomendaAtual;
    }
    
    /**
     * Metodo responsavel por defenir a encomenda que o voluntario transporta
     */
    public void setEncomendaAtual(String encomendaAtual) {
        this.encomendaAtual = encomendaAtual;
    }

    /**
     * Devolve o nome do Voluntario.
     *
     * @return nome do Voluntario.
     */
    public String getNomeVoluntario(){
        return this.nomeVoluntario;
    }

    /**
     * Devolve as cordenadas GPS do Voluntario.
     *
     * @return as cordenadas GPS do Voluntario.
     */
    public GPS getGPS(){
        return this.cord.clone();
    }

    /**
     * Devolve o raio de opera�ao do Voluntario.
     *
     * @return o raio de opera�ao do Voluntario.
     */
    public double getRaio(){
        return this.raio;
    }

    /**
     * Devolve valor referente ao Disponivel
     *
     * @return valor disponivel
     */
    public boolean getDisponivel() {
        return this.disponivel;
    }

    /**
     * Devolve valor referente ao Disponivel
     *
     * @return valor disponivel
     */
    public double getVelocidadeMedia() {
        return this.velocidadeMedia;
    }

    /**
     * Devolve valor referente ao Disponivel
     *
     * @return valor disponivel
     */
    public double getClassificacoes() {
        return classificacoes;
    }

    /**
     * Devolve valor referente ao numAvaliacoes
     *
     * @return double numAvaliacoes
     */
    public double getNumAvaliacoes() {
        return numAvaliacoes;
    }

    /**
     * Devolve valor referente ao historico
     *
     * @return Map<LocalDateTime, PedidoVoluntario> disponivel
     */
    public Map<String, PedidoVoluntario> getHistorico() {
        Map<String, PedidoVoluntario> res = new HashMap<>();

        for(Map.Entry<String, PedidoVoluntario> e: this.historico.entrySet()){
            res.put(e.getKey(), e.getValue().clone());
        }
        return res;
    }

    /**
     * Atualiza Codigo do Voluntario
     *
     * @param codigo do Voluntario
     */
    public void setCodVoluntario(String codigo) {
        this.codVoluntario = codigo;
    }

    /**
     * Atualiza Nome da Empresa
     *
     * @param nome nomeEmpresa
     */
    public void setNomeVoluntario(String nome) {
        this.nomeVoluntario = nome;
    }

    /**
     * Atualiza GPS
     *
     * @param gps gps
     */
    public void setGPS(GPS gps) {
        this.cord = gps.clone();
    }

    /**
     * Atualiza Raio
     *
     * @param raio raio
     */
    public void setRaio(double raio) {
        this.raio = raio;
    }

    /**
     * Atualiza disponivel
     *
     * @param disponivel disponivel
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * Funcao Exigida pela Interface, que nao se aplica a voluntarios;
     * @param preco preco a ignorar
     */
    public void setPrecoPorKm(double preco){
        ;
    }

    /**
     * Retorna o preco medio de um voluntario
     * @return 0 que e o preco medio de um voluntario;
     */
    public double getPrecoPorKm(){
        return 0;
    }

    /**
     * Atualiza velocidadeMedia
     *
     * @param velocidadeMedia disponivel
     */
    public void setVelocidadeMedia(double velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    /**
     * Atualiza disponivel
     *
     * @param historico disponivel
     */

    public void setHistorico(Map<String, PedidoVoluntario> historico) {
        this.historico = new HashMap<>();

        for(Map.Entry<String, PedidoVoluntario> e: historico.entrySet()){
            this.historico.put(e.getKey(), e.getValue().clone());
        }
    }

    /**
     * Verifica se Voluntario está disponível para Transportar Encomenda
     *
     * @return true se estiver disponível
     */
    public boolean aceitaPedido(){
        return this.disponivel;
    }

    /**
     * Funcao que devolve se Voluntario transporta medicamentos
     *
     * @return true se trasnportar medicamentos
     */
    public abstract boolean aceitoTransporteMedicamentos();

    /**
     * Devolve informacao dos pedidos finalizados, ordenados por ordem
     * crescente da hora a que foi submetida uma resposta por parte do Utilizador
     *
     * @return lista com codigos dos pedidos ja finalizados
     */
    public List<String> getListaHistorico(){
        return this.historico.values().parallelStream().sorted((p1,p2)->(p1.getDataSubmissaoResposta().compareTo(p2.getDataSubmissaoResposta())==0)?1:p1.getDataSubmissaoResposta().compareTo(p2.getDataSubmissaoResposta()))
                   .map(PedidoVoluntario::toString).collect(Collectors.toList());
    }

    /**
     * Quando se pretender finalizar a entrega de um Pedido,
     * coloca-se o Voluntario como disponivel de novo,
     * sem nenhuma encomenda atribuida naquele momento e devolve-se um Pedido Completo
     * formado apartir do PedidoVoluntario
     *
     * @return do Pedido Completo, formado apartir do Pedido de Voluntario que foi finalizado
     */
    public PedidoCompleto finalizaEntrega(){
        PedidoVoluntario p = this.historico.get(this.encomendaAtual);
        this.disponivel = true;
        this.encomendaAtual = "None";
        return new PedidoCompleto(p);
    }

    /**
     * Muda o estado de disponibilidade de um Voluntario, ou seja,
     * se tivesse disponivel passa a estar indisponivel, e vice-versa
     */
    public void mudaEstado(){
        this.disponivel = !this.disponivel;
    }

    /**
     * Trata de aceitar uma Pedido atual que estava pendente, colocando esse Pedido como aceite
     * e mudando o seu estado para indisponivel
     *
     * @return null, pois como e voluntario aceita sempre
     */
    public I_PedidosTransportadores aceitarEncomenda(){
        this.disponivel = false;
        return null;
    }

    /**
     * Ao contrario da funcao aceitarEncomenda, esta ira rejeitar o Pedido atual que estava pendente,
     * indicando que atualmente nao tem nenhuma encomenda atribuida a si
     *
     * @return null
     */
    public PedidoLoja rejeitarEncomenda(){
        return null;
    }

    /**
     * Trata de ocupar um Voluntario com um Pedido de uma Loja, colocando depois este
     * como indisponivel e preenchendo tambem os kms percorridos por este
     *
     * @param p correspondente ao Pedido da Loja
     */
    public I_PedidosTransportadores ocupaVoluntario(PedidoLoja p){
        this.encomendaAtual = p.getCodigoPedido();
        this.disponivel = false;
        double km = this.cord.distancia(p.getGpsLoja()) + p.getGpsLoja().distancia(p.getGpsUtilizador());
        PedidoVoluntario v = new PedidoVoluntario(p,this.codVoluntario,this.nomeVoluntario,LocalDateTime.now(),LocalDateTime.now(),true,this.classificacoes,km);
        this.historico.put(v.getCodigoPedido(),v.clone());
        return v.clone();
    }

    /**
     * Funcao que vai verificar se Transportadora cumpre requisitos minimos para
     * aceitar um Pedido
     *
     * @param p correspondente ao PedidoLoja
     * @return true se esta Voluntario estiver disponivel, nao estiver ocupada com
     * nenhuma encomenda, se esse pedido ja nao tiver no historico do Voluntario,
     * e se estiver a uma distancia da loja e do utilizador que seja valida
     */
    public boolean aceitaCaracteristicasEncomenda(PedidoLoja p){

        if(!this.disponivel) return false;

        if(!this.encomendaAtual.equals("None")) return false;

        if(this.historico.get(p.getCodigoPedido())!=null) return false;

        GPS loja = p.getGpsLoja();
        if(this.cord.distancia(loja)>this.raio) return false;

        GPS utilizador = p.getGpsUtilizador();
        if(this.cord.distancia(utilizador)>this.raio) return false;

        return true;
    }

    /**
     * Atribuir uma classificacao ao Voluntario
     *
     * @param aval correspondente a avaliacao a ser atribuida a este
     * @throws ValorErradoException caso o Utilizador insira uma classificacao
     * que nao se encontre no intervalo valido(se inserir classificacao menor que 0
     * ou maior que 5)
     */
    public void avaliaTransportador(int aval) throws ValorErradoException {
        if(aval<0 || aval>5) throw new ValorErradoException("Valor invalido.");
        this.numAvaliacoes++;
        this.classificacoes = ((this.classificacoes+aval)/this.numAvaliacoes);
    }

    /**
     * Atualiza Codigo do Voluntario
     *
     * @param codigo do Voluntario
     */
    public String getCodigo(){
        return this.codVoluntario;
    }

    /**
     * Uma vez que se trata de um Voluntario, nao tera lucro monetario
     *
     * @return sempre 0
     */
    public double totalFaturado(){
        return 0.0;
    }

    /**
     * Permite apresentar no ecrã as informações necessárias
     *
     * @return String com infotmação da Classe
     */
    public String toString(){
        return this.codVoluntario +
                this.nomeVoluntario +
                this.cord.toString() +
                this.raio +
                this.disponivel +
                this.velocidadeMedia +
                this.classificacoes +
                this.numAvaliacoes +
                this.historico.toString();
    }

    /**
     * Verifica se 2 Objects são iguais
     *
     * @return true se Object for igual à Voluntario
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Voluntario t = (Voluntario) o;

        return (this.codVoluntario.equals(t.getCodigo()) &&
                this.nomeVoluntario.equals(t.getNomeVoluntario()) &&
                this.cord.equals(t.getGPS()) &&
                this.raio == t.getRaio() &&
                this.disponivel == t.getDisponivel() &&
                this.velocidadeMedia == t.getVelocidadeMedia() &&
                this.classificacoes == t.getClassificacoes() &&
                this.numAvaliacoes == t.getNumAvaliacoes() &&
                this.historico.equals(t.getHistorico()));
    }

    /**
     * Cria copia da Voluntario
     *
     * @return Voluntario correspondente � sua Copia
     */
    public abstract Voluntario clone();
}
