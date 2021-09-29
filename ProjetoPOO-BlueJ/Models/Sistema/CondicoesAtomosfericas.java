package Models.Sistema;

public enum CondicoesAtomosfericas {

    /**
     * Diferentes estados de tempo que podem existir, bem como os
     * os inteiros associados a cada um desses estados
     */
    SOL(0),NUBLADO(1),CHUVA(2);

    /**
     * Variavel Instancia
     */
    public int estadoTempo;

    /**
     * Construtor das condicoes atmosfericas, dado um estado tempo
     *
     * @param estadoTempo correspondente ao inteiro do estado de Tempo
     */
    CondicoesAtomosfericas(int estadoTempo){
        this.estadoTempo = estadoTempo;
    }

    /**
     * Devolve inteiro do estado de tempo
     *
     * @return valor do estado de tempo atual
     */
    public int getEstadoTempo(){
        return this.estadoTempo;
    }

    /**
     * Verifica se dois estados de tempo sao iguais
     *
     * @param valor correspondente a um estado de tempo
     * @return true caso sejam iguais
     */
    public boolean equals(int valor){
        return this.estadoTempo==valor;
    }
}
