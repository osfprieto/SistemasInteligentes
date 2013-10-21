/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

import java.util.ArrayList;
import unalcol.agents.Action;
import unalcol.agents.search.SearchSpace;
import unalcol.agents.search.State;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author osfprieto
 */
public class TeseoEaterSearchSpace implements SearchSpace{

    public static final int ENERGY_DELTA = 1;
    public static final int MINIMAL_ENERGY_LEVEL = 5;
    
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
        
        
        Cell actualCell = actualState.actualCell;
        
        if(action.equals(TeseoEaterActions.DO_NOTHING) ||
                action.equals(TeseoEaterActions.DIE)){
            //Does not do anything
        }
        else if(action.equals(TeseoEaterActions.EAT) && actualCell.food!=null){
            
            ArrayList<Food> goodFood = actualState.goodFoodList;
            ArrayList<Food> badFood = actualState.badFoodList;
            
            if(goodFood.contains(actualCell.food)){
                newState.energyLevel += ENERGY_DELTA;
            }
            else{
                newState.energyLevel -= ENERGY_DELTA;
            }
            
        }
        else if(action.equals(TeseoEaterActions.GO_BACK)){
            if(actualState.energyLevel>MINIMAL_ENERGY_LEVEL){
                Cell newCell = actualState.getBackCell();
                if(newCell!=null){
                    newState.actualCell = newCell;
                }
            }
        }
        else if(action.equals(TeseoEaterActions.GO_FRONT)){
            if(actualState.energyLevel>MINIMAL_ENERGY_LEVEL){
                Cell newCell = actualState.getFrontCell();
                if(newCell!=null){
                    newState.actualCell = newCell;
                }
            }
        }
        else if(action.equals(TeseoEaterActions.GO_LEFT)){
            if(actualState.energyLevel>MINIMAL_ENERGY_LEVEL){
                Cell newCell = actualState.getLeftCell();
                if(newCell!=null){
                    newState.actualCell = newCell;
                }
            }
        }
        else if(action.equals(TeseoEaterActions.GO_RIGHT)){
            if(actualState.energyLevel>MINIMAL_ENERGY_LEVEL){
                Cell newCell = actualState.getRightCell();
                if(newCell!=null){
                    newState.actualCell = newCell;
                }
            }
        }
        
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
