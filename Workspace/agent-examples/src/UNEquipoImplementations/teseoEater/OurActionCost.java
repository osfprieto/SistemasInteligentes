/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

import unalcol.agents.Action;
import unalcol.agents.search.ActionCost;
import unalcol.agents.search.State;

/**
 *
 * @author osfprieto
 */
public class OurActionCost implements ActionCost{

    //Para esta implementacion
    //No hacer nada y comer valen 0
    //Moverse hacia algún lado vale 1 con la cantidad de veces que toca rotar 1c/u
    //que por ahora cada rotación es gratis
    
    @Override
    public double evaluate(State state, Action action) {
        if(action.equals(OurActions.DO_NOTHING) 
                || action.equals(OurActions.EAT))
            return 0;
            
        return 1;
    }
    
}
