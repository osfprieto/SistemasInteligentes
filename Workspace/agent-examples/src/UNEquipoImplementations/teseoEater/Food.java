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
            if (this.color == otro.color
                    && this.shape == otro.shape
                    && this.weight == otro.weight
                    && this.size == otro.size
                    && this.type == otro.type)
                return true;
            return false;
        }
        
        @Override
        public String toString(){
            return "["+color +","+shape+","+size+","+weight+","+type+"]";
        }
    }