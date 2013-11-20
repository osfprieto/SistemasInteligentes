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
        
        double sum = 0;
        int limit = 5;
        
        for(int i=0;i<limit;i++){
            
            UNEquipoPlayer player = new UNEquipoPlayer(4, 10);
            
            System.out.print(i+"\t");
            
            while(!player.hasBeenBeaten()){
                String guess = player.getGuess();
                int bulls = player.getBulls(guess);
                int cows = player.getCows(guess);
                player.registerBullsAndCows(guess, bulls, cows);
            }
            
            sum += player.getGuessesDone();
            
            System.out.println(player.getGuessesDone());
        }
        System.out.println(sum/limit);
    }
    
}
