
import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author osfprieto
 */
public class Cell extends JLabel{
    
    private int i;
    private int j;
    private String path;
    
    public Cell(int i, int j){
        this.i = i;
        this.j = j;
        path = "";
        setOpaque(true);
    }
    
    public int getI(){
        return i;
    }
    
    public int getJ(){
        return j;
    }
    
    public void setPath(String path){
        this.path = path;
    }
    
    public String getPath(){
        return path;
    }
    
}
