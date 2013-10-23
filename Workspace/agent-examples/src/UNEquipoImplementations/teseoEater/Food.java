/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

/**
 *
 * @author Fredy
 */
public class Food {

        public boolean color;
        public boolean shape;
        public boolean size;
        public boolean weight;
        public boolean type;

        public Food(boolean color, boolean forma, boolean tamano, boolean peso, boolean tipo) {

            this.color = color;
            this.shape = forma;
            this.size = tamano;
            this.weight = peso;
            this.type = tipo;
        }

        public boolean equals(Food otro) {
            if(otro==null)
                return false;
            if (color == otro.color
                    && shape == otro.shape
                    && weight == otro.weight
                    && size == otro.size
                    && type == otro.type)
                return true;
            return false;
        }
        
        @Override
        public String toString(){
            return "["+color +","+shape+","+size+","+weight+","+type+"]";
        }
    }