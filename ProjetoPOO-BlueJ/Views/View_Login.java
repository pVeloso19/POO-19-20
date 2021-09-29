package Views;

import Controllers.I_Controllers;

import java.util.ArrayList;
import java.util.List;

public class View_Login {

    /**
     * Variaveis Instancia
     */
    private I_Controllers controller;

    /**
     * Construtor Parametrizado de View_Historico
     * Aceita como parÃ¢metros os valores para cada VariÃ¡vel de InstÃ¢ncia
     */
    public View_Login(I_Controllers controller){
        this.controller = controller;
    }

    /**
     * Sub menu correspondente a criacao de conta de um Utilizador
     */
    private void criarContaUtilizador(){
        List<String> l = new ArrayList<>();
        l.add("Creat");
        l.add("Utilizador");

        System.out.println("Insira o email:");
        l.add(LeituraDados.lerString());
        System.out.println("Insira a password:");
        l.add(LeituraDados.lerString());

        System.out.println("Insira o seu primeiro Nome");
        l.add(LeituraDados.lerString());
        System.out.println("Insira o seu ultimo Nome");
        l.add(LeituraDados.lerString());

        System.out.println("Insira a sua Longitude (coordenada em X)");
        l.add(LeituraDados.lerDoubleComoString());
        System.out.println("Insira a sua Latitude (coordenada em Y)");
        l.add(LeituraDados.lerDoubleComoString());

        this.controller.processa(l);
    }

    /**
     * Sub menu correspondente a criacao de conta de um Voluntario
     */
    private void criarContaVoluntario(){
        List<String> l = new ArrayList<>();
        l.add("Creat");
        l.add("Voluntario");

        System.out.println("Insira o email:");
        l.add(LeituraDados.lerString());
        System.out.println("Insira a password:");
        l.add(LeituraDados.lerString());

        System.out.println("Insira o seu primeiro Nome");
        l.add(LeituraDados.lerString());
        System.out.println("Insira o seu ultimo Nome");
        l.add(LeituraDados.lerString());

        System.out.println("Insira a sua Longitude (coordenada em X)");
        l.add(LeituraDados.lerDoubleComoString());
        System.out.println("Insira a sua Latitude (coordenada em Y)");
        l.add(LeituraDados.lerDoubleComoString());

        System.out.println("Insira o seu raio de ação");
        l.add(LeituraDados.lerDoubleComoString());

        System.out.println("Tem certificado para Transporte de medicamentos? (S/N)");
        l.add(LeituraDados.lerString());

        this.controller.processa(l);
    }

    /**
     * Sub menu correspondente a criacao de conta de uma Transportadora
     */
    private void criarContaTransportadora(){
        List<String> l = new ArrayList<>();
        l.add("Creat");
        l.add("Transportadora");

        System.out.println("Insira o email:");
        l.add(LeituraDados.lerString());
        System.out.println("Insira a password:");
        l.add(LeituraDados.lerString());

        System.out.println("Insira o nome da Empresa");
        l.add(LeituraDados.lerString());

        System.out.println("Insira o nif da Empresa");
        l.add(LeituraDados.lerString());

        System.out.println("Insira a sua Longitude (coordenada em X)");
        l.add(LeituraDados.lerDoubleComoString());
        System.out.println("Insira a sua Latitude (coordenada em Y)");
        l.add(LeituraDados.lerDoubleComoString());

        System.out.println("Insira a o preço medio por kilometro da sua empresa.");
        l.add(LeituraDados.lerDoubleComoString());

        System.out.println("Insira o raio de ação da sua Empresa.");
        l.add(LeituraDados.lerDoubleComoString());

        System.out.println("Tem certificado para Transporte de medicamentos? (S/N)");
        l.add(LeituraDados.lerString());


        this.controller.processa(l);
    }

    /**
     * Sub menu correspondente a criacao de conta de uma Loja
     */
    private void criarContaLoja(){
        List<String> l = new ArrayList<>();
        l.add("Creat");
        l.add("Loja");

        System.out.println("Insira o email:");
        l.add(LeituraDados.lerString());
        System.out.println("Insira a password:");
        l.add(LeituraDados.lerString());

        System.out.println("Insira o nome da loja");
        l.add(LeituraDados.lerString());

        System.out.println("Insira a sua Longitude (coordenada em X)");
        l.add(LeituraDados.lerDoubleComoString());
        System.out.println("Insira a sua Latitude (coordenada em Y)");
        l.add(LeituraDados.lerDoubleComoString());

        this.controller.processa(l);
    }

    /**
     * Funcao que corre a view com todas as funcoes anterioes, de maneira
     * a interligar os diferentes processos
     */
    public void run(){
        String opcao;
        do{
            System.out.println("");
            System.out.println("");
            System.out.println("Bem Vindo ao TrazAqui");
            System.out.println("");
            System.out.println("1-> Fazer login");
            System.out.println("2-> Criar Conta");
            System.out.println("");
            System.out.println("S-> sair");
            System.out.println("Escolha a opção pretendida.");
            opcao = LeituraDados.lerString();
            opcao = opcao.toUpperCase();

            switch (opcao){
                case "1" :{
                    List<String> l = new ArrayList<>();
                    l.add("Login");

                    System.out.println("Insira o seu email;");
                    l.add(LeituraDados.lerString());
                    System.out.println("Insira a password;");
                    l.add(LeituraDados.lerString());

                    this.controller.processa(l);
                    opcao = "S";
                    break;
                }

                case "2" :{
                    System.out.println("1- Sou um Utilizador Normal.");
                    System.out.println("2- Sou um Voluntario.");
                    System.out.println("3- Sou uma empresa Transportadora.");
                    System.out.println("4- Sou uma loja.");
                    System.out.println("Insira o seu tipo:");
                    String opc = LeituraDados.lerIntComoString();

                    switch (opc.toUpperCase()) {
                        case "1": criarContaUtilizador(); opcao = "S"; break;
                        case "2": criarContaVoluntario(); opcao = "S"; break;
                        case "3": criarContaTransportadora(); opcao = "S"; break;
                        case "4": criarContaLoja(); opc = opcao = "S"; break;
                        default: System.out.println("Comando Invalido"); break;
                    }
                    break;
                }

                case "S" :{
                    break;
                }
            }

        }while (!opcao.equals("S"));
    }
}
