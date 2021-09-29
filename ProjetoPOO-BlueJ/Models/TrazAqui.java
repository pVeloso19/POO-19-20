package Models;

import Excepitions.CodigoNotFoundException;
import Excepitions.EncomendaNotFoundException;
import Excepitions.ProdutoNotFoundException;
import Excepitions.ValorErradoException;
import Models.Encomendas.I_Encomendas;
import Models.Loja.Loja;
import Models.Loja.LojaComFilasEspera;
import Models.Loja.Produto;
import Models.Sistema.*;
import Models.Transportadores.I_Transportadores;
import Models.Transportadores.Transportadora;
import Models.Utilizador.Utilizador;
import Models.Transportadores.Voluntario;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TrazAqui implements Serializable {

    //Map que guarda toda a informação sobre os perfis que existem no sistema
    //Quando existe uma tentativa de login é aqui que está a informação para validar o login.
    //A chave do map é uma string, que corresponde ao email.
    private Map<String, Perfil> perfisExistentes;

    //Map que guarda os utilizadores todos, sendo a chave do map uma String que corresponde ao
    //codigo dos utilizadores
    private Map<String,Utilizador> utilizadores;

    //Map que guarda lojas todas, sendo a chave do map uma String que corresponde ao
    //codigo das lojas
    private Map<String,Loja> lojas;

    //Map que guarda os transportadores todos (tanto os Voluntarios como as Transportadoras, que
    // implementam a interface I_Transportadores), sendo a chave do map uma String que corresponde ao codigo dos trasnportadores.
    private Map<String, I_Transportadores> transportadores;

    //Map que guarda os pedidos de prontos para entrega aceites pela loja, sendo a chave do map uma String que corresponde ao
    //codigo do pedido que no nosso caso é o mesmo que o da encomenda.
    private Map<String, PedidoLoja> pedidosParaRecolha;

    //Map que guarda os pedidos todos que foram entregues ao utilizadores, sendo a chave do map uma String que corresponde ao
    //codigo do pedido que no nosso caso é o mesmo que o da encomenda.
    private Map<String, PedidoCompleto> historico;


    //Lista que contem os proximos codigos para ser gerados, o array contem informação da seguinte maneira:
    //Indice 0 -> codigos para encomendas
    //Indice 1 -> codigos para utilizadores
    //Indice 2 -> codigos para lojas
    //Indice 3 -> codigos para voluntarios
    //Indice 4 -> codigos para transportadoras
    //Indice 5 -> codigos para produtos
    private List<Integer> proximoCodigos;

    /**
     * Construtor por omissão de TrazAqui
     */
    public TrazAqui(){
        this.lojas = new HashMap<>();
        this.utilizadores = new HashMap<>();
        this.transportadores = new HashMap<>();
        this.historico = new HashMap<>();
        this.proximoCodigos = new ArrayList<>();
        this.perfisExistentes = new HashMap<>();
        this.pedidosParaRecolha = new HashMap<>();
    }

    /**
     * Construtor Parametrizado de TrazAqui
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public TrazAqui(Map<String,Utilizador> utilizadores, Map<String,Loja> lojas, Map<String, I_Transportadores> transportadores, Map<String,PedidoCompleto> historico, List<Integer> codigos, Map<String,Perfil> perfis, Map<String,PedidoLoja> pedidosParaRecolha){
        this.lojas = new HashMap<>();
        for (Map.Entry<String,Loja> ml : lojas.entrySet()){
            this.lojas.put(ml.getKey(),ml.getValue().clone());
        }

        this.utilizadores = new HashMap<>();
        for (Map.Entry<String,Utilizador> mu : utilizadores.entrySet()){
            this.utilizadores.put(mu.getKey(),mu.getValue().clone());
        }

        this.transportadores = new HashMap<>();
        for (Map.Entry<String,I_Transportadores> mt : transportadores.entrySet()){
            this.transportadores.put(mt.getKey(),mt.getValue().clone());
        }

        this.historico = new HashMap<>();
        for (Map.Entry<String,PedidoCompleto> mh : historico.entrySet()){
            this.historico.put(mh.getKey(),mh.getValue().clone());
        }

        this.proximoCodigos = new ArrayList<>(codigos);

        this.perfisExistentes = new HashMap<>();
        for (Map.Entry<String,Perfil> mpf : perfis.entrySet()){
            this.perfisExistentes.put(mpf.getKey(),mpf.getValue().clone());
        }

        this.pedidosParaRecolha = new HashMap<>();
        for (Map.Entry<String,PedidoLoja> mpl : pedidosParaRecolha.entrySet()){
            this.pedidosParaRecolha.put(mpl.getKey(),mpl.getValue().clone());
        }
    }

    /**
     * Metodo que é chamado quando uma se pretende pedir ao sistema para entregar uma encomenda.
     * Metodo responsavál por verificar se alguem disponivel têm as caracteristicas necessarias para entregar a encomenda.
     * Metodo que responsavel por guardar as encomenadas sinalizadas como porntas por uma loja.
     * @param p pedido que uma loja pretende que alguem entregue.
     * @return boolean que indica se o pedido foi ou não aceite pelo sistema
     */
    public boolean atribuiEntregador(PedidoLoja p){
        boolean aceita = this.transportadores.values().parallelStream().anyMatch(t->t.aceitaCaracteristicasEncomenda(p));
        if(aceita) this.pedidosParaRecolha.put(p.getCodigoPedido(),p.clone());

        return aceita;
    }

    /**
     * Metodo que devolve um clone de um utilizador dado o seu codigo
     */
    public Utilizador getUtilizador(String cod){  
        return this.utilizadores.get(cod).clone();
    }
    
    /**
     * Metodo que devolve um cline de uma loja dado o seu codigo
     */
    public Loja getLoja(String cod){  
        return this.lojas.get(cod).clone();
    }
    
    /**
     * Metodo responsavel por devolver um clone de um Transportador dado o seu codigo
     */
    public I_Transportadores getTransportador(String cod){  
        return this.transportadores.get(cod).clone();
    }

    /**
     * Metodo responsavál por colocar na respetiva loja o numero (indicado) de pessoas na fila espera.
     * @param codLoja codigo da loja onde se vai mudar o numero de pessas na fila de espera
     * @param pessoas novo numero de pessoas na fila de espera.
     */
    public void setPessoasEmEspera(String codLoja, int pessoas){
        LojaComFilasEspera l = (LojaComFilasEspera) this.lojas.get(codLoja);
        l.setOcupacao(pessoas);
    }

    /**
     * Metodo responsavel por retornar o historico de uma loja em formato List.
     * @param codLoja codigo da loja a que se vai buscar o historico.
     * @return historico em formato List<String>
     */
    public List<String> getHistoricoLoja(String codLoja){
        return this.lojas.get(codLoja).getListaHistorico();
    }

    /**
     * Metodo que remove um produto da loja
     * @param codLoja codigo da loja em que se vai adicionar o produto
     * @param codProd codigo do produto que vai ser removido
     */
    public void removerProdutoLoja(String codLoja, String codProd) throws ProdutoNotFoundException{
        this.lojas.get(codLoja).removeProdutoStock(codProd);
    }

    /**
     * Metodo responsavel por adicionar um produto a uma loja
     * @param loja loja Ã¡ qual vai ser adicionado um produto.
     * @param p produto a adicionar.
     */
    public void adicionaProdutoLoja(String loja, Produto p){
        this.lojas.get(loja).adicionaProdutoStock(p);
    }

    /**
     * Metodo responsavel por devolver o pedido que esta a ser aceite pela loja.
     * @param posEnc posição indicada do pedido a ir buscar.
     * @param codLoja codigo da loja que chamou este metodo.
     * @return pedido da loja.
     * @throws EncomendaNotFoundException caso o pedido não exista.
     */
    public PedidoLoja getPedidoUtilizadorDaLoja(int posEnc, String codLoja) throws EncomendaNotFoundException {
        return this.lojas.get(codLoja).getPedidoUtilizador(posEnc).clone();
    }

    /**
     * Metodo que retorna em List<String> os pedidos que uma loja tem por aceitar.
     * @param loja codigo da loja que chama o metodo (pela view).
     * @return lista dos pedidos por aceitar.
     */
    public List<String> getListPedidosLoja(String loja){
        return this.lojas.get(loja).getListPedidos();
    }

    /**
     * Metodo responsavel por retirar um pedido da fila de espera de uma loja
     * @param cod codigo da loja que chama o metodo (pela view).
     * @param pos posição da fila de espera do pedido a retirar.
     */
    public void removeListaEspera(String cod, int pos){
        this.lojas.get(cod).removeListaEspera(pos);
    }

    /**
     * Metodo responsavel por dar a um utilizador a lista de lojas disponiveis no sistema, para
     * realizar as suas encomenads.
     * @return retorna a lista de lojas existentes no sistema.
     */
    public List<Loja> getLojasDisponiveis(){
        return this.lojas.values().parallelStream().map(Loja::clone).collect(Collectors.toList());
    }

    /**
     * Metodo responsavel por retornar os produtos existentes numa loja.
     * @param loja codigo da loja de que pretende ver os produtos.
     * @return lista dos produtos que existem na loja pedida.
     * @throws CodigoNotFoundException caso a loja não exista no sistema, sendo assim o codigo da loja invalido.
     */
    public List<Produto> getProdutosLoja(String loja) throws CodigoNotFoundException {
        if(!this.lojas.containsKey(loja)) throw new CodigoNotFoundException("Codigo da loja invalido.");
        return this.lojas.get(loja).getProdutos();
    }

    /**
     * Metodo que recebe uma encomenda e transforma-a num pedido a uma loja, pondo-o na fila de espera dessa loja.
     * @param e encomenda que se quer encomnedar a uma loja.
     */
    public void utilizadorToLoja(I_Encomendas e){
        PedidoUtilizador p = new PedidoUtilizador(e.clone(),this.utilizadores.get(e.getCodUtilizador()).getGps().clone(),LocalDateTime.now());
        this.lojas.get(e.getCodLoja()).addPedidoUtilizador(p);
    }

    /**
     * Metodo responsavel por devolver um produto de uma determinada loja.
     * @param codLoja codigo da loja onde se vai buscar o produto.
     * @param codProduto codigo de produto que se quer buscar
     * @return retorna o produto que se pediu.
     * @throws ProdutoNotFoundException caso o produto pedido na exista na loja.
     */
    public Produto getProdutoDaLoja(String codLoja, String codProduto) throws ProdutoNotFoundException {
        return this.lojas.get(codLoja).getProduto(codProduto);
    }

    /**
     * Metodo responsavel por devolver o historico de um utilizador (em forma de lista).
     * @param codUtilizador codigo do urilizador.
     * @return o historico do utilizador em forma de lista de strings.
     */
    public List<String> getHistoricoUtilizador(String codUtilizador){
        return this.utilizadores.get(codUtilizador).getListaHistorico();
    }

    /**
     * Metodo que retorna os transportadores disponiveis no momento para avliar por um dado transportador.
     * @param codUtilizador codigo do utilizador que pretende os transportadores para avaliar.
     * @return uma lista com os tarsnportadores para avaliar.
     */
    public List<PedidoCompleto> getTransportadoresParaAvaliar(String codUtilizador){
        return this.utilizadores.get(codUtilizador).getTransportadoresPorAvaliar();
    }

    /**
     * Metodo responsavel por devolver uma lista com as notificações de um utilizador.
     * @param codU codigo do utilizador para o qual vai ser apresentadas as notificações.
     * @return lista das notificações
     */
    public List<String> getNotificacoesUtilizador(String codU){
        return this.utilizadores.get(codU).getNotificacoes();
    }

    /**
     * Metodo responsavel por limpar todas as notificações de um utilizador.
     * @param cod codigo do utilizador a limpar as notificações.
     */
    public void limpaNotificacoesUtilizador(String cod){
        this.utilizadores.get(cod).limpaNotificacoes();
    }

    /**
     * Metodo reponsavel por aceitar uma transportadora que pediu para entregar uma encomenda deste utilizador.
     * @param codUtilizador codigo do utilizador que pretende aceitar a transportadora
     * @param codEnc codigo da encomenda que pretende aceitar.
     */
    public void aceitaTransportadoraPendente(String codUtilizador, String codEnc){
        PedidoTransportadora pt = this.utilizadores.get(codUtilizador).aceitaTrasnportadora(codEnc);
        this.transportadores.get(pt.getCodigoTransportador()).aceitarEncomenda();
        this.lojas.get(pt.getLoja()).adicionaHistorico(pt);
    }

    /**
     * Metodo responsavel por rejeitar uma transportadora que pediu para entregar uma encomenda deste utilizador.
     * @param codUtilizador codigo do utilizador que pretende rejeitar a transportadora
     * @param codEnc codigo da encomenda que pretende rejeitar.
     */
    public void rejeitaTransportadoraPendente(String codUtilizador, String codEnc){
        PedidoTransportadora pt = this.utilizadores.get(codUtilizador).aceitaTrasnportadora(codEnc);
        PedidoLoja pl = this.transportadores.get(pt.getCodigoTransportador()).rejeitarEncomenda();
        this.atribuiEntregador(pl);
    }

    /**
     * Metodo que vai mudar o preco por kilometro de uma transportadora
     * @param cod codigo da transportadora a mudar
     * @param preco novo preco a aplicar
     */
    public void mudaPrecoMedio(String cod, double preco){
        this.transportadores.get(cod).setPrecoPorKm(preco);
    }

    /**
     * Metodo responsavel por atualizar a velocidade media de um transportador
     * @param cod codigo do transportador a aplicar a mudança
     * @param v nova velocidade a utilizar
     */
    public void mudaVelocidadeMedia(String cod, double v){
        this.transportadores.get(cod).setVelocidadeMedia(v);
    }

    /**
     * Metodo que atualiza o novo raio de acao de um transportador
     * @param cod codigo do transportador a aplicar a mudança
     * @param raio novo raio a utilizar
     */
    public void mudaRaioAcao(String cod, double raio){
        this.transportadores.get(cod).setRaio(raio);
    }

    /**
     * Metodo que retorna uma lista dos pedidos que uma transportadora fez a um utilizador
     * para entregar alguam das suas encomendas.
     * @param codUtilizador codigo do utilizador que pretende esta informação.
     * @return lista dos pedidos pendentes feitos por uma transportadora a um utilizador.
     */
    public List<PedidoTransportadora> getPedidosTransportadorasPendentes(String codUtilizador){
        return this.utilizadores.get(codUtilizador).getPedidosPendentes().values().parallelStream().map(PedidoTransportadora::clone).collect(Collectors.toList());
    }


    /**
     * Metodo retorna a lista de encomendas que existem para ser entregues.
     * @return lista de encomedas (formato string) no sistema para ser entregues.
     */
    public List<String> encomendaParaEntrega(String codTransp){
        return this.pedidosParaRecolha.values().parallelStream().filter(p->this.transportadores.get(codTransp).aceitaCaracteristicasEncomenda(p))
                   .map(p->p.toString()).collect(Collectors.toList());
    }

    /**
     * Metodo responsavel por aceitar um pedido de entrega de um transportador (tanto Volunario como transportadora)
     * @param codigoTransportador codigo do transportador que pretende transportar uma encomenda que existe no sistema
     * @param codEncomenda codigo da encomenda existente no sistema que pretende transportar
     * @return boolean com o resultado de o sistema aceitar ou nao essa intençao de entrega.
     * @throws EncomendaNotFoundException caso o codigo da encomeda nao seja valido.
     * @throws CodigoNotFoundException caso o transportador nao exista no sistema.
     */
    public boolean aceitaIntecaoDeEntrega(String codigoTransportador, String codEncomenda) throws EncomendaNotFoundException, CodigoNotFoundException, NullPointerException{
        boolean res = false;

        if(!this.pedidosParaRecolha.containsKey(codEncomenda)) throw new EncomendaNotFoundException();
        if(!this.transportadores.containsKey(codigoTransportador)) throw new CodigoNotFoundException();

        PedidoLoja p = this.pedidosParaRecolha.get(codEncomenda);
        I_Transportadores transportador = this.transportadores.get(codigoTransportador);

        if(transportador instanceof Transportadora){
            Transportadora t = (Transportadora) transportador;

            if(t.aceitaCaracteristicasEncomenda(p)){
                res = true;
                this.pedidosParaRecolha.remove(codEncomenda);
                I_PedidosTransportadores pt = t.ocupaTransportadora(p.clone());
                this.utilizadores.get(p.getUtilizador()).addPedidosPendentes((PedidoTransportadora) pt.clone());
                this.utilizadores.get(p.getUtilizador()).addNotificacao(String.format("Pedido %s sugerido a uma transportadora (%s), ver trasnportadoras pendentes.",codEncomenda,codigoTransportador));
            }

        }else{
            if(transportador instanceof Voluntario){
                Voluntario v = (Voluntario) transportador;
                if(v.aceitaCaracteristicasEncomenda(p)){
                    res = true;
                    this.pedidosParaRecolha.remove(codEncomenda);
                    I_PedidosTransportadores pt = v.ocupaVoluntario(p);
                    this.utilizadores.get(p.getUtilizador()).addNotificacao(String.format("Pedido %s sugerido a um voluntario (%s), Aguarde a entrega da encomenda.",codEncomenda,codigoTransportador));
                    this.lojas.get(p.getLoja()).adicionaHistorico(pt);
                }
            }
        }

        return res;
    }

    /**
     * Metodo responsavel por avaliar um transportador.
     * @param utlizador codigo do utilizador que pretende avaliar.
     * @param codTransportador codigo da transportador a ser avaliado.
     * @param avaliacao a avaliação a ser dada ao transportador (0-5)
     * @throws ValorErradoException caso a avaliação nao seja valida ou seja nao respeite o farmato de 0-5
     */
    public void avaliaTransportador(String utlizador, String codTransportador, int avaliacao) throws ValorErradoException {
        this.transportadores.get(codTransportador).avaliaTransportador(avaliacao);
        this.utilizadores.get(utlizador).removeDeAvaliacao(codTransportador);
    }

    /**
     * Metodo que retorna o historico dos transportadores
     * @param cod codigo do transportador a ver o seu historico
     * @return lista com o historico.
     */
    public List<String> getHistoricoTransportadores(String cod){
        return this.transportadores.get(cod).getListaHistorico();
    }

    /**
     * Metodo responsavel por finalizar a entrega atual de um transportador
     * @param cod codigo do transportador que pretende declara como finalizada a sua entrega
     */
    public void finalizaTransportadores(String cod){
        PedidoCompleto p = this.transportadores.get(cod).finalizaEntrega();
        this.utilizadores.get(p.getUtilizador()).addHistorico(p.clone());
        this.historico.put(p.getCodigoPedido(),p.clone());
    }

    /**
     * Metodo que muda o estado de um transportador de indisponivel para disponivel e virse-versa
     * @param cod codigo do transportador que pretende mudar o seu estado
     */
    public void mudaEstado(String cod){
        this.transportadores.get(cod).mudaEstado();
    }

    //Estatisticas

    /**
     * Metodo responsavel por mostar as estatisticas ao dono do sistema, mais propriamente sobre o quanto ganhou
     * uma loja.
     * @param cod codigo da loja que pretende ver as estatisticas
     * @return  a lista com o historico da loja bem como na ultima possição do array o total faturado pela mesma
     * @throws CodigoNotFoundException caso o codigo fornecido na seja invalido.
     */
    public List<String> totalFaturadoLoja(String cod) throws CodigoNotFoundException{
        try {
            List<String> res = this.lojas.get(cod).getListaHistorico();
            double fat = this.lojas.get(cod).totalFaturado();
            String fim = String.format("Total faturado por %s = %f",cod,fat);
            res.add(fim);

            return res;
        }catch (NullPointerException e){
            throw new CodigoNotFoundException();
        }
    }

    /**
     * Metodo responsavel por mostar as estatisticas ao dono do sistema, mais propriamente sobre o quanto ganhou
     * uma transportadora
     * @param cod codigo da transportadora que pretende ver as estatisticas
     * @return  a lista com o historico da transportadora bem como na ultima possição do array o total faturado pela mesma
     * @throws CodigoNotFoundException caso o codigo fornecido na seja invalido.
     */
    public List<String> totalFaturadoTransportadora(String cod) throws CodigoNotFoundException{
        try{
            List<String> res = this.transportadores.get(cod).getListaHistorico();
            double fat = this.transportadores.get(cod).totalFaturado();
            String fim = String.format("Total faturado por %s = %f",cod,fat);
            res.add(fim);

            return res;
        }catch (NullPointerException e){
            throw new CodigoNotFoundException();
        }
    }

    /**
     * Metodo reponsavel por devolver uma lista com o top 10 utilizadores
     * em termos de numero de encomendas finalizadas.
     * @return lista com a informaçao do top10
     */
    public List<String> top10Utilizadores(){
        Map<Integer,List<String>> aux = new HashMap<>();
        List<String> res = new ArrayList<>(10);

        for (Utilizador u : this.utilizadores.values()){
            int num = (int) this.historico.values().parallelStream().map(PedidoCompleto::getUtilizador).filter(c->c.equals(u.getCodUtilizador())).count();
            if(aux.get(num)==null){
                aux.put(num,new ArrayList<>());
            }
            aux.get(num).add(u.getCodUtilizador());
        }

        List<List<String>> ordenada = aux.entrySet().parallelStream().sorted((e1,e2)->(e2.getKey()-e1.getKey())).map(Map.Entry::getValue).collect(Collectors.toList());

        int numElem = 0;
        Iterator<List<String>> it1 = ordenada.iterator();
        while (it1.hasNext() && numElem<10){
            List<String> ls = it1.next();
            Iterator<String> it2 = ls.iterator();
            while (it2.hasNext() && numElem<10){
                String s = it2.next();
                res.add(s);
                numElem++;
            }
        }

        return res;
    }

    /**
     * Metodo responsavel por devolver a lista do top 10 transportadora em funçao do numero de km
     * efetudos nas suas encomendas entregues ao utlizador
     * @return lista com o top 10 transportadoras
     */
    public List<String> top10Transportadoras(){
        Map<Double,List<String>> aux = new HashMap<>();
        List<String> res = new ArrayList<>(10);

        for (I_Transportadores t : this.transportadores.values()){
            if(t instanceof Transportadora){
                double km = this.historico.values().parallelStream().filter(c -> c.getCodigoTransportadora().equals(t.getCodigo())).mapToDouble(PedidoCompleto::getKmPercorridos).sum();
                if (aux.get(km) == null) {
                    aux.put(km, new ArrayList<>());
                }
                aux.get(km).add(t.getCodigo());
            }
        }

        List<List<String>> ordenada = aux.entrySet().parallelStream().sorted((e1,e2)->(int)(e2.getKey()-e1.getKey())).map(Map.Entry::getValue).collect(Collectors.toList());

        int numElem = 0;
        Iterator<List<String>> it1 = ordenada.iterator();
        while (it1.hasNext() && numElem<10){
            List<String> ls = it1.next();
            Iterator<String> it2 = ls.iterator();
            while (it2.hasNext() && numElem<10){
                String s = it2.next();
                res.add(s);
                numElem++;
            }
        }

        return res;
    }

    /**
     * Metodo responsavel por devolver o perfil da pessoa com o email dado
     * @param email email da pessoa que pretende receber o perfil.
     * @return perfil da pessoa com o dado email.
     * @throws ValorErradoException caso o email recebido seja invalido.
     */
    public Perfil getPerfil(String email) throws ValorErradoException{
        try {
            return this.perfisExistentes.get(email).clone();
        }catch (NullPointerException e){
            throw new ValorErradoException("Email invalido");
        }
    }

    /**
     * Metodo responsavel por adicionar um perfil ao sistema
     * @param p perfil a adicionar
     */
    public void adicionaPerfil(Perfil p){
        this.perfisExistentes.put(p.getEmail(),p.clone());
    }

    /**
     * Metodo responsavel por adicionar uma transportadora no sistema
     * @param t transportadora a adicionar
     */
    public void adicionaTransportador(I_Transportadores t){
        this.transportadores.put(t.getCodigo(),t.clone());
    }

    /**
     * Metodo responsavel por adicionar um utilizador no sistema
     * @param u utilizador a adicionar
     */
    public void adicionaUtilizador(Utilizador u){
        this.utilizadores.put(u.getCodUtilizador(),u.clone());
    }

    /**
     * Metodo responsavel por adicionar uma loja ao sistema
     * @param l loja a adicionar.
     */
    public void adicionaLoja(Loja l){
        this.lojas.put(l.getCodigoLoja(),l.clone());
    }

    /**
     * Metodo responsavel por guardar o estado do sistema em formato binario
     * @param nomeFicheiro nome do ficheiro a guardar o estado
     */
    public void guardaEstado(String nomeFicheiro) throws IOException {

        FileOutputStream fos = new FileOutputStream(new File(nomeFicheiro));
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    /**
     * Metodo responsavel por gerar um novo codigo de utilizador
     * @return novo codigo gerado
     */
    public String geraCodigoUtilizador(){
        Integer numCodigo = this.proximoCodigos.get(1);
        this.proximoCodigos.add(1,numCodigo+1);
        return String.format("u%d",numCodigo);
    }

    /**
     * Metodo responsavel por gerar um novo codigo de voluntario
     * @return novo codigo gerado
     */
    public String geraCodigoVoluntario(){
        Integer numCodigo = this.proximoCodigos.get(3);
        this.proximoCodigos.add(3,numCodigo+1);
        return String.format("v%d",numCodigo);
    }

    /**
     * Metodo responsavel por gerar um novo codigo de transportadora
     * @return novo codigo gerado
     */
    public String geraCodigoTransportadora(){
        Integer numCodigo = this.proximoCodigos.get(4);
        this.proximoCodigos.add(4,numCodigo+1);
        return String.format("t%d",numCodigo);
    }

    /**
     * Metodo responsavel por gerar um novo codigo de loja
     * @return novo codigo gerado
     */
    public String geraCodigoLoja(){
        Integer numCodigo = this.proximoCodigos.get(2);
        this.proximoCodigos.add(2,numCodigo+1);
        return String.format("l%d",numCodigo);
    }

    /**
     * Metodo responsavel por gerar um novo codigo de encomenda
     * @return novo codigo gerado
     */
    public String geraCodigoEncomenda(){
        Integer numCodigo = this.proximoCodigos.get(0);
        this.proximoCodigos.add(0,numCodigo+1);
        return String.format("e%d",numCodigo);
    }

    /**
     * Metodo responsavel por gerar um novo codigo de produto
     * @return novo codigo gerado
     */
    public String geraCodigoProduto(){
        Integer numCodigo = this.proximoCodigos.get(5);
        this.proximoCodigos.add(5,numCodigo+1);
        return String.format("p%d",numCodigo);
    }
}
