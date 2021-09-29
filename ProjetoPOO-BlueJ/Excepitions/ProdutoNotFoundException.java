package Excepitions;

public class ProdutoNotFoundException extends Exception{

    /**
     * Construtor por Omissao
     */
    public ProdutoNotFoundException(){
        super();
    }

    /**
     * Construtor dada uma mensagem de erro que se pretenda apresentar no ecra
     * quando um Produto nao existe
     *
     * @param erro correspondente a mensagem que se pretende mostrar
     */
    public ProdutoNotFoundException(String erro){
        super(erro);
    }
}
