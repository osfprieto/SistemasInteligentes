/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

/**
 *
 * @author Fredy
 */
public class Comida {

        public boolean color;
        public boolean forma;
        public boolean tamaño;
        public boolean peso;
        public boolean tipo;

        public Comida(boolean color, boolean forma, boolean tamaño, boolean peso, boolean tipo) {

            this.color = color;
            this.forma = forma;
            this.tamaño = tamaño;
            this.peso = peso;
            this.tipo = tipo;
        }

        public boolean equals(Comida otro) {
            if (this.color == otro.color
                    && this.forma == otro.forma
                    && this.peso == otro.peso
                    && this.tamaño == otro.tamaño
                    && this.tipo == otro.tipo)
                return true;
            return false;
        }
    }