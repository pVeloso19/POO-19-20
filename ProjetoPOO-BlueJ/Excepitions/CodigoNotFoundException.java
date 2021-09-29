package Excepitions;

public class CodigoNotFoundException extends Exception{
    public CodigoNotFoundException(){
        super();
    }

    public CodigoNotFoundException(String erro){
        super(erro);
    }
}
