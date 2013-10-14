/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

/**
 *
 * @author Fredy
 */
public class Cell {
    
    public static final Cell WALL = new Cell(
            new Coordinate(Integer.MAX_VALUE, Integer.MAX_VALUE),
            null, null, null, null, null, false);
    
    public Coordinate coordinate;
    public int timesVisited;
    public Cell northCell;
    public Cell southCell;
    public Cell eastCell;
    public Cell westCell;
    public boolean blockedRoad;//Indica si por ahí se llega a un camino muerto
    public boolean hasTreasure;//Indica si es salida del laberinto
    public Food food;//En caso de que haya food

    public Cell(Coordinate coordenada, Cell celdaAlNorte, Cell celdaAlSur,
            Cell celdaAlOriente, Cell celdaAlOccidente, Food comida, boolean hasTreasure) {
        this.timesVisited = 1;
        this.coordinate = coordenada;
        this.northCell = celdaAlNorte;
        this.southCell = celdaAlSur;
        this.eastCell = celdaAlOriente;
        this.westCell = celdaAlOccidente;
        this.food = comida;
        this.blockedRoad = false;
        this.hasTreasure = hasTreasure;
    }
    
    public boolean equals(Cell other){
        boolean ret;
        ret = coordinate.equals(other.coordinate);
        ret = ret && timesVisited==other.timesVisited;
        ret = ret && northCell.equals(other.northCell);
        ret = ret && southCell.equals(other.southCell);
        ret = ret && eastCell.equals(other.eastCell);
        ret = ret && westCell.equals(other.westCell);
        ret = ret && blockedRoad == other.blockedRoad;
        ret = ret && food.equals(other.food);
        
        return ret;
    }
    
}
