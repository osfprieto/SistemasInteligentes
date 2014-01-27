/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

import java.util.HashMap;
import java.util.ArrayList;
import unalcol.agents.Action;
import unalcol.agents.search.State;

/**
 *
 * @author Fredy
 */
public class PathState implements State {

    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;
    public static final int MAX_ENERGY_LEVEL = 40;
    public static final int INITIAL_ENERGY_LEVEL = 20;
    public PathState prevState;
    public Action actionToReachThisState;
    public Cell actualCell;
    public int direction;
    public int energyLevel;
    public ArrayList<Food> goodFoodList;
    public ArrayList<Food> badFoodList;
    public HashMap<String, Cell> cellsByCoordinates;

    public PathState() {
        prevState = null;
        actionToReachThisState = null;
        Coordinate coordenada = new Coordinate(0, 0);
        actualCell = new Cell(coordenada, null, null, null, null, null, false);
        direction = EAST;
        energyLevel = 0;
        goodFoodList = new ArrayList<>();
        badFoodList = new ArrayList<>();
        cellsByCoordinates = new HashMap<>();
        cellsByCoordinates.put(coordenada.toString(), actualCell);
    }

    /**
     *
     * @param state
     * @return boolean
     */
    @Override
    public boolean equal(State state) {
        PathState otherState = (PathState) state;
        boolean ret;
        ret = actualCell.equals(otherState.actualCell);
        ret = ret && direction == otherState.direction;
        ret = ret && energyLevel == energyLevel;
        ret = ret && goodFoodList.equals(otherState.goodFoodList);
        ret = ret && badFoodList.equals(otherState.badFoodList);
        ret = ret && cellsByCoordinates.equals(otherState.cellsByCoordinates);
        return ret;

    }

    /**
     *
     * @return Cell
     */
    public Cell getFrontCell() {
        if (direction == NORTH) {
            return actualCell.northCell;
        }
        if (direction == SOUTH) {
            return actualCell.southCell;
        }
        if (direction == EAST) {
            return actualCell.eastCell;
        }
        if (direction == WEST) {
            return actualCell.westCell;
        }
        return null;
    }

    /**
     *
     * @return Cell
     */
    public Cell getBackCell() {
        if (direction == NORTH) {
            return actualCell.southCell;
        }
        if (direction == SOUTH) {
            return actualCell.northCell;
        }
        if (direction == EAST) {
            return actualCell.westCell;
        }
        if (direction == WEST) {
            return actualCell.eastCell;
        }
        return null;
    }

    /**
     *
     * @return Cell
     */
    public Cell getRightCell() {
        if (direction == NORTH) {
            return actualCell.eastCell;
        }
        if (direction == SOUTH) {
            return actualCell.westCell;
        }
        if (direction == EAST) {
            return actualCell.southCell;
        }
        if (direction == WEST) {
            return actualCell.northCell;
        }
        return null;
    }

    /**
     *
     * @return Cell
     */
    public Cell getLeftCell() {
        if (direction == NORTH) {
            return actualCell.westCell;
        }
        if (direction == SOUTH) {
            return actualCell.eastCell;
        }
        if (direction == EAST) {
            return actualCell.northCell;
        }
        if (direction == WEST) {
            return actualCell.southCell;
        }
        return null;
    }

    /**
     *
     * @return Coordinate
     */
    public Coordinate getFrontCoordinate() {
        Coordinate coordenada = actualCell.coordinate;
        if (direction == NORTH) {
            return coordenada.northCoordinate();
        }
        if (direction == SOUTH) {
            return coordenada.southCoordinate();
        }
        if (direction == EAST) {
            return coordenada.eastCoordinate();
        }
        if (direction == WEST) {
            return coordenada.westCoordinate();
        }
        return null;
    }

    /**
     *
     * @return Coordinate
     */
    public Coordinate getBackCoordinate() {
        Coordinate coordenada = actualCell.coordinate;
        if (direction == NORTH) {
            return coordenada.southCoordinate();
        }
        if (direction == SOUTH) {
            return coordenada.northCoordinate();
        }
        if (direction == EAST) {
            return coordenada.westCoordinate();
        }
        if (direction == WEST) {
            return coordenada.eastCoordinate();
        }
        return null;
    }

    /**
     *
     * @return Coordinate
     */
    public Coordinate getRightCoordinate() {
        Coordinate coordenada = actualCell.coordinate;
        if (direction == NORTH) {
            return coordenada.eastCoordinate();
        }
        if (direction == SOUTH) {
            return coordenada.westCoordinate();
        }
        if (direction == EAST) {
            return coordenada.southCoordinate();
        }
        if (direction == WEST) {
            return coordenada.northCoordinate();
        }
        return null;
    }

    /**
     *
     * @return Coordinate
     */
    public Coordinate getLeftCoordinate() {
        Coordinate coordenada = actualCell.coordinate;
        if (direction == NORTH) {
            return coordenada.westCoordinate();
        }
        if (direction == SOUTH) {
            return coordenada.eastCoordinate();
        }
        if (direction == EAST) {
            return coordenada.northCoordinate();
        }
        if (direction == WEST) {
            return coordenada.southCoordinate();
        }
        return null;
    }

    /**
     *
     * @return a copy of the PathState
     */
    @Override
    public PathState clone() {

        PathState ret = new PathState();
        ret.actionToReachThisState = this.actionToReachThisState;
        ret.actualCell = this.actualCell;
        ret.cellsByCoordinates = (HashMap<String, Cell>) this.cellsByCoordinates.clone();
        ret.goodFoodList = (ArrayList<Food>) this.goodFoodList.clone();
        ret.badFoodList = (ArrayList<Food>) this.badFoodList.clone();
        ret.direction = this.direction;
        ret.energyLevel = this.energyLevel;
        ret.prevState = this.prevState;
        return ret;
    }

    /**
     * Not completed
     *
     * @return
     */
    public PathState createNew(String action) {

        PathState newPathState = this.clone();
        newPathState.prevState = this;
        newPathState.actionToReachThisState = new Action(action);
        Cell newCell;
        newCell = new Cell(null, null, null, null, null, null, false);
        if (action.equals(TeseoEaterActions.GO_FRONT_STRING)) {
            if (cellsByCoordinates.containsKey(getFrontCoordinate().toString())) {
                newCell = cellsByCoordinates.get(getFrontCoordinate().toString());
                newCell.timesVisited += 1;
            } else {
                newCell.coordinate = getFrontCoordinate();
            }
            switch (direction) {
                case NORTH:
                    newCell.southCell = this.actualCell;
                    this.actualCell.northCell = newCell;
                    break;
                case SOUTH:
                    newCell.northCell = this.actualCell;
                    this.actualCell.southCell = newCell;
                    break;
                case EAST:
                    newCell.westCell = this.actualCell;
                    this.actualCell.eastCell = newCell;
                    break;
                case WEST:
                    newCell.eastCell = this.actualCell;
                    this.actualCell.westCell = newCell;
                    break;
            }
        } else if (action.equals(TeseoEaterActions.GO_BACK_STRING)) {
            newCell = this.prevState.actualCell;
            newCell.timesVisited += 1;
            switch (direction) {
                case NORTH:
                    direction = SOUTH;
                    break;
                case SOUTH:
                    direction = NORTH;
                    break;
                case EAST:
                    direction = WEST;
                    break;
                case WEST:
                    direction = EAST;
                    break;
            }
        } else if (action.equals(TeseoEaterActions.GO_RIGHT_STRING)) {
            if (cellsByCoordinates.containsKey(getRightCoordinate().toString())) {
                newCell = cellsByCoordinates.get(getRightCoordinate().toString());
                newCell.timesVisited += 1;
            } else {
                newCell.coordinate = getRightCoordinate();
            }
            switch (direction) {
                case NORTH:
                    direction = EAST;
                    newCell.westCell = this.actualCell;
                    this.actualCell.eastCell = newCell;
                    break;
                case SOUTH:
                    direction = WEST;
                    newCell.eastCell = this.actualCell;
                    this.actualCell.westCell = newCell;
                    break;
                case EAST:
                    direction = SOUTH;
                    newCell.northCell = this.actualCell;
                    this.actualCell.southCell = newCell;
                    break;
                case WEST:
                    direction = NORTH;
                    newCell.southCell = this.actualCell;
                    this.actualCell.northCell = newCell;
                    break;
            }
        } else if (action.equals(TeseoEaterActions.GO_LEFT_STRING)) {
            if (cellsByCoordinates.containsKey(getLeftCoordinate().toString())) {
                newCell = cellsByCoordinates.get(getLeftCoordinate().toString());
                newCell.timesVisited += 1;
            } else {
                newCell.coordinate = getLeftCoordinate();
            }
            switch (direction) {
                case NORTH:
                    direction = WEST;
                    newCell.eastCell = this.actualCell;
                    this.actualCell.westCell = newCell;
                    break;
                case SOUTH:
                    direction = EAST;
                    newCell.westCell = this.actualCell;
                    this.actualCell.eastCell = newCell;
                    break;
                case EAST:
                    direction = NORTH;
                    newCell.southCell = this.actualCell;
                    this.actualCell.northCell = newCell;
                    break;
                case WEST:
                    direction = SOUTH;
                    newCell.northCell = this.actualCell;
                    this.actualCell.southCell = newCell;
                    break;
            }
        }
        newPathState.cellsByCoordinates.put(newCell.coordinate.toString(), newCell);
        newPathState.actualCell = newCell;
        newPathState.direction = direction;
        return newPathState;
    }
}