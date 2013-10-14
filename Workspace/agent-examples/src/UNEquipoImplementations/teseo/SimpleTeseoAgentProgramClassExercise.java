/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseo;

import unalcol.agents.examples.labyrinth.teseo.simple.SimpleTeseoAgentProgram;

/**
 *
 * @author Fredy
 */
public class SimpleTeseoAgentProgramClassExercise extends SimpleTeseoAgentProgram {

    public static final int GO_AHEAD = 0;
    public static final int GO_RIGHT = 1;
    public static final int GO_BACK = 2;
    public static final int GO_LEFT = 3;
    public static final int DIE = 4;
    private LabyrinthPath path = new LabyrinthPath();

    @Override
    public int accion(boolean PF, boolean PD, boolean PA, boolean PI, boolean MT) {
        
        if(MT)
            return DIE;

        path.saveData(PF, PD, PA, PI, MT);
        
        int order = analyzePathToMakeOrder();
        
        path.receiveOrder(order);
        
        return order;
    }

    private int analyzePathToMakeOrder(){
        
        if(path.unableToMove()){
            return DIE;
        }
        
        int ahead = path.getCountAhead();
        int right = path.getCountRight();
        int left = path.getCountLeft();
        int back = path.getCountBack();
        
        int min = Math.min(ahead, right);
        min = Math.min(min, left);
        min = Math.min(min, back);
        
        if(min == Integer.MAX_VALUE)
            return DIE;
        else if(min == right)
            return GO_RIGHT;
        else if(min == ahead)
            return GO_AHEAD;
        else if(min == left)
            return GO_LEFT;
        else if(min == back){
            path.markGoingBack();
            return GO_BACK;
        }
        else
            return DIE;
    }
    
    
    
    private class LabyrinthPath {

        public static final int EMPTY = 0;
        public static final int WALL = -1;
        public static final int EXIT = -2;
        public static final int LENGTH = 100;
        public static final int NORTH = 0;
        public static final int SOUTH = 1;
        public static final int EAST = 2;
        public static final int WEST = 3;
        private int x;
        private int y;
        private int mat[][];
        private int dir;

        public LabyrinthPath() {
            mat = new int[LENGTH][LENGTH];
            x = LENGTH / 2;
            y = LENGTH / 2;
            dir = EAST;
        }
        
        public void markGoingBack(){
            if(dir==NORTH){
                mat[x+2][y] = WALL;
            }
            else if(dir == EAST){
                mat[x][y-1] = WALL;
            }
            else if(dir == WEST){
                mat[x][y+1] = WALL;
            }
            else if(dir == SOUTH){
                mat[x-1][y] = WALL;
            }
        }
        
        public int getCountAhead(){
        
            if(dir==NORTH && mat[x-1][y]!= WALL){
                return mat[x-2][y];
            }
            if(dir==SOUTH && mat[x+1][y]!= WALL){
                return mat[x+2][y];
            }
            if(dir==EAST && mat[x][y-1]!= WALL){
                return mat[x][y-2];
            }
            if(dir==WEST && mat[x][y+1]!= WALL){
                return mat[x][y+2];
            }
            
            return Integer.MAX_VALUE;
            
        }
        
        public int getCountBack(){
        
            if(dir==NORTH && mat[x+1][y]!= WALL){
                return mat[x+2][y];
            }
            if(dir==SOUTH && mat[x-1][y]!= WALL){
                return mat[x-2][y];
            }
            if(dir==EAST && mat[x][y+1]!= WALL){
                return mat[x][y+2];
            }
            if(dir==WEST && mat[x][y-1]!= WALL){
                return mat[x][y-2];
            }
            
            return Integer.MAX_VALUE;
            
        }
        
        public int getCountLeft(){
        
            if(dir==EAST && mat[x-1][y]!= WALL){
                return mat[x-2][y];
            }
            if(dir==WEST && mat[x+1][y]!= WALL){
                return mat[x+2][y];
            }
            if(dir==SOUTH && mat[x][y-1]!= WALL){
                return mat[x][y-2];
            }
            if(dir==NORTH && mat[x][y+1]!= WALL){
                return mat[x][y+2];
            }
            
            return Integer.MAX_VALUE;
            
        }
        
        public int getCountRight(){
        
            if(dir==WEST && mat[x-1][y]!= WALL){
                return mat[x-2][y];
            }
            if(dir==EAST && mat[x+1][y]!= WALL){
                return mat[x+2][y];
            }
            if(dir==NORTH && mat[x][y-1]!= WALL){
                return mat[x][y-2];
            }
            if(dir==SOUTH && mat[x][y+1]!= WALL){
                return mat[x][y+2];
            }
            
            return Integer.MAX_VALUE;
            
        }
        
        private boolean unableToMove(){
        
            boolean ret = mat[x+1][y] == WALL;
            
            ret = ret && mat[x-1][y] == WALL;
            ret = ret && mat[x][y+1] == WALL;
            ret = ret && mat[x][y-1] == WALL;
        
            return ret;
        }
        
        private void saveData(boolean PF, boolean PD, boolean PA, boolean PI, boolean MT) {

            if (dir == NORTH) {
                if (PF) {
                    mat[x - 1][y] = WALL;
                } else {
                    mat[x - 1][y] = EMPTY;
                }
                if (PD) {
                    mat[x][y - 1] = WALL;
                } else {
                    mat[x][y - 1] = EMPTY;
                }
                if (PI) {
                    mat[x][y + 1] = WALL;
                } else {
                    mat[x][y + 1] = EMPTY;
                }
                if (PA) {
                    mat[x + 1][y] = WALL;
                } else {
                    mat[x + 1][y] = EMPTY;
                }
            } else if (dir == SOUTH) {
                if (PF) {
                    mat[x + 1][y] = WALL;
                } else {
                    mat[x + 1][y] = EMPTY;
                }
                if (PD) {
                    mat[x][y + 1] = WALL;
                } else {
                    mat[x][y + 1] = EMPTY;
                }
                if (PI) {
                    mat[x][y - 1] = WALL;
                } else {
                    mat[x][y - 1] = EMPTY;
                }
                if (PA) {
                    mat[x - 1][y] = WALL;
                } else {
                    mat[x - 1][y] = EMPTY;
                }
            } else if (dir == EAST) {
                if (PF) {
                    mat[x][y - 1] = WALL;
                } else {
                    mat[x][y - 1] = EMPTY;
                }
                if (PD) {
                    mat[x + 1][y] = WALL;
                } else {
                    mat[x + 1][y] = EMPTY;
                }
                if (PI) {
                    mat[x - 1][y] = WALL;
                } else {
                    mat[x - 1][y] = EMPTY;
                }
                if (PA) {
                    mat[x][y + 1] = WALL;
                } else {
                    mat[x][y + 1] = EMPTY;
                }
            } else if (dir == WEST) {
                if (PF) {
                    mat[x][y + 1] = WALL;
                } else {
                    mat[x][y + 1] = EMPTY;
                }
                if (PD) {
                    mat[x - 1][y] = WALL;
                } else {
                    mat[x - 1][y] = EMPTY;
                }
                if (PI) {
                    mat[x + 1][y] = WALL;
                } else {
                    mat[x + 1][y] = EMPTY;
                }
                if (PA) {
                    mat[x][y - 1] = WALL;
                } else {
                    mat[x][y - 1] = EMPTY;
                }
            }
            
            if(MT){
                mat[x][y] = EXIT;
            }

        }

        private void receiveOrder(int order) {

            if (dir == NORTH) {
                switch (order) {
                    case GO_AHEAD:
                        x = x - 2;
                        break;
                    case GO_BACK:
                        x = x + 2;
                        dir = SOUTH;
                        break;
                    case GO_RIGHT:
                        y = y - 2;
                        dir = EAST;
                        break;
                    case GO_LEFT:
                        y = y + 2;
                        dir = WEST;
                        break;
                }
            } else if (dir == SOUTH) {
                switch (order) {
                    case GO_AHEAD:
                        x = x + 2;
                        break;
                    case GO_BACK:
                        x = x - 2;
                        dir = NORTH;
                        break;
                    case GO_RIGHT:
                        y = y + 2;
                        dir = WEST;
                        break;
                    case GO_LEFT:
                        y = y - 2;
                        dir = EAST;
                        break;
                }
            } else if (dir == EAST) {
                switch (order) {
                    case GO_AHEAD:
                        y = y - 2;
                        break;
                    case GO_BACK:
                        y = y + 2;
                        dir =WEST;
                        break;
                    case GO_RIGHT:
                        x = x + 2;
                        dir = SOUTH;
                        break;
                    case GO_LEFT:
                        x = x - 2;
                        dir = NORTH;
                        break;
                }
            } else if (dir == WEST) {
                switch (order) {
                    case GO_AHEAD:
                        y = y + 2;
                        break;
                    case GO_BACK:
                        y = y - 2;
                        dir =EAST;
                        break;
                    case GO_RIGHT:
                        x = x - 2;
                        dir = NORTH;
                        break;
                    case GO_LEFT:
                        x = x + 2;
                        dir = SOUTH;
                        break;
                }
            }
            mat[x][y]++;
        }
    }
}
