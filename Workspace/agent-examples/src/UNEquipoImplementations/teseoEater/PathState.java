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
public class PathState implements State{
    
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
    public LinkedList<Food> goodFoodList;
    public LinkedList<Food> badFoodList;    
    public HashMap<Coordinate, Cell> cellsByCoordinates;
    
    public PathState(){
        prevState = null;
        actionToReachThisState = null;        
        Coordinate coordenada = new Coordinate(0, 0);
        actualCell = new Cell(coordenada, null, null, null, null, null, false);
        direction = EAST;        
        energyLevel = 0;        
        goodFoodList = new LinkedList<>();
        badFoodList = new LinkedList<>();        
        cellsByCoordinates = new HashMap<>();
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
    public Cell getFrontCell(){
        if(direction == NORTH)
            return actualCell.northCell;
        if(direction == SOUTH)
            return actualCell.southCell;
        if(direction == EAST)
            return actualCell.eastCell;
        if(direction == WEST)
            return actualCell.westCell;
        return null;
    }
    
    /**
     *
     * @return Cell
     */
    public Cell getBackCell(){
        if(direction == NORTH)
            return actualCell.southCell;
        if(direction == SOUTH)
            return actualCell.northCell;
        if(direction == EAST)
            return actualCell.westCell;
        if(direction == WEST)
            return actualCell.eastCell;
        return null;
    }
    
    /**
     *
     * @return Cell
     */
    public Cell getRightCell(){
        if(direction == NORTH)
            return actualCell.eastCell;
        if(direction == SOUTH)
            return actualCell.westCell;
        if(direction == EAST)
            return actualCell.southCell;
        if(direction == WEST)
            return actualCell.northCell;
        return null;
    }
    
    /**
     *
     * @return Cell
     */
    public Cell getLeftCell(){
        if(direction == NORTH)
            return actualCell.westCell;
        if(direction == SOUTH)
            return actualCell.eastCell;
        if(direction == EAST)
            return actualCell.northCell;
        if(direction == WEST)
            return actualCell.southCell;
        return null;
    }
    
    /**
     *
     * @return Coordinate
     */
    public Coordinate getFrontCoordinate(){
        Coordinate coordenada = actualCell.coordinate;
        if(direction == NORTH)
            return coordenada.northCoordinate();
        if(direction == SOUTH)
            return coordenada.southCoordinate();
        if(direction == EAST)
            return coordenada.eastCoordinate();
        if(direction == WEST)
            return coordenada.westCoordinate();
        return null;
    }
    
    /**
     *
     * @return Coordinate
     */
    public Coordinate getBackCoordinate(){
        Coordinate coordenada = actualCell.coordinate;
        if(direction == NORTH)
            return coordenada.southCoordinate();
        if(direction == SOUTH)
            return coordenada.northCoordinate();
        if(direction == EAST)
            return coordenada.westCoordinate();
        if(direction == WEST)
            return coordenada.eastCoordinate();
        return null;
    }
    
    /**
     *
     * @return Coordinate
     */
    public Coordinate getRightCoordinate(){
        Coordinate coordenada = actualCell.coordinate;
        if(direction == NORTH)
            return coordenada.eastCoordinate();
        if(direction == SOUTH)
            return coordenada.westCoordinate();
        if(direction == EAST)
            return coordenada.southCoordinate();
        if(direction == WEST)
            return coordenada.northCoordinate();
        return null;
    }
    
    /**
     *
     * @return Coordinate
     */
    public Coordinate getLeftCoordinate(){
        Coordinate coordenada = actualCell.coordinate;
        if(direction == NORTH)
            return coordenada.westCoordinate();
        if(direction == SOUTH)
            return coordenada.eastCoordinate();
        if(direction == EAST)
            return coordenada.northCoordinate();
        if(direction == WEST)
            return coordenada.southCoordinate();
        return null;
    }
    
    /**
     *
     * @return a copy of the PathState
     */
    @Override
    public PathState clone(){
        
        PathState ret = new PathState();
        ret.actionToReachThisState = actionToReachThisState;
        ret.actualCell = actualCell;
        ret.cellsByCoordinates = (HashMap<Coordinate, Cell>) cellsByCoordinates.clone();
        ret.goodFoodList = (LinkedList<Food>) goodFoodList.clone();
        ret.badFoodList = (LinkedList<Food>) badFoodList.clone();
        ret.direction = direction;
        ret.energyLevel = energyLevel;
        ret.prevState = prevState;
        
        return ret;
        
    }
    
    /**
     * Not completed
     * @return 
     */
    public PathState createNew(String action) {
        
        PathState newPathState = this.clone();
        newPathState.prevState = this;
        newPathState.actionToReachThisState = new Action(action);
        Cell newCell;
        newCell = new Cell(null, null, null, null, null, null, false);
        if(action.equals(TeseoEaterActions.GO_FRONT_STRING)){
            newCell.coordinate = getFrontCoordinate();
            switch(direction){
                case NORTH:
                    newCell.southCell = this.actualCell;
                    break;
                case SOUTH:
                    newCell.northCell = this.actualCell;
                    break;
                case EAST:
                    newCell.westCell = this.actualCell;
                    break;
                case WEST:
                    newCell.eastCell = this.actualCell;
                    break;
            }            
        }else if(action.equals(TeseoEaterActions.GO_BACK_STRING)){
            newCell.coordinate = getBackCoordinate();
            switch(direction){
                case NORTH:
                    direction = SOUTH;
                    newCell.northCell = this.actualCell;
                    break;
                case SOUTH:
                    direction = NORTH;
                    newCell.southCell = this.actualCell;
                    break;
                case EAST:
                    direction = WEST;
                    newCell.eastCell = this.actualCell;
                    break;
                case WEST:
                    direction = EAST;
                    newCell.westCell = this.actualCell;
                    break;
            }            
        }else if(action.equals(TeseoEaterActions.GO_RIGHT_STRING)){
            newCell.coordinate = getRightCoordinate();
            switch(direction){
                case NORTH:
                    direction = EAST;
                    newCell.northCell = this.actualCell;
                    break;
                case SOUTH:
                    direction = WEST;
                    newCell.southCell = this.actualCell;
                    break;
                case EAST:
                    direction = SOUTH;
                    newCell.eastCell = this.actualCell;
                    break;
                case WEST:
                    direction = NORTH;
                    newCell.westCell = this.actualCell;
                    break;
            }            
        }
        newPathState.actualCell = newCell;
        return newPathState;
    }
    
}