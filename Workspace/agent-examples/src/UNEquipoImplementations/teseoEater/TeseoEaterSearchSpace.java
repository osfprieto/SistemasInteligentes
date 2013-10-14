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
public class TeseoEaterSearchSpace implements SearchSpace{

    @Override
    public boolean feasible(State state) {
        PathState recorridoState = (PathState)state;
        
        if(recorridoState.actualCell.equals(Cell.WALL)
                || recorridoState.energyLevel<= 0)
            return false;
        
        return true;
        
    }

    @Override
    public State succesor(State state, Action action) {
        PathState actualState = (PathState) state;
        
        PathState newState = actualState.clone();
        
        if(action.equals(TeseoEaterActions.DO_NOTHING)){
        
        }
        
        //@TODO Terminar esto
        
        return newState;
    }

    @Override
    public Vector<Action> succesor(State state) {
        Vector<Action> ret = new Vector<Action>();
        
        ret.add(TeseoEaterActions.EAT);
        ret.add(TeseoEaterActions.GO_RIGHT);
        ret.add(TeseoEaterActions.GO_FRONT);
        ret.add(TeseoEaterActions.GO_LEFT);
        ret.add(TeseoEaterActions.GO_BACK);
        ret.add(TeseoEaterActions.DO_NOTHING);
        
        return ret;
    }
    
}
