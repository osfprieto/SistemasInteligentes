/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

import java.util.HashMap;
import java.util.LinkedList;
import unalcol.agents.Action;
import unalcol.agents.search.State;

/**
 *
 * @author Fredy
 */
public class RecorridoState implements State{
    
    public static final int NORTE = 0;
    public static final int SUR = 1;
    public static final int ORIENTE = 2;
    public static final int OCCIDENTE = 3;
    
    public static final int ENERGIA_MAXIMA = 40;
    public static final int ENERGIA_INICIAL = 20;
    
    public RecorridoState prevState;
    public Action accionParaLlegarAEsteEstado;
    
    public Celda celdaActual;
    public int direccion;
    
    public int energyLevel;
    
    public LinkedList<Celda> comidaBuena;
    public LinkedList<Celda> comidaMala;
    
    public HashMap<Coordenada, Celda> celdasPorCoordenadas;
    
    public RecorridoState(){
        prevState = null;
        accionParaLlegarAEsteEstado = null;
        Coordenada coordenada = new Coordenada(0, 0);
        celdaActual = new Celda(coordenada, null, null, null, null, null, false);
    }
    
    public boolean equal(State state) {
        RecorridoState otherState = (RecorridoState) state;
        boolean ret;
        
        ret = celdaActual.equals(otherState.celdaActual);
        ret = ret && direccion == otherState.direccion;
        ret = ret && energyLevel == energyLevel;
        ret = ret && comidaBuena.equals(otherState.comidaBuena);
        ret = ret && comidaMala.equals(otherState.comidaMala);
        ret = ret && celdasPorCoordenadas.equals(otherState.celdasPorCoordenadas);
        
        return ret;
        
    }
    
    public Celda getCeldaAdelante(){
        if(direccion == NORTE)
            return celdaActual.celdaAlNorte;
        if(direccion == SUR)
            return celdaActual.celdaAlSur;
        if(direccion == ORIENTE)
            return celdaActual.celdaAlOriente;
        if(direccion == OCCIDENTE)
            return celdaActual.celdaAlOccidente;
        return null;
    }
    
    public Celda getCeldaAtras(){
        if(direccion == NORTE)
            return celdaActual.celdaAlSur;
        if(direccion == SUR)
            return celdaActual.celdaAlNorte;
        if(direccion == ORIENTE)
            return celdaActual.celdaAlOccidente;
        if(direccion == OCCIDENTE)
            return celdaActual.celdaAlOriente;
        return null;
    }
    
    public Celda getCeldaDerecha(){
        if(direccion == NORTE)
            return celdaActual.celdaAlOriente;
        if(direccion == SUR)
            return celdaActual.celdaAlOccidente;
        if(direccion == ORIENTE)
            return celdaActual.celdaAlSur;
        if(direccion == OCCIDENTE)
            return celdaActual.celdaAlNorte;
        return null;
    }
    
    public Celda getCeldaIzquierda(){
        if(direccion == NORTE)
            return celdaActual.celdaAlOccidente;
        if(direccion == SUR)
            return celdaActual.celdaAlOriente;
        if(direccion == ORIENTE)
            return celdaActual.celdaAlNorte;
        if(direccion == OCCIDENTE)
            return celdaActual.celdaAlSur;
        return null;
    }
    
    public Coordenada getCoordenadaAdelante(){
        Coordenada coordenada = celdaActual.coordenada;
        if(direccion == NORTE)
            return coordenada.coordenadaAlNorte();
        if(direccion == SUR)
            return coordenada.coordenadaAlSur();
        if(direccion == ORIENTE)
            return coordenada.coordenadaAlOriente();
        if(direccion == OCCIDENTE)
            return coordenada.coordenadaAlOccidente();
        return null;
    }
    
    public Coordenada getCoordenadaAtras(){
        Coordenada coordenada = celdaActual.coordenada;
        if(direccion == NORTE)
            return coordenada.coordenadaAlSur();
        if(direccion == SUR)
            return coordenada.coordenadaAlNorte();
        if(direccion == ORIENTE)
            return coordenada.coordenadaAlOccidente();
        if(direccion == OCCIDENTE)
            return coordenada.coordenadaAlOriente();
        return null;
    }
    
    public Coordenada getCoordenadaDerecha(){
        Coordenada coordenada = celdaActual.coordenada;
        if(direccion == NORTE)
            return coordenada.coordenadaAlOriente();
        if(direccion == SUR)
            return coordenada.coordenadaAlOccidente();
        if(direccion == ORIENTE)
            return coordenada.coordenadaAlSur();
        if(direccion == OCCIDENTE)
            return coordenada.coordenadaAlNorte();
        return null;
    }
    
    public Coordenada getCoordenadaIzquierda(){
        Coordenada coordenada = celdaActual.coordenada;
        if(direccion == NORTE)
            return coordenada.coordenadaAlOccidente();
        if(direccion == SUR)
            return coordenada.coordenadaAlOriente();
        if(direccion == ORIENTE)
            return coordenada.coordenadaAlNorte();
        if(direccion == OCCIDENTE)
            return coordenada.coordenadaAlSur();
        return null;
    }
    
}