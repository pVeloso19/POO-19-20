package Excepitions;

public class EncomendaNotFoundException extends Exception{

    /**
     * Construtor por Omissao
     */
    public EncomendaNotFoundException(){
        super();
    }

    /**
     * Construtor dada uma mensagem de erro que se pretenda apresentar no ecra
     * quando uma encomenda nao existe
     *
     * @param erro correspondente a mensagem que se pretende mostrar
     */
    public EncomendaNotFoundException(String erro){
        super(erro);
    }
}
