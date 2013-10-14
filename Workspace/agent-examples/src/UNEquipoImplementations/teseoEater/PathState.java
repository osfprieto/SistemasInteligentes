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
    
    public LinkedList<Cell> goodFood;
    public LinkedList<Cell> badFood;
    
    public HashMap<Coordinate, Cell> cellsByCoordinates;
    
    public PathState(){
        prevState = null;
        actionToReachThisState = null;
        
        Coordinate coordenada = new Coordinate(0, 0);
        actualCell = new Cell(coordenada, null, null, null, null, null, false);
        direction = EAST;
        
        energyLevel = 0;
        
        goodFood = new LinkedList<Cell>();
        badFood = new LinkedList<Cell>();
        
        cellsByCoordinates = new HashMap<Coordinate, Cell>();
    }
    
    public boolean equal(State state) {
        PathState otherState = (PathState) state;
        boolean ret;
        
        ret = actualCell.equals(otherState.actualCell);
        ret = ret && direction == otherState.direction;
        ret = ret && energyLevel == energyLevel;
        ret = ret && goodFood.equals(otherState.goodFood);
        ret = ret && badFood.equals(otherState.badFood);
        ret = ret && cellsByCoordinates.equals(otherState.cellsByCoordinates);
        
        return ret;
        
    }
    
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
    
    public PathState clone(){
        
        PathState ret = new PathState();
        ret.actionToReachThisState = actionToReachThisState;
        ret.actualCell = actualCell;
        ret.cellsByCoordinates = (HashMap<Coordinate, Cell>) cellsByCoordinates.clone();
        ret.goodFood = (LinkedList<Cell>) goodFood.clone();
        ret.badFood = (LinkedList<Cell>) badFood.clone();
        ret.direction = direction;
        ret.energyLevel = energyLevel;
        ret.prevState = prevState;
        
        return ret;
        
    }
    
}