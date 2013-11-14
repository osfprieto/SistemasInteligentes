/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Estudiante
 */
public class Cell extends JLabel{
    
    public static final ImageIcon QUEEN_IMAGE
            = new ImageIcon("royal_crown.png");
    public static final ImageIcon BLANK_IMAGE
            = new ImageIcon("blank.png");
    
    private Color color;
    private boolean hasQueen;
    private int i;
    private int j;
    
    public Cell(Color color, int i, int j){
        this.color = color;
        this.i = i;
        this.j = j;
        setOpaque(true);
        setBackground(color);
    }
    
    public void setHasQueen(boolean option){
        removeAll();
        hasQueen = option;
        if(option){
            setIcon(QUEEN_IMAGE);
        }
        else{
            setIcon(BLANK_IMAGE);
        }
        updateUI();
    }
 
    public boolean hasQueen(){
        return hasQueen;
    }
    
    public int getI(){
        return i;
    }
    
    public int getJ(){
        return j;
    }
    
    public String toString(){
        return "("+i+", "+j+")";
    }
    
}
