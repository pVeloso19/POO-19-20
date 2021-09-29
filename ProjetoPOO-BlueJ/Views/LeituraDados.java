package Views;

import java.util.Scanner;

public class LeituraDados {

    /**
     * Le do stdin
     *
     * @return input do Utilizador
     */
    public static String lerString() {
        Scanner input = new Scanner(System.in);
        return input.next();
    }

    /**
     * Converter para String a opcao do Utilizador
     *
     * @return string correspodente ao Inteiro
     */
    public static String lerIntComoString() {
        Scanner input = new Scanner(System.in);
        boolean ok = false;
        String txt = "";
        while(!ok) {
            try {
                txt = input.next();
                Integer.parseInt(txt);
                ok = true;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Valor Invalido");
                System.out.print("Novo valor: ");
                input.next();
            }
        }
        return txt;
    }

    /**
     * Converter para String a opcao do Utilizador
     *
     * @return string correspodente ao Double
     */
    public static String lerDoubleComoString() {
        Scanner input = new Scanner(System.in);
        boolean ok = false;
        String txt = "";
        while(!ok) {
            try {
                txt = input.next();
                Double.parseDouble(txt);
                ok = true;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Valor Invalido");
                System.out.print("Novo valor: ");
                input.next();
            }
        }
        return txt;
    }

    /**
     * Converter para String a opcao do Utilizador
     *
     * @return string correspodente ao Inteiro anterior
     */
    public static String lerIntAnteriorComoString() {
        Scanner input = new Scanner(System.in);
        boolean ok = false;
        String txt = "";
        int res = 0;
        while(!ok) {
            try {
                txt = input.next();
                res = Integer.parseInt(txt);
                ok = true;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Valor Invalido");
                System.out.print("Novo valor: ");
                input.next();
            }
        }
        return String.valueOf(res-1);
    }
}
