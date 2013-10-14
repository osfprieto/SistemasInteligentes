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
        
        RecorridoState newState = actualState.clone();
        
        if(action.equals(OurActions.DO_NOTHING)){
        
        }
        
        //@TODO Terminar esto
        
        return newState;
    }

    @Override
    public Vector<Action> succesor(State state) {
        Vector<Action> ret = new Vector<Action>();
        
        ret.add(OurActions.EAT);
        ret.add(OurActions.GO_RIGHT);
        ret.add(OurActions.GO_FRONT);
        ret.add(OurActions.GO_LEFT);
        ret.add(OurActions.GO_BACK);
        ret.add(OurActions.DO_NOTHING);
        
        return ret;
    }
    
}
