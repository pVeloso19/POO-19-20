package Excepitions;

public class ValorErradoException extends Exception {

    /**
     * Construtor por Omissao
     */
    public ValorErradoException(){
        super();
    }

    /**
     * Construtor dada uma mensagem de erro que se pretenda apresentar no ecra
     * quando um Valor nao cumpre certos requisitos
     *
     * @param erro correspondente a mensagem que se pretende mostrar
     */
    public ValorErradoException(String erro){
        super(erro);
    }
}
