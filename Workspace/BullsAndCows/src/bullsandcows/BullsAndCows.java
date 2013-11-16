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
        justGuess();
        justBeGuessed();
        playAgainstHumanUser();
        matchTwoVirtualPlayers();
    }
    
    private static void justGuess(){
        System.out.println();
        System.out.println("Bulls and Cows");
        System.out.println("==============");
        System.out.println("Just guessing");
        System.out.println();
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Answer length:");
        int answerSize = sc.nextInt();
        
        System.out.println("Digits:");
        int digits = sc.nextInt();

        UNEquipoPlayer player = new UNEquipoPlayer(answerSize, digits);

        while(player.hasToAsk() && !player.hasBeenBeaten()){
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
        else if(player.hasBeenBeaten())
            System.out.println("You've discovered the number!");
        else
            System.out.println("The secret number is "+player.getGuess()+
                    ". Guesses done: "+player.getGuessesDone());
    }
    
    private static void justBeGuessed(){
        System.out.println();
        System.out.println("Bulls and Cows");
        System.out.println("==============");
        System.out.println("Find my number");
        System.out.println();
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Answer length:");
        int answerSize = sc.nextInt();
        
        System.out.println("Digits:");
        int digits = sc.nextInt();

        UNEquipoPlayer player = new UNEquipoPlayer(answerSize, digits);

        while(!player.hasBeenBeaten()){
            
            try{
                
                System.out.println("What's your guess?");
                String userGuess = sc.next();
                
                System.out.println("Bulls: "+player.getBulls(userGuess));
                System.out.println("Cows: "+player.getCows(userGuess));
                
            }catch(Exception e){
                System.out.println("Fail. Try again");
            }
        }

        if(player.hasBeenCheated())
            System.out.println("You're cheating!");
        else if(player.hasBeenBeaten())
            System.out.println("You've discovered the number!");
        else
            System.out.println("The secret number is "+player.getGuess()+
                    ". Guesses done: "+player.getGuessesDone());
    }
    
    private static void playAgainstHumanUser(){
        System.out.println();
        System.out.println("Bulls and Cows");
        System.out.println("==============");
        System.out.println("Let's play both");
        System.out.println();
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Answer length:");
        int answerSize = sc.nextInt();
        
        System.out.println("Digits:");
        int digits = sc.nextInt();

        UNEquipoPlayer player = new UNEquipoPlayer(answerSize, digits);

        while(player.hasToAsk() && !player.hasBeenBeaten()){
            String playerGuess = player.getGuess();
            try{
                
                System.out.println("The guess is "+playerGuess);
                
                System.out.println("How many bulls?");
                int bulls = sc.nextInt();
                
                System.out.println("How many cows?");
                int cows = sc.nextInt();
                
                player.registerBullsAndCows(playerGuess, bulls, cows);
                
                System.out.println("What's your guess?");
                String userGuess = sc.next();
                
                System.out.println("Bulls: "+player.getBulls(userGuess));
                System.out.println("Cows: "+player.getCows(userGuess));
                
            }catch(Exception e){
                System.out.println("Fail. Try again");
            }
        }

        if(player.hasBeenCheated())
            System.out.println("You're cheating!");
        else if(player.hasBeenBeaten())
            System.out.println("You've discovered the number!");
        else
            System.out.println("The secret number is "+player.getGuess()+
                    ". Guesses done: "+player.getGuessesDone());
    }
    
    private static void matchTwoVirtualPlayers(){
        System.out.println();
        System.out.println("Bulls and Cows");
        System.out.println("==============");
        System.out.println("Two virtual players");
        System.out.println();
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Answer length:");
        int answerSize = sc.nextInt();
        
        System.out.println("Digits:");
        int digits = sc.nextInt();

        UNEquipoPlayer player1 = new UNEquipoPlayer(answerSize, digits);
        UNEquipoPlayer player2 = new UNEquipoPlayer(answerSize, digits);

        while(player1.hasToAsk() && player2.hasToAsk()){
            
            try{
                
                String guess1 = player1.getGuess();
                
                System.out.println("The guess of p1 is "+guess1);
                
                System.out.println("Bulls on p2: "+player2.getBulls(guess1));
                int bulls1 = player2.getBulls(guess1);
                
                System.out.println("Cows on p2: "+player2.getCows(guess1));
                int cows1 = player2.getCows(guess1);
                
                player1.registerBullsAndCows(guess1, bulls1, cows1);
                
                
                String guess2 = player2.getGuess();
                
                System.out.println("The guess of p2 is "+guess2);
                
                System.out.println("Bulls on p1: "+player1.getBulls(guess2));
                int bulls2 = player1.getBulls(guess2);
                
                System.out.println("Cows on p1: "+player1.getCows(guess2));
                int cows2 = player1.getCows(guess2);
                
                player2.registerBullsAndCows(guess2, bulls2, cows2);
                
                
            }catch(Exception e){
                System.out.println("Fail. Try again");
            }
        }

        if(!player1.hasToAsk())
            System.out.println("Player 1 wins: "+player1.getGuess()+
                    ". Bulls: "+player2.getBulls(player1.getGuess())+
                    ". Guesses done: "+player1.getGuessesDone());
        else
            System.out.println("Player 2 wins: "+player2.getGuess()+
                    ": "+player1.getBulls(player2.getGuess())+
                    ". Guesses done: "+player2.getGuessesDone());
    }
    
}
