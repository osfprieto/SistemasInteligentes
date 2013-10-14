/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

/**
 *
 * @author Fredy
 */
public class Celda {
    
    public static final Celda WALL = new Celda(
            new Coordenada(Integer.MAX_VALUE, Integer.MAX_VALUE),
            null, null, null, null, null, false);
    
    public Coordenada coordenada;
    public int vecesVisitada;
    public Celda celdaAlNorte;
    public Celda celdaAlSur;
    public Celda celdaAlOriente;
    public Celda celdaAlOccidente;
    public boolean caminoBloqueado;//Indica si por ahí se llega a un camino muerto
    public boolean esSalida;//Indica si es salida del laberinto
    public Comida comida;//En caso de que haya comida

    public Celda(Coordenada coordenada, Celda celdaAlNorte, Celda celdaAlSur,
            Celda celdaAlOriente, Celda celdaAlOccidente, Comida comida, boolean esSalida) {
        this.vecesVisitada = 1;
        this.coordenada = coordenada;
        this.celdaAlNorte = celdaAlNorte;
        this.celdaAlSur = celdaAlSur;
        this.celdaAlOriente = celdaAlOriente;
        this.celdaAlOccidente = celdaAlOccidente;
        this.comida = comida;
        this.caminoBloqueado = false;
        this.esSalida = esSalida;
    }
    
    public boolean equals(Celda other){
        boolean ret;
        ret = coordenada.equals(other.coordenada);
        ret = ret && vecesVisitada==other.vecesVisitada;
        ret = ret && celdaAlNorte.equals(other.celdaAlNorte);
        ret = ret && celdaAlSur.equals(other.celdaAlSur);
        ret = ret && celdaAlOriente.equals(other.celdaAlOriente);
        ret = ret && celdaAlOccidente.equals(other.celdaAlOccidente);
        ret = ret && caminoBloqueado == other.caminoBloqueado;
        ret = ret && comida.equals(other.comida);
        
        return ret;
    }
    
}
