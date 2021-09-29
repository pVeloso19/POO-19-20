import Controllers.Controller_Login;
import Models.Sistema.LeituraFicheiros;
import Models.TrazAqui;
import Views.View_Erro;
import Views.View_Login;

import java.io.IOException;

public class TrazerAquiApp {

    /**
     * Metodo que carrega os dados para o sistema.
     * Tenta carregar os dados em formato binário que permite recuperar o estado do ponto de paragem
     * Em caso de erro volta a recarregar os dados default que estão no ficheiro logs.
     * Se não conseguir ler os dados de nenhum dos ficheiros sai com codigo de erro 1.
     * @return o estado carregado do sistema.
     */
    public static TrazAqui carregaSitemaBinario(){
        TrazAqui s = null;

        try {
            s = LeituraFicheiros.carregaEstado("EstadoBinario.txt");
        } catch (IOException | ClassNotFoundException e) {

            View_Erro erro = new View_Erro("Erro ao carregar os dados. Recarregando sistema.");
            erro.run();

            try {
                s = LeituraFicheiros.lerFicheiro();
            } catch (IOException ioException) {
                System.out.println("Ficheiro Default em falta.");
                System.exit(1);
            }
        }

        return s;
    }

    public static void main(String[] args) {
        TrazAqui sistema = carregaSitemaBinario();

        View_Login view = new View_Login(new Controller_Login(sistema));
        view.run();

        try {
            sistema.guardaEstado("EstadoBinario.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
