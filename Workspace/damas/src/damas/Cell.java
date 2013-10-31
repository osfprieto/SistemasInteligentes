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
public class Cell extends JPanel{
    
    public static final ImageIcon QUEEN_IMAGE
            = new ImageIcon("queenimage.png");
    
    private Color color;
    private boolean hasQueen;
    
    public Cell(Color color){
        this.color = color;
        setBackground(color);
    }
    
    public void setHasQueen(boolean option){
        removeAll();
        hasQueen = option;
        if(option){
            add(new JLabel(QUEEN_IMAGE));
        }
        updateUI();
    }
    
}
