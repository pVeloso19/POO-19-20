package Models.Sistema;

public enum Transito {

    /**
     * Diferentes tipos de transito que podem existir, bem como os
     * os inteiros associados a cada um desses tipos
     */
    INEXISTENTE(0), ACEITAVEL(1), CONGESTIONADO(2);

    /**
     * Variavel Instancia
     */
    public int estadoTransito;

    /**
     * Construtor do Transito, dado um estado de transito
     *
     * @param estadoTransito correspondente ao inteiro do estado do transito
     */
    Transito(int estadoTransito){
        this.estadoTransito = estadoTransito;
    }

    /**
     * Devolve inteiro do estado de transito
     *
     * @return valor do estado de transito atual
     */
    public int getEstadoTransito(){
        return this.estadoTransito;
    }

    /**
     * Verifica se dois estados de transito sao iguais
     *
     * @param valor correspondente a um estado de transito
     * @return true caso sejam iguais
     */
    public boolean equals(int valor){
        return this.estadoTransito==valor;
    }
}
