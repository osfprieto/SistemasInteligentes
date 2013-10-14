/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

import unalcol.agents.Action;
import unalcol.agents.search.SearchSpace;
import unalcol.agents.search.State;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author osfprieto
 */
public class OurSearchSpace implements SearchSpace{

    @Override
    public boolean feasible(State state) {
        RecorridoState recorridoState = (RecorridoState)state;
        
        if(recorridoState.celdaActual.equals(Celda.WALL)
                || recorridoState.energyLevel<= 0)
            return false;
        
        return true;
        
    }

    @Override
    public State succesor(State state, Action action) {
        RecorridoState actualState = (RecorridoState) state;
        if(action.equals(OurActions.DO_NOTHING))
            return actualState;
        
        if(action.equals(OurActions.))
            return actualState;
       
        
        
    }

    @Override
    public Vector<Action> succesor(State state) {
        Vector<Action> ret = new Vector<Action>();
        
        RecorridoState actualState = (RecorridoState)state;
        
        Celda celdaActual = actualState.celdaActual;
        
        if(actualState.comidaBuena.contains(celdaActual))
        
        ret.add(OurActions.DO_NOTHING);
        if()
        
    }
    
}
