/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

import unalcol.agents.simulate.util.SimpleLanguage;

/**
 *
 * @author Fredy Virguez
 */
public class TeseoEaterAgentProgram extends TeseoEaterAbstractAgentProgram {

    private PathState pathState = new PathState();

    public TeseoEaterAgentProgram(SimpleLanguage language) {
        super(language);
    }

    @Override
    public String accion(boolean pf, boolean pd, boolean pa, boolean pi, boolean te, boolean co, boolean color, boolean forma, boolean tamano, boolean peso, boolean tipo, int energia) {

        String action = TeseoEaterActions.DO_NOTHING_STRING;
        if (te) {
            return TeseoEaterActions.DIE_STRING;
        }
        pathState.energyLevel = energia;
        Food food;
        if (pathState.prevState != null) {
            if (pathState.energyLevel - pathState.prevState.energyLevel < -1) {
                int size = pathState.goodFoodList.size();
                food = pathState.goodFoodList.remove(size - 1);
                pathState.badFoodList.add(food);
                System.out.println(food + " " + pathState.badFoodList);
            }
        }
        if (co) {
            food = new Food(color, forma, tamano, peso, tipo);
            if (energia < PathState.MAX_ENERGY_LEVEL) {
                boolean isABadFood = false;
                for (Food f : pathState.badFoodList) {
                    if (f.equals(food)) {
                        isABadFood = true;
                    }
                }
                if (!isABadFood) {
                    action = TeseoEaterActions.EAT_STRING;
                    boolean isAGoodFood = false;
                    for (Food f : pathState.goodFoodList) {
                        if (f.equals(food)) {
                            isAGoodFood = true;
                        }
                    }
                    if (!isAGoodFood) {
                        pathState.goodFoodList.add(food);
                        System.out.println("new food found");
                    }                    
                }
            }
            pathState.actualCell.food = food;
        }
        if (!action.equals(TeseoEaterActions.EAT_STRING)) {
            if (!pd) {
                action = TeseoEaterActions.GO_RIGHT_STRING;
            } else if (!pf) {
                action = TeseoEaterActions.GO_FRONT_STRING;
            } else if (!pi) {
                action = TeseoEaterActions.GO_LEFT_STRING;
            } else if (!pa) {
                action = TeseoEaterActions.GO_BACK_STRING;
            }
            pathState = pathState.createNew(action);
        }
        return action;
    }
}