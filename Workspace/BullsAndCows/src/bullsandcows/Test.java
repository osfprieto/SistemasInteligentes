/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows;

import java.util.Scanner;

/**
 *
 * @author osfprieto
 */
public class Test {
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        UNEquipoPlayer player = new UNEquipoPlayer(4, 10);
        while(true){
            String input = sc.next();
            System.out.println(player.getBulls(input));
            System.out.println(player.getCows(input));
        }
    }
    
}
