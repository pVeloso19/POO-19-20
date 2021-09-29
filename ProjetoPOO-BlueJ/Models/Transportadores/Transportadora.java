package Models.Transportadores;
import Excepitions.ValorErradoException;
import Models.Encomendas.LinhaEncomenda;
import Models.Sistema.I_PedidosTransportadores;
import Models.Sistema.PedidoCompleto;
import Models.Sistema.PedidoLoja;
import Models.Sistema.PedidoTransportadora;
import Models.Utilizador.GPS;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Transportadora implements Serializable, I_Transportadores {

    /**
     * Variaveis Instancia
     */
    private String codEmpresa;
    private String nomeEmpresa;
    private GPS gps;
    private String nif;
    private double raio;
    private double precoPorKm;
    private boolean disponivel;
    private double velocidadeMedia;

    private double classificacoes;
    private double numAvaliacoes;

    private Map<String, PedidoTransportadora> historico;

    private String codEncomendaAtual;

    /**
     * Construtor por omissao de Transportadora
     */
    public Transportadora() {
        this.codEmpresa = "";
        this.nomeEmpresa = "";
        this.gps = new GPS();
        this.nif = "";
        this.raio = 0;
        this.precoPorKm = 0;
        this.disponivel = true;
        this.velocidadeMedia = 0.0;
        this.classificacoes = 0.0;
        this.numAvaliacoes = 0;
        this.historico = new HashMap<>();
        this.codEncomendaAtual = "None";
    }

    /**
     * Construtor Parametrizado de Transportadora
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public Transportadora(String a, String b, GPS g, String c, double r, double p) {
        this.codEmpresa = a;
        this.nomeEmpresa = b;
        this.gps = g.clone();
        this.nif = c;
        this.raio = r;
        this.precoPorKm = p;

        this.disponivel = true;
        this.velocidadeMedia = 50;

        this.classificacoes = 0;
        this.numAvaliacoes = 0;

        this.historico = new HashMap<>();

        this.codEncomendaAtual = "None";
    }

    /**
     * Construtor de copia de uma Transportadora
     * Aceita como parametros outra Transportadora e utiliza os metodos
     * de acesso aos valores das Variaveis de Instancia
     */
    public Transportadora(Transportadora t) {
        this.codEmpresa = t.getCodigo();
        this.nomeEmpresa = t.getNomeEmpresa();
        this.gps = t.getGPS();
        this.nif = t.getNIF();
        this.raio = t.getRaio();
        this.precoPorKm = t.getPrecoPorKm();

        this.disponivel = t.getDisponivel();
        this.velocidadeMedia = t.getVelocidadeMedia();

        this.classificacoes = t.getClassificacoes();
        this.numAvaliacoes = t.getNumAvaliacoes();

        this.historico = t.getHistorico();
        this.codEncomendaAtual = t.getCodEncomendaAtual();
    }

    /**
     * Funcao responsavel por devolver o codigo da empresa
     * @return codigo da empresa
     */
    public String getCodigo(){
        return this.codEmpresa;
    }

    /**
     * Devolve valor referente ao Nome Empresa
     *
     * @return String nomeEmpresa
     */
    public String getNomeEmpresa() {
        return this.nomeEmpresa;
    }

    /**
     * Devolve valor referente ao GPS
     *
     * @return GPS gps
     */
    public GPS getGPS() {
        return this.gps.clone();
    }

    /**
     * Devolve valor referente ao NIF
     *
     * @return String nif
     */
    public String getNIF() {
        return this.nif;
    }

    /**
     * Devolve valor referente ao Raio
     *
     * @return double raio
     */
    public double getRaio() {
        return this.raio;
    }

    /**
     * Devolve valor referente ao PrecoPorKm
     *
     * @return double precoPorKm
     */
    public double getPrecoPorKm() {
        return this.precoPorKm;
    }

    /**
     * Devolve valor referente ao Disponivel
     *
     * @return double disponivel
     */
    public boolean getDisponivel() {
        return this.disponivel;
    }

    /**
     * Devolve valor referente a Velocidade Media
     *
     * @return double velocidadeMedia
     */
    public double getVelocidadeMedia() {
        return this.velocidadeMedia;
    }

    /**
     * Devolve valor referente à classificacao
     *
     * @return double classificacao
     */
    public double getClassificacoes() {
        return classificacoes;
    }

    /**
     * Definir nova classificacao de uma Transportadora
     *
     * @param classificacoes correspodente aa nova classificacao
     */
    public void setClassificacoes(double classificacoes) {
        this.classificacoes = classificacoes;
    }

    /**
     * Funcao que devolve o numero de avaliacoes atribuidas a Transportadora
     *
     * @return numero de avaliacoes
     */
    public double getNumAvaliacoes() {
        return numAvaliacoes;
    }

    /**
     * Definir o novo numero de avaliacoes da Transportadora
     *
     * @param numAvaliacoes correspondente ao novo numero de avaliacoes
     */
    public void setNumAvaliacoes(double numAvaliacoes) {
        this.numAvaliacoes = numAvaliacoes;
    }

    /**
     * Devolve valor referente ao Disponivel
     *
     * @return valor disponivel
     */
    public Map<String, PedidoTransportadora> getHistorico() {
        Map<String, PedidoTransportadora> res = new HashMap<>();
        for(Map.Entry<String, PedidoTransportadora> e: this.historico.entrySet()){
            res.put(e.getKey(), e.getValue().clone());
        }
        return res;
    }

    /**
     * Devolve valor referente ao PedidoLoja
     *
     * @return PedidoLoja disponivel
     */
    public PedidoTransportadora getPedidoTransportadora() {
        return this.historico.get(this.codEncomendaAtual);
    }

    /**
     * Devolve valor referente ao codProd
     *
     * @return String disponivel
     */
    public String getCodEncomendaAtual() {
        return this.codEncomendaAtual;
    }

    /**
     * Atualiza Código da Empresa
     *
     * @param codigo codEmpresa
     */
    public void setCodEmpresa(String codigo) {
        this.codEmpresa = codigo;
    }

    /**
     * Atualiza Nome da Empresa
     *
     * @param nome nomeEmpresa
     */
    public void setNomeEmpresa(String nome) {
        this.nomeEmpresa = nome;
    }

    /**
     * Atualiza GPS
     *
     * @param gps gps
     */
    public void setGPS(GPS gps) {
        this.gps = gps.clone();
    }

    /**
     * Atualiza NIF da Empresa
     *
     * @param nif nif
     */
    public void setNIF(String nif) {
        this.nif = nif;
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
     * Atualiza PrecoPorKm
     *
     * @param preco precoPorKm
     */
    public void setPrecoPorKm(double preco) {
        this.precoPorKm = preco;
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
    public void setHistorico(Map<String, PedidoTransportadora> historico) {
        this.historico = new HashMap<>();
        for(Map.Entry<String, PedidoTransportadora> e: historico.entrySet()){
            this.historico.put(e.getKey(), e.getValue().clone());
        }
    }

    /**
     * Atualiza disponivel
     *
     * @param p disponivel
     */

    public void setCodEncomendaAtual(String p) {
        this.codEncomendaAtual = p;
    }

    /**
     * Verifica se Transportadora está disponível para Transportar Encomenda
     *
     * @return true se estiver disponível
     */
    public boolean aceitaPedido(){
        return this.disponivel;
    }

    /**
     * Verifica se Transportadora está disponível para Transportar Encomenda
     *
     * @return true se estiver disponível
     */
    public boolean alteraEstado(){
        return !this.disponivel;
    }

    /**
     * Calcula Preco de Transporte
     *
     * @return double correspondente a esse valor
     */
    public abstract double precoTransporte(PedidoLoja p);

    /**
     * Calcula Tempo de Transporte
     *
     * @return double correspondente a esse valor
     */
    public abstract double tempoTransporte(PedidoLoja p);

    /**
     * Verifica se uma transportadora, no dado momento, aceita transportar medicamentos
     *
     * @return true se estiver certificada para transportar medicamentos e se nesse momento aceitar
     * fazer transporte de medicamentos
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
                   .map(PedidoTransportadora::toString).collect(Collectors.toList());
    }

    /**
     * Calcula a distancia da Transportadora a Loja onde tem de ir buscar o Pedido, e da
     * distancia da loja ao Utilizador, sabendo assim a distancia total que ira percorrer
     *
     * @param p correspondente ao PedidoLoja
     * @return da distancia total que ira percorrer da sua localizacao ate a loja, e da loja
     * ao utilizador
     */
    public double calculaDistancia(PedidoLoja p){
        return this.getGPS().distancia(p.getGpsLoja()) + p.getGpsLoja().distancia(p.getGpsUtilizador());
    }

    /**
     * Com base no estado da fila de espera na loja onde tera que ir buscar a encomenda,
     * ira ser calculado tempo de espera
     *
     * @param estado correspodente ao estado da fila de espera na Loja
     * @return tempo de espera
     */
    public double calculaTempoEspera(String estado){
        double r = 0.28;
        switch (estado){
            case "Muito" : r = 0.5;
            case "Moderado" : r = 0.25;
            case "Pouco" : r = 0.01;
        }
        return r;
    }

    /**
     * Com base no peso de uma encomenda ira determinar a taxa deste para o preco de transporte,
     * onde quanto mais pesado este for, maior sera a sua taxa de transporte
     *
     * @param peso correspondente ao peso da encomenda
     * @return taxa associado ao seu peso
     */
    public double calculaTaxaPeso(double peso){
        double r = 1;
        if(peso>10 && peso<50) r+=0.1;
        if(peso>50) r+=0.4;
        return r;
    }

    /**
     * Quando se pretender finalizar a entrega de um Pedido,
     * coloca-se a transportadora como disponivel de novo,
     * sem nenhuma encomenda atribuida naquele momento e devolve-se um Pedido Completo
     * formado apartir do Pedido da Transportadora
     *
     * @return do Pedido Completo, formado apartir do Pedido de transportadora que foi finalizado
     */
    public PedidoCompleto finalizaEntrega(){
        PedidoTransportadora p = this.historico.get(this.codEncomendaAtual).clone();
        this.disponivel = true;
        this.codEncomendaAtual = "None";
        return new PedidoCompleto(p);
    }

    /**
     * Muda o estado de disponibilidade de uma Transportadora, ou seja,
     * se tivesse disponivel passa a estar indisponivel, e vice-versa
     */
    public void mudaEstado(){
       this.disponivel = !this.disponivel;
    }

    /**
     * Trata de ocupar uma Transportadora com um Pedido de uma Loja
     *
     * @param p correspondente ao Pedido da Loja
     * @return de um Pedido Transportadora formado apartir desse Pedido Loja
     */
    public abstract I_PedidosTransportadores ocupaTransportadora(PedidoLoja p);

    /**
     * Funcao que vai verificar se Transportadora cumpre requisitos minimos para
     * aceitar um Pedido
     *
     * @param p correspondente ao PedidoLoja
     * @return true se esta transportadora estiver disponivel, nao estiver ocupada com
     * nenhuma encomenda, se esse pedido ja nao tiver no historico da transportadora,
     * e se estiver a uma distancia da loja e do utilizador que seja valida
     */
    public boolean aceitaCaracteristicas(PedidoLoja p){

        if(!this.disponivel) return false;

        if(!this.codEncomendaAtual.equals("None")) return false;

        if(this.historico.get(p.getCodigoPedido())!=null) return false;

        GPS loja = p.getGpsLoja();
        if(this.gps.distancia(loja)>this.raio) return false;

        GPS utilizador = p.getGpsUtilizador();
        if(this.gps.distancia(utilizador)>this.raio) return false;

        return true;
    }
    
    /**
     * Funcao que vai verificar se Transportadora cumpre todos os requisitos para aceitar um Pedido
     *
     * @param p correspondente ao PedidoLoja
     * @return true se esta transportadora estiver disponivel, nao estiver ocupada com
     * nenhuma encomenda, se esse pedido ja nao tiver no historico da transportadora,
     * e se estiver a uma distancia da loja e do utilizador que seja valida
     */
    public abstract boolean aceitaCaracteristicasEncomenda(PedidoLoja p);

    /**
     * Recebe uma sugestao de um PedidoLoja
     *
     * @param p correspondente ao PedidoLoja
     * @return PedidoTransportadora criado a partir do PedidoLoja
     */
    public PedidoTransportadora recebeSugestao(PedidoLoja p){
        String tempoPrevisto = String.format("%f horas",this.tempoTransporte(p));
        PedidoTransportadora pt = new PedidoTransportadora(p,this.getCodigo(),this.getNomeEmpresa(),this.precoTransporte(p), LocalDateTime.now(), LocalDateTime.now(), false, tempoPrevisto,this.getClassificacoes(),this.calculaDistancia(p));
        return pt.clone();
    }

    /**
     * Atribuir uma classificacao a transportadora
     *
     * @param aval correspondente a avaliacao a ser atribuida a esta
     * @throws ValorErradoException caso o Utilizador insira uma classificacao
     * que nao se encontre no intervalo valido(se inserir classificacao menor que 0
     * ou maior que 5)
     */
    public void avaliaTransportador(int aval)throws ValorErradoException {
        if(aval<0 || aval>5) throw new ValorErradoException("Valor invalido.");

        this.numAvaliacoes++;
        this.classificacoes = ((this.classificacoes+aval)/this.numAvaliacoes);
    }

    /**
     * Calcula o total Faturado pela Transportadora associado a todos os transportes que realizou
     *
     * @return valor do acumulado de faturacao de cada Transporte que realizou
     */
    public double totalFaturado(){
        double fat = 0.0;

        for (PedidoTransportadora p : this.historico.values()){
            fat += p.getPreco();
        }
        return fat;
    }

    /**
     * Trata de aceitar uma Pedido atual que estava pendente, colocando esse Pedido como aceite
     * e mudando o seu estado para indisponivel
     *
     * @return I_PedidosTransportadores que foi aceite
     */
    public I_PedidosTransportadores aceitarEncomenda(){
        PedidoTransportadora p = this.historico.get(this.codEncomendaAtual);
        p.setAceite(true);
        this.disponivel = false;
        return p.clone();
    }

    /**
     * Ao contrario da funcao aceitarEncomenda, esta ira rejeitar o Pedido atual que estava pendente,
     * indicando que atualmente nao tem nenhuma encomenda atribuida a si
     *
     * @return do Pedido que foi Rejeitado
     */
    public PedidoLoja rejeitarEncomenda(){
        PedidoTransportadora p = this.historico.get(this.codEncomendaAtual);
        this.codEncomendaAtual = "None";

        return p.getPedidoLoja().clone();
    }

    /**
     * Adiciona um Pedido ao seu Historico
     *
     * @param p correspondente ao Pedido a adicionar ao Historico
     */
    public void adicionaHistorico(PedidoTransportadora p){
        this.historico.put(p.getCodigoPedido(),p.clone());
    }

    /**
     * Permite apresentar no ecra as informacoes necessarias
     *
     * @return String com informacao da Classe
     */
    public String toString(){
        return this.codEmpresa +
                this.nomeEmpresa +
                this.gps.toString() +
                this.nif +
                this.raio +
                this.precoPorKm +
                this.disponivel +
                this.velocidadeMedia +
                this.classificacoes +
                this.numAvaliacoes +
                this.historico.toString();
    }

    /**
     * Verifica se 2 Objects sao iguais
     *
     * @return true se Object for igual a Transportadora
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Transportadora t = (Transportadora)o;

        return (this.codEmpresa.equals(t.getCodigo()) &&
                this.nomeEmpresa.equals(t.getNomeEmpresa()) &&
                this.gps.equals(t.getGPS()) &&
                this.nif.equals(t.getNIF()) &&
                this.raio == t.getRaio() &&
                this.precoPorKm == t.getPrecoPorKm() &&
                this.disponivel == t.getDisponivel() &&
                this.velocidadeMedia == t.getVelocidadeMedia() &&
                this.classificacoes == t.getClassificacoes() &&
                this.numAvaliacoes == t.getNumAvaliacoes() &&
                this.historico.equals(t.getHistorico()) &&
                this.codEncomendaAtual.equals(t.getCodEncomendaAtual()));
    }

    /**
     * Cria copia da Transportadora
     *
     * @return Transportadora correspondente a sua Copia
     */
    public abstract Transportadora clone();
}
