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
        String tempAction = null;
        if (getRightCell() == null && !pd) {
            tempAction = TeseoEaterActions.GO_RIGHT_STRING;
        }
        if (getFrontCell() == null && !pf && tempAction == null) {
            tempAction = TeseoEaterActions.GO_FRONT_STRING;
        }
        if (getLeftCell() == null && !pi && tempAction == null) {
            tempAction = TeseoEaterActions.GO_LEFT_STRING;
        }
        if (getBackCell() == null && !pa && tempAction == null) {
            tempAction = TeseoEaterActions.GO_BACK_STRING;
        }
        if (tempAction == null) {
            tempAction = calculateActionToReachLessVisitedCell(pd, pf, pi, pa);
        }
        if (!action.equals(TeseoEaterActions.EAT_STRING)) {
            action = tempAction;
            pathState = pathState.createNew(action);
        }
        return action;
    }

    private Cell getFrontCell() {

        return pathState.cellsByCoordinates.get(pathState.getFrontCoordinate().toString());
    }

    private Cell getBackCell() {

        return pathState.cellsByCoordinates.get(pathState.getBackCoordinate().toString());
    }

    private Cell getRightCell() {

        return pathState.cellsByCoordinates.get(pathState.getRightCoordinate().toString());
    }

    private Cell getLeftCell() {

        return pathState.cellsByCoordinates.get(pathState.getLeftCoordinate().toString());
    }

    private String calculateActionToReachLessVisitedCell(boolean pd, boolean pf, boolean pi, boolean pa) {

        int r = Integer.MAX_VALUE, l = Integer.MAX_VALUE, f = Integer.MAX_VALUE, b = Integer.MAX_VALUE;
        if (getBackCell() != null && !pa) {
            b = getBackCell().timesVisited;
        }
        if (getFrontCell() != null && !pf) {
            f = getFrontCell().timesVisited;
        }
        if (getRightCell() != null && !pd) {
            r = getRightCell().timesVisited;
        }
        if (getLeftCell() != null && !pi) {
            l = getLeftCell().timesVisited;
        }
        int min = Math.min(r, l);
        min = Math.min(f, min);
        min = Math.min(b, min);
        if (min == r) {
            return TeseoEaterActions.GO_RIGHT_STRING;
        }
        if (min == l) {
            return TeseoEaterActions.GO_LEFT_STRING;
        }
        if (min == f) {
            return TeseoEaterActions.GO_FRONT_STRING;
        }
        return TeseoEaterActions.GO_BACK_STRING;
    }
}