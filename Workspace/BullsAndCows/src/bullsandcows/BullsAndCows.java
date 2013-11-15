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
public class BullsAndCows {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Bulls and Cows");
        System.out.println("==============");
        System.out.println();
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Answer length:");
        int answerSize = sc.nextInt();
        
        System.out.println("Digits:");
        int digits = sc.nextInt();

        UNEquipoPlayer player = new UNEquipoPlayer(answerSize, digits);

        while(player.hasToAsk()){
            String playerGuess = player.getGuess();
            try{
                
                System.out.println("The guess is "+playerGuess);
                
                System.out.println("How many bulls?");
                int bulls = sc.nextInt();
                
                System.out.println("How many cows?");
                int cows = sc.nextInt();
                
                player.registerBullsAndCows(playerGuess, bulls, cows);
            }catch(Exception e){
                System.out.println("Fail. Try again");
            }
        }

        if(player.hasBeenCheated())
            System.out.println("You're cheating!");
        else
            System.out.println("The secret number is "+player.getGuess()+
                    ". Guesses done: "+player.getGuessesDone());
    }
}
