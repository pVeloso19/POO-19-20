package Models.Utilizador;

import java.io.Serializable;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

public class GPS implements Serializable {

    /**
     * Variaveis Instancia
     */
    private double x;
    private double y;

    /**
     * Construtor por omissão de GPS
     */
    public GPS(){
        this.x = 0;
        this.y = 0;
    }

    /**
     * Construtor Parametrizado de GPS
     * Aceita como parametros os valores para cada Variavel de Instancia
     */
    public GPS(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Construtor de copia de um GPS
     * Aceita como parametros outro GPS e utiliza os metodos
     * de acesso aos valores das Variaveis de Instancia
     */
    public GPS(GPS g){
        this.x = g.getX();
        this.y = g.getY();
    }

    /**
     * Devolve valor referente à  Coordenada x
     * @return valor x
     */
    public double getX(){
        return this.x;
    }

    /**
     * Devolve valor referente à  Coordenada y
     * @return valor y
     */
    public double getY(){
        return this.y;
    }

    /**
     * Atualiza valor da Coordenada x
     * @param x novo de x
     */
    public void setX(double x){
        this.x = x;
    }

    /**
     * Atualiza valor da Coordenada y
     * @param y novo de y
     */
    public void setY(double y){
        this.y = y;
    }

    /**
     * Com base nas suas coordenadas e nas coordenadas de outra localizacao
     * calcula qual a distancia a essa localizacao
     *
     * @param gps correspondente a localizacao do lugar que pretendemos calcular a distancia
     * @return distancia entre os dois lugares
     */
    public double distancia(GPS gps){
        return sqrt(pow(this.x - gps.getX(), 2) + pow(this.y - gps.getY(), 2));
    }

    /**
     * Tentar calcular a distancia, nao com base na longitude e latitude, mas sim
     * na conversao destes para Km.
     *
     * @param gps2 correspondente a localizacao do lugar que pretendemos calcular a distancia
     * @return distancia entre os dois lugares mas em Km
     */
    public double DistanciaRealEmKM(GPS gps2) {
        double _eQuatorialEarthRadius = 6378.1370D;
        double _d2r = (Math.PI / 180D);

        double dlong = (gps2.getY() - this.y) * _d2r;
        double dlat = (gps2.getX() - this.x) * _d2r;

        double a = Math.pow(Math.sin(dlat / 2D), 2D) + Math.cos(this.x * _d2r) * Math.cos(gps2.getX() * _d2r)
                * Math.pow(Math.sin(dlong / 2D), 2D);
        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
        double d = _eQuatorialEarthRadius * c;

        return d;
    }

    /**
     * Metodo que devolve a representaçao em String do GPS
     * @return String com Informação do GPS
     */
    public String toString(){
        return "Longitude: " + this.y + "," +
                "Latitude: " + this.x;
    }

    /**
     * Metodo que determina se dois GPS são iguais
     * @return booleano que é verdadeiro se todos os valores forem iguais
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        GPS g = (GPS)o;

        return (this.x == g.getX() &&
                this.y == g.getY());
    }

    /**
     * Metodo que faz uma copia do objecto receptor da mensagem .
     * Para tal invoca o construtor de copia
     *
     * @return objeto clone do objeto que recebe a mensagem
     */
    public GPS clone(){
        return new GPS(this);
    }
}

