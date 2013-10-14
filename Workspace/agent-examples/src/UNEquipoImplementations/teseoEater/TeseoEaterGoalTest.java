/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

import unalcol.agents.search.GoalTest;
import unalcol.agents.search.State;

/**
 *
 * @author osfprieto
 */
public class TeseoEaterGoalTest implements GoalTest{

    @Override
    public boolean test(State state) {
        RecorridoState ourState = (RecorridoState) state;
        Celda celdaActual = ourState.celdaActual;
        return celdaActual.esSalida;
    }
    
}
