/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens.GUI;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Estudiante
 */
public class Cell extends JLabel {

    private Color color;
    private boolean hasQueen;
    private int i;
    private int j;

    public Cell(Color color, int i, int j) {
        this.color = color;
        this.i = i;
        this.j = j;
        setOpaque(true);
        setBackground(color);
    }

    public void setHasQueen(boolean option) {

        removeAll();
        hasQueen = option;
        int dimension = 2*Math.max(this.getWidth(), this.getHeight());
        if (option) {
            setIcon(getImageIcon(0, dimension, dimension));
        } else {
            setIcon(getImageIcon(1, dimension, dimension));
        }
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        updateUI();
    }

    public ImageIcon getImageIcon(int option, int width, int heigh) {

        Image img;
        if (option == 0) {
            ImageIcon queen = new ImageIcon("royal_crown.png");
            img = queen.getImage();
        } else {
            ImageIcon blank = new ImageIcon("blank.png");
            img = blank.getImage();
        }
        Image newimg = img.getScaledInstance(width, heigh, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    public boolean hasQueen() {
        return hasQueen;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public String toString() {
        return "(" + i + ", " + j + ")";
    }
}
