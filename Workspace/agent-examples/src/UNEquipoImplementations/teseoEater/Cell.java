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
        this.timesVisited = 0;
        this.coordinate = coordenada;
        this.northCell = celdaAlNorte;
        this.southCell = celdaAlSur;
        this.eastCell = celdaAlOriente;
        this.westCell = celdaAlOccidente;
        this.food = comida;
        this.blockedRoad = false;
        this.hasTreasure = hasTreasure;
    }

    public boolean equals(Cell other) {
        
        if(other == null){
            return false;
        }
        
        boolean ret;
        ret = coordinate.equals(other.coordinate);
        ret = ret && timesVisited == other.timesVisited;
        
        ret = ret && northCell == other.northCell;
        ret = ret && southCell == other.southCell;
        ret = ret && eastCell == other.eastCell;
        ret = ret && westCell == other.westCell;
        
        ret = ret && blockedRoad == other.blockedRoad;
        
        if(food!=null){
            ret = ret && food.equals(other.food);
        }
        else if(other.food!=null){
            return false;
        }
        else{
            //ret = ret && true;//both food are null
        }

        return ret;
    }

    @Override
    public String toString() {
        return "[" + coordinate + ":" + timesVisited + "," + blockedRoad + "]";
    }
}
