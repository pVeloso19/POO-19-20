package Models.Sistema;

import java.io.Serializable;

public class Perfil implements Serializable {

    /**
     * Variaveis Instancia
     */
    private TiposUtilizadores tiposUtilizadores;

    private String email;
    private String pass;
    private String codigo;

    /**
     * Construtor Parametrizado de Perfil
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public Perfil(TiposUtilizadores tipo, String codigo, String email, String pass) {
        this.codigo = codigo;
        this.tiposUtilizadores = tipo;
        this.email = email;
        this.pass = pass;
    }

    /**
     * Construtor de cópia de uma Perfil
     * Aceita como parametros outro Perfil e utiliza os métodos
     * de acesso aos valores das Variaveis de Instancia
     */
    public Perfil(Perfil p){
        this.codigo = p.getCodigo();
        this.tiposUtilizadores = p.getTipo();
        this.email = p.getEmail();
        this.pass = p.getPass();
    }

    /**
     * Devolve codigo do Perfil
     *
     * @return codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Definir novo codigo do Perfil
     *
     * @param codigo correspondente ao novo codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Devolve Tipo de Utilizador que esta a operar
     *
     * @return tipo de utilizadores
     */
    public TiposUtilizadores getTipo() {
        return this.tiposUtilizadores;
    }

    /**
     * Definir novo tipo de Utilizador
     *
     * @param tipo correspondente ao novo tipo
     */
    public void setTipo(TiposUtilizadores tipo) {
        this.tiposUtilizadores = tipo;
    }

    /**
     * Devolve email do Perfil
     *
     * @return email do Perfil
     */
    public String getEmail() {
        return email;
    }

    /**
     * Definir novo email do Perfil
     *
     * @param email correspondente ao novo email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Devolve pass do Perfil
     *
     * @return pass do Perfil
     */
    public String getPass() {
        return pass;
    }

    /**
     * Definir nova pass do Perfil
     *
     * @param pass correspondente a nova pass
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Permite apresentar no ecra as informacoes necessarias
     *
     * @return String com informacao da Classe
     */
    public String toString() {
        return "Tipo: " + this.tiposUtilizadores +
               "Codigo: " + this.codigo +
               "Email: " + this.email +
               "Password: " + this.pass;
    }

    /**
     * Verifica se 2 Objects sao iguais
     *
     * @return true se Object for igual ao Perfil
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Perfil p = (Perfil) o;

        return (this.tiposUtilizadores.equals(p.getTipo()) &&
                this.codigo.equals(p.getCodigo()) &&
                this.email.equals(p.getEmail()) &&
                this.pass.equals(p.getPass()));
    }

    /**
     * Cria copia do Perfil
     *
     * @return Perfil correspondente a sua Copia
     */
    public Perfil clone(){
        return new Perfil(this);
    }
}
