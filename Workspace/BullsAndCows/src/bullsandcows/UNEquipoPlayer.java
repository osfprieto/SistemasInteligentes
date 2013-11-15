/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows;

//Player for Bulls and cows

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;


public class UNEquipoPlayer <T>{
   
        static final int ANSWER_SIZE = 4;
 
        static LinkedList<String> Permutations(int size)
        {
            if (size > 0)
            {
                LinkedList ret = new LinkedList<String>();
                for (String s : Permutations(size - 1))
                    for (char n : "1234567890".toCharArray())
                        if (!s.contains(n+""))
                            ret.add(s + n);
                return ret;
            }
            else{
                LinkedList<String> ret = new LinkedList<String>();
                ret.add("");
                return ret;
                
            }
        }
 
        static LinkedList<String> Shuffle(LinkedList<String> source)
        {
            LinkedList<String> ret = new LinkedList<String>();
            Random random = new Random();
            
            LinkedList<String> list = (LinkedList<String>)source.clone();
            while (list.size() > 0)
            {
                int ix = random.nextInt(list.size());
                ret.add(list.get(ix));
                list.remove(ix);
            }
            return ret;
        }
 
        static int bulls;
        static int cows;
        
        static boolean ReadBullsCows()
        {
            Scanner sc = new Scanner(System.in);
            String[] input = sc.nextLine().trim().split(",");
            bulls = cows = 0;
            if (input.length < 2)
                return false;
            else
                try{
                    bulls = Integer.parseInt(input[0]);
                    cows = Integer.parseInt(input[1]);
                    return true;
                }catch(Exception e){
                    return false;
                }
        }
 
        public static void main(String[] args)
        {
            System.out.println("Bulls and Cows");
            System.out.println("==============");
            System.out.println();
            LinkedList<String> answers = Shuffle(Permutations(ANSWER_SIZE));
            
            while (answers.size() > 1)
            {
                String guess = answers.get(0);
                System.out.println("My guess is "+guess+". How many bulls, cows? ");
                if (!ReadBullsCows())
                    System.out.println("Sorry, I didn't understand that. Please try again.");
                else
                    for (int ans = answers.size() - 1; ans >= 0; ans--)
                    {
                        int tb = 0, tc = 0;
                        for (int ix = 0; ix < ANSWER_SIZE; ix++)
                            if (answers.get(ans).charAt(ix) == guess.charAt(ix))
                                tb++;
                            else if (answers.get(ans).contains(""+guess.charAt(ix)))
                                tc++;
                        if ((tb != bulls) || (tc != cows))
                            answers.remove(ans);
                    }
            }
            if (answers.size() == 1)
                System.out.println("Hooray! The answer is "+answers.get(0)+"!");
            else
                System.out.println("No possible answer fits the scores you gave.");
        }
}
    
