/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

/**
 *
 * @author Fredy
 */
public class Coordenada implements Comparable{
    
    public int x;
    public int y;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Coordenada coordenadaAlNorte(){
        Coordenada ret = new Coordenada(x-1, y);
        return ret;
    }
    
    public Coordenada coordenadaAlSur(){
        Coordenada ret = new Coordenada(x+1, y);
        return ret;
    }
    public Coordenada coordenadaAlOriente(){
        Coordenada ret = new Coordenada(x, y-1);
        return ret;
    }
    public Coordenada coordenadaAlOccidente(){
        Coordenada ret = new Coordenada(x, y+1);
        return ret;
    }
 
    public int compareTo(Coordenada comp){
        if(x==comp.x && y==comp.y)
            return 0;
        return 1;
    }

    public int compareTo(Object t) {
        return compareTo((Celda)t);
    }

    public boolean equals(Coordenada other){
        return compareTo(other)==0;
    }
    
}
