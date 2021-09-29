package Models.Loja;

import Excepitions.EncomendaNotFoundException;
import Models.Utilizador.GPS;
import Models.Sistema.PedidoLoja;
import Models.Sistema.PedidoUtilizador;

import java.time.LocalDateTime;

public class LojaComFilasEspera extends Loja{

    /**
     * Variaveis de Instancia
     */
    private int ocupacao;

    /**
     * Construtor por Omissao
     */
    public LojaComFilasEspera(){
        super();
        this.ocupacao = 0;
    }

    /**
     * Construtor Parametrizado de LojaComFilasEspera
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public LojaComFilasEspera(String codLoja, String nomeLoja, GPS gps) {
        super(codLoja, nomeLoja, gps);
        this.ocupacao = 0;
    }

    /**
     * Construtor de cÛpia de uma LojaComFilasEspera
     * Aceita como parametros outra LojaComFilasEspera e utiliza os metodos
     * de acesso aos valores das Variaveis de Instancia
     */
    public LojaComFilasEspera(LojaComFilasEspera l) {
        super(l);
        this.ocupacao = l.getOcupacao();
    }

    /**
     * Metodo que retorna o numero de pessoas na fila da loja (n„o confundir com o numero de pedidos na fila de espera)
     * @return a ocupaÁao da loja
     */
    public int getOcupacao() {
        return this.ocupacao;
    }

    /**
     * Metodo que atualiza o numero de pessoas na fila da loja (n„o confundir com o numero de pedidos na fila de espera)
     * @param ocupacao nova ocupaÁao da loja
     */
    public void setOcupacao(int ocupacao) {
        this.ocupacao = ocupacao;
    }

    /**
     * Devolve o numero de pessoas em espera de forma qualitativa e nao quantitativa
     * @return numero de pessoas em espera de forma qualitativa
     */
    private String pessoasEsperaQualitativo(){
        String res = "None";

        if(this.ocupacao>10) res = "Muito";
        if(this.ocupacao>5 && this.ocupacao<10) res = "Moderado";
        if(this.ocupacao<5 && this.ocupacao>0) res = "Pouco";

        return res;
    }

    /**
     * Metodo que devolve um pedido que estava em espera em formato de aceite pela loja
     * Metodo que vai a lista de espera e aceita um pedido devolvendo o pedido da loja
     * @param pos posi√ßao da lista de espera a retirar o pedido
     * @return pedido da loja para alguem vir buscar a encomenda
     * @throws EncomendaNotFoundException caso a posicao dada nao exista
     */
    public PedidoLoja getPedidoUtilizador(int pos) throws EncomendaNotFoundException {
        try {
            PedidoUtilizador p = super.getListaEspera().get(pos).clone();
            boolean temMedicamentos = super.temMedicamentos(p);
            return new PedidoLoja(p, LocalDateTime.now(),super.getCodigoLoja(),super.getCord().clone(),temMedicamentos,this.pessoasEsperaQualitativo());

        }catch (NullPointerException | IndexOutOfBoundsException e){
            throw new EncomendaNotFoundException();
        }
    }

    /**
     * Verifica se 2 Objects sao iguais
     *
     * @return true se Object for igual a Loja
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        LojaComFilasEspera l = (LojaComFilasEspera) o;

        return (super.equals(l) &&
                this.ocupacao == l.getOcupacao());
    }

    /**
     * Metodo que replica esta LojaComFilasEspera com fila de espera
     * @return copia da LojaComFilasEspera
     */
    public LojaComFilasEspera clone(){
        return new LojaComFilasEspera(this);
    }
}
