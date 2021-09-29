package Models.Transportadores;

import Models.Sistema.PedidoLoja;
import Models.Utilizador.GPS;

import java.io.Serializable;

public class VoluntarioEspecial extends Voluntario implements Serializable{

    /**
     * Variaveis Instancia
     */
    public boolean aceitoMedicamentos;

    /**
     * Construtor por Omissao
     */
    public VoluntarioEspecial(){
        super();
    }

    /**
     * Construtor Parametrizado de VoluntarioEspecial
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public VoluntarioEspecial(String codVoluntario, String nomeVoluntario, GPS cord, double raio, boolean aceitoM){
        super(codVoluntario, nomeVoluntario, cord, raio);
        this.aceitoMedicamentos = aceitoM;
    }

    /**
     * Construtor de copia de um VoluntarioEspecial
     * Aceita como parametros outro VoluntarioEspecial e utiliza os metodos
     * de acesso aos valores das Variaveis de Instancia
     */
    public VoluntarioEspecial(VoluntarioEspecial v){
        super(v);
        this.aceitoMedicamentos = v.isAceitoMedicamentos();
    }

    /**
     * Indica se aceita transportar medicamentos
     *
     * @return true caso consiga transportar medicamentos
     */
    public boolean isAceitoMedicamentos() {
        return aceitoMedicamentos;
    }

    /**
     * Definir se voluntario aceita ou nao transportar medicamentos
     *
     * @param aceitoMedicamentos correspondente ao novo estado de aceitacao de medicamentos
     */
    public void setAceitoMedicamentos(boolean aceitoMedicamentos) {
        this.aceitoMedicamentos = aceitoMedicamentos;
    }

    /**
     * Indica se aceita transportar medicamentos
     *
     * @return true caso consiga transportar medicamentos
     */
    public boolean aceitoTransporteMedicamentos(){
        return this.aceitoMedicamentos;
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
     * @return String com a informação da Classe
     */
    public String toString(){
        return super.toString() + "Transporta Medicamentos: " + this.aceitoMedicamentos;
    }

    /**
     * Verifica se 2 Objects sÃo iguais
     *
     * @return true se Object for igual ao Voluntario
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        VoluntarioEspecial t = (VoluntarioEspecial) o;

        return (super.equals(t) &&
                this.aceitoMedicamentos == t.isAceitoMedicamentos());
    }

    /**
     * Cria copia da VoluntarioEspecial
     *
     * @return VoluntarioEspecial correspondente á sua Copia
     */
    public VoluntarioEspecial clone(){
        return new VoluntarioEspecial(this);
    }
}
