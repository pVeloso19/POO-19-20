package Models.Transportadores;

import Excepitions.ValorErradoException;
import Models.Sistema.I_PedidosTransportadores;
import Models.Sistema.PedidoCompleto;
import Models.Sistema.PedidoLoja;

import java.util.List;

public interface I_Transportadores {

    /**
     * Metodo que retorna o historico do transportador
     * @return uma lista com o historico
     */
    List<String> getListaHistorico();

    /**
     * Metodo que atualiza a velocidade media de um transportador
     * @param velocidadeMedia nova velocidade
     */
    void setVelocidadeMedia(double velocidadeMedia);

    /**
     * Metod responsavel por obter a velocidade media a que um transportador se desloca
     * @return a velocidade media de um transportador
     */
    double getVelocidadeMedia();

    /**
     * Metodo responsavel por atualizar o raio de acao de um transportador
     * @param raio novo raio de acao
     */
    void setRaio(double raio);

    /**
     * Metodo responsavel por obter o valor do raio de acao de um transportador
     * @return o valor do raio de acao de um transportador
     */
    double getRaio();

    /**
     * Metodo responsavel por obter o preco por kilometros de um transportador
     * @return o preco por kilometros de um transportador
     */
    double getPrecoPorKm();

    /**
     * Metodo responsavel por atualizar o preco por kilometros de um transportador
     * @param preco novo preco a usar
     */
    void setPrecoPorKm(double preco);

    /**
     * Metodo responsavel por finalizar a entrega de um transportador
     * @return o pedido que se originou ao entregar uma encomenda
     */
    PedidoCompleto finalizaEntrega();

    /**
     * Funcao responsavel por mudar um estado de disponibilidade de um transportador (True->False | False->True)
     */
    void mudaEstado();

    /**
     * Metodo responsavel por determinar se um transportador aceita o transporte de medicamentos ou nao.
     * @return se aceita transportar medicamentos.
     */
    boolean aceitoTransporteMedicamentos();

    /**
     * Metodo responsavel por aceitar uma encomenda
     * @return se for uma Trasnportadora retorna um pedido para o utilizador
     */
    I_PedidosTransportadores aceitarEncomenda();

    /**
     * Metodo responsavel por rejeitar uma encomenda
     * @return o pedido rejeitado de volta para o sistema
     */
    PedidoLoja rejeitarEncomenda();

    /**
     * Metodo responsavel por avaliar um transportador
     * @param aval valor a atribuir
     * @throws ValorErradoException caso o valor nao respeite os parametros (0-5)
     */
    void avaliaTransportador(int aval) throws ValorErradoException;

    /**
     * Funcao responsavel por determinar se o transportador aceita as caracteristicas da encomenda
     * @param p pedido com a encomenda para verificar se aceita
     * @return resultado se aceita ou naop a encomenda
     */
    boolean aceitaCaracteristicasEncomenda(PedidoLoja p);

    /**
     * Funcao responsavel por devolver o codigo de um transportador
     * @return o codigo de um transportador
     */
    String getCodigo();

    /**
     * Metodo responsavel por clonar um transportador
     * @return o clone do Transportador
     */
    I_Transportadores clone();

    /**
     * Metodo responsavel por devolver o total faturado por um transportador
     * @return total faturado por um transportador
     */
    double totalFaturado();
}
