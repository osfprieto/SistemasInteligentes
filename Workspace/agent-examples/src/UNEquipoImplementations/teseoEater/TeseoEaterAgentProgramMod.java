/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.agents.simulate.util.SimpleLanguage;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author Fredy Virguez
 */
public class TeseoEaterAgentProgramMod implements AgentProgram {
    
    
    public static final String START_SET_COST_PER_MOVE = "START_SET_COST_PER_MOVE";
    public static final String START_SET_COST_PER_TURN = "START_SET_COST_PER_TURN";
    public static final String START_SET_MAX_ENERGY = "START_SET_MAX_ENERGY";
    public static final String START_CHECK_FOOD = "START_CHECK_FOOD";
    
    public static final String SET_COST_PER_MOVE = "SET_COST_PER_MOVE";
    public static final String SET_COST_PER_TURN = "SET_COST_PER_TURN";
    public static final String SET_MAX_ENERGY = "SET_MAX_ENERGY";
    public static final String CHECK_FOOD = "CHECK_FOOD";
    
    public static final String START_CRUISE_MODE = "START_CRUISE_MODE";
    public static final String END_CRUISE_MODE = "END_CRUISE_MODE";
    
    int prevEnergyLevel;

    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    
    
    
    Cell actualCell;
    
    LinkedList<Cell> goodFoodCells;
    LinkedList<Cell> badFoodCells;
    LinkedList<Food> goodFoodType;
    LinkedList<Food> badFoodType;
    
    int energyLevel;
    int maxEnergyLevel;
    boolean isMaxEnergySet;
    
    public int direction;
    
    int energyDeltaPerMove;
    int energyDeltaPerTurn;
    boolean isCostPerMoveSet;
    boolean isCostPerTurnSet;
    
    boolean explorationModeEnabled;
    boolean cruiseModeEnabled;
    
    HashMap<String, Cell> cellsByCoordinates;

    
    protected SimpleLanguage language;
  protected Vector<String> cmd = new Vector<String>();
  protected int ultimoNivelEnergia;  
  protected String noOpLanguage;
  protected String dieLanguage;
  protected String advanceLanguage;
  protected String rotateLanguage;
  protected String eatLanguage;
    
    public TeseoEaterAgentProgramMod(SimpleLanguage language) {
        setLanguage(language);
        isCostPerMoveSet = false;
        isCostPerTurnSet = false;
        isMaxEnergySet = false;
        explorationModeEnabled = true;
        cruiseModeEnabled = false;
        direction = NORTH;
        
        Coordinate origin = new Coordinate(0, 0);
        actualCell = new Cell(origin, null, null, null, null, null, false);
        actualCell.timesVisited = 1;
        goodFoodCells = new LinkedList<Cell>();
        badFoodCells = new LinkedList<Cell>();
        goodFoodType = new LinkedList<Food>();
        badFoodType = new LinkedList<Food>();
        cellsByCoordinates = new HashMap<String, Cell>();
        cellsByCoordinates.put(actualCell.coordinate.toString(), actualCell);
    }
    
    public void setLanguage(  SimpleLanguage _language ){
        language = _language;
        noOpLanguage = language.getAction(0);
        dieLanguage = language.getAction(1);
        advanceLanguage = language.getAction(2);
        rotateLanguage = language.getAction(3);
        eatLanguage = language.getAction(4);
      }

      public void init(){
        cmd.clear();
      }

  public Action compute(Percept p){
    
        
      //"front"0, "right"1, "back"2, "left"3, "treasure"4, "resource"5,
      //"resource-color"6, "resource-shape"7, "resource-size"8,
      //"resource-weight"9, "resource-type"10, "energy_level"11
        

      boolean PF = ( (Boolean) p.getAttribute(language.getPercept(0))).
          booleanValue();
      boolean PD = ( (Boolean) p.getAttribute(language.getPercept(1))).
          booleanValue();
      boolean PA = ( (Boolean) p.getAttribute(language.getPercept(2))).
          booleanValue();
      boolean PI = ( (Boolean) p.getAttribute(language.getPercept(3))).
          booleanValue();
      boolean TRE = ( (Boolean) p.getAttribute(language.getPercept(4))).
          booleanValue();
      boolean RES = ( (Boolean) p.getAttribute(language.getPercept(5))).
          booleanValue();
      boolean COL = ( (Boolean) p.getAttribute(language.getPercept(6))).
          booleanValue();
      boolean SHA = ( (Boolean) p.getAttribute(language.getPercept(7))).
          booleanValue();
      boolean SIZ = ( (Boolean) p.getAttribute(language.getPercept(8))).
          booleanValue();
      boolean WEI = ( (Boolean) p.getAttribute(language.getPercept(9))).
          booleanValue();
      boolean TYP = ( (Boolean) p.getAttribute(language.getPercept(10))).
          booleanValue();
      
      int EL = ((Integer) p.getAttribute(language.getPercept(11))).intValue();
      
      
      if(!cruiseModeEnabled || (cmd.size()>0 && cmd.get(0).equals(END_CRUISE_MODE))){
          
          String accion = accion(PF, PD, PA, PI, TRE, RES, COL, SHA, SIZ, WEI, TYP, EL);

          if(accion.equals(START_CHECK_FOOD)){
              cmd.add(0, CHECK_FOOD);
              cmd.add(0, eatLanguage);
          }
          else if(accion.equals(START_SET_COST_PER_MOVE)){
              if(!Cell.WALL.equals(getFrontCell())){
                  cmd.add(0, SET_COST_PER_MOVE);
                  cmd.add(0, advanceLanguage);
              }
              if(!Cell.WALL.equals(getRightCell())){
                  cmd.add(0, SET_COST_PER_MOVE);
                  cmd.add(0, advanceLanguage);
                  cmd.add(0, rotateLanguage);
              }
              if(!Cell.WALL.equals(getBackCell())){
                  cmd.add(0, SET_COST_PER_MOVE);
                  cmd.add(0, advanceLanguage);
                  cmd.add(0, rotateLanguage);
                  cmd.add(0, rotateLanguage);
              }
              if(!Cell.WALL.equals(getLeftCell())){
                  cmd.add(0, SET_COST_PER_MOVE);
                  cmd.add(0, advanceLanguage);
                  cmd.add(0, rotateLanguage);
                  cmd.add(0, rotateLanguage);
                  cmd.add(0, rotateLanguage);
              }
          }
          else if(accion.equals(START_SET_COST_PER_TURN)){
              cmd.add(0, SET_COST_PER_TURN);
              cmd.add(0, rotateLanguage);
          }
          else if(accion.equals(START_SET_MAX_ENERGY)){
              cmd.add(0, SET_MAX_ENERGY);
              cmd.add(0, eatLanguage);
          }
          else if(accion.equals(START_CRUISE_MODE)){//No hace más sino hasta
              //que llegue al punto determinado por END_CRUISE_MODE
              cruiseModeEnabled = true;
          }
          else if(accion.equals(CHECK_FOOD)){
              if(energyLevel<prevEnergyLevel){//badFood
                  badFoodCells.add(actualCell);
                  badFoodType.add(actualCell.food);
                  System.out.println("Bad food registered");
              }
              else{
                  goodFoodCells.add(actualCell);
                  goodFoodType.add(actualCell.food);
                  System.out.println("Good food registered");
              }
          }
          else if(accion.equals(SET_COST_PER_MOVE)){
              energyDeltaPerMove = energyLevel-prevEnergyLevel;
              System.out.println("Energy delta per move: "+energyDeltaPerMove);
          }
          else if(accion.equals(SET_COST_PER_TURN)){
              energyDeltaPerTurn = energyLevel-prevEnergyLevel;
              System.out.println("Energy delta per turn: "+energyDeltaPerTurn);
          }
          else if(accion.equals(SET_MAX_ENERGY)){
              if(energyLevel == prevEnergyLevel){
                  maxEnergyLevel = energyLevel;
                  System.out.println("Max energy level: "+maxEnergyLevel);
              }
              else{
                  cmd.add(0, SET_MAX_ENERGY);
                  cmd.add(0, eatLanguage);
              }
          }
          else if(accion.equals(END_CRUISE_MODE)){
              cruiseModeEnabled = false;
          }
          else if(accion.equals(TeseoEaterActions.GO_BACK_STRING)){
              cmd.add(rotateLanguage);
              cmd.add(rotateLanguage);
              cmd.add(advanceLanguage);
          }
          else if(accion.equals(TeseoEaterActions.GO_FRONT_STRING)){
              cmd.add(advanceLanguage);
          } 
          else if(accion.equals(TeseoEaterActions.GO_LEFT_STRING)){
              cmd.add(rotateLanguage);
              cmd.add(rotateLanguage);
              cmd.add(rotateLanguage);
              cmd.add(advanceLanguage);
          }
          else if(accion.equals(TeseoEaterActions.GO_RIGHT_STRING)){
              cmd.add(rotateLanguage);
              cmd.add(advanceLanguage);
          }
          else if(accion.equals(TeseoEaterActions.EAT_STRING)){
              cmd.add(eatLanguage);
          }
          else if(accion.equals(TeseoEaterActions.DIE_STRING)){
              cmd.add(dieLanguage);
          }
          else if(accion.equals(TeseoEaterActions.DO_NOTHING_STRING)){
              cmd.add(noOpLanguage);
          }

          ////////////////////////////////////////////////////////////////////////////////////////
      }
      
      if(cmd.isEmpty()){
          cmd.add(noOpLanguage);
      }
      
    String x = cmd.get(0);
    cmd.remove(0);
    
    System.out.println(x);
    
    if(x.equals(advanceLanguage)){
        actualCell = getFrontCell();
        actualCell.timesVisited++;
    }
    else if(x.equals(rotateLanguage)){
        direction++;
        direction%=4;
    }
    
    return new Action(x);
  }
      
    public String accion(boolean pf,
                        boolean pd,//derecha
                        boolean pa,//atrás
                        boolean pi,
                        boolean te,//Tesoro
                        boolean co,//Comida
                        boolean color,
                        boolean forma,
                        boolean tamano,
                        boolean peso,
                        boolean tipo,
                        int energia) {
        
        if(te){
            return TeseoEaterActions.DO_NOTHING_STRING;
        }
        
        //Registro de percepciones
        
        prevEnergyLevel = energyLevel;
        energyLevel = energia;
        
        if(pf){
            setFrontCell(Cell.WALL);
        }
        else{
            Cell adyacentCell =
                    cellsByCoordinates.get(getFrontCoordinate().toString());
            if(adyacentCell!=null){
                setFrontCell(adyacentCell);
                if(direction == NORTH){
                    adyacentCell.southCell = actualCell;
                }
                else if(direction == EAST){
                    adyacentCell.westCell = actualCell;
                }
                else if(direction == SOUTH){
                    adyacentCell.northCell = actualCell;
                }
                else if(direction == WEST){
                    adyacentCell.eastCell = actualCell;
                }
            }
            else{
                Coordinate adyacentCoordinate = getFrontCoordinate();
                Cell newCell = new Cell(adyacentCoordinate,
                        direction==SOUTH?actualCell:null,
                        direction==NORTH?actualCell:null,
                        direction==WEST?actualCell:null,
                        direction==EAST?actualCell:null,
                        null,
                        false);
                setFrontCell(newCell);
                cellsByCoordinates.put(newCell.coordinate.toString(), newCell);
            }
        }
        
        if(pd){
            setRightCell(Cell.WALL);
        }
        else{
            Cell adyacentCell =
                    cellsByCoordinates.get(getRightCoordinate().toString());
            if(adyacentCell!=null){
                setRightCell(adyacentCell);
                if(direction == NORTH){
                    adyacentCell.westCell = actualCell;
                }
                else if(direction == EAST){
                    adyacentCell.northCell = actualCell;
                }
                else if(direction == SOUTH){
                    adyacentCell.eastCell = actualCell;
                }
                else if(direction == WEST){
                    adyacentCell.southCell = actualCell;
                }
            }
            else{
                Coordinate adyacentCoordinate = getRightCoordinate();
                Cell newCell = new Cell(adyacentCoordinate,
                        direction==EAST?actualCell:null,
                        direction==WEST?actualCell:null,
                        direction==SOUTH?actualCell:null,
                        direction==NORTH?actualCell:null,
                        null,
                        false);
                setRightCell(newCell);
                cellsByCoordinates.put(newCell.coordinate.toString(), newCell);
            }
        }
        
        if(pa){
            setBackCell(Cell.WALL);
        }
        else{
            Cell adyacentCell =
                    cellsByCoordinates.get(getBackCoordinate().toString());
            if(adyacentCell!=null){
                setBackCell(adyacentCell);
                if(direction == NORTH){
                    adyacentCell.northCell = actualCell;
                }
                else if(direction == EAST){
                    adyacentCell.eastCell = actualCell;
                }
                else if(direction == SOUTH){
                    adyacentCell.southCell = actualCell;
                }
                else if(direction == WEST){
                    adyacentCell.westCell = actualCell;
                }
            }
            else{
                Coordinate adyacentCoordinate = getBackCoordinate();
                Cell newCell = new Cell(adyacentCoordinate,
                        direction==NORTH?actualCell:null,
                        direction==SOUTH?actualCell:null,
                        direction==EAST?actualCell:null,
                        direction==WEST?actualCell:null,
                        null,
                        false);
                setBackCell(newCell);
                cellsByCoordinates.put(newCell.coordinate.toString(), newCell);
            }
        }
        
        if(pi){
            setLeftCell(Cell.WALL);
        }
        else{
            Cell adyacentCell =
                    cellsByCoordinates.get(getLeftCoordinate().toString());
            if(adyacentCell!=null){
                setLeftCell(adyacentCell);
                if(direction == NORTH){
                    adyacentCell.eastCell = actualCell;
                }
                else if(direction == EAST){
                    adyacentCell.southCell = actualCell;
                }
                else if(direction == SOUTH){
                    adyacentCell.westCell = actualCell;
                }
                else if(direction == WEST){
                    adyacentCell.northCell = actualCell;
                }
            }
            else{
                Coordinate adyacentCoordinate = getLeftCoordinate();
                Cell newCell = new Cell(adyacentCoordinate,
                        direction==WEST?actualCell:null,
                        direction==EAST?actualCell:null,
                        direction==NORTH?actualCell:null,
                        direction==SOUTH?actualCell:null,
                        null,
                        false);
                setLeftCell(newCell);
                cellsByCoordinates.put(newCell.coordinate.toString(), newCell);
            }
        }
        
        if((pi || getLeftCell().blockedRoad) &&
                (pd || getRightCell().blockedRoad) &&
                (pf || getFrontCell().blockedRoad)){//BlockedRoad
            actualCell.blockedRoad = true;
        }
        
        boolean mustCheckNewFood = false;
        if(co){
            Food food = new Food(color, forma, tamano, peso, tipo);
            actualCell.food = food;
            if(isRegisteredGoodFood(food)){
                goodFoodCells.add(actualCell);
            }
            else if(isRegisterdBadFood(food)){
                badFoodCells.add(actualCell);
            }
            else{
                mustCheckNewFood = true;
            }
        }
        
        //////////////////////
        
        
        if(cmd.size()>0){
            String command = cmd.get(0);
            if(command.equals(SET_COST_PER_MOVE)
                    || command.equals(SET_COST_PER_TURN)
                    || command.equals(SET_MAX_ENERGY)
                    || command.equals(CHECK_FOOD)
                    || command.equals(END_CRUISE_MODE)){
                cmd.remove(0);
                return command;
            }
        }
        
        if(mustCheckNewFood){
            return START_CHECK_FOOD;
        }
        
        if(!isCostPerTurnSet){
            isCostPerTurnSet = true;
            return START_SET_COST_PER_TURN;
        }
        
        if(!isCostPerMoveSet){
            isCostPerMoveSet = true;
            return START_SET_COST_PER_MOVE;
        }
        
        if(!isMaxEnergySet && isRegisteredGoodFood(actualCell.food)){
            isMaxEnergySet = true;
            return START_SET_MAX_ENERGY;
        }
        
        //De estar en una comida buena llenarse.
        
        if(isRegisteredGoodFood(actualCell.food) && energyLevel<maxEnergyLevel){
            return TeseoEaterActions.EAT_STRING;
        }
        
        //@TODO Terminar el proceso de decisión
        ///////////////////////////////////////
        
        LinkedList<Cell> closestPathToSurvive = closestPathToSurvive();
        
        if(explorationModeEnabled){//Explorando, busca cosas nuevas
            if(closestPathToSurvive!=null){
                explorationModeEnabled = false;
            }
            else{//Primero va al lugar menos visitado que no esté bloqueado
                //y desde allí explora haciendo el recorrido de Teseo
                
                Cell lessVisitedNotBlockedReachableCell = actualCell;
                
                for(String s : cellsByCoordinates.keySet()){
                    Cell cell = cellsByCoordinates.get(s);
                    if(cell.timesVisited<lessVisitedNotBlockedReachableCell.timesVisited
                            && !cell.blockedRoad && isReachable(cell)){
                        lessVisitedNotBlockedReachableCell = cell;
                    }
                }
                
                if(lessVisitedNotBlockedReachableCell == actualCell){//Teseo
                    int lessVisitedCount = Integer.MAX_VALUE;
                    Cell teseoDestiny = null;
                    if(getRightCell()!=null && getRightCell()!=Cell.WALL
                            && !getRightCell().blockedRoad && 
                            getRightCell().timesVisited<lessVisitedCount){
                        lessVisitedCount = getRightCell().timesVisited;
                        teseoDestiny = getRightCell();
                    }
                    if(getFrontCell()!=null && getFrontCell()!=Cell.WALL
                            && !getFrontCell().blockedRoad && 
                            getFrontCell().timesVisited<lessVisitedCount){
                        lessVisitedCount = getFrontCell().timesVisited;
                        teseoDestiny = getFrontCell();
                    }
                    if(getLeftCell()!=null && getLeftCell()!=Cell.WALL
                            && !getLeftCell().blockedRoad && 
                            getLeftCell().timesVisited<lessVisitedCount){
                        lessVisitedCount = getLeftCell().timesVisited;
                        teseoDestiny = getLeftCell();
                    }
                    if(getBackCell()!=null && getBackCell()!=Cell.WALL
                            && !getBackCell().blockedRoad && 
                            getBackCell().timesVisited<lessVisitedCount){
                        lessVisitedCount = getRightCell().timesVisited;
                        teseoDestiny = getBackCell();
                    }
                    
                    if(teseoDestiny==null){//There is no way around avoiding blocked cells
                        
                        if(getRightCell()!=null && getRightCell()!=Cell.WALL && 
                            getRightCell().timesVisited<lessVisitedCount){
                            lessVisitedCount = getRightCell().timesVisited;
                            teseoDestiny = getRightCell();
                        }
                        if(getFrontCell()!=null && getFrontCell()!=Cell.WALL && 
                                getFrontCell().timesVisited<lessVisitedCount){
                            lessVisitedCount = getFrontCell().timesVisited;
                            teseoDestiny = getFrontCell();
                        }
                        if(getLeftCell()!=null && getLeftCell()!=Cell.WALL && 
                                getLeftCell().timesVisited<lessVisitedCount){
                            lessVisitedCount = getLeftCell().timesVisited;
                            teseoDestiny = getLeftCell();
                        }
                        if(getBackCell()!=null && getBackCell()!=Cell.WALL && 
                                getBackCell().timesVisited<lessVisitedCount){
                            lessVisitedCount = getRightCell().timesVisited;
                            teseoDestiny = getBackCell();
                        }

                    }
                    
                    if(teseoDestiny==getFrontCell()){
                        return TeseoEaterActions.GO_FRONT_STRING;
                    }
                    if(teseoDestiny==getRightCell()){
                        return TeseoEaterActions.GO_RIGHT_STRING;
                    }
                    if(teseoDestiny==getLeftCell()){
                        return TeseoEaterActions.GO_LEFT_STRING;
                    }
                    if(teseoDestiny==getBackCell()){
                        return TeseoEaterActions.GO_BACK_STRING;
                    }
                        
                }
                else{//Cruise to lessVisitedNonBlockedReachableCell
                    
                    cmd.add(END_CRUISE_MODE);
                    
                    LinkedList<Cell> pathToLessVisitedCell =
                            bestPathFromCellToCell(actualCell,
                            lessVisitedNotBlockedReachableCell, direction);
                    
                    LinkedList<String> commandsToWalkPathToLessVisited =
                            commandsToWalkPath(pathToLessVisitedCell, direction);
                    
                    for(int i=commandsToWalkPathToLessVisited.size()-1;
                            i>=0;i--){
                        cmd.add(commandsToWalkPathToLessVisited.get(i));
                    }
                    
                    return START_CRUISE_MODE;
                    
                }
                
            }
        }
        
        //closestPathToSurvive
        if(!explorationModeEnabled){//Está sin explorar y se mueve a donde conoce
            if(energyLevel==maxEnergyLevel){
                explorationModeEnabled = true;
                return TeseoEaterActions.DO_NOTHING_STRING;
            }
            else{
                
                LinkedList<String> commandsOfClosestPathToSurvive =
                        commandsToWalkPath(closestPathToSurvive, direction);
                
                cmd.add(END_CRUISE_MODE);
                
                for(int i=commandsOfClosestPathToSurvive.size()-1;
                        i>=0;i--){
                    cmd.add(commandsOfClosestPathToSurvive.get(i));
                }
                
                return START_CRUISE_MODE;
            }
        }
        
        
        return TeseoEaterActions.DIE_STRING;
    }
    
    public boolean isReachable(Cell cell){
        LinkedList<Cell> bestPath = bestPathFromCellToCell(actualCell, cell, direction);
        return abs(energyDeltaGoingOverCellPath(bestPath, direction))<=energyLevel;
    }
    
    //If has to survive returns the closest path that it can use, else returns
    //null and the agent keeps moving
    private LinkedList<Cell> closestPathToSurvive(){
        if(goodFoodCells.size()>0){
            int closestPathCost = Integer.MAX_VALUE;
            int tempPathCost;
            LinkedList<Cell> closestPath = null;
            LinkedList<Cell> tempPath = null;
            for(Cell cell : goodFoodCells){
                tempPath = bestPathFromCellToCell(actualCell, cell, direction);
                tempPathCost = abs(energyDeltaGoingOverCellPath(tempPath, direction));
                if(tempPathCost < closestPathCost){
                    closestPath = tempPath;
                    closestPathCost = tempPathCost;
                }
            }
            if(closestPathCost <= energyLevel &&
                    closestPathCost>energyLevel-5*abs(energyDeltaPerMove)){
                return closestPath;//Alcanza a llegar a algo de comida y es recomendable que lo haga
            }
            else{
                return null;//No llega a nada
            }
        }
        else{
            return null;//Can't survive, must keep looking
        }
    }
    
    private LinkedList<Cell> bestPathFromCellToCell(Cell source, Cell destiny,
            int initialDirection){
        LinkedList<Cell> path = new LinkedList<Cell>();
        
        if(destiny.equals(source)){
            path.add(destiny);
            return path;
        }
        
        HashMap<Cell, Pair> dijkstra = dijkstra(source, initialDirection);
        
        path.add(destiny);
        
        Cell parent = dijkstra.get(destiny).cell;
        
        while(!parent.equals(source)){
            path.add(0, parent);
            parent = dijkstra.get(parent).cell;
        }
        
        path.add(0, parent); //Lo mismo que path.add(0, source);
        
        return path;
    }
    
    /*
     * returns a hashmap containing a pair per cell: parent to get to that cell
     * and cost to get to that cell
     * from de source.
     */
    private HashMap<Cell, Pair> dijkstra(Cell source, int initialDirection){
        HashMap<Cell, Pair> parentsAndDistance = new HashMap<Cell, Pair>();
        
        parentsAndDistance.put(source, new Pair(null, 0));
        
        PriorityQueue<Pair> q = new PriorityQueue<Pair>();
        
        //En la cola van pares: Celda y la distancia para llegar a ella
        q.add(new Pair(source, 0));
        
        while(!q.isEmpty()){
            
            Pair tempPair = q.poll();
            
            if(tempPair.cell!=null && 
                    parentsAndDistance.get(tempPair.cell).integer == tempPair.integer){
            
                Cell temp = tempPair.cell;
                
                int distanceTempHas = parentsAndDistance.get(temp).integer;

                Cell nextCell;
                int startingDirection;
                int distanceAdyHas;
                int costGoingToAdy;
                int distanceByNewPath;
                
                //North

                nextCell = temp.northCell;
                
                distanceAdyHas = (parentsAndDistance.get(nextCell)!=null? 
                        parentsAndDistance.get(nextCell).integer: Integer.MAX_VALUE);


                if(temp.equals(source)){
                    startingDirection = initialDirection;
                }
                else{
                    startingDirection =
                            finalDirection(parentsAndDistance.get(temp).cell, temp);
                }

                costGoingToAdy = abs(energyDeltaGoingFromCellToAdyacent
                        (temp, nextCell, startingDirection));

                distanceByNewPath = distanceTempHas + costGoingToAdy;
                
                if(distanceByNewPath < distanceAdyHas){
                    q.add(new Pair(nextCell, distanceByNewPath));
                    parentsAndDistance.put(nextCell, new Pair(temp, distanceByNewPath));
                }

                //West

                nextCell = temp.westCell;
                
                distanceAdyHas = (parentsAndDistance.get(nextCell)!=null? 
                        parentsAndDistance.get(nextCell).integer: Integer.MAX_VALUE);


                if(temp.equals(source)){
                    startingDirection = initialDirection;
                }
                else{
                    startingDirection =
                            finalDirection(parentsAndDistance.get(temp).cell, temp);
                }

                costGoingToAdy = abs(energyDeltaGoingFromCellToAdyacent
                        (temp, nextCell, startingDirection));

                distanceByNewPath = distanceTempHas + costGoingToAdy;
                
                if(distanceByNewPath < distanceAdyHas){
                    q.add(new Pair(nextCell, distanceByNewPath));
                    parentsAndDistance.put(nextCell, new Pair(temp, distanceByNewPath));
                }
                
                //South

                nextCell = temp.southCell;
                
                distanceAdyHas = (parentsAndDistance.get(nextCell)!=null? 
                        parentsAndDistance.get(nextCell).integer: Integer.MAX_VALUE);


                if(temp.equals(source)){
                    startingDirection = initialDirection;
                }
                else{
                    startingDirection =
                            finalDirection(parentsAndDistance.get(temp).cell, temp);
                }

                costGoingToAdy = abs(energyDeltaGoingFromCellToAdyacent
                        (temp, nextCell, startingDirection));

                distanceByNewPath = distanceTempHas + costGoingToAdy;
                
                if(distanceByNewPath < distanceAdyHas){
                    q.add(new Pair(nextCell, distanceByNewPath));
                    parentsAndDistance.put(nextCell, new Pair(temp, distanceByNewPath));
                }
                
                //East
                
                nextCell = temp.eastCell;
                
                distanceAdyHas = (parentsAndDistance.get(nextCell)!=null? 
                        parentsAndDistance.get(nextCell).integer: Integer.MAX_VALUE);


                if(temp.equals(source)){
                    startingDirection = initialDirection;
                }
                else{
                    startingDirection =
                            finalDirection(parentsAndDistance.get(temp).cell, temp);
                }

                costGoingToAdy = abs(energyDeltaGoingFromCellToAdyacent
                        (temp, nextCell, startingDirection));

                distanceByNewPath = distanceTempHas + costGoingToAdy;
                
                if(distanceByNewPath < distanceAdyHas){
                    q.add(new Pair(nextCell, distanceByNewPath));
                    parentsAndDistance.put(nextCell, new Pair(temp, distanceByNewPath));
                }

                //////////////////////////////////////////////////////////////////
            
            }
            
        }
        
        return parentsAndDistance;
    }
    
    private LinkedList<String> commandsToWalkPath(LinkedList<Cell> path, int initialDirection) {
        LinkedList<String> commandsToWalk = new LinkedList<String>();
        if(path.size()>=2){
            Cell temp = path.get(0);
            int startingDirection;
            for(int i=1;i<path.size();i++){
                if(i==1){
                    startingDirection = initialDirection;
                }
                else{
                    startingDirection = finalDirection(path.get(i-2), path.get(i-1));
                }
                LinkedList<String> tempCommands =
                        commandsToGoToAdyacentCell(temp, path.get(i), startingDirection);
                for(String s:tempCommands){
                    commandsToWalk.add(s);
                }
            }
        }
        return commandsToWalk;
    }
    
    private int energyDeltaGoingOverCellPath(LinkedList<Cell> path, int initialDirection){
        int energyDelta = 0;
        if(path.size()>=2){
            Cell temp = path.get(0);
            int startingDirection;
            for(int i=1;i<path.size();i++){
                if(i==1){
                    startingDirection = initialDirection;
                }
                else{
                    startingDirection = finalDirection(path.get(i-2), path.get(i-1));
                }
                energyDelta += energyDeltaGoingFromCellToAdyacent(temp, path.get(i), startingDirection);
                temp = path.get(i);
            }
        }
        return energyDelta;
    }
    
    //The direction where the robot will be pointing after moving there
    private int finalDirection(Cell start, Cell finish){
        if(finish.equals(start.southCell))
            return SOUTH;
        else if(finish.equals(start.northCell))
            return NORTH;
        else if(finish.equals(start.eastCell))
            return EAST;
        else
            return WEST;
    }
    
    //In here, direction is the starting direction of the robot
    private int energyDeltaGoingFromCellToAdyacent(Cell source, Cell destiny, int direction){
    
        LinkedList<String> commands = commandsToGoToAdyacentCell(source, destiny, direction);
        
        int energyDelta = 0;
        
        for(String s : commands)
            if(s.equals(advanceLanguage))
                energyDelta += energyDeltaPerMove;
            else if(s.equals(rotateLanguage))
                energyDelta += energyDeltaPerTurn;
        
        return energyDelta;
        
    }
    
    //In here, direction is the starting direction of the robot
    private LinkedList<String> commandsToGoToAdyacentCell(Cell source, Cell destiny, int direction){
        LinkedList<String> commands = new LinkedList<String>();
        
        if(direction==NORTH){
            if(source.northCell == destiny){
                commands.add(advanceLanguage);
            }
            else if(source.eastCell == destiny){
                commands.add(rotateLanguage);
                commands.add(advanceLanguage);
            }
            else if(source.southCell == destiny){
                commands.add(rotateLanguage);
                commands.add(rotateLanguage);
                commands.add(advanceLanguage);
            }
            else if(source.westCell == destiny){
                commands.add(rotateLanguage);
                commands.add(rotateLanguage);
                commands.add(rotateLanguage);
                commands.add(advanceLanguage);
            }
        }
        else if(direction==EAST){
            if(source.eastCell == destiny){
                commands.add(advanceLanguage);
            }
            else if(source.southCell == destiny){
                commands.add(rotateLanguage);
                commands.add(advanceLanguage);
            }
            else if(source.westCell == destiny){
                commands.add(rotateLanguage);
                commands.add(rotateLanguage);
                commands.add(advanceLanguage);
            }
            else if(source.northCell == destiny){
                commands.add(rotateLanguage);
                commands.add(rotateLanguage);
                commands.add(rotateLanguage);
                commands.add(advanceLanguage);
            }
        }
        else if(direction==SOUTH){
            if(source.southCell == destiny){
                commands.add(advanceLanguage);
            }
            else if(source.westCell == destiny){
                commands.add(rotateLanguage);
                commands.add(advanceLanguage);
            }
            else if(source.northCell == destiny){
                commands.add(rotateLanguage);
                commands.add(rotateLanguage);
                commands.add(advanceLanguage);
            }
            else if(source.eastCell == destiny){
                commands.add(rotateLanguage);
                commands.add(rotateLanguage);
                commands.add(rotateLanguage);
                commands.add(advanceLanguage);
            }
        }
        else if(direction==WEST){
            if(source.westCell == destiny){
                commands.add(advanceLanguage);
            }
            else if(source.northCell == destiny){
                commands.add(rotateLanguage);
                commands.add(advanceLanguage);
            }
            else if(source.eastCell == destiny){
                commands.add(rotateLanguage);
                commands.add(rotateLanguage);
                commands.add(advanceLanguage);
            }
            else if(source.southCell == destiny){
                commands.add(rotateLanguage);
                commands.add(rotateLanguage);
                commands.add(rotateLanguage);
                commands.add(advanceLanguage);
            }
        }
        
        
        return commands;
    }
    
    public int abs(int value){
        if(value<0)
            return -value;
        return value;
    }
    
    public boolean isRegisteredGoodFood(Food food){
        if(food == null)
            return false;
        for(Food f : goodFoodType)
            if(f.equals(food))
                return true;
        return false;
    }
    
    public boolean isRegisterdBadFood(Food food){
        if(food == null)
            return false;
        for(Food f : badFoodType)
            if(f.equals(food))
                return true;
        return false;
    }
    
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

    
    public void setFrontCell(Cell cell){
        if(direction == NORTH){
            actualCell.northCell = cell;
        }
        else if(direction == EAST){
            actualCell.eastCell = cell;
        }
        else if(direction == SOUTH){
            actualCell.southCell = cell;
        }
        else if(direction == WEST){
            actualCell.westCell = cell;
        }
    }
    
    public void setRightCell(Cell cell){
        if(direction == NORTH){
            actualCell.eastCell = cell;
        }
        else if(direction == EAST){
            actualCell.southCell = cell;
        }
        else if(direction == SOUTH){
            actualCell.westCell = cell;
        }
        else if(direction == WEST){
            actualCell.northCell = cell;
        }
    }
    
    public void setBackCell(Cell cell){
        if(direction == NORTH){
            actualCell.southCell = cell;
        }
        else if(direction == EAST){
            actualCell.westCell = cell;
        }
        else if(direction == SOUTH){
            actualCell.northCell = cell;
        }
        else if(direction == WEST){
            actualCell.eastCell = cell;
        }
    }
    
    public void setLeftCell(Cell cell){
        if(direction == NORTH){
            actualCell.westCell = cell;
        }
        else if(direction == EAST){
            actualCell.northCell = cell;
        }
        else if(direction == SOUTH){
            actualCell.eastCell = cell;
        }
        else if(direction == WEST){
            actualCell.southCell = cell;
        }
    }
    
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
    
    private class Pair implements Comparable{
        public Cell cell;
        public int integer;
        public Pair(Cell cell, int integer){
            this.cell = cell;
            this.integer = integer;
        }

        public int compareTo(Object obj) {
            
            Pair other = (Pair) obj;
            
            if(integer == other.integer)
                return 0;
            else if(integer < other.integer)
                return -1;
            else
                return 1;
        }
    }
}