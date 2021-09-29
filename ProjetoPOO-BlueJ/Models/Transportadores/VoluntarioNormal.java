package Models.Transportadores;

import Models.Sistema.PedidoLoja;
import Models.Utilizador.GPS;

import java.io.Serializable;

public class VoluntarioNormal extends Voluntario implements Serializable{

    /**
     * Construtor por Omissao
     */
    public VoluntarioNormal(){
        super();
    }

    /**
     * Construtor Parametrizado de VoluntarioNormal
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public VoluntarioNormal(String codVoluntario, String nomeVoluntario, GPS cord, double raio){
        super(codVoluntario, nomeVoluntario, cord, raio);
    }

    /**
     * Construtor de copia de um VoluntarioEspecial
     * Aceita como parametros outro VoluntarioEspecial e utiliza os metodos
     * de acesso aos valores das Variaveis de Instancia
     */
    public VoluntarioNormal(VoluntarioNormal v){
        super(v);
    }

    /**
     * Como se trata de um Voluntario Normal, nunca ira transportar medicamentos
     *
     * @return sempre false
     */
    public boolean aceitoTransporteMedicamentos(){
        return false;
    }

    /**
     * Determina se aceita caracteristicas da encomenda, caso esta contenha algum tipo de
     * medicamento
     *
     * @param p correspondente ao PedidoLoja
     * @return true se aceitar transportar
     */
    public boolean aceitaCaracteristicasEncomenda(PedidoLoja p){
        if(!super.aceitaCaracteristicasEncomenda(p)) return false;
        return (p.isTemMedicamentos()) ? this.aceitoTransporteMedicamentos() : true;
    }

    /**
     * Permite apresentar no ecra as informaçoes necessarias
     *
     * @return String com infotmação da Classe
     */
    public String toString(){
        return super.toString();
    }

    /**
     * Verifica se 2 Objects sÃo iguais
     *
     * @return true se Object for igual ao Voluntario
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        VoluntarioNormal t = (VoluntarioNormal) o;

        return (super.equals(t));
    }

    /**
     * Cria copia da VoluntarioNormal
     *
     * @return VoluntarioNormal correspondente á sua Copia
     */
    public VoluntarioNormal clone(){
        return new VoluntarioNormal(this);
    }
}
