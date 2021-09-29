package Models.Sistema;

import Models.Encomendas.Encomenda;
import Models.Encomendas.LinhaEncomenda;
import Models.Utilizador.GPS;
import Models.Transportadores.I_Transportadores;
import Models.Loja.Loja;
import Models.Loja.LojaComFilasEspera;
import Models.Loja.Produto;
import Models.Loja.Stock;
import Models.Transportadores.Transportadora;
import Models.Transportadores.TransportadoraNormal;
import Models.TrazAqui;
import Models.Utilizador.Utilizador;
import Models.Transportadores.Voluntario;
import Models.Transportadores.VoluntarioNormal;


import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeituraFicheiros {

    private static String nomeFicheiro = "logs_20200416.txt";

    /**
     * Metodo que realiza a leitura do ficheiro logs.
     * Retorna o TrazAqui com os dados defalt
     *
     * Depois deve abrir a aplicacao com o estado em binario e nao com o ficheiro logs.
     */
    public static TrazAqui lerFicheiro() throws IOException{
        Map<String,Loja> ls = new HashMap<>();
        Map<String,Utilizador> us = new HashMap<>();
        Map<String, I_Transportadores> ts = new HashMap<>();
        Stock stock = new Stock();
        Map<String,Encomenda> encomendas = new HashMap<>();

        List<PedidoLoja> pls = new ArrayList<>();
        TrazAqui s = new TrazAqui();

        int numLojas =0;

        FileReader arq = new FileReader(nomeFicheiro);
        BufferedReader lerArq = new BufferedReader(arq);

        String linha = lerArq.readLine();
        int opcao = -1;
        while (linha != null) {

            opcao = linha.codePointAt(0) - 65;

            String[] r = linha.split(":");
            String[] valoresLinha = r[1].split(",");
            switch (opcao){
                case 0 : {      //Aceites
                    Encomenda ea = encomendas.get(r[1].trim());
                    Loja lea = ls.get(ea.getCodLoja());
                    Utilizador uea = us.get(ea.getCodUtilizador());

                    PedidoUtilizador pu = new PedidoUtilizador(ea,uea.getGps().clone(),LocalDateTime.now());

                    PedidoLoja pl = new PedidoLoja(pu, LocalDateTime.now(),lea.getCodigoLoja(),lea.getCord().clone(),false,"None");
                    pls.add(pl);
                    break;
                }
                case 4 : {      //Encomendas
                    List<LinhaEncomenda> linhaEnc = new ArrayList<>();
                    for(int i=4; i<valoresLinha.length; i+=4){
                        LinhaEncomenda l = new LinhaEncomenda(valoresLinha[i],valoresLinha[i+1],Double.parseDouble(valoresLinha[i+2]),Double.parseDouble(valoresLinha[i+3]));

                        Produto p = new Produto(valoresLinha[i],valoresLinha[i+1],0.0,(Double.parseDouble(valoresLinha[i+3])/Double.parseDouble(valoresLinha[i+2])),false);
                        stock.addStock(p);

                        linhaEnc.add(l);
                    }

                    Encomenda e = new Encomenda(valoresLinha[0],valoresLinha[1],valoresLinha[2],Double.parseDouble(valoresLinha[3]),new ArrayList<>());
                    e.setLinhasEncomenda(linhaEnc);
                    encomendas.put(e.getCodEncomenda(),e);
                    break;
                }
                case 11 : {    //Lojas
                    if(numLojas<3){
                        GPS gps = new GPS(Double.parseDouble(valoresLinha[2]), Double.parseDouble(valoresLinha[3]));
                        Loja loja = new LojaComFilasEspera(valoresLinha[0], valoresLinha[1], gps);
                        ls.put(loja.getCodigoLoja(), loja);
                        numLojas++;
                    }else{
                        GPS gps = new GPS(Double.parseDouble(valoresLinha[2]), Double.parseDouble(valoresLinha[3]));
                        Loja loja = new Loja(valoresLinha[0], valoresLinha[1], gps);
                        ls.put(loja.getCodigoLoja(), loja);
                    }

                    break;
                }
                case 19 : {     //Transportadoras
                    GPS gps = new GPS(Double.parseDouble(valoresLinha[2]), Double.parseDouble(valoresLinha[3]));
                    Transportadora p = new TransportadoraNormal(valoresLinha[0], valoresLinha[1], gps, valoresLinha[4], Double.parseDouble(valoresLinha[5]), Double.parseDouble(valoresLinha[6]));

                    ts.put(p.getCodigo(),p);

                    break;
                }
                case 20 : {     //Utilizador
                    GPS gps = new GPS(Double.parseDouble(valoresLinha[2]), Double.parseDouble(valoresLinha[3]));
                    Utilizador utilizador = new Utilizador(valoresLinha[0], valoresLinha[1], gps);

                    us.put(utilizador.getCodUtilizador(),utilizador);

                    break;
                }
                case 21 : {     //Voluntarios
                    GPS gps = new GPS(Double.parseDouble(valoresLinha[2]), Double.parseDouble(valoresLinha[3]));
                    Voluntario voluntario = new VoluntarioNormal(valoresLinha[0], valoresLinha[1], gps, Double.parseDouble(valoresLinha[4]));

                    ts.put(voluntario.getCodigo(),voluntario);

                    break;
                }

                default:{
                    System.out.println("Errou");
                    break;
                }

            }

            linha = lerArq.readLine(); // lê da segunda até a última linha
        }
        arq.close();

        Produto medicamento = new Produto("p1","Brufene",0.05,5.30,true);
        stock.addStock(medicamento);
        List<Produto> lp = stock.getListaProdutos();

        for (Produto p : lp){
            for (Loja l : ls.values()){
                l.adicionaProdutoStock(p);
            }
        }

            /*
             * Proximos Codigos para ser gerados
             * Indice 0 -> encomendas -> Maior 8779 -> 8780
             * Indice 1 -> utilizadores -> Maior 97 -> 98
             * Indice 2 -> lojas -> Maior 83 -> 84
             * Indice 3 -> voluntarios -> Maior 73 -> 74
             * Indice 4 -> transportadoras -> Maior 51 -> 52
             * Indice 5 -> produtos -> Maior ??? -> 100
             */

        List<Integer> lprox = new ArrayList<>();
        lprox.add(0,8780);
        lprox.add(1,98);
        lprox.add(2,84);
        lprox.add(3,74);
        lprox.add(4,52);
        lprox.add(5,100);

        Map<String,Perfil> perfis = new HashMap<>();

        for (Loja l : ls.values()){
            Perfil p = new Perfil(TiposUtilizadores.LOJAS,l.getCodigoLoja(),l.getCodigoLoja(),"1234");
            perfis.put(p.getEmail(),p.clone());
        }

        for (Utilizador l : us.values()){
            Perfil p = new Perfil(TiposUtilizadores.UTILIZADORES,l.getCodUtilizador(),l.getCodUtilizador(),"1234");
            perfis.put(p.getEmail(),p.clone());
        }

        for (I_Transportadores l : ts.values()){
            Perfil p;
            if(l instanceof Transportadora) p = new Perfil(TiposUtilizadores.TRANSPORTADORA,l.getCodigo(),l.getCodigo(),"1234");
            else p = new Perfil(TiposUtilizadores.VOLUNTARIOS,l.getCodigo(),l.getCodigo(),"1234");
            perfis.put(p.getEmail(),p.clone());
        }

        Perfil pS = new Perfil(TiposUtilizadores.SISTEMA,"Pedro Veloso","pedroVeloso","1234");
        perfis.put(pS.getEmail(),pS.clone());

        s = new TrazAqui(us, ls, ts, new HashMap<>(),lprox,perfis, new HashMap<>());

        for (PedidoLoja pla : pls){
            boolean conseguiu = s.atribuiEntregador(pla);
            if(!conseguiu){
                ls.get(pla.getLoja()).addPedidoUtilizador(new PedidoUtilizador(pla.getEncomenda(),us.get(pla.getUtilizador()).getGps().clone(),LocalDateTime.now()));
            }
        }

        return s;
    }

    /**
     * Metodo que l� o estado dos nossos dados guardados em binario
     * @param nomeFicheiroBinario nome do ficheiro onde contem os dados a ler.
     * @return A estrutura carregado e pronta a utilizar.
     */
    public static TrazAqui carregaEstado(String nomeFicheiroBinario) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(nomeFicheiroBinario);
        ObjectInputStream ois = new ObjectInputStream(fis);
        TrazAqui s = (TrazAqui) ois.readObject();
        ois.close();

        return s;
    }
}
