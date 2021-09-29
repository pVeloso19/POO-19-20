package Models.Encomendas;

import Models.Loja.Produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Encomenda implements Serializable, I_Encomendas {

   /**
   * Variaveis de Instancia
   */
   private String codEncomenda;
   private String codUtilizador;
   private String codLoja;
   private double peso;
   private List<LinhaEncomenda> linhasEncomenda;
   

   /**
    * Construtor por omissao da clase Encomenda.
    */
   public Encomenda(){
       this.codEncomenda = "";
       this.codUtilizador = "";
       this.codLoja = "";
       this.peso = 0;
       this.linhasEncomenda= new ArrayList<>();
   } 
    
    /**
     * Construtor parametrizado de Encomenda.
     * Aceita como parametros os valores de cada parametro.
     */
   public Encomenda(String codEncomenda,String codUtilizador,String codLoja, double peso, List<LinhaEncomenda> lista){
       this.codEncomenda = codEncomenda;
       this.codUtilizador = codUtilizador;
       this.codLoja = codLoja;
       this.peso = peso;
       this.setLinhasEncomenda(lista);
   }

    /**
    * Construtor de copia de Encomenda.
    * Aceita como parametro outra Encomenda e utiliza os metodos
    * de acesso aos valores das valiaveis
    */
   public Encomenda(Encomenda ec){
       this.codEncomenda = ec.getCodEncomenda();
       this.codUtilizador = ec.getCodUtilizador();
       this.codLoja = ec.getCodLoja();
       this.peso = ec.getPeso();
       this.linhasEncomenda = ec.getLinhasEncomenda() ;
   }    
   
   /**
    * Devolve a string correspondente ao codigo de encomenda.
    * 
    * @return string do codigo de encomenda.
    */
   public String getCodEncomenda(){
       return this.codEncomenda;
   }
   
   /**
    * Devolve a string correspondente ao codigo do utilizador.
    * 
    * @return string do codigo do utilizador.
    */
   
   public String getCodUtilizador(){
       return this.codUtilizador;
   }
   
   /**
    * Devolve a string correspondente ao codigo da loja.
    * 
    * @return string do codigo da loja.
    */
   public String getCodLoja(){
       return this.codLoja;
   }
   
   /**
    * Devolve o valor do peso da encomenda.
    * 
    * @return valor do peso da encomenda.
    */
   public double getPeso(){
       return this.peso;
   }
   
   /**
    * Devolve a lista das linhas de encomenda.
    * 
    * @return a lista das linhas de encomenda
    */
   public List<LinhaEncomenda> getLinhasEncomenda(){
       List<LinhaEncomenda> res = new ArrayList<>();
       
       for(LinhaEncomenda l: this.linhasEncomenda){
           res.add(l.clone());
        }
       return res;
    }
   
   /**
    * Atualiza o codigo da encomenda
    * 
    * @param codEnc novo codigo de encomenda. 
    */
   public void setCodEncomenda(String codEnc){
       this.codEncomenda=codEnc;
   }
   
   /**
    * Atualiza o codigo do utilizador.
    * 
    * @param codUtilidor novo codigo do Models.Utilizador.
    */   
   public void setCodUtilizador(String codUtilidor){
       this.codUtilizador=codUtilidor;
   }
   
   /**
    * Atualiza o codigo da Models.Loja
    * 
    * @param codLoja novo codigo da Models.Loja.
    */
   public void setCodLoja(String codLoja){
       this.codLoja=codLoja;
   } 
   
   /**
    * Atualiza o valor do peso
    * 
    * @param peso novo valor do peso. 
    */
   public void setPeso(double peso){
       this.peso=peso;
   }
   
   /**
    * Atualiza a lista da encomenda.
    * 
    * @param lc nova lista da encomenda. 
    */
   public void setLinhasEncomenda(List<LinhaEncomenda> lc){
       this.linhasEncomenda = new ArrayList<>();
       for(LinhaEncomenda l: lc){
           this.linhasEncomenda.add(l.clone());
       }
    }
    
   /**
    * Metodo que adiciona uma LinhaEncomenda a lista das linhasEncomenda.
    * 
    * @param l LinhaEncomenda a adicionar.
    */
   public void addLinhaEncomenda(LinhaEncomenda l){
       this.linhasEncomenda.add(l.clone());
    }

   /**
    * Verifica se uma Lista de Produtos contem algum medicamento
    *
    * @param medicamentos correspondente a lista de Produtos
    * @return false se nao tiver medicamentos
    */
   public boolean contemMedicamentos(List<Produto> medicamentos){
      Iterator<Produto> it = medicamentos.iterator();
      boolean temMedicamento = false;
      while (it.hasNext() && !temMedicamento){
          Produto p = it.next();
          temMedicamento = this.linhasEncomenda.stream().map(LinhaEncomenda::getCodProduto).anyMatch(c->c.equals(p.getCodigoProduto()));
      }
       return temMedicamento;
   }
   
   /**
    * Metodo que devolve a representação em String de Encomenda.
    * 
    * @return String com todos os parametros de Encomenda. 
    */
    
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo: "+this.codEncomenda)
          .append(", Utilizador: ").append(this.codUtilizador)
          .append(", Loja: ").append(this.codLoja)
          .append(", Peso: ").append(this.peso)
          .append(", Encomenda: ").append(this.linhasEncomenda.toString());
          
          return sb.toString();
    }

   /**
    * Verifica se 2 Objects sao iguais
    *
    * @return true se Object for igual a Loja
    */
   public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Encomenda e = (Encomenda) o;

        return (this.codEncomenda.equals(e.getCodEncomenda()) &&
                this.codLoja.equals(e.getCodLoja()) &&
                this.codUtilizador.equals(e.getCodUtilizador()) &&
                this.peso == e.getPeso() &&
                this.linhasEncomenda.equals(e.getLinhasEncomenda()));
   }
   
   /**
   * Metodo que faz uma copia do objecto receptor da mensagem.
   * Para tal invoca o construtor de copia.
   * 
   * @return objecto clone do objecto que recebe a mensagem.
   */
   public Encomenda clone(){
       return new Encomenda(this);
   }
   
}
