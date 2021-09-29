package Models.Sistema;

public enum EstadoEstrada {

    /**
     * Diferentes estados da estrada que podem existir, bem como os
     * os inteiros associados a cada um desses estados
     */
    BOM(0), RAZOAVEL(1), PESSIMO(2);

    /**
     * Variavel Instancia
     */
    public int estadoEstrada;

    /**
     * Construtor do EstadoEstrada, dado um estado de Estrada
     *
     * @param estadoEstrada correspondente ao inteiro do estado da estrada
     */
    EstadoEstrada(int estadoEstrada){
        this.estadoEstrada = estadoEstrada;
    }

    /**
     * Devolve inteiro do estado da estrada
     *
     * @return valor do estado da estrada atual
     */
    public int getEstadoEstrada(){
        return this.estadoEstrada;
    }

    /**
     * Verifica se dois estados de estrada sao iguais
     *
     * @param valor correspondente a um estado de estrada
     * @return true caso sejam iguais
     */
    public boolean equals(int valor){
        return this.estadoEstrada==valor;
    }
}
