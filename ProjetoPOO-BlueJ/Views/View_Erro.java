package Views;

public class View_Erro {

    /**
     * Variaveis Instancia
     */
    private String mensagem;

    /**
     * Construtor Parametrizado de View_Erro
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public View_Erro(String erro){
        this.mensagem = erro;
    }

    /**
     * Apresenta no ecra a mensagem de erro, esperando depois 1 segundo antes de terminar
     */
    public void run(){
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println(this.mensagem);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        try { Thread.sleep (1000); } catch (InterruptedException ignored) {}
    }
}
