/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author osfprieto
 */
public class Test extends JFrame{
    
    static int n = 8;
    
    public static void main(String[] args){
        Test test = new Test();
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public Test(){
        getContentPane().removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(n, n));
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                panel.add(new JLabel(new ImageIcon("queenimage.png")));
            }
        }
        
        getContentPane().add(panel);
        setVisible(true);
        setBounds(100, 100, 400, 400);
    }
    
}
