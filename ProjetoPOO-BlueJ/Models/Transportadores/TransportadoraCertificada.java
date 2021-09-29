package Models.Transportadores;

import Models.Sistema.Aleatoridade;
import Models.Utilizador.GPS;
import Models.Sistema.I_PedidosTransportadores;
import Models.Sistema.PedidoLoja;
import Models.Sistema.PedidoTransportadora;

import java.time.LocalDateTime;

public class TransportadoraCertificada extends Transportadora {

    /**
     * Variaveis Instancia
     */
    private boolean transportaMedicamentos;

    /**
     * Construtor por Omissao
     */
    public TransportadoraCertificada(){
        super();
    }

    /**
     * /**
     * Construtor Parametrizado de TransportadoraCertificada
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public TransportadoraCertificada(String a, String b, GPS g, String c, double r, double p, boolean med) {
        super(a, b, g, c, r, p);
        this.transportaMedicamentos = med;
    }

    /**
     * Construtor de copia de uma TransportadoraCertificada
     * Aceita como parametros outra TransportadoraCertificada e utiliza os metodos
     * de acesso aos valores das Variaveis de Instancia
     */
    public TransportadoraCertificada(TransportadoraCertificada t){
        super(t);
    }


    /**
     * Calcula o preco que estara associado ao transporte do Pedido. Esse preco
     * sera afetado por fatores como distancia total a percorrer, uma taxa associada ao peso que
     * a encomenda tem e uma vez que transporta medicamentos, ira ter uma taxa associada ao seu transporte
     * caso essa encomenda contenha medicamentos
     *
     * @param p correspondente ao PedidoLoja
     * @return do preco total do transporte
     */
    public double precoTransporte(PedidoLoja p){
        double distanciaLoja = super.getGPS().distancia(p.getGpsLoja());
        double distanciaUtilizador = p.getGpsLoja().distancia(p.getGpsUtilizador());
        double taxaPeso = super.calculaTaxaPeso(p.getEncomenda().getPeso());
        double taxaPorAceitarMedicamentos = 0.01 + ((p.isTemMedicamentos()) ? 1.01 : 1);

        return (((distanciaLoja + distanciaUtilizador)*super.getPrecoPorKm())*taxaPorAceitarMedicamentos)*taxaPeso;
    }


    /**
     * Determina o tempo de transporte de uma Encomenda a um Utilizador.
     * Este tempo sera afetado por fatores como distancias quer a loja quer ao utilizador,
     * tempo associado ao transporte ou nao de medicamentos e do tempo de espera na fila de uma loja
     * (caso esta exista). Para além disso, decidimos inserir fatores de aleatoriedade que afetarao o tempo de transporte.
     * Fatores como estado do clima, estado do pavimento e transito sao fatores que achamos adequados e realistas para
     * afetar o tempo de transporte.
     *
     * @param p correspondente ao PedidoLoja
     * @return tempo associado ao Transporte da encomenda
     */
    public double tempoTransporte(PedidoLoja p){
        double distanciaLoja = super.getGPS().distancia(p.getGpsLoja());
        double distanciaUtilizador = p.getGpsLoja().distancia(p.getGpsUtilizador());
        double tempoMenosPorTransportarMedicamentos = -0.01 + ((p.isTemMedicamentos()) ? 0.96 : 1);
        double taxaEsperaLoja = super.calculaTempoEspera(p.getEstadoEsperaLoja());

        return ((((distanciaLoja + distanciaUtilizador)/super.getVelocidadeMedia())+taxaEsperaLoja)*tempoMenosPorTransportarMedicamentos) * Aleatoridade.geraTaxaAleatoridade();
    }

    /**
     * Definir novo estado para transporte de medicamentos
     *
     * @param transportaMedicamentos correspondente ao novo valor de transportaMedicamentos
     */
    public void setTransportaMedicamentos(boolean transportaMedicamentos) {
        this.transportaMedicamentos = transportaMedicamentos;
    }

    /**
     * Funcao que devolve se transportadora transporta medicamentos
     *
     * @return true se trasnportar medicamentos
     */
    public boolean aceitoTransporteMedicamentos(){
        return this.transportaMedicamentos;
    }

    /**
     * Ocupa Transportadora com um Pedido, indicando o tempo previsto de transporte
     * e criando um Pedido Transportadora com base no Peido Loja
     *
     * @param p correspondente ao Pedido da Loja
     * @return PedidoTransportadora criado
     */
    public I_PedidosTransportadores ocupaTransportadora(PedidoLoja p){
        super.setCodEncomendaAtual(p.getCodigoPedido());
        String tempoPrevisto = String.format("%f horas",this.tempoTransporte(p));
        PedidoTransportadora pt = new PedidoTransportadora(p,super.getCodigo(),super.getNomeEmpresa(),this.precoTransporte(p),LocalDateTime.now(),LocalDateTime.now(),false, tempoPrevisto,super.getClassificacoes(),this.calculaDistancia(p));
        super.adicionaHistorico(pt);
        return pt;
    }

    /**
     * Determina se aceita caracteristicas da encomenda, caso esta contenha algum tipo de
     * medicamento
     *
     * @param p correspondente ao PedidoLoja
     * @return true se aceitar transportar
     */
    public boolean aceitaCaracteristicasEncomenda(PedidoLoja p){
        if(!super.aceitaCaracteristicas(p)) return false;
        return true;
    }

    /**
     * Permite apresentar no ecra as informacoes necessarias
     *
     * @return String com informacao da Classe
     */
    public String toString(){
        return super.toString() + "Transporte Medicamentos: " + this.transportaMedicamentos;
    }

    /**
     * Verifica se 2 Objects sao iguais
     *
     * @return true se Object for igual a TransportadoraCertificada
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        TransportadoraCertificada t = (TransportadoraCertificada) o;

        return (super.equals(t) &&
                this.transportaMedicamentos == t.aceitoTransporteMedicamentos());
    }

    /**
     * Cria cópia da Transportadora
     *
     * @return Transportadora correspondente à sua Cópia
     */
    public TransportadoraCertificada clone(){
        return new TransportadoraCertificada(this);
    };
}
